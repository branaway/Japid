Eclipse Plugin for Play/Japid
-----------------------

Source code

- https://github.com/branaway/playclipse, branched from https://github.com/erwan/playclipse

Features

- The plugin as of now offers the same level of features that are in the original plugin for the groovy-based templates to the Japid template engine.
  - Commands to navigate between actions and views.
  - An enhanced Play HTML editor that recognizes some Japid syntax. 
  - Ctrl-click navigation in html views to actions, layout templates and tags. 

- It also integrate the Japid template trasforming process to the standard Eclipse project incremental and full building processes, thus eliminate any manual process in applying Japid templates while deliverying the best possible performance.   

- It has also fixed a few bugs coming with the original plugin and enhanced the popeup menu in the views and editors. 

Intallation:

- Just put the jar file in the dropins directory of the Eclipse installation and restart the IDE. 

Note:

- The plugin bundles the jar files of the Japid module in a single jar. Try to use the plugin from each Japid distribution to keep the Japid engine in sync with the plugin. 
   

Usage:

- Enable the Play nature to your Play/Japid application in the Eclipse, or the Japid transformation will not be integrated with the project building process, neither the popup menu will display the proper menu items. 
- This plugin has been tested with Eclipse v3.6.1.  

History:

2010/12/21:
        1. work started. 
2010/12/27:
        1. The "New controller" command can generate Japid controllers. 
        2. the "Go to view" command can smartly go to either the Groovy based views or Japid views, depending on the context.
2011/1/1:
	1. added a few commands to the package explorer context menu.
	2. added context menu to go to action from a view editor.Works with Play html editor and WST html editor.
	3. added Japid template transformation to the build process to automate the template conversion from the html files to Java code.
	4. added comand in the editor context manu to switch between japid html and japid java code.
	5. enhanced the tag, action and layout navigation via ctrl-click in views.

2011/1/5, version 0.8.2
	1. support ctrl-click on #{invoke package.controller.action /} in views.

