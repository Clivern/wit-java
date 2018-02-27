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
import com.clivern.wit.api.endpoint.EntityEndpoint;

/**
 * Entity API
 *
 * @since 1.0.0
 */
public class Entity implements Contract {

    public String appId = "";
    public String accessToken = "";
    public String endpointName = "";
    public String contentType = EntityEndpoint.CONTENT_TYPE;
    public String url = "";
    public String method = "";
    public Map<String, String> data = new HashMap<String, String>();
    public String finalData = "";
    public Map<String, String> headers = new HashMap<String, String>();

    public String entityId = "";
    public String entityValue = "";
    public String expressionValue = "";


    /**
     * Class Constructor
     *
     * @param endpointName The Endpoint Name
     */
    public Entity(String endpointName)
    {
        this.endpointName = endpointName;
    }

    /**
     * Class Constructor
     *
     * @param endpointName The Endpoint Name
     * @param contentType The Content Type
     */
    public Entity(String endpointName, String contentType)
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
        if( this.endpointName.equals(EntityEndpoint.GET_ENTITIES) ){

            this.url = EntityEndpoint.GET_ENTITIES_ENDPOINT;
            this.method = EntityEndpoint.GET_ENTITIES_METHOD;

        }else if( this.endpointName.equals(EntityEndpoint.CREATE_ENTITY) ){

            this.url = EntityEndpoint.CREATE_ENTITY_ENDPOINT;
            this.method = EntityEndpoint.CREATE_ENTITY_METHOD;

        }else if( this.endpointName.equals(EntityEndpoint.GET_ENTITY) ){

            this.url = EntityEndpoint.GET_ENTITY_ENDPOINT;
            this.method = EntityEndpoint.GET_ENTITY_METHOD;

        }else if( this.endpointName.equals(EntityEndpoint.UPDATE_ENTITY) ){

            this.url = EntityEndpoint.UPDATE_ENTITY_ENDPOINT;
            this.method = EntityEndpoint.UPDATE_ENTITY_METHOD;

        }else if( this.endpointName.equals(EntityEndpoint.DELETE_ENTITY) ){

            this.url = EntityEndpoint.DELETE_ENTITY_ENDPOINT;
            this.method = EntityEndpoint.DELETE_ENTITY_METHOD;

        }else if( this.endpointName.equals(EntityEndpoint.CREATE_ENTITY_VALUE) ){

            this.url = EntityEndpoint.CREATE_ENTITY_VALUE_ENDPOINT;
            this.method = EntityEndpoint.CREATE_ENTITY_VALUE_METHOD;

        }else if( this.endpointName.equals(EntityEndpoint.DELETE_ENTITY_VALUE) ){

            this.url = EntityEndpoint.DELETE_ENTITY_VALUE_ENDPOINT;
            this.method = EntityEndpoint.DELETE_ENTITY_VALUE_METHOD;

        }else if( this.endpointName.equals(EntityEndpoint.CREATE_ENTITY_VALUE_EXPRESSION) ){

            this.url = EntityEndpoint.CREATE_ENTITY_VALUE_EXPRESSION_ENDPOINT;
            this.method = EntityEndpoint.CREATE_ENTITY_VALUE_EXPRESSION_METHOD;

        }else if( this.endpointName.equals(EntityEndpoint.DELETE_ENTITY_VALUE_EXPRESSION) ){

            this.url = EntityEndpoint.DELETE_ENTITY_VALUE_EXPRESSION_ENDPOINT;
            this.method = EntityEndpoint.DELETE_ENTITY_VALUE_EXPRESSION_METHOD;

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

                if( (value.indexOf("||") > 0) && (!value.startsWith("{")) && (!value.endsWith("}")) ){
                    value = "[\"" + value.replace("||", "\",\"") + "\"]";
                }else if( (value.indexOf("||") > 0) && (value.startsWith("{")) && (value.endsWith("}")) ){
                    value = "[" + value.replace("||", ",") + "]";
                }else{
                    value = "\"" + value + "\"";
                }

                this.finalData += "\"" + key + "\":" + value + ",";
            }
            this.finalData = finalData.replaceAll(",$", "");
            this.finalData += "}";
        }

        this.url = this.url.replace("{$ENTITY_ID}", this.entityId);
        this.url = this.url.replace("{$ENTITY_VALUE}", this.entityValue);
        this.url = this.url.replace("{$EXPRESSION_VALUE}", this.expressionValue);

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
     * Set the Entity ID
     *
     * @param entityId The Entity Id
     */
    public void setEntityId(String entityId)
    {
        this.entityId = entityId;
    }

    /**
     * Set The Entity Value
     *
     * @param entityValue The Entity Value
     */
    public void setEntityValue(String entityValue)
    {
        this.entityValue = entityValue;
    }

    /**
     * Set the Expression Value
     *
     * @param expressionValue The Expression Value
     */
    public void setExpressionValue(String expressionValue)
    {
        this.expressionValue = expressionValue;
    }


    /**
     * Set The Entity Id
     *
     * @param id The Entity id
     */
    public void setId(String id)
    {
        this.data.put("id", id);
    }

    /**
     * Set A Short sentence describing this entity
     *
     * @param doc A Short sentence describing this entity
     */
    public void setDoc(String doc)
    {
        this.data.put("doc", doc);
    }

   /**
     * Set Entity Values
     *
     * @param values Entity Values
     */
    public void setValues(String[] values)
    {
        this.data.put("values", String.join("||", values));
    }

   /**
     * Set Entity Value
     *
     * @param value Entity Value
     */
    public void setValue(String value)
    {
        this.data.put("value", value);
    }

   /**
     * Set Entity Value Expressions
     *
     * @param expressions Entity Value Expressions
     */
    public void setExpressions(String[] expressions)
    {
        this.data.put("expressions", String.join("||", expressions));
    }

   /**
     * Set A metadata to attach to Entity value
     *
     * @param metadata A metadata value to be attached to Entity value
     */
    public void setMetadata(String metadata)
    {
        this.data.put("metadata", metadata);
    }

   /**
     * Set Entity Value Expression
     *
     * @param expression Entity Value Expression
     */
    public void setExpression(String expression)
    {
        this.data.put("expression", expression);
    }

    /**
     * Get the Entity ID
     *
     * @return String The Entity Id
     */
    public String getEntityId()
    {
        return this.entityId;
    }

    /**
     * Get The Entity Value
     *
     * @return String The Entity Value
     */
    public String getEntityValue()
    {
        return this.entityValue;
    }

    /**
     * Get the Expression Value
     *
     * @return String The Expression Value
     */
    public String getExpressionValue()
    {
        return this.expressionValue;
    }

    /**
     * Get The Entity Id
     *
     * @return String The Entity id
     */
    public String getId()
    {
        return (this.data.containsKey("id")) ? this.data.get("id") : "";
    }

    /**
     * Get A Short sentence describing this entity
     *
     * @return String A Short sentence describing this entity
     */
    public String getDoc()
    {
        return (this.data.containsKey("doc")) ? this.data.get("doc") : "";
    }

   /**
     * Get Entity Values
     *
     * @return ArrayList Entity Values
     */
    public String[] getValues()
    {
        return (this.data.containsKey("values")) ? this.data.get("values").split("||") : new String[]{};
    }

   /**
     * Get Entity Value
     *
     * @return String Entity Value
     */
    public String getValue()
    {
        return (this.data.containsKey("value")) ? this.data.get("value") : "";
    }

   /**
     * Get Entity Value Expressions
     *
     * @return ArrayList Entity Value Expressions
     */
    public String[] getExpressions()
    {
        return (this.data.containsKey("expressions")) ? this.data.get("expressions").split("||") : new String[]{};
    }

   /**
     * Get A metadata to attach to Entity value
     *
     * @return String A metadata value to be attached to Entity value
     */
    public String getMetadata()
    {
        return (this.data.containsKey("metadata")) ? this.data.get("metadata") : "";
    }

   /**
     * Get Entity Value Expression
     *
     * @return String Entity Value Expression
     */
    public String getExpression()
    {
        return (this.data.containsKey("expression")) ? this.data.get("expression") : "";
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

                if( value.indexOf("||") > 0 ){
                    value = "[\"" + value.replace("||", "\",\"") + "\"]";
                }

                debug += " -H \"" + key + ": " + value + "\"";
            }
        }

        if( !this.getData().equals("") ){
            debug += " -d '" + this.getData() + "'";
        }

        return debug;
    }
}