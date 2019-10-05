/*
 * Copyright (C) 2019 Clivern <http://clivern.com>
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
package com.clivern.wit.util;

import com.clivern.wit.exception.DataNotFound;
import java.io.IOException;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.pmw.tinylog.Logger;

/**
 * HTTP Utils Class
 *
 * @since 1.0.0
 */
public class Http {

    protected Boolean isOk;
    protected Response response;
    protected String responseStr;
    protected String url;
    protected String method;
    protected String body;
    protected Map<String, String> headers;

    /**
     * Class Constructor
     *
     * @param url The API Endpoint URL
     * @param method The Call Method
     * @param headers The List of Headers
     */
    public Http(String url, String method, Map<String, String> headers) {
        this.url = url;
        this.method = method;
        this.headers = headers;
    }

    /**
     * Class Constructor
     *
     * @param url The API Endpoint URL
     * @param method The Call Method
     * @param headers The List of Headers
     * @param body The request body
     */
    public Http(String url, String method, Map<String, String> headers, String body) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.body = body;
    }

    /**
     * Execute The API Call
     *
     * @return String The Response
     * @throws DataNotFound some data missing
     * @throws IOException Unable to do the call
     */
    public String execute() throws DataNotFound, IOException {
        if (!this.headers.containsKey("Content-Type")
                || !this.headers.containsKey("Authorization")) {
            Logger.error("Error! Content-Type and Authorization required to make a request.");
            throw new DataNotFound(
                    "Error! Content-Type and Authorization required to make a request.");
        }

        this.url = this.url.replaceAll(" ", "%20");

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse(this.headers.get("Content-Type"));

        if (this.method.equals("GET")) {

            Request request =
                    new Request.Builder()
                            .url(this.url)
                            .get()
                            .addHeader("Authorization", this.headers.get("Authorization"))
                            .addHeader("Content-Type", this.headers.get("Content-Type"))
                            .build();
            Response response = client.newCall(request).execute();
            return response.body().string();

        } else if (this.method.equals("POST")) {

            RequestBody body = RequestBody.create(mediaType, this.body);
            Request request =
                    new Request.Builder()
                            .url(this.url)
                            .post(body)
                            .addHeader("Authorization", this.headers.get("Authorization"))
                            .addHeader("Content-Type", this.headers.get("Content-Type"))
                            .build();
            Response response = client.newCall(request).execute();
            return response.body().string();

        } else if (this.method.equals("PUT")) {

            RequestBody body = RequestBody.create(mediaType, this.body);
            Request request =
                    new Request.Builder()
                            .url(this.url)
                            .put(body)
                            .addHeader("Authorization", this.headers.get("Authorization"))
                            .addHeader("Content-Type", this.headers.get("Content-Type"))
                            .build();
            Response response = client.newCall(request).execute();
            return response.body().string();

        } else if (this.method.equals("DELETE")) {

            Request request =
                    new Request.Builder()
                            .url(this.url)
                            .delete(null)
                            .addHeader("Authorization", this.headers.get("Authorization"))
                            .addHeader("Content-Type", this.headers.get("Content-Type"))
                            .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        return "";
    }
}
