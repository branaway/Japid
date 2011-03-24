package cn.bran.japid.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


public class DirUtilTests {
	@Test
	public void testOrphan() {
		File src = new File("tests/testdir");
		Set<File> orphan = DirUtil.findOrphanJava(src, src);
		assertEquals(1, orphan.size());
		File next = orphan.iterator().next();
		System.out.println(next);
		assertTrue(next.getName().endsWith("C.java"));
	}

	@Test
	public void testScanning() {
		String[] exts = new String[] { "java", "html" };
		File src = new File("tests/testdir");
		String[] fs = DirUtil.getAllFileNames(src, exts);
		for (String s : fs) {
			System.out.println(s);
		}
	}

	@Test
	public void testAllFiles() {
		String[] exts = new String[] { ".java", ".html" };
		File src = new File("tests/testdir");
		Set<File> fs = new HashSet<File>();
		fs = DirUtil.getAllFiles(src, exts, fs);
		assertEquals(9, fs.size());
		for (File f : fs) {
			String name = f.getPath();
			assertTrue(name.endsWith(".java") || name.endsWith(".html"));
			System.out.println(name);
		}
	}
	
	@Test
	public void testChangedHtml() throws IOException {
		File src = new File("tests/testdir");
		File newer = new File ("tests/testdir/A.html");
		DirUtil.touch(newer);
		List<File> fs = DirUtil.findChangedSrcFiles(src);
		assertEquals(3, fs.size());
//		for (File f: fs) {
//			System.out.println(f.getPath());
//		}
		DirUtil.touch(new File("tests/testdir/A.java"));
		fs = DirUtil.findChangedSrcFiles(src);
		assertEquals(2, fs.size());
		
	}
	
	@Test
	public void testJavaToSrc() {
		String javasrc = "my/Action_xml.java";
		String res = DirUtil.mapJavaToSrc(javasrc);
		assertEquals("my/Action.xml", res);
		
		javasrc = "my/Action_json.java";
		res = DirUtil.mapJavaToSrc(javasrc);
		assertEquals("my/Action.json", res);
	}
	
}
