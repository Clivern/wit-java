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

import java.util.HashMap;
import java.util.Map;
import org.pmw.tinylog.Logger;
import com.clivern.wit.exception.DataNotFound;
import com.clivern.wit.exception.DataNotValid;
import com.clivern.wit.api.Contract;
import com.clivern.wit.api.endpoint.AppEndpoint;

/**
 * App API
 *
 * @since 1.0.0
 */
public class App implements Contract {

    public String appId = "";
    public String accessToken = "";
    public String endpointName = "";
    public String contentType = AppEndpoint.CONTENT_TYPE;
    public String url = "";
    public String method = "";
    public Map<String, String> data = new HashMap<String, String>();
    public String finalData = "";
    public Map<String, String> headers = new HashMap<String, String>();
    public Integer offset = 0;
    public Integer limit = 500;


    /**
     * Class Constructor
     *
     * @param endpointName The Endpoint Name
     */
    public App(String endpointName)
    {
        this.endpointName = endpointName;
    }

    /**
     * Class Constructor
     *
     * @param endpointName The Endpoint Name
     * @param contentType The Content Type
     */
    public App(String endpointName, String contentType)
    {
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
    public Boolean config() throws DataNotValid, DataNotFound
    {
        // Build Headers
        this.headers.put("Authorization", "Bearer " + this.accessToken);
        this.headers.put("Content-Type", this.contentType);

        // Set URL & Method
        if( this.endpointName.equals(AppEndpoint.GET) ){

            this.url = AppEndpoint.GET_ENDPOINT;
            this.method = AppEndpoint.GET_METHOD;

        }else if( this.endpointName.equals(AppEndpoint.CREATE) ){

            this.url = AppEndpoint.CREATE_ENDPOINT;
            this.method = AppEndpoint.CREATE_METHOD;

            if( this.getName().equals("") || this.getLang().equals("") || this.getPrivate().equals("") ){
                Logger.error("Error! App Name, Lang and IsPrivate are required to create a New App.");
                throw new DataNotFound("Error! App Name, Lang and IsPrivate are required to create a New App.");
            }

        }else if( this.endpointName.equals(AppEndpoint.UPDATE) ){

            this.url = AppEndpoint.UPDATE_ENDPOINT;
            this.method = AppEndpoint.UPDATE_METHOD;

        }else if( this.endpointName.equals(AppEndpoint.DELETE) ){

            this.url = AppEndpoint.DELETE_ENDPOINT;
            this.method = AppEndpoint.DELETE_METHOD;

        }else{
            Logger.error("Error! Invalid endpointName Value.");
            throw new DataNotValid("Error! Invalid endpointName Value.");
        }

        // Build Data
        if( !this.data.isEmpty() ){
            this.finalData += "{";
            for (Map.Entry<String, String> entry : this.data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                this.finalData += "\"" + key + "\":\"" + value + "\",";
            }
            this.finalData = finalData.replaceAll(",$", "");
            this.finalData += "}";
        }

        this.url = this.url.replace("{$APP_ID}", this.appId);
        this.url = this.url.replace("{$OFFSET}", this.offset.toString());
        this.url = this.url.replace("{$LIMIT}", this.limit.toString());

        return true;
    }

    /**
     * Get The URL
     *
     * @return String The URL
     */
    public String getUrl()
    {
        return this.url;
    }

    /**
     * Get The Method
     *
     * @return String The Method
     */
    public String getMethod()
    {
        return this.method;
    }

    /**
     * Get The Headers
     *
     * @return Map The Headers
     */
    public Map<String, String> getHeaders()
    {
        return this.headers;
    }

    /**
     * Get The Data to Send
     *
     * @return String The Data to Send
     */
    public String getData()
    {
        return this.finalData;
    }

    /**
     * Get The App ID
     *
     * @return String The App ID
     */
    public String getAppId()
    {
        return this.appId;
    }

    /**
     * Get Access Token
     *
     * @return String The Access Token
     */
    public String getAccessToken()
    {
        return this.accessToken;
    }

    /**
     * Get The apps list offset
     *
     * @return Integer the app list offset
     */
    public Integer getOffset()
    {
        return this.offset;
    }

    /**
     * Get The Apps List Limit
     *
     * @return Integer the apps list limit
     */
    public Integer getLimit()
    {
        return this.limit;
    }

    /**
     * Get The App Name
     *
     * @return String the App name
     */
    public String getName()
    {
        return (this.data.containsKey("name")) ? this.data.get("name") : "";
    }

    /**
     * Get The App Lang
     *
     * @return String the App Lang
     */
    public String getLang()
    {
        return (this.data.containsKey("lang")) ? this.data.get("lang") : "";
    }

    /**
     * Get Whether App is private or not
     *
     * @return String Whether app private or not
     */
    public String getPrivate()
    {
        return (this.data.containsKey("private")) ? this.data.get("private") : "";
    }

    /**
     * Get The App Desc
     *
     * @return String the App Desc
     */
    public String getDesc()
    {
        return (this.data.containsKey("desc")) ? this.data.get("desc") : "";
    }

    /**
     * Get The App Timezone
     *
     * @return String the App Timezone
     */
    public String getTimezone()
    {
        return (this.data.containsKey("timezone")) ? this.data.get("timezone") : "";
    }

    /**
     * Set App ID
     *
     * @param appId Application ID
     */
    public void setAppId(String appId)
    {
        if( this.appId.equals("") ){
            this.appId = appId;
        }
    }

    /**
     * Set Access Token
     *
     * @param accessToken Access Token
     */
    public void setAccessToken(String accessToken)
    {
        if( this.accessToken.equals("") ){
            this.accessToken = accessToken;
        }
    }

    /**
     * Set Apps list offset
     *
     * @param offset The apps list offset
     */
    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    /**
     * Set Apps list limit
     *
     * @param limit The apps list limit
     */
    public void setLimit(Integer limit)
    {
        this.limit = limit;
    }

    /**
     * Set App Name
     *
     * @param name The app name
     */
    public void setName(String name)
    {
        this.data.put("name", name);
    }

    /**
     * Set App Lang
     *
     * @param lang The app lang
     */
    public void setLang(String lang)
    {
        this.data.put("lang", lang);
    }

    /**
     * Whether App Private or Not
     *
     * @param isPrivate The private status
     */
    public void setPrivate(String isPrivate)
    {
        this.data.put("private", isPrivate);
    }

   /**
     * Set the Desc
     *
     * @param desc The App Desc
     */
    public void setDesc(String desc)
    {
        this.data.put("desc", desc);
    }

   /**
     * Set the Timezone
     *
     * @param timezone The App Timezone
     */
    public void setTimezone(String timezone)
    {
        this.data.put("timezone", timezone);
    }

    /**
     * Debug The Request
     *
     * This Used for Development Purposes and Shouldn't used in Production
     *
     * @return String The debug data
     */
    public String debug()
    {
        String debug = "> curl -X" + this.method + " '" + this.url + "'";

        if( !this.headers.isEmpty() ){
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                debug += " -H \"" + key + ": " + value + "\"";
            }
        }

        if( !this.getData().equals("") ){
            debug += " -d '" + this.getData() + "'";
        }

        return debug;
    }
}