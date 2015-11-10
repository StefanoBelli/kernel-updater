# kernel-updater
A small java program to fetch Linux kernel srcs from command line :)

# Execute

Jarfile
~~~
$ cd jar/
$ java -jar kernelupdater.jar
~~~

Using java with single class
~~~
$ cd class/
$ java it.stefanobelli.kernelupdater.Main
~~~

# Creating Jarfile

(Inside src/ directory [from the package (sub)directories])

Writing META-INF/MANIFEST.MF (Manifest file which contains main class)
~~~
Main-Class: it.stefanobelli.kernelupdater.Main
~~~

Command:
~~~
jar cmvf META-INF/MANIFEST.MF kernelupdater.jar it/stefanobelli/kernelupdater/Main.class it/stefanobelli/kernelupdater/GetKernel.class
~~~

# Sources

in src/

# To-Do

 - Background daemon
 - GUI (AWT)
