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

import java.io.UnsupportedEncodingException;
import com.clivern.wit.exception.DataNotFound;
import com.clivern.wit.exception.DataNotValid;
import com.clivern.wit.api.Message;
import com.clivern.wit.api.endpoint.MessageEndpoint;
import junit.framework.TestCase;

/**
 * Wit Message API Test Cases
 *
 * @since 1.0.0
 */
public class MessageTest extends TestCase {

    public void testGet01() throws DataNotValid, DataNotFound, UnsupportedEncodingException
    {
        Message message = new Message(MessageEndpoint.GET);
        message.setAppId("app__id");
        message.setAccessToken("access__token");
        message.setQ("Good Morning");
        //message.setContext("");
        message.setMsgId("789");
        message.setThreadId("fb_th");
        message.setN(6);
        message.setVerbose(true);
        message.config();
        assertEquals(message.debug(), "> curl -XGET 'https://api.wit.ai/message?v=20180225&q=Good Morning&msg_id=789&thread_id=fb_th&n=6&verbose=true' -H \"Authorization: Bearer access__token\" -H \"Content-Type: application/json\"");
    }

    public void testGet02() throws DataNotValid, DataNotFound, UnsupportedEncodingException
    {
        Message message = new Message(MessageEndpoint.GET);
        message.setAppId("app__id");
        message.setAccessToken("access__token");
        message.setQ("Good Morning");
        //message.setContext("");
        //message.setMsgId("789");
        //message.setThreadId("fb_th");
        //message.setN(6);
        //message.setVerbose(true);
        message.config();
        assertEquals(message.debug(), "> curl -XGET 'https://api.wit.ai/message?v=20180225&q=Good Morning' -H \"Authorization: Bearer access__token\" -H \"Content-Type: application/json\"");
    }

    public void testGet03() throws DataNotValid, DataNotFound, UnsupportedEncodingException
    {
        Message message = new Message(MessageEndpoint.GET);
        message.setAppId("app__id");
        message.setAccessToken("access__token");
        message.setQ("Good Morning");
        //message.setContext("");
        //message.setMsgId("789");
        message.setThreadId("fb_th");
        //message.setN(6);
        message.setVerbose(false);
        message.config();
        assertEquals(message.debug(), "> curl -XGET 'https://api.wit.ai/message?v=20180225&q=Good Morning&thread_id=fb_th&verbose=false' -H \"Authorization: Bearer access__token\" -H \"Content-Type: application/json\"");
    }
}