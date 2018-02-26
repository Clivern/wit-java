Wit-Java
========

Wit-Java Is A Java Library For [Wit.ai](https://wit.ai)

*Current Version: 1.0.0*

[![Build Status](https://travis-ci.org/Clivern/wit-java.svg?branch=master)](https://travis-ci.org/Clivern/wit-java)
![](https://img.shields.io/maven-central/v/com.clivern/wit-java.svg)
[![Javadocs](http://www.javadoc.io/badge/com.clivern/wit-java.svg)](http://www.javadoc.io/doc/com.clivern/wit-java)
![](https://img.shields.io/github/license/clivern/wit-java.svg)

Installation
------------
To add a dependency using Maven, use the following:
```xml
<dependency>
  <groupId>com.clivern</groupId>
  <artifactId>wit-java</artifactId>
  <version>1.0.0</version>
</dependency>
```

To add a dependency using Gradle, use the following:
```java
dependencies {
  compile 'com.clivern:wit-java:1.0.0'
}
```

To add a dependency using Scala SBT, use the following:
```java
libraryDependencies += "com.clivern" % "wit-java" % "1.0.0"
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

*Please Note That* You can put configs manually using `set` method of config class like the following:
```java
Config config = new Config();
config.set("wit_api_id", "app id here");
config.set("wit_access_token", "access token here");
config.put("logging_level", "tarce or debug or info or warning or error");
config.put("logging_file_path", "src/main/java/resources/");
config.put("logging_file_format", "current_date or app");
config.put("logging_log_type", "file or console or both");
config.put("logging_current_date_format", "yyyy-MM-dd");
config.put("logging_append", "true or false");
config.put("logging_buffered", "true or false");
config.configLogger();
```
*Both `wit_api_id` and `wit_access_token` can be changed with each request* with `setAppId` and `setAccessToken` methods. I will let you know within the docs whenever you can override them.

### [Get all Apps](https://wit.ai/docs/http/20170307#get--apps-link)
To get an array of all apps that you own.

```java
import com.clivern.wit.api.App;
import com.clivern.wit.api.endpoint.AppEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

App getApp = new App(AppEndpoint.GET);
// To Use another App Id different from the one on your properties file
// getApp.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// getApp.setAccessToken("Your Custom Access Token Here");
String result = "";
String error = "";

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
            // To Use another App Id different from the one on your properties file
            // getApp.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // getApp.setAccessToken("Your Custom Access Token Here");

            if( wit.send(getApp) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Update an existing app](https://wit.ai/docs/http/20170307#put--apps-:app-id-link)

To Update an app with the given attributes.

```java
import com.clivern.wit.api.App;
import com.clivern.wit.api.endpoint.AppEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

App updateApp = new App(AppEndpoint.UPDATE);
updateApp.setName("Clark");
updateApp.setLang("English");
updateApp.setPrivate("false");
updateApp.setDesc("Hello World");
updateApp.setTimezone("America/Los_Angeles");
// To Use another App Id different from the one on your properties file
// updateApp.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// updateApp.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(updateApp) ){
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

            App updateApp = new App(AppEndpoint.UPDATE);
            updateApp.setName("Clark");
            updateApp.setLang("English");
            updateApp.setPrivate("false");
            updateApp.setDesc("Hello World");
            updateApp.setTimezone("America/Los_Angeles");
            // To Use another App Id different from the one on your properties file
            // updateApp.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // updateApp.setAccessToken("Your Custom Access Token Here");

            if( wit.send(updateApp) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Delete an App](https://wit.ai/docs/http/20170307#delete--apps-:app-id-link)

To Permanently delete the app.

```java
import com.clivern.wit.api.App;
import com.clivern.wit.api.endpoint.AppEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

App deleteApp = new App(AppEndpoint.DELETE);
// To Use another App Id different from the one on your properties file
// deleteApp.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// deleteApp.setAccessToken("Your Custom Access Token Here");
String result = "";
String error = "";

if( wit.send(deleteApp) ){
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

            App deleteApp = new App(AppEndpoint.DELETE);
            // To Use another App Id different from the one on your properties file
            // deleteApp.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // deleteApp.setAccessToken("Your Custom Access Token Here");
            if( wit.send(deleteApp) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Create a New App](https://wit.ai/docs/http/20170307#post--apps-link)

To Create a new app for an existing user.

```java
import com.clivern.wit.api.App;
import com.clivern.wit.api.endpoint.AppEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

App createApp = new App(AppEndpoint.CREATE);
createApp.setName("Clark");
createApp.setLang("English");
createApp.setPrivate("false");
createApp.setDesc("Hello World");
// To Use another App Id different from the one on your properties file
// createApp.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// createApp.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(createApp) ){
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

            App createApp = new App(AppEndpoint.CREATE);
            createApp.setName("Clark");
            createApp.setLang("English");
            createApp.setPrivate("false");
            createApp.setDesc("Hello World");
            // To Use another App Id different from the one on your properties file
            // createApp.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // createApp.setAccessToken("Your Custom Access Token Here");

            if( wit.send(createApp) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Get The Meaning of a Sentence](https://wit.ai/docs/http/20170307#get--message-link)

To extract the meaning from a sentence, based on the app data:

```java
import com.clivern.wit.api.Message;
import com.clivern.wit.api.endpoint.MessageEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Message message = new Message(MessageEndpoint.GET);
message.setQ("Good Morning");
//message.setContext("");
//message.setMsgId("789");
//message.setThreadId("fb_th");
//message.setN(6);
//message.setVerbose(true);

// To Use another App Id different from the one on your properties file
// message.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// message.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(message) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Message;
import com.clivern.wit.api.endpoint.MessageEndpoint;
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

            Message message = new Message(MessageEndpoint.GET);
            message.setQ("Good Morning");
            //message.setContext("");
            //message.setMsgId("789");
            //message.setThreadId("fb_th");
            //message.setN(6);
            //message.setVerbose(true);

            // To Use another App Id different from the one on your properties file
            // message.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // message.setAccessToken("Your Custom Access Token Here");

            if( wit.send(message) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Get Entities](https://wit.ai/docs/http/20170307#get--entities-link)

To get a list of available entities for the app.

```java
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Entity entity = new Entity(EntityEndpoint.GET_ENTITIES);

// To Use another App Id different from the one on your properties file
// entity.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// entity.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(entity) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
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

            Entity entity = new Entity(EntityEndpoint.GET_ENTITIES);

            // To Use another App Id different from the one on your properties file
            // entity.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // entity.setAccessToken("Your Custom Access Token Here");

            if( wit.send(entity) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }

        }
    }
}
```

### [Create Entity](https://wit.ai/docs/http/20170307#post--entities-link)

To Create a new entity with the given attributes.

```java
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Entity entity = new Entity(EntityEndpoint.CREATE_ENTITY);
entity.setId("favorite_city"); // required
entity.setDoc("A city that I like"); //optional

// To Use another App Id different from the one on your properties file
// entity.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// entity.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(entity) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
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

            Entity entity = new Entity(EntityEndpoint.CREATE_ENTITY);
            entity.setId("favorite_city"); // required
            entity.setDoc("A city that I like"); //optional

            // To Use another App Id different from the one on your properties file
            // entity.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // entity.setAccessToken("Your Custom Access Token Here");

            if( wit.send(entity) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Get Entity](https://wit.ai/docs/http/20170307#get--entities-:entity-id-link)

To get entity data and values.

```java
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Entity entity = new Entity(EntityEndpoint.GET_ENTITY);
entity.setEntityId("favorite_city");

// To Use another App Id different from the one on your properties file
// entity.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// entity.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(entity) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
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

            Entity entity = new Entity(EntityEndpoint.GET_ENTITY);
            entity.setEntityId("favorite_city");

            // To Use another App Id different from the one on your properties file
            // entity.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // entity.setAccessToken("Your Custom Access Token Here");

            if( wit.send(entity) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Update Entity](https://wit.ai/docs/http/20170307#put--entities-:entity-id-link)

To Update an entity with the given attributes.

```java
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Entity entity = new Entity(EntityEndpoint.UPDATE_ENTITY);
entity.setEntityId("favorite_land");
entity.setId("favorite_city");
entity.setDoc("These are lands worth going to");
entity.setValues(new String[]{
    "{\"value\": \"Paris\",\"expressions\": [\"Paris\", \"City of Light\", \"Capital of FR\"]}",
    "{\"value\": \"London\",\"expressions\": [\"London\", \"City of Dreams\", \"Capital of UK\"]}",
    "{\"value\": \"Amsterdam\",\"expressions\": [\"Amsterdam\", \"City of Love\", \"Capital of NL\"]}"
});

// To Use another App Id different from the one on your properties file
// entity.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// entity.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(entity) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
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

            Entity entity = new Entity(EntityEndpoint.UPDATE_ENTITY);
            entity.setEntityId("favorite_land");
            entity.setId("favorite_city");
            entity.setDoc("These are lands worth going to");
            entity.setValues(new String[]{
                "{\"value\": \"Paris\",\"expressions\": [\"Paris\", \"City of Light\", \"Capital of FR\"]}",
                "{\"value\": \"London\",\"expressions\": [\"London\", \"City of Dreams\", \"Capital of UK\"]}",
                "{\"value\": \"Amsterdam\",\"expressions\": [\"Amsterdam\", \"City of Love\", \"Capital of NL\"]}"
            });

            // To Use another App Id different from the one on your properties file
            // entity.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // entity.setAccessToken("Your Custom Access Token Here");

            if( wit.send(entity) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Delete Entity](https://wit.ai/docs/http/20170307#delete--entities-:entity-id-link)

To Permanently delete an entity.

```java
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Entity entity = new Entity(EntityEndpoint.DELETE_ENTITY);
entity.setEntityId("favorite_land");

// To Use another App Id different from the one on your properties file
// entity.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// entity.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(entity) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
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

            Entity entity = new Entity(EntityEndpoint.DELETE_ENTITY);
            entity.setEntityId("favorite_land");

            // To Use another App Id different from the one on your properties file
            // entity.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // entity.setAccessToken("Your Custom Access Token Here");

            if( wit.send(entity) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Create Entity Value](https://wit.ai/docs/http/20170307#post--entities-:entity-id-values-link)

To Add new values to a keyword entity.

```java
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Entity entity = new Entity(EntityEndpoint.CREATE_ENTITY_VALUE);
entity.setEntityId("favorite_city");
entity.setValue("London");
entity.setExpressions(new String[]{"London", "Big Ben City"});
entity.setMetadata("CITY_1234");

// To Use another App Id different from the one on your properties file
// entity.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// entity.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(entity) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
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

            Entity entity = new Entity(EntityEndpoint.CREATE_ENTITY_VALUE);
            entity.setEntityId("favorite_city");
            entity.setValue("London");
            entity.setExpressions(new String[]{"London", "Big Ben City"});
            entity.setMetadata("CITY_1234");

            // To Use another App Id different from the one on your properties file
            // entity.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // entity.setAccessToken("Your Custom Access Token Here");

            if( wit.send(entity) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Delete Entity Value](https://wit.ai/docs/http/20170307#delete--entities-:entity-id-values-link)

To Remove a given value from an entity.

```java
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Entity entity = new Entity(EntityEndpoint.DELETE_ENTITY_VALUE);
entity.setEntityId("favorite_city");
entity.setEntityValue("London");

// To Use another App Id different from the one on your properties file
// entity.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// entity.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(entity) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
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

            Entity entity = new Entity(EntityEndpoint.DELETE_ENTITY_VALUE);
            entity.setEntityId("favorite_city");
            entity.setEntityValue("London");

            // To Use another App Id different from the one on your properties file
            // entity.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // entity.setAccessToken("Your Custom Access Token Here");

            if( wit.send(entity) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Create Entity Value Expression](https://wit.ai/docs/http/20170307#post--entities-:entity-id-values-:value-id-expressions-link)

To Create a new expression for a keyword entity.

```java
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Entity entity = new Entity(EntityEndpoint.CREATE_ENTITY_VALUE_EXPRESSION);
entity.setEntityId("favorite_city");
entity.setEntityValue("London");
entity.setExpression("Britain Capital");

// To Use another App Id different from the one on your properties file
// entity.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// entity.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(entity) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
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

            Entity entity = new Entity(EntityEndpoint.CREATE_ENTITY_VALUE_EXPRESSION);
            entity.setEntityId("favorite_city");
            entity.setEntityValue("London");
            entity.setExpression("Britain Capital");

            // To Use another App Id different from the one on your properties file
            // entity.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // entity.setAccessToken("Your Custom Access Token Here");

            if( wit.send(entity) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```

### [Delete Entity Value Expression](https://wit.ai/docs/http/20170307#delete--entities-:entity-id-values-:value-id-expressions-link)

To Remove an expression from an entity.

```java
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
import com.clivern.wit.util.Config;
import com.clivern.wit.Wit;

//....


Config config = new Config();
config.loadPropertiesFile("config.properties");
config.configLogger();

Wit wit = new Wit(config);

Entity entity = new Entity(EntityEndpoint.DELETE_ENTITY_VALUE_EXPRESSION);
entity.setEntityId("favorite_city");
entity.setEntityValue("London");
entity.setExpressionValue("Britain Capital");

// To Use another App Id different from the one on your properties file
// entity.setAppId("Your Custom App ID Here");
// To Use Another Access Token Different From The one on your properties file
// entity.setAccessToken("Your Custom Access Token Here");

String result = "";
String error = "";

if( wit.send(entity) ){
    result = wit.getResponse();
}else{
    error = wit.getError();
}
```

So in case we use [spark java framework](http://sparkjava.com/), Our code can be look like the following:

```java
package com.clivern.test;

import static spark.Spark.*;
import com.clivern.wit.api.Entity;
import com.clivern.wit.api.endpoint.EntityEndpoint;
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

            Entity entity = new Entity(EntityEndpoint.DELETE_ENTITY_VALUE_EXPRESSION);
            entity.setEntityId("favorite_city");
            entity.setEntityValue("London");
            entity.setExpressionValue("Britain Capital");

            // To Use another App Id different from the one on your properties file
            // entity.setAppId("Your Custom App ID Here");
            // To Use Another Access Token Different From The one on your properties file
            // entity.setAccessToken("Your Custom Access Token Here");

            if( wit.send(entity) ){
                return wit.getResponse();
            }else{
                return wit.getError();
            }
        }
    }
}
```


Misc
====

Changelog
---------
Version 1.0.0:
```
Initial Release.
```

Acknowledgements
----------------

© 2018, Clivern. Released under [The Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt).

**Wit-Java** is authored and maintained by [@clivern](http://github.com/clivern).