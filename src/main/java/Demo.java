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
import aspose.omr.cloud.sdk.api.RecognizeTemplate;
import aspose.omr.cloud.sdk.models.*;
import com.google.gson.Gson;
import com.sun.jersey.core.util.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Demo {
    private static String configFileName = "test_config.json";

    /// <summary>
    /// Name of the sub-module with demo data and the configuration file
    /// </summary>
    private static String demoDataSubmoduleName = "aspose-omr-cloud-demo-data";

    /// <summary>
    /// File names for template sources, printable form, recognition pattern and results
    /// </summary>
    private static String templateGenerationFileName = "Aspose_test.txt";
    private static String templateImageName = "Aspose_test.jpg";
    private static String omrFileName = "Aspose_test.omr";
    private static String resultFileName = "Aspose_test.csv";
    private static List<String> templateLogosImagesNames = Arrays.asList(new String[]{"logo1.jpg", "logo2.png"});

    private static ApiClient apiClient ;
    /// <summary>
    /// Declare an object to hold an instance of GenerateTemplateApi class
    /// </summary>
    private static GenerateTemplate generateApi ;

    /// <summary>
    /// Declare an object to hold an instance of RecognizeTemplateApi class
    /// </summary>
    private static RecognizeTemplate recognizeApi ;

    /// <summary>
    /// Declare an object to hold the parsed configuration data
    /// </summary>
    private static Config config = new Config();
    private static void init()  {
        /// <summary>
        /// Get the parent of working directory of the application   ??? так норм?
        /// </summary>
        File currentDirParent = Paths.get("").toAbsolutePath().getParent().toFile();
        /// <summary>
        /// Get the absolute path to the configuration file
        /// </summary>
        File configFilePath = Paths.get(currentDirParent.getAbsolutePath().toString(),demoDataSubmoduleName,configFileName).toFile();

        /// <summary>
        /// Parse the configuration file
        /// </summary>
        Gson g = new Gson();
        try {
            config = g.fromJson(FileUtils.readFileToString(configFilePath,"UTF-8"),Config.class);
        }
        catch (Exception e){
            System.out.println("Unable to find file");
        }

        config.setDataFolder(
                Paths.get(currentDirParent.getAbsolutePath().toString(),demoDataSubmoduleName, config.getDataFolder()).toAbsolutePath().toString());
        config.setResultFolder(
                Paths.get(currentDirParent.getAbsolutePath().toString(),demoDataSubmoduleName, config.getResultFolder()).toAbsolutePath().toString());

        /// <summary>
        /// TODO ??? (Base)
        /// </summary>
        apiClient = new ApiClient(config.getBasePath(), config.getAuthUrl(), config.getClientId(), config.getClientSecret());

        /// <summary>
        /// Create an instance of GenerateTemplateApi class
        /// </summary>
        generateApi = new GenerateTemplate(apiClient);

        /// <summary>
        /// Create an instance of RecognizeTemplateApi class
        /// </summary>
        recognizeApi = new RecognizeTemplate(apiClient);
    }
    public static void main(String[] args) {
        init();

        /// <summary>
        /// STEP 1: Queue the template source file for generation
        /// </summary>
        System.out.println("\t\tGenerate template...");
        String templateId = generateTemplate();

        /// <summary>
        /// STEP 2: Fetch generated printable form and recognition pattern
        /// </summary>
        System.out.println("\t\tGet generation result by ID...");
        OMRResponse generationResult = getGenerationResultById(templateId);

        /// <summary>
        /// STEP 3: Save the printable form and recognition pattern into result_folder
        /// </summary>
        System.out.println("\t\tSave generation result...");
        saveGenerationResult(generationResult);

        /// <summary>
        /// STEP 4: Queue the scan / photo of the filled form for recognition
        /// </summary>
        System.out.println("\t\tRecognize image...");
        String recognizeTemplateId = recognizeImage(
                Paths.get(config.getDataFolder(), templateImageName).toAbsolutePath().toFile(),
                Paths.get(config.getResultFolder(), omrFileName).toAbsolutePath().toFile());

        /// <summary>
        /// STEP 5: Fetch recognition results
        /// </summary>
        System.out.println("\t\tGet recognition result by ID...");
        OMRResponse recognitionResponse =
                getRecognitionResultById(recognizeTemplateId);

        /// <summary>
        /// STEP 6: Save the recognition results into result_folder
        /// </summary>
        System.out.println("\t\tSave recognition result...");
        saveRecognitionResult(recognitionResponse);
    }


    /// <summary>
    /// Generate the template from the provided sources
    /// </summary>
    /// <returns>Response from generation queue</returns>
    private static String generateTemplate() {
        File markupFile = Paths.get(config.getDataFolder(),templateGenerationFileName).toFile();
        try {
            byte[] markupFileB = FileUtils.readFileToByteArray(markupFile);



        Map<String, String> images = new HashMap<>();
        for (int i = 0; i < templateLogosImagesNames.size(); i++) {
            try {
                byte[] f = FileUtils.readFileToByteArray(Paths.get(config.getDataFolder(),templateLogosImagesNames.get(i)).toFile());
                String logo = new String(Base64.encode(f));
                images.put(templateLogosImagesNames.get(i),logo);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

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

        OmrGenerateTask task = new OmrGenerateTask();
        task.setMarkupFile(new String(Base64.encode(markupFileB)));
        task.settings(settings);
        task.images(images);

        return generateApi.postGenerateTemplate(task);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "";
        }
    }

    /// <summary>
    /// Fetch generated printable form and recognition pattern by ID
    /// If the request is still being processed, wait for 5 seconds and try again
    /// </summary>
    /// <param name="templateId">Generated template ID</param>
    /// <returns>OMRResponse</returns>
    private static OMRResponse getGenerationResultById(String templateId) {
        OMRResponse generationResult = new OMRResponse();
        try {
            while (true) {
                generationResult = generateApi.getGenerateTemplate(templateId);

                if (generationResult.getResponseStatusCode().getValue() == "Ok") {
                    break;
                }

                System.out.println("Wait, please! Your request is still being processed");
                TimeUnit.SECONDS.sleep(5);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return generationResult;
    }

    /// <summary>
    /// Save the printable form and recognition pattern
    /// </summary>
    /// <param name="generationResult">Response from GetGenerationResultById method</param>
    private static void saveGenerationResult(OMRResponse generationResult) {
        try {
            if (generationResult.getError() == null) {
                for (int i = 0; i < generationResult.getResults().size(); i++) {
                    String type = generationResult.getResults().get(i).getType();
                    String name = "Aspose_test" + "." + type.toLowerCase();
                    File path = Paths.get(config.getResultFolder(), name).toAbsolutePath().toFile();
                    FileUtils.writeByteArrayToFile(path, Base64.decode(generationResult.getResults().get(i).getData()));
                }
            } else {
                System.out.println("Error :" + generationResult.getError().toString());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /// <summary>
    /// Recognize the image of the filled form
    /// </summary>
    /// <param name="imagePath">Path to the scanned or photographed image of the filled form</param>
    /// <param name="omrFilePath">Path to the recognition pattern file (.OMR)</param>
    /// <returns>Response from recognition queue</returns>
    private static String recognizeImage(File imagePath, File omrFilePath) {
        try {
            // get the omr file
            byte[] omrFile = FileUtils.readFileToByteArray(omrFilePath);
            // set up recognition threshold
            int recognitionThreshold = 30;

            // get the filled template
            byte[] image = FileUtils.readFileToByteArray(imagePath);
            List<String> images = new ArrayList<>();
            images.add(new String(Base64.encode(image)));
            // Set up request
            OmrRecognizeTask task = new OmrRecognizeTask();
            task.setOmrFile(new String(Base64.encode(omrFile)));
            task.setRecognitionThreshold(recognitionThreshold);
            task.setImages(images);

            // call image recognition
            return recognizeApi.postRecognizeTemplate(task);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "";
    }

    /// <summary>
    /// Fetch recognition result by ID
    /// If the request is still being processed, wait for 5 seconds and try again
    /// </summary>
    /// <param name="templateId">Template ID</param>
    /// <returns>OMRResponse</returns>
    private static OMRResponse getRecognitionResultById(String templateId) {
        OMRResponse recognitionResult = new OMRResponse();
        try {
            while (true) {
                recognitionResult = recognizeApi.getRecognizeTemplate(templateId);

                if (recognitionResult.getResponseStatusCode().getValue() == "Ok") {
                    break;
                }

                System.out.println("Wait, please! Your request is still being processed");
                TimeUnit.SECONDS.sleep(5);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return recognitionResult;
    }

    /// <summary>
    /// Save the recognition results
    /// </summary>
    /// <param name="recognitionResult">Response from GetRecognitionResultById method</param>
    private static void saveRecognitionResult(OMRResponse recognitionResult) {
        try {
            if (recognitionResult.getError() == null) {
                File path = Paths.get(config.getResultFolder(), resultFileName).toAbsolutePath().toFile();
                FileUtils.writeByteArrayToFile(path,Base64.decode(recognitionResult.getResults().get(0).getData()));
            } else {
                System.out.println("Error :" + recognitionResult.getError().toString());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
