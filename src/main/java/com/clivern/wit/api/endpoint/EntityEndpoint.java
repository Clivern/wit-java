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

    public static String GET = "GET_APPS";
    public static String GET_METHOD = "GET";
    public static String GET_ENDPOINT = "https://api.wit.ai/apps?v=20180225&offset={$OFFSET}&limit={$LIMIT}";

    public static String CREATE = "CREATE_APP";
    public static String CREATE_METHOD = "POST";
    public static String CREATE_ENDPOINT = "https://api.wit.ai/apps?v=20180225";

    public static String UPDATE = "UPDATE_APP";
    public static String UPDATE_METHOD = "PUT";
    public static String UPDATE_ENDPOINT = "https://api.wit.ai/apps/{$APP_ID}?v=20180225";

    public static String DELETE = "DELETE_APP";
    public static String DELETE_METHOD = "DELETE";
    public static String DELETE_ENDPOINT = "https://api.wit.ai/apps/{$APP_ID}?v=20180225";

}