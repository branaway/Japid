About Japid-Play, Version 0.1


Bing Ran<bing_ran@hotmail.com>, 2010.2

0. License: Apache License, Version 2.0, http://www.apache.org/licenses/LICENSE-2.0.

1. Purpose of this work

The work aims to create a fast template rendering engine for use in general templating
and in web frameworks such as Play! Framework. 

The performance goal of this rendering engine is to deliver near raw Java speed in rendering
dynamic content. 

2. Features

- templates are strongly typed. Each template must declare the arguments it takes. 
- transformed to Java for best performance
- a clear object model for each template syntax
-- Inheritance: 
--- parent templates (aka layout), child templates. Layouts are transformed to abstract
template classes. Directly callable templates are concrete classes that optionally inherit from 
a layoiut class.
--- tags are regular template classes, that can be invoked from other templates or invoked directly
from java code.
--- expression lanaguage is plain Java. 
---  

2. The performace

Some rough numbers:

Hand-written Java code: 0.85X
Velocity: 2X
Freemarker: 3X
Play! rendering layer in Groovy: 4-12X


3. The limitations

4. The Design

5. The implementation

6. The Code


