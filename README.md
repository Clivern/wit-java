Wit-Java
========

Wit-Java Is A Java Library For [Wit.ai](https://wit.ai)

*Current Version: Under Development*

[![Build Status](https://travis-ci.org/Clivern/wit-java.svg?branch=master)](https://travis-ci.org/Clivern/wit-java)
![](https://img.shields.io/github/license/clivern/wit-java.svg)

Installation
------------
To add a dependency using Maven, use the following:
```xml
<dependency>
  <groupId>com.clivern</groupId>
  <artifactId>wit-java</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

To add a dependency using Gradle, use the following:
```java
dependencies {
  compile 'com.clivern:wit-java:1.0.0-SNAPSHOT'
}
```

To add a dependency using Scala SBT, use the following:
```java
libraryDependencies += "com.clivern" % "wit-java" % "1.0.0-SNAPSHOT"
```

Usage
-----
After adding the package as a dependency, Please read the following steps:

### Basic Configurations

In order to cofigure the package create `config.properties` file with the following data

```java
wit_api_id=app id here
wit_access_token=access token here

logging_level=tarce or debug or info or warning or error
logging_file_path=src/main/java/resources/
logging_file_format=current_date or app
logging_log_type=file or console or both
logging_current_date_format=yyyy-MM-dd
logging_append=true or false
logging_buffered=true or false
```

### [Get Apps](https://wit.ai/docs/http/20170307#get--apps-link)
In order to get an array of all apps that you own.

```java
Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

App getApp = new App(AppEndpoint.GET);
String result = "";
String error = ""

if( wit.send(getApp) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.App;
import com.clivern.wit.api.endpoint.AppEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

/**
 * Main Class
 *
 * @since 1.0.0
 */
public class Main {

    public static void main(String[] args)
    {
        get("/", (request, response) -> {

            Config config = new Config();
            config.loadPropertiesFile("src/main/java/resources/config.properties");
            config.configLogger();

            Wit wit = new Wit(config);

            App getApp = new App(AppEndpoint.GET);
            if( wit.send(getApp) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### Update App

### Delete App

### Create App


Misc
====

Tutorials & Examples
--------------------

> For almost all supported features you can take a look at [`examples/`](https://github.com/Clivern/wit-java/tree/master/examples) folder for working examples.

Changelog
---------
Version 1.0.0:
```
Coming Soon
```

Acknowledgements
----------------

© 2018, Clivern. Released under [The Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt).

**Wit-Java** is authored and maintained by [@clivern](http://github.com/clivern).