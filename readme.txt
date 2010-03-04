                Japid-Play, a template engine at raw Java speed

                    Bing Ran<bing_ran@hotmail.com>
                    

                   
Version History:

2012/2/24:	v0.31
			1. added directive in script to add any http response headers. The usage example:
			
			-------------------------------------------------------------------------------------------------------
			`setHeader Cache-Control	max-age=600
			-------------------------------------------------------------------------------------------------------
			
			see headers.html for more examples. HTTP header settings can be inherited by children templates.
			
2012/2/22:	v0.3
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
			
2012/2/14:	v0.2
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
2012/2/12: 	v0.12 
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



 

***********************************************
- Quick start for using the Play-Japid module
***********************************************


Japid works with both the 1.1 and 1.0 nightly builds. Please read the quick start tutorial here:

http://wiki.github.com/branaway/Japid/quick-start-with-japid-module-for-play


Play and enjoy!

Bing


