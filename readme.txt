                Japid-Play, a template engine at raw Java speed

                    Bing Ran<bing_ran@hotmail.com>
                    

* Links:

-- The Japid/Play User Manual:   
    https://github.com/branaway/Japid/wiki/Japid-User-Guide
-- Use Japid as a Generic Template Engine:
    https://github.com/branaway/Japid/wiki/Japid-Generic-Template-Engine-Guide
-- Porting to Japid:        
    https://github.com/branaway/Japid/wiki/Porting-ZenContact-to-Japid
-- The Japid Plugin for Eclipse:
    https://github.com/branaway/playclipse
    
* Note:

Whenever you upgrade the Japid module, 

1. do a "play japid:regen" to regenerate all the derived Java files.
2. Or if you're using the Japid Plugin for Eclipse, re-enable the "Play nature", or do a "Project -> clean"


* Version History:

2011/4/1: V0.8.1.2: 
    1. bug fix: now the template always return RenderResultPartial and evaluate the action runners to make sure the super class's action runners get evaluated    

2011/3/20: V0.8.1: 
    1. The Eclipse plug: upgraded to 0.8.8.1   
    2. The Eclipse plug: fixed syntax coloring for lines containing multiple back quote.
    3. The Eclipse plug: added synchronization of the templates with the controller type, method and package renaming refactoring.  
    4. The Eclipse plug: added ctrl-alt-B to invoke current action in an external browser.   

2011/3/10: V0.8.0: 
    1. bug fix: calling tag without args but a callback block.  
    2. naming change: the jar for the play connector was renamed from play-japid-{version}.jar to japidplay-{version}.jar.
    3. the standalone japid is working now. The additional dependency is in the lib.plain directory.   
    4. updated eclipse plugin to support the latest syntax change.   

2011/2/25: V0.7.1: 
    1. added a new way to pass arguments from views to layout in the `extends command. See perf.html for an example.  
        
        `args String a
        `extends myLayout(a, "home")
        
        The myLayout template must match the parameter list:
        
        `args String a, String b
     
    2. Enhanced the "if" and "else if" statements to take any expression as the condition without enclosing it in a pair of parenthesis "()"  
        `if expr {
            xxx
        `} else if expr {
            yyy
        `} else {
            zzz
        `}

        The expression value can be any object. The compiler wrap it in a static method asBoolean(), which infers a boolean value from it. See ifs.html for an example. 
        Using parenthesis it becomes regular Java if statement. 
    3. I went one step further to create what I call "open if-else"
            `if expr
                xxx
            `else if expr
                yyy
            `else
                zzz
            `
    4. new feature: enhanced for loop (open-for loop), a replacement of the "each" tag.
        `for String a: myCollection
            ${a}, $_index, $_parity, etc.
        `
    5. bug fix: could not pass null the renderJapid(); Added a new template invoke utility. 
    6. enhanced:  added flash() in the JapidPlayAdapter so you can do:
        ${flash(mykey)} to retrieve an object associated with a key in the flash object 
    7. New feature: added Elvis operator in ${} expressions to set default values to display in case of nulls and empty string values.  
        ${name ?: "empty"}
    8. bug fix: reverse lookup works with complex object
        `MyObject o = new ...;
        <a href="@{action(o)}">link</a>
    9. internally, move implicit objects and tag instances to fields. This allows the use of them in def methods.  
    10. supported renderJapidWith("@anotherTemplate", bar) to render to another template in the same target directory.
    11. added a sample: JapidContact, ported from the Zencontact sample from the Play distribution.
2011/2/16: V0.7.0: 
    1. Improved the integration of Play! built-in @CacheFor@ annotation with the @`invoke@/@`a@ command. Now it's recommended to use the annotation to specify the timeout value for caching, instead of using an optional argument with the @`invoke@ command.   
        Please see the more.Portlets.index() action in the JapidSample sample app for an example.
    2. Major improvement to the home.textile in the documentation directory. It's usable as a quick Japid guide.
       
2011/1/26: V0.6.2: the old tag invocation syntax is deprecated in favor of directives.
    1. internally, started using JavaParser to accurately parse parameters and arguments to tags and actions. 
       
    2. new syntax: tag calls without a body can now use a more explicit syntax(say goodbye to #$%%{):
        -- One line tag invocation:
        
        was: hello: #{myTag param1, param2/}!
        is: hello: `tag myTag param1, param2`! 
        or: hello: `tag myTag(param1, param2)`!
        or even shorter: `t myTag(param1, param2), where `t == `tag and t must follow ` immediately.

        Note: a second ` defines the end of the directive. The second ` is optional if there is nothing after the directive on the same line. 
        
        i.e.: only one back quote is required in the following case:
        
        hello: `tag myTag(param1, param2)
        
        -- Tag with callback block:
        
        was:
        
        #{mytag 1, "2" | String backParam}
            The param from the tag: ${backParam}
        #{/} 
        
        is:
        
        `tag mytag 1, "2" | String backParam
            The param from the tag: ${backParam}
        `
        
        Note:  a stand-alone ` is used to close the tag invocation. 
        
    3. new syntax: the doBody tag that was used to callback the tag caller is deprecated in favor of a simpler syntax:
        `doBody arg1, arg2...
        The sample code is in the Display.html template in the JapidSample.   
    4. new syntax: the doLayout tag in a layout is deprecated in favor of a simpler syntax:
        `doLayout
        The sample code is in the TagLayout.html in the JapidSample directory.   
    5. new syntax: `def, to define a method for reusing layout logic. e.g.:
        
        `def foo(String a)
          hi, #a!
        `
        ${foo("world")}
        
        Don't forget the closing `.
        
        See def.html in the JapidSample for examples.   
    6. new syntax: `set and `get, to pass text from views to layouts, functionally equivalent to #{set ...} and #{get ...} 
        in a view:
        
        `set title: "home page"
        
        or use the set block
        
        `set title
             home page
        `
        Again, a single ` is used to close the set block.        
        
        In a layout:
        
        <title>`get title`</title>
        
        Note: a second ` defines the end of the directive. The second ` is optional if there is nothing after the directive on the same line. 
        
        See Set.html in the JapidSample for examples.
        
    7. new syntax: `each, ported from the #{Each...} tag, to loop through collections with auxiliary looping information:
        
        `each users | User user
            user name: $user.name, is the first: $_isFirst, is the last: $_isLast, etc...
        `
        
        See EachCall.html in the JapidSample for examples.

    8. behavior change: all the `get tags in the layouts will default to empty string and overriding them sub-classes are optional.
    9. `invoke has now a shorter form: `a, for action invocation. 
    10. internally added flag useWithPlay in the compiler and template meta data to control Play-dependency in the generated code.  
    11. Enhancement: _size variable is available inside a `each loop. It's -1 if the size of the iterable cannot be determined.  
    12. new feature: added `verbatim command to wrap a block a raw text in the template. Example:
        `verbatim
            ok, anything inside this block is not parsed: `command, ${expression}, etc
        `
        Note: it must be closed by a standalone back quote.
2011/1/20: V0.6.1
    1. internal change: all tag invocations are now local in the layout method. The idea was to reduce using fields and favor local variables;  
    2. fix: all implicit variables are labeled as final for use in inner classes.
    3. the compiler now recognizes .xml, .json, .txt as the template extensions and set the content type accordingly. The renderJapid() method will select the proper template based on content negotiation as per: http://www.playframework.org/documentation/1.1/routes#content-negotiation
    4. new feature: added a new directive to label a generated template class as abstract for subclassing only: `abstract
    5. new feature: tag qualifiers, layout names and import clauses can start with a "." which means the tag path starts from the current package. e.g.
        #{.my.tag /} will be translated to #{the.current.package.my.tag /}.
        
        `extends .my.layout  
            will be translated to 
        `extends the.current.package.my.layout
        
        and 
        
        `import .my.tags.*
            will be translated to 
        `import the.current.package.my.tags.*
    6. included the eclipse plugin 0.8.4
        
2011/1/8: V0.6.0
    1. bundled a Japid development plugin for Eclipse in the eclipse-plugin directory. Read the readme.txt in the directory for instructions.  

2011/1/5: V0.5.7
    1. added a simpler way to invoke an action: `invoke. e.g.
        `invoke MyController.action(xxx)
        It used to be a tag.  
    2. static content is now printed in-line.  
2010/12/12: V0.5.6
    1. Fixed a bug that caused `{ to detached from the previous if/while statement thus changed the semantics of the resultant Java code. 
    2. Improved the performance of CacheablePlayActinoRunner.checkActionCacheFor(). 
    3. New feature: one can specify the full path of the tag in tag invocations. e.g.:
        #{japidviews/templates/aTag strings /}
        #{japidviews.templates.aTag strings /}
        Search callPicka.html in the Japid distribution for examples.
2010/10/11: V0.5.5
    1. Fixed a bug that caused NPE in generated template classes involving the Validation.current().errors().
    2. removed the renderJapidWith special route, which is not compatible with the current Play 1.1.x brunch.              
2010/10/11: V0.5.4
    1. Fixed a bug that prevented using a controller name in a sub-package in the invoke tag, even if the controller was imported via the `import directive.              
2010/10/11: V0.5.3
	1. tested to work with latest 1.1 master branch on github.
	2. added special URLs to invoke the japid commands:
		e.g. 
			http://localhost:9000/_japidgen to invoke the command to transform updated Japid templates;
			This is equivalent to "play japid:gen" command.
			
		 	http://localhost:9000/_japidregen to invoke the command to transform all Japid templates;
			This is equivalent to "play japid:regen" command.
			
2010/8/28:	V0.5.2
	1. made Play's CacheFor annotation on outer action works with JapidResult, including #{invoke } tag in the templates. The content extraction in JapidResult is postponed until the apply() stage. The invoke tag can invoke actions with CacheFor as well and the cache TTL is respected. 
	The CacheFor will replace the old verbose way of setting up cache control in controller and TTL spec in the invoke tag.
	2. let all template relates class to implement Serializable so the templates and render results can be cached to memcached  
2010/8/8:	V0.5.1
	1. imported play.mvc.Http.* in generated java files from templates, which means one can reference Request, Response etc. 
	2. added some implicit objects such as "request", "flash", "session". See the ImplicitObjects.html for examples.  
	3. added "_play" implicit object, so named to avoid conflict with play package name.
	4. added "suppressNull", a directive to allow safe navigation in expression, default off 
		e.g. ` suppressNull on
	5. added a property in application.conf that directs the plugin to dump a request in the system console for debugging. The property is named as japid.dump.request, which can take "yes|no|regex". The default is "no". See the application.conf and the Application.dumpPost() in the japid sample application for an example.  
2010/8/8:	V0.5
	1. moved all samples to the JapidSample sub directory, which was a sample Play application to demo Japid features.
	2. added renderJapidWith() in the JapidController which can be used to render a designated template with parameters.
	3. added an interceptor in the JapidPlugin that intercepts a special url and renders a Japid template without going thru a controller.
	The url format: {anything}/renderJapidWith/{template path from the japidview directory (not included)}
	e.g.
	http://localhost:9000/renderJapidWith/templates/callPicka
	will render the template "templates/callPicka.html" in the japidview package in the app dir.
	4. would create app/notifiers if the directory did not exist. 
	 		5. added support of using primitives in JapidController.renderText(...); 
2010/7/20:	v0.4.2
	1. Now e-mails can be sent with JapidMailer. The use of JapidMailer is very similar to the Play's Emailer class, except that the default template path is in the japidview._notifiers sub packages. The email controllers (a.k.a. notifiers) are still in the app/notifiers package. See the Application.email() for an example.
2010/7/7:	v0.4.1
	1. Manual transforming no more! Enhanced the JapidController.renderJapid() so it can find compatible template args list. It used to do exact match only. "play japid:gen" is not mandatory any more in dev mode.
	2. included the japid view directory scanning in the gen() method.
	3. added authenticityToken.html in the _tags directory of app/japidviews. Now one can add #{authenticityToken.html /} in a form to embed a hidden field to prevent CSRF attack. See: http://www.playframework.org/documentation/1.0.2/releasenotes-1.0.2 . 
	4. override renderJson(Object o) in the JapidController to use JapidResult so it would play well with Japid cache mechanism in templates - aka the invoke tag. 
2010/6/3:	v0.4
	1. fixed the command.py to work with the latest main 1.1 branch
2010/5/3:	v0.35
	1. now the JapidPlugin pre-compiles newer html templates at onLoad() event. Previously one must use "play japid:gen"
	to compile the templates in PROD mode or Japid pre-processing won't happen.
	2. added log directive to print log info in console for use in ` line. see log.html sample in the tempgen tree. 
	3. modified the commands.py to make it compatible with the Play 1.1 main branch.
2010/3/28:	v0.34
	1. the japid:mkdir command will create a file named JapidWebUtil.java in the japidviews._javatags package.
	All the public static methods are statically imported to generated Java source files. This file is 
	supposed to be added with more user defined static methods for use in Japid templates. 
	2. now the japid:regen command includes the japid:mkdir command.
	After upgrading to the the latest Japid, please run play japid:regen to refresh the templates structure.
	3. added #{def foo}xxx#{/} special tag that defines a method to build a string from the tag body. The method can
	be called from the current class or any of its parent classes by using get("foo"), as in ${get("foo")} or
	for example use in a tag invocation: #{myTag get("foo")/}
	4. fixed a bug for creating invoke code in JapidAbstractCompiler that used to generated invalid code when action
	take parameters with " mark. It's now escaped.
	5. [feature] added deleteCache in CacheableRunner so controller action can discretely choose to delete a Japid render 
	result cache if determined invalid.
	6. [feature] added getFreshActionResult to JapidController so one can make a call to another action and take the result
	for whatever purpose.
	7. [enhancement] the stopwatch on directive now generated a duration log to the console for each invocation
	of a template. This is useful for debugging performance issues with templates.  
	--------------------------
	` stopwatch on
	--------------------------
	8. [feature] added directive "log" to log information to console with template name, line number and optional an argument. 
	--------------------------
	` log
	` log "msg"
	` log var1 + var2
	--------------------------
2010/3/10:	v0.33
	1. removed the dependency on Ant DirectoryScanner for change detection. Now the module archive is 1.5M 
	less in size. Hopefully it's faster too.  

2010/3/10:	v0.32
	1. added RenderResultCache to fine control cache early reloading and cache read thru.
	The new CacheableRunner makes it easy to do whole page cache in actions. 
	
	Sample: this code within an action will cache the result for 5 seconds under the name of "My Key" 
	
	-------------------------------------------------------------------------------------------------------
	CacheableRunner cacheableRunner = new CacheableRunner("5s", "My Key") {
		@Override
		protected RenderResult render() {
			// preparing data
			// ...
			return new MyTemplate().render(data);;
		}
	};

	render(cacheableRunner);// the render(...) is defined in the JapidController 
	-------------------------------------------------------------------------------------------------------
	In the above code the index class is a Japid generated template.
	
	2. Added ignoreCache() and ignoreCacheNowAndNext()methods in JapidController.
	The above two methods set a flag for cache to ignore get requests from the current session so 
	clients will need to read from DB.
	3. add JapidController.dontRedirect() which can be used before calling another action in an action.
	This is like server-side forwarding. 
	
2010/2/24:	v0.31
	1. added directive in script to add any http response headers. The usage example:
	
	-------------------------------------------------------------------------------------------------------
	`setHeader Cache-Control	max-age=600
	-------------------------------------------------------------------------------------------------------
	
	see headers.html for more examples. HTTP header settings can be inherited by children templates.
	
2010/2/22:	v0.3
	1. added cache support in JapidController, which has been moved to the Play-Jpiad-{version}.jar. The 
	helper method in the JapidController is:
		
		runWithCache(ActionController, String ttl, Object... keys)
	
	To add cache control to a controller action, make sure your controller extends JapidController, and
	---------------8<-----------------------------------------------------------------------------------
		public static void panel2(final String who) {
			runWithCache(new ActionRunner(){
		@Override
		public RenderResult run() {
			return new panel2().render(who);
		}
			}, "6s", who);
		}	
	--------------->8------------------------------------------------------------------------------------
	In the above example, the result of the action invocation will be cached for 6 second under of the key
	of the who value( not the "who" literal). 
	
	2. added special tag called invoke as in #{invoke MyApplication.action(param)/} that can be used in
	templates to dynamically include render results from other action invocations. The invoke tag can take
	optional cache control parameter such as time-to-live and key. For example:
	
	-----------------------------------------------------------------------------------------------------
	`args models.japidsample.Post post 
	`import controllers.japid.SampleController

	<p>This is the composite content header</p>
	
	<div>#{invoke SampleController.authorPanel(post.getAuthor())/}</div>

	<div>this one has full cache control</div>
	<div>#{invoke SampleController.authorPanel(post.getAuthor()), "10s", post.getAuthor()/}</div>

	<div>this one has cache control using the default signature. </div>
	<div>#{invoke SampleController.authorPanel(post.getAuthor()), "10s"/}</div>
	------------------------------------------------------------------------------------------------------
	
2010/2/14:	v0.2
	1. added support for back-quote as line oriented script block prefix, for example:
	
	------------------------------------------------------------------------------------------------------
	`extends mylayout.html
	`args boolean mycondition
	
	`if (mycondition) {
		<p>true</p>
	`} else {
		<p>false</p>
	`}
	------------------------------------------------------------------------------------------------------
	
	The ` sign indicates that the rest of the line is treated as Java code block. It does not need to be the 
	first char of a line.  
	
	2. "play japid:mkdir" now creates optional packages for controllers.
	3. added code in JapidPlugin to remove Orphaned Java source file in the japidviews directory.
2010/2/12: 	v0.12 
	1. added support for auto-loading in DEV mode, with the latest head version
	in 1.0 and 1.1 branches.
	2. added play command "play japid:mkdir" to create the required package structure
	in the app/japidviews directory.
	3. removed some cache optimization in RouterAdapter to make it compatible with Play's 
	code base.
2010/2/10: 	v0.1 
	the initial release


0. License: Apache License, Version 2.0, http://www.apache.org/licenses/LICENSE-2.0.

1. Purpose of this work

The work aims to create a fast template rendering engine for use in general templating
and in web frameworks such as Play! Framework. 

The performance goal of this rendering engine is to deliver near raw Java speed in rendering
dynamic content. 

2. Features

- templates are strongly typed. A template must declare the arguments it takes. 
- transformed to Java for best possible performance.
- a clear Java object model for templates.
- expression language is plain Java. 
- templates are precompiled to Java, with commandline tool integrated with play command


3. The performance

Some rough numbers:

Hand-written Java code: 0.85X
Velocity: 2X
Freemarker: 3X
Play! rendering layer in Groovy: 4-12X

When integrated in Play, Japid can help Japid deliver 2-3X more total throughput under load. 

4. The limitations

- It supports the to-be-released Play 1.1 and 1.02 only.
- Development in "cold" mode (meaning developing without the application running) requires manually
synchronizing the html templates and the Java artifacts, using play commands: japid:gen, japid:regen,
japid:mkdir and japid:clean.

- Action URL reverse lookup is weak. The implementation can handle simple cases only.

- No other EL than Java can be used in expressions.

5. Thanks go to the original Play authors for a well thought-out web framework for best 
development time productivity and run-time performance. Some of the code in this template
engine is shamefully adapted from the Play code. The Jamon template engine proves 
transforming templates to Java source code is possible and has inspired me to start this
project.

6. Quick build

To build this module from source:

# play build-module

 

***********************************************
- Quick start for using the Play-Japid module
***********************************************


Japid works with both the 1.1 and 1.0 nightly builds. Please read the quick start tutorial here:

http://github.com/branaway/Japid/wiki/Quick-start-with-Japid-module-for-Play

Play and enjoy!

Bing


