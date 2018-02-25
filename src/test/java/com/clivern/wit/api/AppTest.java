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
package com.clivern.wit.api;

import com.clivern.wit.exception.DataNotFound;
import com.clivern.wit.exception.DataNotValid;
import com.clivern.wit.api.App;
import com.clivern.wit.api.endpoint.AppEndpoint;
import junit.framework.TestCase;

/**
 * Wit App API Test Cases
 *
 * @since 1.0.0
 */
public class AppTest extends TestCase {

    public void testGet() throws DataNotValid, DataNotFound
    {
        App getApp = new App(AppEndpoint.GET);
        getApp.setAppId("app__id");
        getApp.setAccessToken("access__token");
        getApp.config();
        assertEquals(getApp.debug(), "> curl -XGET 'https://api.wit.ai/apps?v=20180225&offset=0&limit=500' -H \"Authorization: Bearer access__token\" -H \"Content-Type: application/json\"");
    }

    public void testDelete() throws DataNotValid, DataNotFound
    {
        App deleteApp = new App(AppEndpoint.DELETE);
        deleteApp.setAppId("app__id");
        deleteApp.setAccessToken("access__token");
        deleteApp.config();
        assertEquals(deleteApp.debug(), "> curl -XDELETE 'https://api.wit.ai/apps/app__id?v=20180225' -H \"Authorization: Bearer access__token\" -H \"Content-Type: application/json\"");
    }

    public void testUpdate() throws DataNotValid, DataNotFound
    {
        App updateApp = new App(AppEndpoint.UPDATE);
        updateApp.setName("Clark");
        updateApp.setLang("English");
        updateApp.setPrivate("false");
        updateApp.setDesc("Hello World");
        updateApp.setTimezone("America/Los_Angeles");
        updateApp.setAppId("app__id");
        updateApp.setAccessToken("access__token");
        updateApp.config();
        assertEquals(updateApp.debug(), "> curl -XPUT 'https://api.wit.ai/apps/app__id?v=20180225' -H \"Authorization: Bearer access__token\" -H \"Content-Type: application/json\" -d '{\"private\":\"false\",\"timezone\":\"America/Los_Angeles\",\"name\":\"Clark\",\"lang\":\"English\",\"desc\":\"Hello World\"}'");
    }

    public void testCreate() throws DataNotValid, DataNotFound
    {
        App createApp = new App(AppEndpoint.CREATE);
        createApp.setName("Clark");
        createApp.setLang("English");
        createApp.setPrivate("false");
        createApp.setDesc("Hello World");
        createApp.setAppId("app__id");
        createApp.setAccessToken("access__token");
        createApp.config();
        assertEquals(createApp.debug(), "> curl -XPOST 'https://api.wit.ai/apps?v=20180225' -H \"Authorization: Bearer access__token\" -H \"Content-Type: application/json\" -d '{\"private\":\"false\",\"name\":\"Clark\",\"lang\":\"English\",\"desc\":\"Hello World\"}'");
    }
}