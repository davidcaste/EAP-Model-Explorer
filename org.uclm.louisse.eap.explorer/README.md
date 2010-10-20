EAP Model Explorer
==================


About
-----

EAP Model Explorer lets you navigate through an Enterprise Architect model and
explore the properties and values of its elements. It is a small Eclipse RCP
application written in Java programming language, which uses a Java API
(`eaapi.jar`) from
[Sparx's Enterprise Architect (EA)](http://www.sparxsystems.com/)
to access EAP models. 

Due to its dependency on `eaapi.jar`, EAP Model Explorer only runs on Microsoft
Windows platform, EA must be installed and have a valid license. If you want to
try EAP Model Explorer (and EA), Sparx offers 30-days trial licenses for free.


EA Java API installation
------------------------

EA Java API is composed of two files primarily: `eaapi.jar` and `SSJavaCOM.dll`.
These files can not be redistributed freely, but they are found in every EA
installation in the directory
`C:\Program Files (x86)\Sparx Systems\EA\Java API`.
In order to compile or execute EAP Model Explorer, both files must be copied in
the root directory of the deployed program or the project, respectively.


Special thanks
--------------

- [Ueli Brawand](http://twitter.com/ubrawand) from
[components4oaw](http://components4oaw.sourceforge.net/) project for its
patience.
- Most of the icons comes from
[Eclipse Modeling Project](http://www.eclipse.org/modeling/).
- The rest of the icons are from [Papyrus UML](http://www.papyrusuml.org)
project.


Contributing
------------

Bug fixes and features are welcomed. Please fork the source code and submit a
pull request: <http://github.com/davidcaste/EAP-Model-Explorer/tree/master>


License
-------

This program is provided under an EPL open source license, read the
[LICENSE](org.uclm.louisse.eap.explorer/LICENSE) file for details.


Copyright
---------

Copyright (c) 2010 David Castellanos Serrano
