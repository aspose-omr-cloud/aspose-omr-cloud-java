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

import aspose.omr.cloud.sdk.models.OMRResponse;
import aspose.omr.cloud.sdk.models.OmrRecognizeTask;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecognizeTemplate {
    ApiClient apiClient;

    public RecognizeTemplate(ApiClient apiClient){
        this.apiClient = apiClient;
    }

    void cancelRecognizeTemplate(String id) {
        Object postBody = null;

        // create path and map variables
        String path = "RecognizeTemplate/CancelRecognizeTemplate".replaceAll("format", "json");

        // query params
        Map<String, String> queryParams = new HashMap<>();
        Map<String, String> headerParams = new HashMap<>();
        Map<String, String> formParams = new HashMap<>();
        if (id != null) {
            queryParams.put( "id", id);
        }

        List<String> contentTypes = new ArrayList<>();

        String contentType =
                !contentTypes.isEmpty() ? contentTypes.get(0) : "application/json";
        List<String> authNames = new ArrayList<>();


        ClientResponse response = apiClient.invokeAPI(path, "DELETE", queryParams,
                postBody, headerParams, formParams, contentType, authNames);

        if (response.getStatus() >= 400) {
            System.out.println(response.getStatus());
        } else if (response.getStatus() == 200) {
            return;
        } else {
            return;
        }
    }

    public OMRResponse getRecognizeTemplate(String id) {
        Object postBody = null;

        // create path and map variables
        String path = "RecognizeTemplate/GetRecognizeTemplate".replaceAll("format", "json");

        // query params
        Map<String, String> queryParams = new HashMap<>();
        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("Accept","application/json");

        Map<String, String> formParams = new HashMap<>();
        if (id != null) {
            queryParams.put("id", id);
        }

        List<String> contentTypes = new ArrayList<>();

        String contentType =
                !contentTypes.isEmpty() ? contentTypes.get(0) : "application/json";
        List<String> authNames = new ArrayList<>();

        ClientResponse response = apiClient.invokeAPI(path, "GET", queryParams,
                postBody, headerParams, formParams, contentType, authNames);
        Gson json = new Gson();
        OMRResponse r = json.fromJson(response.getEntity(String.class), OMRResponse.class);

        if (response.getStatus() >= 400) {
            System.out.println(response.getStatus());
        } else if (response.getStatus()==200) {
            return r;
            //apiClient.deserialize(response.body, 'OMRResponse'));
        }
        return null;
    }

    public String postRecognizeTemplate(OmrRecognizeTask body) {
        Object postBody = body;

        // create path and map variables
        String path = "RecognizeTemplate/PostRecognizeTemplate".replaceAll("format", "json");

        // query params
        Map<String, String> queryParams = new HashMap<>();
        Map<String, String> headerParams = new HashMap<>();

        List<String> contentTypes = new ArrayList<>();
        contentTypes.add("application/json");

        String contentType =
                !contentTypes.isEmpty() ? contentTypes.get(0) : "application/json";
        List<String> authNames = new ArrayList<>();

        Map<String, String> formParams = new HashMap<>();

        ClientResponse response = apiClient.invokeAPI(path, "POST", queryParams,
                postBody, headerParams, formParams, contentType, authNames);

        String result = "";
        if (response.getStatus() >= 400) {
            System.out.println(response.getStatus());
        } else if (response.getStatus()==200) {
            result = response.getEntity(String.class);
        }
        return result;
    }
}
