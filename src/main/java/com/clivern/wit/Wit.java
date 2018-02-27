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

import java.util.HashMap;
import java.util.Map;
import com.clivern.wit.util.Config;
import com.clivern.wit.util.Http;
import com.clivern.wit.api.Contract;
import org.pmw.tinylog.Logger;
import com.clivern.wit.exception.DataNotFound;
import com.clivern.wit.exception.DataNotValid;
import java.io.IOException;

/**
 * Wit Base Class
 *
 * @author A.F
 * @since 1.0.0
 */
public class Wit {

    protected Config config;
    protected String response = "";
    protected Boolean status = false;
    protected String error = "";

    /**
     * Class Constructor
     *
     * @param config An instance of config class
     */
    public Wit(Config config)
    {
        this.config = config;
    }

    /**
     * Send A Call To Wit.Ai
     *
     * @param item Item Data to Send to Wit.AI
     * @return Boolean the call status
     * @throws DataNotValid Data are not valid
     * @throws DataNotFound Some data are missing
     * @throws IOException Unable to send the request
     */
    public Boolean send(Contract item) throws DataNotValid, DataNotFound, IOException
    {
        this.status = false;
        this.response = "";
        this.error = "";

        try {
            item.setAppId(this.config.get("wit_api_id", ""));
            item.setAccessToken(this.config.get("wit_access_token", ""));
            item.config();
            HashMap<String,String> headers = (HashMap<String,String>) item.getHeaders();
            Logger.info(item.debug());
            Http httpRequest = new Http(item.getUrl(), item.getMethod(), item.getHeaders(), item.getData());
            this.response = httpRequest.execute();
        } catch (Exception e) {
            this.status = false;
            this.error = "Error! " + e.getMessage();
            Logger.error(this.error);
            return this.status;
        }

        if( this.response.equals("") || this.response.indexOf("error") > 0 ){
            this.status = false;
        }else{
            this.status = true;
        }

        return this.status;
    }

    /**
     * Get Latest Call Response
     *
     * @return String The Call Response
     */
    public String getResponse()
    {
        return this.response;
    }

    /**
     * Get Latest Call Error
     *
     * @return String The Call Error
     */
    public String getError()
    {
        return this.error;
    }

    /**
     * Get Latest Call Status
     *
     * @return Boolean The Call Status
     */
    public Boolean getRequestStatus()
    {
        return this.status;
    }

    /**
     * Get Package Name and Version
     *
     * @return String The Package Name and Version
     */
    public String getName()
    {
        return "Wit-Java ~ v1.0.0";
    }
}