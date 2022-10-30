# Brick-Breaker

Bar Ilan project instructions: https://github.com/ariecattan/biuoop2022/wiki


INSTRUCTIONS:
In this project I used a software called Apache Ant.
In order to run the game, we need to do:
1. go to File -> Project Structure -> Modules -> Add JARs and choose the biuoop-1.4.jar, and click the box next to it for export.
2. go to 'edit configurations' -> Modify options -> Add VM options -> from left to right fill the black:
java 17 SDK, -ea, Ass6Game, (stages u want the game to run).


ANT EXPLANATIONS:
In Ant, the main configuration file is called build.xml, which is somewhat equivalent to the makefile you are already familiar with. As you can understand from its extension, it is an XML file, which specify a certain format for files so that machines can unambiguously retrieve information from it.
Similarly to makefile, the build.xml file specifies certain targets (i.e. automated tasks you'll want to use over and over again) and how to perform them. After writing a build.xml and putting it in your current directory, you can execute a target by running the command ant <target-name>. This will invoke the Ant software, which in turn will search for and read the build.xml and perform the the specified task.


