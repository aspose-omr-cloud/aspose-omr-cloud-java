/*
 * Copyright (c) 2018 Aspose Pty Ltd. All Rights Reserved.
 *
 * Licensed under the MIT (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/aspose-omr-cloud/aspose-omr-cloud-java/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aspose.omr.cloud.demo;

import com.aspose.storage.api.StorageApi;
import com.aspose.omr.client.ApiException;
import com.aspose.omr.api.OmrApi;
import com.aspose.omr.model.FileInfo;
import com.aspose.omr.model.OMRFunctionParam;
import com.aspose.omr.model.OMRResponse;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.migcomponents.migbase64.Base64;

import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class App
{
	public class TestConfig {
        String app_key;
        String app_sid;
        String base_path;
        String data_folder;
    }
    public App() throws Exception {
        TestConfig config = loadConfig();
		boolean debugging = false;
		
        api = new OmrApi(config.app_key, config.app_sid
                , config.base_path);
        api.getApiClient().setDebugging(debugging);

        storageApi = new StorageApi(config.base_path, config.app_key, config.app_sid);

		System.out.println("Using " + config.base_path + " app_sid " + config.app_sid);
    }

    public class OmrFile{
        public String Name;
        public int Size;
        public String Data;
        public OmrFile(){
            Name = "";
            Data = "";
            Size = 0;
        }
    }
	
    public class CorrectionParameter {
        public java.util.List<OmrFile> Files;
        public CorrectionParameter() {
            Files = new ArrayList<>();
        }
    }

    private final OmrApi api;
	private final StorageApi storageApi;
    /// File with dictionary for configuration in JSON format
    /// The config file should be looked like:
    /// {
    ///     "app_key"  : "xxxxx",
    ///     "app_sid"   : "xxx-xxx-xxx-xxx-xxx",
    ///     "base_path" : "https://api.aspose.cloud/v1.1",
    ///     "data_folder" : "Data"
    /// }
    /// Provide your own app_key and app_sid, which you can receive by registering at Aspose Cloud Dashboard (https://dashboard.aspose.cloud/)
    private final String configFileName = "test_config.json";
    private final String demoDataSubmoduleName = "aspose-omr-cloud-demo-data";
	private final String LogosFolderName = "Logos";
	private final String[] LogoFiles = {"logo1.jpg", "logo2.png"};
    String dataFolderPath;
    static String tmpFolder = System.getProperty("java.io.tmpdir");

    String dataFolder() {
        return dataFolderPath;
    }
	
	public static void log(String message) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		System.out.println(dateFormat.format(new Date()) + " " + message);
	}
	
	public void UploadFile(Path inputFile, String destinationFolder) throws Exception{
		String destinationPath = inputFile.getFileName().toString();
		if (!destinationFolder.isEmpty())
			destinationPath = destinationFolder + "/" + destinationPath;
		log("Uploading " + inputFile.toString() + " to " + destinationPath);
		storageApi.PutCreate(destinationPath, null, null, inputFile.toFile());
	}
	
	public boolean FileExists(String path) throws ApiException {
        com.aspose.storage.model.FileExistResponse fileExistsResponse = storageApi.GetIsExist(path, null, null);

        return fileExistsResponse != null && fileExistsResponse.getFileExist().getIsExist();
	}
	
	public void UploadDemoFiles() throws Exception {
		// check if folder Logos exists
		if(!FileExists(LogosFolderName)){
			// create Folder Logos
			storageApi.PutCreateFolder(LogosFolderName, null, null);
        };
		// Upload files
		for(String logoFileName : LogoFiles)
		{
			if (!FileExists(LogosFolderName + "/" + logoFileName)) 
				UploadFile(Paths.get(dataFolder(), logoFileName), LogosFolderName);
			else log("File " + logoFileName + " already exist");
		}
		
	}
	
    public String getFileExt(Path file) {
		String fileName = file.getFileName().toString();
        if (fileName.indexOf(".") > 0)
            fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileName;
	}
	
	String PackTemplate(String templateName, byte[] templateData) throws Exception{
        CorrectionParameter parameter = new CorrectionParameter();
        OmrFile file = new OmrFile();
        file.Name = templateName;
        file.Data = Base64.encodeToString(templateData, false);
        file.Size = file.Data.length();

        parameter.Files.add(file);

        Gson gson = new GsonBuilder().create();
        return gson.toJson(parameter, CorrectionParameter.class);
    }

	private void CheckResult(OMRResponse response) throws Exception{
		if (response.getErrorCode() != 0)
			throw new Exception(response.getErrorText());
	}

    TestConfig loadConfig() throws Exception {
        TestConfig result = new TestConfig();

        String configFileRelativePath = Paths.get(demoDataSubmoduleName, configFileName).toString();
        String current = Paths.get("").toRealPath().toString();
        String prevFolder = "";
        while (!Files.exists(Paths.get(current, configFileRelativePath)) && !current.equals(prevFolder)) {
            prevFolder = current;
            current = Paths.get(current, "..").toRealPath().toString();
        }
        if (!Files.exists(Paths.get(current, configFileRelativePath)))
            throw new Exception("Config file not found '" + configFileName + "'");
        Path configFilePath = Paths.get(current, configFileRelativePath);
        String content = new String(Files.readAllBytes(configFilePath));
        Gson gson = new GsonBuilder().create();
        result = gson.fromJson(content, TestConfig.class);

        dataFolderPath = Paths.get(configFilePath.getParent().toString(), result.data_folder).toString();

        return result;
    }

    private Path saveResponseFile(FileInfo info, String destinationFolder) throws Exception{
        Path fileName = Paths.get(destinationFolder, info.getName()).toAbsolutePath();
        Files.write(fileName, info.getData(), StandardOpenOption.CREATE);
        log("Saved " + fileName);
		return fileName;
    }


    public OMRResponse GenerateTemplate(Path templateFile) throws Exception{
		String templateName = templateFile.getFileName().toString();
		
		UploadFile(templateFile, "");
        
        OMRFunctionParam param = new OMRFunctionParam();
        param.setFunctionParam("{ \"ExtraStoragePath\":\"" + LogosFolderName + "\"}");
        OMRResponse response = api.postRunOmrTask(templateName, "GenerateTemplate", param, null, null);
        return response;
    }

    public OMRResponse CorrectTemplate(Path templateFile, Path imageFile) throws Exception{
        OMRFunctionParam param = new OMRFunctionParam();
        String packedTemplate = PackTemplate(templateFile.getFileName().toString(), Files.readAllBytes(templateFile));
        param.setFunctionParam(packedTemplate);
		
		UploadFile(imageFile, "");
        
		return api.postRunOmrTask(imageFile.getFileName().toString(), "CorrectTemplate", param, null, null);
    }

    public OMRResponse FinalizeTemplate(String templateId, Path templateFile) throws Exception{
        OMRFunctionParam param = new OMRFunctionParam();
        param.setFunctionParam(templateId);
		
		UploadFile(templateFile, "");
        
		return api.postRunOmrTask(templateFile.getFileName().toString(), "FinalizeTemplate", param, null, null);
    }

    public OMRResponse RecognizeImage(String templateId, Path imageFile) throws Exception{
        OMRFunctionParam param = new OMRFunctionParam();
        param.setFunctionParam(templateId);
		
		UploadFile(imageFile, "");
        
        OMRResponse response = api.postRunOmrTask(imageFile.getFileName().toString(), "RecognizeImage", param, null, null);
        return response;
    }
	
	public void Demo() throws Exception {

		log("GenerateTemplate ...");
		Path templateFile = Paths.get("null"), imageFile = Paths.get("null"), correctedTemplateFile = Paths.get("null");
		OMRResponse generateResponse = GenerateTemplate(Paths.get(dataFolder(), "Aspose_test.txt"));
		CheckResult(generateResponse);
		
		for ( FileInfo info : generateResponse.getPayload().getResult().getResponseFiles()) {
			Path tmp = saveResponseFile(info, tmpFolder);
			if (getFileExt(tmp).equals("omr"))
				templateFile = tmp;
			else if (getFileExt(tmp).equals("png"))
				imageFile = tmp;
		}
		
		log("CorrectTemplate ...");
		OMRResponse correctResponse = CorrectTemplate(templateFile, imageFile);
		CheckResult(correctResponse);
		
		for ( FileInfo info : correctResponse.getPayload().getResult().getResponseFiles()) {
			Path tmp = saveResponseFile(info, tmpFolder);
			if (getFileExt(tmp).equals("omrcr"))
				correctedTemplateFile = tmp;
		}
		
		log("FinalizeTemplate ...");
		String templateId = correctResponse.getPayload().getResult().getTemplateId();
		OMRResponse finalizeResponse = FinalizeTemplate(templateId, correctedTemplateFile);
		CheckResult(finalizeResponse);
		
		log("RecognizeImage ...");
		OMRResponse recognizeResponse = RecognizeImage(templateId, Paths.get(dataFolder(), "photo.jpg"));
        CheckResult(recognizeResponse);
		
		log("------ R E S U L T ------");
		for ( FileInfo info : recognizeResponse.getPayload().getResult().getResponseFiles()) {
			Path tmp = saveResponseFile(info, tmpFolder);
			log("Output file " + tmp.toString());
		}
	}
    public static void main( String[] args ) throws Exception
    {
        App app = new App();
		log("Uploading demo files ...");
		app.UploadDemoFiles();
		app.Demo();
		System.out.println("DONE");
    }
}
