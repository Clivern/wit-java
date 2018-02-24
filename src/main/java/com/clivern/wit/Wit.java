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
import com.clivern.wit.api.Contract;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.pmw.tinylog.Logger;
import com.clivern.wit.exception.DataNotFound;
import com.clivern.wit.exception.DataNotValid;

/**
 * Wit Base Class
 *
 * @author A.F
 * @since 1.0.0
 */
public class Wit {

    public Wit()
    {

    }

    public String getName()
    {
        return "Wit";
    }

    public Boolean send(Contract item) throws UnirestException, DataNotValid, DataNotFound
    {
        item.config();
        HashMap<String,String> headers = (HashMap<String,String>) item.getHeaders();
        Logger.info(item.debug());

        if( item.getMethod().equals("GET") ){

            HttpResponse<String> responseObj = Unirest.get(item.getUrl()).header("Authorization", headers.get("Authorization")).header("Content-Type", headers.get("Content-Type")).asString();
            Logger.info(responseObj.getBody());

        }else if( item.getMethod().equals("POST") ){

            HttpResponse<String> responseObj = Unirest.post(item.getUrl()).header("Authorization", headers.get("Authorization")).header("Content-Type", headers.get("Content-Type")).body(item.getData()).asString();
            Logger.info(responseObj.getBody());

        }else if( item.getMethod().equals("PUT") ){

            HttpResponse<String> responseObj = Unirest.put(item.getUrl()).header("Authorization", headers.get("Authorization")).header("Content-Type", headers.get("Content-Type")).body(item.getData()).asString();
            Logger.info(responseObj.getBody());

        }else if( item.getMethod().equals("DELETE") ){

            HttpResponse<String> responseObj = Unirest.delete(item.getUrl()).header("Authorization", headers.get("Authorization")).header("Content-Type", headers.get("Content-Type")).body(item.getData()).asString();
            Logger.info(responseObj.getBody());

        }else{
            return false;
        }

        return true;
    }
}