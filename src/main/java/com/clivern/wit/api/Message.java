/*
 * Copyright (C) 2020 Clivern <http://clivern.com>
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

import com.clivern.wit.api.endpoint.MessageEndpoint;
import com.clivern.wit.exception.DataNotFound;
import com.clivern.wit.exception.DataNotValid;
import java.util.HashMap;
import java.util.Map;
import org.pmw.tinylog.Logger;

/**
 * Message API
 *
 * @since 1.0.0
 */
public class Message implements Contract {

    public String appId = "";
    public String accessToken = "";
    public String endpointName = "";
    public String contentType = MessageEndpoint.CONTENT_TYPE;
    public String url = "";
    public String method = "";
    public Map<String, String> data = new HashMap<String, String>();
    public String finalData = "";
    public Map<String, String> headers = new HashMap<String, String>();

    public String q = "";
    public String context = "";
    public String msgId = "";
    public String threadId = "";
    public Integer n;
    public Boolean verbose;

    /**
     * Class Constructor
     *
     * @param endpointName The Endpoint Name
     */
    public Message(String endpointName) {
        this.endpointName = endpointName;
    }

    /**
     * Class Constructor
     *
     * @param endpointName The Endpoint Name
     * @param contentType The Content Type
     */
    public Message(String endpointName, String contentType) {
        this.endpointName = endpointName;
        this.contentType = contentType;
    }

    /**
     * Configure The API Call Info
     *
     * @return Boolean Whether Call Will be Valid or Not
     * @throws DataNotValid Invalid Data Within The Request
     * @throws DataNotFound Some Data are Missing
     */
    public Boolean config() throws DataNotValid, DataNotFound {
        // Build Headers
        this.headers.put("Authorization", "Bearer " + this.accessToken);
        this.headers.put("Content-Type", this.contentType);

        // Set URL & Method
        if (this.endpointName.equals(MessageEndpoint.GET)) {

            this.url = MessageEndpoint.GET_ENDPOINT;
            this.method = MessageEndpoint.GET_METHOD;

            if (this.q.trim().equals("")) {
                Logger.error("Error! Message user query is required.");
                throw new DataNotFound("Error! Message user query is required.");
            }

            if ((this.n != null) && ((this.n < 1) || (this.n > 8))) {
                Logger.error("Error! n-best trait must be from 1 to 8.");
                throw new DataNotFound("Error! n-best trait must be from 1 to 8.");
            }

        } else {
            Logger.error("Error! Invalid endpointName Value.");
            throw new DataNotValid("Error! Invalid endpointName Value.");
        }

        // Build Data
        if (!this.data.isEmpty()) {
            this.finalData += "{";
            for (Map.Entry<String, String> entry : this.data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                this.finalData += "\"" + key + "\":\"" + value + "\",";
            }
            this.finalData = finalData.replaceAll(",$", "");
            this.finalData += "}";
        }

        this.url = this.url.replace("{$Q}", this.q);

        if (this.context.equals("")) {
            this.url = this.url.replace("&context={$CONTEXT}", "");
        } else {
            this.url = this.url.replace("{$CONTEXT}", this.context);
        }

        if (this.msgId.equals("")) {
            this.url = this.url.replace("&msg_id={$MSG_ID}", "");
        } else {
            this.url = this.url.replace("{$MSG_ID}", this.msgId);
        }

        if (this.threadId.equals("")) {
            this.url = this.url.replace("&thread_id={$THREAD_ID}", "");
        } else {
            this.url = this.url.replace("{$THREAD_ID}", this.threadId);
        }

        if (this.n == null) {
            this.url = this.url.replace("&n={$N}", "");
        } else {
            this.url = this.url.replace("{$N}", this.n.toString());
        }

        if (this.verbose == null) {
            this.url = this.url.replace("&verbose={$VERBOSE}", "");
        } else {
            this.url = this.url.replace("{$VERBOSE}", (this.verbose) ? "true" : "false");
        }

        return true;
    }

    /**
     * Get The URL
     *
     * @return String The URL
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Get The Method
     *
     * @return String The Method
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * Get The Headers
     *
     * @return Map The Headers
     */
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    /**
     * Get Q (User’s query). Length must be more than 0 and less than 256
     *
     * @return String The users query
     */
    public String getQ() {
        return this.q;
    }

    /**
     * Get context object (JSON)
     *
     * @return String The context object JSON
     */
    public String getContext() {
        return this.context;
    }

    /**
     * Get The Message ID
     *
     * @return String The Message ID
     */
    public String getMsgId() {
        return this.msgId;
    }

    /**
     * Get The Thread ID
     *
     * @return String The Thread ID
     */
    public String getThreadId() {
        return this.threadId;
    }

    /**
     * Get The number of n-best trait entities you want to get back. The Default is 1, and the
     * maximum is 8
     *
     * @return Integer The number of n-best
     */
    public Integer getN() {
        return this.n;
    }

    /**
     * Get Verbose Status. A flag to get auxiliary information about entities, like the location
     * within the sentence.
     *
     * @return Boolean The Verbose Value
     */
    public Boolean getVerbose() {
        return this.verbose;
    }

    /**
     * Get The Data to Send
     *
     * @return String The Data to Send
     */
    public String getData() {
        return this.finalData;
    }

    /**
     * Get The App ID
     *
     * @return String The App ID
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * Get Access Token
     *
     * @return String The Access Token
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * Set Q (User’s query). Length must be more than 0 and less than 256
     *
     * @param q The users query
     */
    public void setQ(String q) {
        this.q = q;
    }

    /**
     * Set context object (JSON)
     *
     * @param context The context object JSON
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Set The Message ID
     *
     * @param msgId The Message ID
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * Set The Thread ID
     *
     * @param threadId The Thread ID
     */
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    /**
     * Set The number of n-best trait entities you want to get back. The Default is 1, and the
     * maximum is 8
     *
     * @param n The number of n-best
     */
    public void setN(Integer n) {
        this.n = n;
    }

    /**
     * Set Verbose Status. A flag to get auxiliary information about entities, like the location
     * within the sentence.
     *
     * @param verbose The Verbose Value
     */
    public void setVerbose(Boolean verbose) {
        this.verbose = verbose;
    }

    /**
     * Set App ID
     *
     * @param appId Application ID
     */
    public void setAppId(String appId) {
        if (this.appId.equals("")) {
            this.appId = appId;
        }
    }

    /**
     * Set Access Token
     *
     * @param accessToken Access Token
     */
    public void setAccessToken(String accessToken) {
        if (this.accessToken.equals("")) {
            this.accessToken = accessToken;
        }
    }

    /**
     * Debug The Request
     *
     * <p>This Used for Development Purposes and Shouldn't used in Production
     *
     * @return String The debug data
     */
    public String debug() {
        String debug = "> curl -X" + this.method + " '" + this.url + "'";

        if (!this.headers.isEmpty()) {
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                debug += " -H \"" + key + ": " + value + "\"";
            }
        }

        if (!this.getData().equals("")) {
            debug += " -d '" + this.getData() + "'";
        }

        return debug;
    }
}
