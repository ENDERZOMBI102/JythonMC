_DISCLAIMER! THIS LIBRARY DOES NOT LET YOU CREATE MODS IN PYTHON!_

# JythonMC
A library containing Jython for use within minecraft mods.

# Use
There's two methods to initialize the python system:

 1) Add `"jythonmc:use" : true` to `custom` in your `fabric.mod.json`<br>
      this will automatically start the python system when JythonMC is loaded
 3) Call `com.enderzombi102.jythonmc.Jython.initSystem()`<br>
      this will initialize the python system, but if its already initialized it'll do nothing

After the system is initialized, you can use the python interpreter liek this:
```java
try ( PythonInterpreter python = new PythonInterpreter() ) {
	python.setErr(System.err);  // to set python's stderr
	python.setOut(System.out);  // to set python's stdout
	python.exec(CODE);  // executes the code in the string CODE
} catch (Exception e) {
	e.printStackTrace();  // python exceptions are translated to java ones if nothing catch them
}
```

# Install
build.gradle
```gradle
repositories {
	mavenCentral()
	maven {
		url 'https://repo.repsy.io/mvn/enderzombi102/mc/'
	}
}

dependencies {
	modImplementation "com.enderzombi102:JythonMC:$jythonmc_version"
}
```
gradle.properties
```properties
jythonmc_version=1.0.0
```
