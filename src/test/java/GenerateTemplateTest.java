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
import aspose.omr.cloud.sdk.api.GenerateTemplate;
import aspose.omr.cloud.sdk.models.*;
import com.sun.jersey.core.util.Base64;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GenerateTemplateTest {

    @Test
    public void GenerateTemplateTest() throws Exception {
        try {
            Common common = new Common();
            common.init();
            String url = common.GetURL();

            ApiClient apiClient = new ApiClient(url, common.getAuthPath(),common.getClientId(),common.getClientSecret());
            GenerateTemplate api = new GenerateTemplate(apiClient);

            List<String> templateLogosImagesNames = Arrays.asList(new String[]{"logo1.jpg", "logo2.png"});
            File markupFile = Paths.get(common.GetDataFolderDir(),"Aspose_test.txt").toFile();

            PageSettings settings = new PageSettings();
            settings.fontFamily("Segoe UI");
            settings.fontStyle(FontStyle.REGULAR);
            settings.fontSize(12);
            settings.bubbleColor(Color.BLACK);
            settings.paperSize(PaperSize.A4);
            settings.pageMarginLeft(210);
            settings.orientation(Orientation.VERTICAL);
            settings.bubbleSize(BubbleSize.NORMAL);
            settings.outputFormat(FileExtension.PNG);

            Map<String, String> images = new HashMap<>();

            byte[] markupFileB = FileUtils.readFileToByteArray(markupFile);
            for (int i = 0; i < templateLogosImagesNames.size(); i++) {
                try {
                    byte[] f = FileUtils.readFileToByteArray(Paths.get(common.GetDataFolderDir(),templateLogosImagesNames.get(i)).toFile());
                    String logo = new String(Base64.encode(f));
                    images.put(templateLogosImagesNames.get(i),logo);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
            OmrGenerateTask task = new OmrGenerateTask();
            task.setMarkupFile(new String(Base64.encode(markupFileB)));
            task.settings(settings);
            task.images(images);

            String templateId = api.postGenerateTemplate(task);

            Assert.assertEquals(templateId.isEmpty(),false);

            OMRResponse generationResult = new OMRResponse();

            while (true) {
                generationResult = api.getGenerateTemplate(templateId);

                if (generationResult.getResponseStatusCode().getValue() == "Ok") {
                    break;
                }

                System.out.println("Wait, please! Your request is still being processed");
                TimeUnit.SECONDS.sleep(5);
            }

            Assert.assertEquals(generationResult.getResponseStatusCode().getValue(),"Ok");
            Assert.assertEquals(generationResult.getResults().size()>1,true);
            Assert.assertEquals(generationResult.getError(),null);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
