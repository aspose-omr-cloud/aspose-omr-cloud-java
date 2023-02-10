/*
 * Copyright (C) 2022 Aspose Pty Ltd. All Rights Reserved.
 *
 * Licensed under the MIT License (hereinafter the "License");
 * you may not use this file except in accordance with the License.
 * You can obtain a copy of the License at
 *
 *      https://github.com/aspose-omr-cloud/aspose-omr-cloud-java/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package aspose.omr.cloud.sdk.api;

import aspose.omr.cloud.sdk.models.AuthResponse;
import aspose.omr.cloud.sdk.models.OMRResponse;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.representation.Form;

import java.util.*;

public class ApiClient {
    String basePath = "";

    String clientSecret = "";
    String clientId = "";
    String authPath = "";

    private Client client = Client.create();

    public ApiClient(String path, String authPath, String clientId, String clientSecret) {
        this.basePath = path;
        this.authPath = authPath;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }


    // We don't use a Map<String, String> for queryParams.
    // If collectionFormat is 'multi' a key might appear multiple times.
    private String GetToken(){
        Form form = new Form();
        form.add("grant_type", "client_credentials");
        form.add("client_id", this.clientId);
        form.add("client_secret", this.clientSecret);
        ClientResponse resp = client.resource(authPath)
                .post(ClientResponse.class,form);
        Gson json = new Gson();
        AuthResponse r = json.fromJson(resp.getEntity(String.class), AuthResponse.class);
        return r.getTokenType() + " " + r.getAccessToken();
    }
    ClientResponse invokeAPI(
            String path,
            String method,
            Map<String,String> queryParams,
            Object body,
            Map<String, String> headerParams,
            Map<String, String> formParams,
            String contentType,
            List<String> authNames) {
            String queryString = "?";
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                queryString += entry.getKey() + "=" + entry.getValue()+"&";
            }

            String url = basePath + path + queryString;

            if (headerParams.isEmpty()) {
                headerParams.put("Content-Type",contentType);
            }
            String token = GetToken();
            headerParams.put("Authorization",token);

            Gson g = new Gson();
            String msgBody = g.toJson(body);
            Iterator<Map.Entry<String,String>> it = headerParams.entrySet().iterator();
            Map.Entry<String,String> ob = it.next();
            Map.Entry<String,String> ob2 = it.next();

            switch (method) {
                case "POST":
                    return client.resource(url)
                            .header(ob.getKey(),ob.getValue())
                            .header(ob2.getKey(),ob2.getValue())
                            .post(ClientResponse.class,msgBody);
                case "PUT":
                    return client.resource(url)
                            .header(ob.getKey(),ob.getValue())
                            .header(ob2.getKey(),ob2.getValue())
                            .put(ClientResponse.class,msgBody);
                case "DELETE":
                    return client.resource(url)
                            .header(ob.getKey(),ob.getValue())
                            .header(ob2.getKey(),ob2.getValue())
                            .delete(ClientResponse.class);
                default:
                    return client.resource(url)
                            .header(ob.getKey(),ob.getValue())
                            .header(ob2.getKey(),ob2.getValue())
                            .get(ClientResponse.class);
            }

    }
}
