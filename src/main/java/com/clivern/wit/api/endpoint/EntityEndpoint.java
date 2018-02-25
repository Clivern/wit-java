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
package com.clivern.wit.api.endpoint;

/**
 * Entity Endpoints
 *
 * @since 1.0.0
 */
public class EntityEndpoint {

    public static String CONTENT_TYPE = "application/json";

    // https://wit.ai/docs/http/20170307#get--entities-link
    public static String GET_ENTITIES = "GET_ENTITIES";
    public static String GET_ENTITIES_METHOD = "GET";
    public static String GET_ENTITIES_ENDPOINT = "https://api.wit.ai/entities?v=20180225";

    // https://wit.ai/docs/http/20170307#post--entities-link
    // Data
    // id	required	string	ID or name of the requested entity
    // doc	optional	string	Short sentence describing this entity
    public static String CREATE_ENTITY = "CREATE_ENTITY";
    public static String CREATE_ENTITY_METHOD = "POST";
    public static String CREATE_ENTITY_ENDPOINT = "https://api.wit.ai/entities?v=20180225";

    // https://wit.ai/docs/http/20170307#get--entities-:entity-id-link
    public static String GET_ENTITY = "GET_ENTITY";
    public static String GET_ENTITY_METHOD = "GET";
    public static String GET_ENTITY_ENDPOINT = "https://api.wit.ai/entities/{$ENTITY_ID}?v=20180225";

    // https://wit.ai/docs/http/20170307#put--entities-:entity-id-link
    //id	optional	string	ID or name of the entity
    //doc	optional	string	Short sentence describing this entity
    //values	optional	array	Possible values if this is a keyword entity
    public static String UPDATE_ENTITY = "UPDATE_ENTITY";
    public static String UPDATE_ENTITY_METHOD = "PUT";
    public static String UPDATE_ENTITY_ENDPOINT = "https://api.wit.ai/entities/{$ENTITY_ID}?v=20180225";

    // https://wit.ai/docs/http/20170307#delete--entities-:entity-id-link
    public static String DELETE_ENTITY = "DELETE_ENTITY";
    public static String DELETE_ENTITY_METHOD = "DELETE";
    public static String DELETE_ENTITY_ENDPOINT = "https://api.wit.ai/entities/{$ENTITY_ID}?v=20180225";

    // https://wit.ai/docs/http/20170307#post--entities-:entity-id-values-link
    // Data
    //value	required	string	Canonical value of the entity
    //expressions	optional	array of string	Ways of expressing this canonical value
    //metadata	optional	string	Metadata you want to attach to this value, will be sent back in runtime.
    public static String CREATE_ENTITY_VALUE = "CREATE_ENTITY_VALUE";
    public static String CREATE_ENTITY_VALUE_METHOD = "POST";
    public static String CREATE_ENTITY_VALUE_ENDPOINT = "https://api.wit.ai/entities/{$ENTITY_ID}/values?v=20180225";

    // https://wit.ai/docs/http/20170307#delete--entities-:entity-id-values-link
    public static String DELETE_ENTITY_VALUE = "DELETE_ENTITY_VALUE";
    public static String DELETE_ENTITY_VALUE_METHOD = "DELETE";
    public static String DELETE_ENTITY_VALUE_ENDPOINT = "https://api.wit.ai/entities/{$ENTITY_ID}/values/{$ENTITY_VALUE}?v=20180225";

    // https://wit.ai/docs/http/20170307#post--entities-:entity-id-values-:value-id-expressions-link
    // Data
    // expression	required	string	new expression for the canonical value of the entity. Must be shorter than 256 characters.
    public static String CREATE_ENTITY_VALUE_EXPRESSION = "CREATE_ENTITY_VALUE_EXPRESSION";
    public static String CREATE_ENTITY_VALUE_EXPRESSION_METHOD = "POST";
    public static String CREATE_ENTITY_VALUE_EXPRESSION_ENDPOINT = "https://api.wit.ai/entities/{$ENTITY_ID}/values/{$ENTITY_VALUE}/expressions?v=20180225";

    // https://wit.ai/docs/http/20170307#delete--entities-:entity-id-values-:value-id-expressions-link
    public static String DELETE_ENTITY_VALUE_EXPRESSION = "DELETE_ENTITY_VALUE_EXPRESSION";
    public static String DELETE_ENTITY_VALUE_EXPRESSION_METHOD = "DELETE";
    public static String DELETE_ENTITY_VALUE_EXPRESSION_ENDPOINT = "https://api.wit.ai/entities/{$ENTITY_ID}/values/{$ENTITY_VALUE}/expressions/{$EXPRESSION_VALUE}?v=20180225";
}