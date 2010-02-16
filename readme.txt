                      About Japid-Play, Version 0.1


                    Bing Ran<bing_ran@hotmail.com>
                    
                    
Version History:

2012/2/14:	v0.2
			1. added support for back-quote as script line prefix
			2. japid:mkdir now creates optional packages for controllers.
			3. added code in JapidPlugin to remove Orphaned Java source file in the japidviews
			directory.
2012/2/12: 	v0.12 
			1. added support for autoloading in DEV mode, with the latest head version
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

*Install the module to your Pplay runtime*

First of all, to get the latest japid module, issue the following command from a commandline. 

# play install japid

This download a zip file and extract the content to modules directory in your Play! installation, 
such as:

${play.path}/modules/japid-0.2

Go to there and find the source and some sample scripts in the tempgen directory.

*Include the module in your application*

There are two ways to include the modele in your application:

- For an existing application:
  add an entry in your application.conf:

module.japid=${play.path}/modules/japid-0.2

- To create a new application with Japid support:

# play new junkie --with japid

Verify the existence of the above japid reference in the application.conf file.

*Create the required directory structure for Japid*

Now create the required package structure for the japid views:

# play japid:mkdir

This command creates 

 - app/japidviews/_javatags, for placing your java based tag definitions
 - app/japidviews/_layouts, for placing layout templates
 - app/japidviews/_tags, for placing tag templates.

It also creates a package for each controllers in the app/controllers/ directory/subdirectory,
such as:

 - app/japidviews/Application

And you *usually* create a template named after the controller's action name with ".html" extension. 
This is optional however, since controller actions can explicitly call the renders directly using Java invocation syntax, 
the templates can be put anywhere under japidviews root package. But it's mandetory if you use implicit
template invocation, as explained later, in your controller.

*Create the first template*

# cd japidviews/Application
# vi hello.html

Here is the content of hello.html:
--------------------------------------
%{
  args  String who
}%

<html>
        Hello ${who}!
</html>
--------------------------------------

Or you can use the line oriented scrip prefix: the singel back-quote, which is the "tilde" key on a PC keyboard:
--------------------------------------
`args  String who

<html>
        Hello ${who}!
</html>
--------------------------------------

The "args" directive tells what arguments/objects the template actually renders. a Java String called "who" in this case.

Now transform/pre-compile the template to Java source:

If you develop in "cold" mode - not running your app in DEV mode while you develop:

# cd {back to your application root}
# play japid:gen

This command tranforms the template to a Java file called hello.java in the same package. 

*NOTE*: if you do your developing live with you application running in DEV mode, you don't
need to run japid:gen command to synchronize the Java artifacts for the templates. It'll be
handled automatically for you. japid:gen is for you to develop you app "off-line", or "cold".

*Invoke the renderer in controllers*

# cd app/controllers

and modify your Application.java to something like this:

--------------------------------------------------------
package controllers;

import play.mvc.*;
import japidviews.Application.hello;

import cn.bran.japid.template.RenderResult;
import cn.bran.play.JapidResult;
import controllers.japid.JapidController;

public class Application extends JapidController {
    public static void hello() {
    	String obj = "me";
        throw new JapidResult(new hello().render(obj));
        // or alternatively
        // renderJapid(obj);
    }


    public static void index() {
        render();
    }
}
-----------------------------------------------------------

The interesting line is:

        throw new JapidResult(new hello().render("me"));

where we invoke the renderer *hello* class directly and throw a JapidResult for the Play! framework to do its work. 

You can also invoke the renderer implicitly from the controller using a method from the JapidController super class:

instead of the above line, we can also do:

		renderJapid(param1, param2, etc);
		
For this to work we must make sure the hello.html is exactly in the app/japidviews/Application/ directory. This method
is slightly slower than direct invocation since it uses reflection to find the renderer, and we lose strong parameter 
type checking until runtime. 

Now point your brower to http://127.0.0.1:9000/Application/hello and you'll see:

-----------------
Hello me!
-----------------

In this sample action the dynamic content is actually a static "me" object, but you basically assemble your objects of any level
of complexity in the controller and pass them to the renderer in a similar way. Of course you will need to decalre those parameters
explicitly in your templates by way of using the args directive. 


*Test the auto-reloading works*

While you're running the app in DEV mode, modify the hello.html and add one more ! to the end of ${who}.Reloading your browser 
you'll see the change.

Now you're in business!

Read the introduction of Japid here: http://cloud.github.com/downloads/branaway/Japid/Japid.pdf

Note the pdf is for version 0.1 and needs update for new features such as DEV mode auto-reloading, japid:mkdir command, etc. 

The play1.1/modules/japid-0.2/tempgen/ contains a few sample scripts showing the syntax. Take a
look at for example the AllPost.html for how to use layout and tags.


The Japid can be embedded in any Java application as a general template engine. Read the pdf 
file for detail.


The source code is hosted here:  http://github.com/branaway/Japid.


- Build instructions for hackers

The build.xml contains targets for both the generic verion and the module for the Play! Framework.

  . To build standalone version: run "ant build.japid", which will create japid-{version}.jar in the 
lib directory, which will be good for generic templating purpose.

  . To build the Japid module for Play!: run "play build-module", which will create play-japid-{version}.jar
in the lib directory. Both jars are required for use in Play!.

The ant.jar and ant-laucher.jar are required if one is going to use the japid ant task to synchronize
the html templates and generated Java source files.

Issues, feature requests please log to the gitbub.

Hackers are welcome to participate in the project.

Enjoy

Bing


