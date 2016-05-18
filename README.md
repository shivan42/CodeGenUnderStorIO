### CodeGenUnderStorIO
####It's a Java classes code generator to work with `SQLite` through [StorIO](https://github.com/pushtorefresh/storio)

CodeGenUnderStorIO generates entity classes (with annotations) needed to work **StorIO**, as well as the supporting metadata classes that contain static information describing the tables of the database.

####For whom and why:
The project will be useful for those users of **StorIO**, who is developing its database in any visual editor that can generate queries to create tables. And **will help you to save time** when you describe а developed database schema in Android project **by generating the required java classes** of the tables description.

####How to use:
1. You need to collect in a single text file the queries such as CREATE TABLE 
2. Run with arguments the file **CodeGenUnderStorIO.jar**

*Available arguments in the order they appear:*
* The full path and name of the file containing the SQL queries (the path can be omitted if the file is located in the same folder with CodeGenUnderStorIO.jar)
* The java package name to specify it in the generated classes and create the appropriate directory structure
* Full or relative path to the location of the generated files

####Example
* [For Windows OS](https://github.com/shivan42/CodeGenUnderStorIO/tree/master/sample)

For those who have not decided with a visual editor database structure, it is proposed to use [Visual Paradigm Community Edition](https://www.visual-paradigm.com/download/community.jsp). In this editor was generated schema used in the [example](https://github.com/shivan42/CodeGenUnderStorIO/tree/master/sample).

####Assembly of project
To run the source codes of the project will need to specify dependencies from third party libraries:
* [FreeMarker v2.3.x+](http://freemarker.org/freemarkerdownload.html)
* [Commons-lang3 v3.4+](http://commons.apache.org/proper/commons-lang/download_lang.cgi)

----
The project is inspired by the plugin [AutoGenerator](https://github.com/i17c/AutoGenerator) for which separate him thanks.
There are also plans to assembly from this project a plugin for IDE, but when... who knows. So, if you like the project and you have time to create a plugin from it, I will gladly accept your pull-requsts :)

Created by [@Shivan](https://github.com/shivan42)
