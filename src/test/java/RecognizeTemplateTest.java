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

import aspose.omr.cloud.sdk.api.ApiClient;
import aspose.omr.cloud.sdk.api.RecognizeTemplate;
import aspose.omr.cloud.sdk.models.OMRResponse;
import aspose.omr.cloud.sdk.models.OmrRecognizeTask;
import com.sun.jersey.core.util.Base64;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RecognizeTemplateTest {

    @Test
    public void RecognizeTemplateTest() throws Exception {
        try {
            Common common = new Common();
            common.init();
            String url = common.GetURL();

            ApiClient apiClient = new ApiClient(url, common.getAuthPath(),common.getClientId(),common.getClientSecret());
            RecognizeTemplate api = new RecognizeTemplate(apiClient);

            // get the omr file
            byte[] omrFile = FileUtils.readFileToByteArray(Paths.get(common.GetResultFolderDir(), "Aspose_test.omr").toAbsolutePath().toFile());
            // set up recognition threshold
            int recognitionThreshold = 30;

            // get the filled template
            byte[] image = FileUtils.readFileToByteArray(Paths.get(common.GetDataFolderDir(), "Aspose_test.jpg").toAbsolutePath().toFile());
            List<String> images = new ArrayList<>();
            images.add(new String(Base64.encode(image)));
            // Set up request
            OmrRecognizeTask task = new OmrRecognizeTask();
            task.setOmrFile(new String(Base64.encode(omrFile)));
            task.setRecognitionThreshold(recognitionThreshold);
            task.setImages(images);

            String templateId = api.postRecognizeTemplate(task);

            Assert.assertEquals(templateId.isEmpty(),false);

            OMRResponse recognitionResult = new OMRResponse();

            while (true) {
                recognitionResult = api.getRecognizeTemplate(templateId);

                if (recognitionResult.getResponseStatusCode().getValue() == "Ok") {
                    break;
                }

                System.out.println("Wait, please! Your request is still being processed");
                TimeUnit.SECONDS.sleep(5);
            }

            Assert.assertEquals(recognitionResult.getResponseStatusCode().getValue(),"Ok");
            Assert.assertEquals(recognitionResult.getResults().size()>0,true);
            Assert.assertEquals(recognitionResult.getError(),null);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}