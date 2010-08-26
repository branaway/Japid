                Japid-Play, a template engine at raw Java speed

                    Bing Ran<bing_ran@hotmail.com>
                    

                   
Version History:

2010/8/28:	V0.5.2
			1. made Play's CacheFor annotation on outer action works with JapidResult, including #{invoke } tag in the templates. The content extraction in JapidResult is postponed until the apply() stage. The invoke tag can invoke actions with CacheFor as well and the cache TTL is respected. 
			The CacheFor will replace the old verbose way of setting up cache control in controller and TTL spec in the invoke tag.  
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

http://wiki.github.com/branaway/Japid/quick-start-with-japid-module-for-play


Play and enjoy!

Bing


