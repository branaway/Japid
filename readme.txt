About Japid-Play, Version 0.1


Bing Ran<bing_ran@hotmail.com>, 2010.2


- Quick start for using the Play-Japid module

# cd my-project
# play install japid
# mkdir -p japidviews/_layouts
# mkdir -p japidviews/_tags

Now create a package for each of your controllers in japidviews, for example for a controller named Application,
you create a package names japidviews.Application. This is not required, but a common way of organizing views
with controller. Since controllers actions will call the renders directly, the templates can be put anywhere
under japidviews root package.

# mkdir -p japidviews/Application
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


This file defines a template that take one object to render, a Java String in this case.

# cd back to your application root
# play japid:gen  

this command tranforms the template to a Java file called hello.java in the same package

now

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
        throw new JapidResult(new hello().render("me"));
    }


    public static void index() {
        render();
    }

}
-----------------------------------------------------------


Now point your brower to http://127.0.0.1:9000/Application/hello and you'll see:

-------------------------------------
Hello me!
-------------------------------------

Now modify the hello.html and add one more ! to the end of the line. Before you reload your
browser, you'll need to manually refresh the generated Java file:

in the application root:

# play japid:gen

And reloading your browser you'll see the change.

Now you're in business!



Read the introduction of Japid here: http://cloud.github.com/downloads/branaway/Japid/Japid.pdf 

The play1.1/modules/japid-0.1/tempgen/ contains a few sample scripts showing the syntax. Take a 
look at for example the AllPost.html for how to use layout and tags. 


The Japid can be embedded in any Java application as a general template engine. Read the pdf file
for detail.


The source code is hosted here:  http://github.com/branaway/Japid.


- Build instruction for hackers

The build.xml contains targets for both the generic verion and the module for the Play! Framework.

  . To build standalone version: run "ant build.japid", this will create japid-{version}.jar in the lib
directory, which will be good for generic templating purpose.

  . To build the Japid module for Play!: run "play build-module", this will create play-japid-{version}.jar
in the lib directory. Both jars are required for use in Play!.

The ant.jar and ant-laucher.jar are required if one is going to use the japid ant task to synchronize
the html templates and generated Java source files.

Issues, feature requests please log to the gitbub. 

Hackers are welcome to participate in the project.

Enjoy

Bing




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
- expression lanaguage is plain Java. 
- templates are precompiled to Java, with commandline tool integrated with play command

3. The performance

Some rough numbers:

Hand-written Java code: 0.85X
Velocity: 2X
Freemarker: 3X
Play! rendering layer in Groovy: 4-12X

When integrated in Play, Japid can help Japid deliver 2-3X more total throughput under load. 

4. The limitations

Not integrated in the Play code change detection yet. Play command is provided to manually 
refresh the Java artifacts. 

Action URL reverse lookup is weak. The implementation can handle simple cases only.

No other EL can be used in expressions. Everything is in Java.

5. Thanks go to the original Play authors for a well-thoughtout web framework for best 
development time productivity and run-time performance. Some of the code in this template
engine is shamefully adapted from the Play code. The Jamon template engine proves 
transforming templates to Java source code is possible and has inspired me to start this
project.

 
