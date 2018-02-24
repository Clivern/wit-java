/*
 * Copyright (C) 2018 Clivern <http://clivern.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clivern.wit;

import static spark.Spark.*;
import com.clivern.wit.api.App;
import com.clivern.wit.api.endpoint.AppEndpoint;
import com.clivern.wit.util.Config;

/**
 * Test Package
 *
 * @author A.F
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args)
    {
        get("/", (request, response) -> {

            Config config = new Config();
            config.loadPropertiesFile("src/main/java/resources/config.properties");
            config.configLogger();
            App getApp = new App(config.get("wit_api_id", ""), config.get("wit_access_token", ""), AppEndpoint.GET);
            getApp.config();

            App createApp = new App(config.get("wit_api_id", ""), config.get("wit_access_token", ""), AppEndpoint.CREATE);
            createApp.setName("Clark");
            createApp.setLang("English");
            createApp.setPrivate("false");
            createApp.setDesc("Hello World");
            createApp.config();

            App updateApp = new App(config.get("wit_api_id", ""), config.get("wit_access_token", ""), AppEndpoint.UPDATE);
            updateApp.setDesc("Viola");
            updateApp.config();

            App deleteApp = new App(config.get("wit_api_id", ""), config.get("wit_access_token", ""), AppEndpoint.DELETE);
            deleteApp.config();

            return "<pre>" + getApp.debug() + "\n" +  createApp.debug() + "\n" + updateApp.debug() + "\n" + deleteApp.debug() + "\n" + "</pre>";
        });
    }
}