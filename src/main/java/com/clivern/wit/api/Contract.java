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

import com.clivern.wit.exception.DataNotFound;
import com.clivern.wit.exception.DataNotValid;
import java.util.Map;

/**
 * API Contract
 *
 * @since 1.0.0
 */
public interface Contract {
    /**
     * Configure The API Call Info
     *
     * @return Boolean Whether Call Will be Valid or Not
     * @throws DataNotValid Invalid Data Within The Request
     * @throws DataNotFound Some Data are Missing
     */
    public Boolean config() throws DataNotValid, DataNotFound;

    /**
     * Set App ID
     *
     * @param appId Application ID
     */
    public void setAppId(String appId);

    /**
     * Set Access Token
     *
     * @param accessToken Access Token
     */
    public void setAccessToken(String accessToken);

    /**
     * Get App ID
     *
     * @return String The App ID
     */
    public String getAppId();

    /**
     * Get Access Token
     *
     * @return String The Access Token
     */
    public String getAccessToken();

    /**
     * Get The URL
     *
     * @return String The URL
     */
    public String getUrl();

    /**
     * Get The Method
     *
     * @return String The Method
     */
    public String getMethod();

    /**
     * Get The Headers
     *
     * @return Map The Headers
     */
    public Map<String, String> getHeaders();

    /**
     * Get The Data to Send
     *
     * @return String The Data to Send
     */
    public String getData();

    /**
     * Debug The Request
     *
     * @return String The debug data
     */
    public String debug();
}
