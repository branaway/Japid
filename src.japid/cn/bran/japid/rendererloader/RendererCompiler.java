package cn.bran.japid.rendererloader;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.IProblemFactory;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileReader;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFormatException;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;


/**
 * Java compiler (uses eclipse JDT)
 * 
 * based on 
 */
public class RendererCompiler {

    Map<String, Boolean> packagesCache = new HashMap<String, Boolean>();
    Map<String, RendererClass> classes ;//= new HashMap<String, RendererClass>();
    
    TemplateClassLoader crlr;
    
    Map<String, String> settings;
    {
    	/**
    	 * Try to guess the magic configuration options
    	 */
    	this.settings = new HashMap<String, String>();
        this.settings.put(CompilerOptions.OPTION_ReportMissingSerialVersion, CompilerOptions.IGNORE);
        this.settings.put(CompilerOptions.OPTION_LineNumberAttribute, CompilerOptions.GENERATE);
        this.settings.put(CompilerOptions.OPTION_SourceFileAttribute, CompilerOptions.GENERATE);
        this.settings.put(CompilerOptions.OPTION_ReportDeprecation, CompilerOptions.IGNORE);
        this.settings.put(CompilerOptions.OPTION_ReportUnusedImport, CompilerOptions.IGNORE);
        this.settings.put(CompilerOptions.OPTION_Encoding, "UTF-8");
        this.settings.put(CompilerOptions.OPTION_LocalVariableAttribute, CompilerOptions.GENERATE);
        String javaVersion = CompilerOptions.VERSION_1_6;
        this.settings.put(CompilerOptions.OPTION_Source, javaVersion);
        this.settings.put(CompilerOptions.OPTION_TargetPlatform, javaVersion);
        this.settings.put(CompilerOptions.OPTION_PreserveUnusedLocal, CompilerOptions.PRESERVE);
        this.settings.put(CompilerOptions.OPTION_Compliance, javaVersion);
    }

    /**
     * supposed to have a single instance for the entire application
     * 
     * @param classes
     * @param cl
     */
    public RendererCompiler(Map<String, RendererClass> classes, TemplateClassLoader cl ) {
        this.classes = classes;
        this.crlr = cl;
    }

    /**
     * Something to compile
     */
    final class CompilationUnit implements ICompilationUnit {

        final private String clazzName;
        final private String fileName;
        final private char[] typeName;
        final private char[][] packageName;

        CompilationUnit(String pClazzName) {
            clazzName = pClazzName;
            if (pClazzName.contains("$")) {
                pClazzName = pClazzName.substring(0, pClazzName.indexOf("$"));
            }
            fileName = pClazzName.replace('.', '/') + ".java";
            int dot = pClazzName.lastIndexOf('.');
            if (dot > 0) {
                typeName = pClazzName.substring(dot + 1).toCharArray();
            } else {
                typeName = pClazzName.toCharArray();
            }
            StringTokenizer izer = new StringTokenizer(pClazzName, ".");
            packageName = new char[izer.countTokens() - 1][];
            for (int i = 0; i < packageName.length; i++) {
                packageName[i] = izer.nextToken().toCharArray();
            }
        }

        @Override
		public char[] getFileName() {
            return fileName.toCharArray();
        }

        @Override
		public char[] getContents() {
            return classes.get(clazzName).getSourceCode().toCharArray();
        }

        @Override
		public char[] getMainTypeName() {
            return typeName;
        }

        @Override
		public char[][] getPackageName() {
            return packageName;
        }
    }

    /**
     * Please compile this className and set the bytecode to the class holder 
     */
    @SuppressWarnings("deprecation")
    public void compile(String[] classNames) {

        ICompilationUnit[] compilationUnits = new CompilationUnit[classNames.length];
        for (int i = 0; i < classNames.length; i++) {
            compilationUnits[i] = new CompilationUnit(classNames[i]);
        }
        IErrorHandlingPolicy policy = DefaultErrorHandlingPolicies.exitOnFirstError();
        IProblemFactory problemFactory = new DefaultProblemFactory(Locale.ENGLISH);

        /**
         * To find types ...
         */
        INameEnvironment nameEnvironment = new INameEnvironment() {

            @Override
			public NameEnvironmentAnswer findType(final char[][] compoundTypeName) {
                final StringBuffer result = new StringBuffer();
                for (int i = 0; i < compoundTypeName.length; i++) {
                    if (i != 0) {
                        result.append('.');
                    }
                    result.append(compoundTypeName[i]);
                }
                return findType(result.toString());
            }

            @Override
			public NameEnvironmentAnswer findType(final char[] typeName, final char[][] packageName) {
                final StringBuffer result = new StringBuffer();
                for (int i = 0; i < packageName.length; i++) {
                    result.append(packageName[i]);
                    result.append('.');
                }
                result.append(typeName);
                return findType(result.toString());
            }

            private NameEnvironmentAnswer findType(final String name) {
                try {
                    if (!name.startsWith("japidviews.")) {
                    	// let super class loader to load the bytecode
                        byte[] bytes = crlr.getClassDefinition(name);
                        if (bytes != null) {
                            ClassFileReader classFileReader = new ClassFileReader(bytes, name.toCharArray(), true);
                            return new NameEnvironmentAnswer(classFileReader, null);
                        }
                        return null;
                    }

                    char[] fileName = name.toCharArray();
                    RendererClass applicationClass = classes.get(name);

                    // ApplicationClass exists
                    if (applicationClass != null) {

                        byte[] bytecode = applicationClass.getBytecode();
						if (bytecode != null) {
                            ClassFileReader classFileReader = new ClassFileReader(bytecode, fileName, true);
                            return new NameEnvironmentAnswer(classFileReader, null);
                        }
                        // Cascade compilation
                        ICompilationUnit compilationUnit = new CompilationUnit(name);
                        return new NameEnvironmentAnswer(compilationUnit, null);
                    }

                    // So it's a standard class
                    byte[] bytes = crlr.getClassDefinition(name);
                    if (bytes != null) {
                        ClassFileReader classFileReader = new ClassFileReader(bytes, fileName, true);
                        return new NameEnvironmentAnswer(classFileReader, null);
                    }

                    // So it does not exist
                    return null;
                } catch (ClassFormatException e) {
                    // Something very very bad
                    throw new RuntimeException(e);
                }
            }

            @Override
			public boolean isPackage(char[][] parentPackageName, char[] packageName) {
                // Rebuild something usable
                StringBuilder sb = new StringBuilder();
                if (parentPackageName != null) {
                    for (char[] p : parentPackageName) {
                        sb.append(new String(p));
                        sb.append(".");
                    }
                }
                sb.append(new String(packageName));
                String name = sb.toString();
                if (packagesCache.containsKey(name)) {
                    return packagesCache.get(name).booleanValue();
                }
//                 Check if there is a .java or .class for this resource
                if (crlr.getClassDefinition(name) != null) {
                    packagesCache.put(name, false);
                    return false;
                }
                if (classes.get(name) != null) {
                    packagesCache.put(name, false);
                    return false;
                }
                packagesCache.put(name, true);
                return true;
            }

            @Override
			public void cleanup() {
            }
        };

        /**
         * Compilation result
         */
        ICompilerRequestor compilerRequestor = new ICompilerRequestor() {

            @Override
			public void acceptResult(CompilationResult result) {
                // If error
                if (result.hasErrors()) {
                    for (IProblem problem: result.getErrors()) {
                        String className = new String(problem.getOriginatingFileName()).replace("/", ".");
                        className = className.substring(0, className.length() - 5);
                        String message = problem.getMessage();
                        if (problem.getID() == IProblem.CannotImportPackage) {
                            // Non sense !
                            message = problem.getArguments()[0] + " cannot be resolved";
                        }
                        throw new RuntimeException(className + ": " + message);
//                        throw new RuntimeException(className + message +  problem);
                    }
                }
                // Something has been compiled
                ClassFile[] clazzFiles = result.getClassFiles();
                for (int i = 0; i < clazzFiles.length; i++) {
                    final ClassFile clazzFile = clazzFiles[i];
                    final char[][] compoundName = clazzFile.getCompoundName();
                    final StringBuffer clazzName = new StringBuffer();
                    for (int j = 0; j < compoundName.length; j++) {
                        if (j != 0) {
                            clazzName.append('.');
                        }
                        clazzName.append(compoundName[j]);
                    }
                    byte[] bytes = clazzFile.getBytes();
                    System.out.println("[RenderCompiler]Compiled " + clazzName);
                    // XXX address anonymous inner class issue!! ....$1...
                    String cname = clazzName.toString();
					RendererClass rc = classes.get(cname);
                    if (rc == null) {
                    	if (cname.contains("$")) {
                    		// inner class
                    		rc = new RendererClass();
                    		rc.className = cname;
                    		classes.put(cname, rc);
                    	}
                    	else {
                    		throw new RuntimeException("name not in the classes container: " + cname);
                    	}
                    }
					rc.setBytecode(bytes);
                }
            }
        };

        /**
         * The JDT compiler
         */
        Compiler jdtCompiler = new Compiler(nameEnvironment, policy, settings, compilerRequestor, problemFactory) {

            @Override
            protected void handleInternalException(Throwable e, CompilationUnitDeclaration ud, CompilationResult result) {
            }
        };

        // Go !
        jdtCompiler.compile(compilationUnits);

    }
}
