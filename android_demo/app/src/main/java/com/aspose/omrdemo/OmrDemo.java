package com.aspose.omrdemo;

import android.provider.MediaStore;

import com.aspose.omr.api.OmrApi;
import com.aspose.omr.api.OmrStorageApi;
import com.aspose.omr.client.ApiException;
import com.aspose.omr.model.FileInfo;
import com.aspose.omr.model.OMRFunctionParam;
import com.aspose.omr.model.OMRResponse;
import com.aspose.omr.model.PathExistsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.migcomponents.migbase64.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class OmrDemo {

    public interface ILogger {
        void log(String...strings);
    }

    OmrStorageApi storageApi;
    OmrApi omrApi;
    private final String LogosFolderName = "Logos";
    private final String[] LogoFiles = {"logo1.jpg", "logo2.png"};
    File dataFolder;
    ILogger logger;

    public OmrDemo(OmrApi omrApi, OmrStorageApi storageApi, ILogger logger, File dataFolder) {
        this.omrApi = omrApi;
        this.storageApi = storageApi;
        this.logger = logger;
        this.dataFolder = dataFolder;
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

    public void UploadFile(File inputFile, String destinationFolder) throws Exception{
        String destinationPath = inputFile.getName();
        if (!destinationFolder.isEmpty())
            destinationPath = destinationFolder + "/" + destinationPath;
        logger.log("Uploading " + inputFile.toString() + " to " + destinationPath);
        storageApi.putUpload(destinationPath, inputFile, "", "");
    }

    public boolean FileExists(String path) throws ApiException {
        PathExistsResponse fileExistsResponse = storageApi.getIsExists(path, "", "");

        return fileExistsResponse != null && fileExistsResponse.isPathExists();
    }



    String serializeFile(String templateName, byte[] templateData) throws Exception{
        CorrectionParameter parameter = new CorrectionParameter();
        OmrFile file = new OmrFile();
        file.Name = templateName;
        file.Data = Base64.encodeToString(templateData, false);
        file.Size = file.Data.length();

        parameter.Files.add(file);

        Gson gson = new GsonBuilder().create();
        return gson.toJson(parameter, CorrectionParameter.class);
    }

    public void UploadDemoFiles() throws Exception {
        // check if folder Logos exists
        if(!FileExists(LogosFolderName)){
            // create Folder Logos
            storageApi.postCreateFolder(LogosFolderName, "");
        };
        // Upload files
        for(String logoFileName : LogoFiles)
        {
            if (!FileExists(LogosFolderName + "/" + logoFileName))
                UploadFile(new File(dataFolder, logoFileName), LogosFolderName);
            else logger.log("File " + logoFileName + " already exist");
        }
    }

    public File deserializeFile(FileInfo info, File destinationFolder) throws Exception{
        File fileName = new File(destinationFolder, info.getName());
        FileOutputStream stream = new FileOutputStream(fileName);
        try {
            stream.write(info.getData());
        } finally {
            stream.close();
        }
        logger.log("Saved " + fileName);
        return fileName;
    }

    private byte[] readAllBytes(File file) throws IOException {
        RandomAccessFile f = new RandomAccessFile(file, "r");
        try {
            byte[] data = new byte[(int)f.length()];
            f.readFully(data);
            return data;
        } finally {
            f.close();
        }
    }

    public OMRResponse checkResult(OMRResponse response) throws Exception{
        if (response.getErrorCode() != 0)
            throw new Exception(response.getErrorText());
        return response;
    }

    public OMRResponse GenerateTemplate(File templateFile) throws Exception{
        String fileName = templateFile.getName();

        UploadFile(templateFile, "");

        OMRFunctionParam param = new OMRFunctionParam();
        param.setFunctionParam("{ \"ExtraStoragePath\":\"" + LogosFolderName + "\"}");
        return checkResult(omrApi.postRunOmrTask(fileName, "GenerateTemplate", param, null, null));
    }

    public OMRResponse CorrectTemplate(File templateFile, File imageFilePath) throws Exception{
        OMRFunctionParam param = new OMRFunctionParam();
        param.setFunctionParam(serializeFile(templateFile.getName(), readAllBytes(templateFile)));

        UploadFile(imageFilePath, "");

        return checkResult(omrApi.postRunOmrTask(imageFilePath.getName(), "CorrectTemplate", param, null, null));
    }

    public OMRResponse FinalizeTemplate(String templateId, File templateFile) throws Exception{
        OMRFunctionParam param = new OMRFunctionParam();
        param.setFunctionParam(templateId);

        UploadFile(templateFile, "");

        return checkResult(omrApi.postRunOmrTask(templateFile.getName(), "FinalizeTemplate", param, null, null));
    }

    public OMRResponse RecognizeImage(String templateId, File imageFile) throws Exception{
        OMRFunctionParam param = new OMRFunctionParam();
        param.setFunctionParam(templateId);

        UploadFile(imageFile, "");

        return checkResult(omrApi.postRunOmrTask(imageFile.getName(), "RecognizeImage", param, null, null));
    }

}
