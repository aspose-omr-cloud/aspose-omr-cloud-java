package com.aspose.omrdemo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.aspose.omr.api.OmrStorageApi;
import com.aspose.omr.model.FileInfo;
import com.aspose.omr.model.OMRResponse;
import com.aspose.omr.api.OmrApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.aspose.omrdemo.OmrDemo.ILogger;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ILogger {
    TextView tvResult;
    private ProgressBar spinner;
    OmrStorageApi storageApi;
    OmrApi omrApi;
    String appSID, appKey, basePath;
    private final String LogosFolderName = "Logos";
    private final String[] LogoFiles = {"logo1.jpg", "logo2.png"};
    File dataFolder;
    static String configFileName = "test_config.json";
    File configFile;

    EditText etAppSID, etAppKEY, etBasePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);

        spinner = (ProgressBar) findViewById(R.id.progressBar);
        storageApi = null;
        tvResult.setMovementMethod(new ScrollingMovementMethod());

        dataFolder = getExternalFilesDir(null);

        copyAssets(dataFolder);
        configFile = new File(dataFolder, configFileName);

        loadConfig(configFile);

        etAppSID =  (EditText) findViewById(R.id.etAppSID);
        etAppKEY =  (EditText) findViewById(R.id.etAppKey);
        etBasePath =  (EditText) findViewById(R.id.etBasePath);

        etAppSID.setText(appSID);
        etAppKEY.setText(appKey);
        etBasePath.setText(basePath);
    }

    @Override
    public void onBackPressed()
    {
        saveConfig(configFile);
        super.onBackPressed();
    }

    public void generateTemplate(View view) {
        tvResult = (TextView) findViewById(R.id.tvResult);

        appSID = etAppSID.getText().toString();
        appKey = etAppKEY.getText().toString();
        basePath = etBasePath.getText().toString();

        saveConfig(configFile);

        storageApi = new OmrStorageApi(appKey, appSID, basePath);
        omrApi = new OmrApi(appKey, appSID, basePath);
        tvResult.setText("");

        log( String.format("Starting Demo using base path %s as %s", basePath, appSID));
        new GenerateTemplateTask().execute();
    }

    void loadConfig(File configFile) {
        try {
            JSONObject jObject = new JSONObject(readAllLines(configFile));
            appSID = jObject.getString("app_sid");
            appKey = jObject.getString("app_key");
            basePath = jObject.getString("base_path");
        }catch(Exception ex)
        {
            appSID = "";
            appKey = "";
            basePath = "";
            Log.e("tag", String.format("Failed to read config file %s", configFile.getName()), ex);

        }
    }

    void saveConfig(File configFile) {
        try {
            JSONObject jObject = new JSONObject();
            jObject.put("app_sid", appSID);
            jObject.put("app_key", appKey);
            jObject.put("base_path", basePath);

            PrintWriter p = new PrintWriter(new FileOutputStream(configFile));
            p.write(jObject.toString());
            p.close();
        }catch(Exception ex)
        {
            Log.e("tag", String.format("Failed to write config file %s", configFile.getName()), ex);
        }
    }

    private void displayResult(String result) {
        Intent intent = new Intent(this, ViewResultActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }

    private String readAllLines(File file) {

        String ret = "";

        try {
            FileInputStream inputStream = new FileInputStream(file);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString + "\n");
            }

            inputStream.close();
            ret = stringBuilder.toString();

        }
        catch (FileNotFoundException e) {
            Log.e("Error", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Error", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public void log(String... messages) {
        for (String m : messages) {
            tvResult.append( (tvResult.getText().toString().isEmpty() ? "" : "\n") + m);
        }
        //scrollToBottom();
    }

    void showLoading(){
        spinner.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    void cancelLoading(){
        spinner.setVisibility(View.INVISIBLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void copyAssets(File toFolder) {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {
            File outFile = new File(toFolder, filename);
            //Log.d("notify", String.format("filename %s configfile name %s exists %b", filename, configFile.getName(), outFile.exists()));
            if (filename.contains(configFileName) && outFile.exists())
                continue;

            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    public static String getFileExt(File file) {
        String fileName = file.getName();
        if (fileName.indexOf(".") > 0)
            fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileName;
    }

    //AsyncTask<[Input_Parameter Type], [Progress_Report Type], [Result Type]>
    abstract class OmrAsyncTask<InputType, ResultType> extends AsyncTask<InputType, String, ResultType> implements OmrDemo.ILogger
    {
        private Exception exceptionInfo;
        private String name;
        public OmrAsyncTask(String name) {
            exceptionInfo = null;
            this.name = name;
        }
        public String getName() {
            return name;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            clearError();
            showLoading();
        }

        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate();

            logMessages(values);
        }

        private void logMessages(String... messages) {
            for (String value : messages) {
                MainActivity.this.log(messages);
            }
        }

        public void log(String... strings) {
            if (getStatus() == Status.RUNNING)
                publishProgress(strings);
            else
                logMessages(strings);
        }

        @Override
        protected void onPostExecute(ResultType result) {
            super.onPostExecute(result);

            cancelLoading();

            if (hasError()) {
                log("Exception", getError().toString());
                getError().printStackTrace();
            } else {
                onExecuteNextTask(result);
            }
        }

        abstract void onExecuteNextTask(ResultType result);


        protected void setError(Exception exceptionInfo) {
            this.exceptionInfo = exceptionInfo;
        }

        protected void clearError() {
            exceptionInfo = null;
        }

        protected Exception getError() {
            return exceptionInfo;
        }

        protected boolean hasError() {
            return null != exceptionInfo;
        }
    }

    class GenerateTemplateTask extends OmrAsyncTask<Void, OMRResponse>
    {
        public GenerateTemplateTask() {
            super("GenerateTemplate");
        }
        File templateFile, imageFile;
        @Override
        protected OMRResponse doInBackground(Void... params) {
            try {
                log("Uploading demo files...");
                OmrDemo demo = new OmrDemo(omrApi, storageApi, this, dataFolder);
                demo.UploadDemoFiles();

                log(getName() + " ...");
                OMRResponse response = demo.GenerateTemplate(new File(dataFolder, "Aspose_test.txt"));
                //log(getName() + " Response", response.toString());
                for ( FileInfo info : response.getPayload().getResult().getResponseFiles()) {
                    File tmp = demo.deserializeFile(info, dataFolder);
                    if (getFileExt(tmp).equals("omr"))
                        templateFile = tmp;
                    else if (getFileExt(tmp).equals("png"))
                        imageFile = tmp;
                }
                return response;
            } catch (Exception exc) {
                setError(exc);
            }
            return null;
        }

        @Override
        protected void onExecuteNextTask(OMRResponse result){
            log(getName() + " -> CorrectTemplateTask ...");
            new CorrectTemplateTask(templateFile, imageFile).execute();
        }
    }

    class CorrectTemplateTask extends OmrAsyncTask<Void, OMRResponse>
    {
        public CorrectTemplateTask(File templateFile, File imageFile) {
            super("CorrectTemplate");
            this.templateFile = templateFile;
            this.imageFile = imageFile;
        }

        File templateFile, imageFile, correctedTemplateFile;

        @Override
        protected OMRResponse doInBackground(Void... params) {
            try {
                OmrDemo demo = new OmrDemo(omrApi, storageApi, this, dataFolder);

                log(getName() + " ...");
                OMRResponse response = demo.CorrectTemplate(templateFile, imageFile);
                for ( FileInfo info : response.getPayload().getResult().getResponseFiles()) {
                    File tmp = demo.deserializeFile(info, dataFolder);
                    if (getFileExt(tmp).equals("omrcr"))
                        correctedTemplateFile = tmp;
                }
                return response;
            } catch (Exception exc) {
                setError(exc);
            }
            return null;
        }

        @Override
        protected void onExecuteNextTask(OMRResponse result){
            log(getName() + " -> FinalizeTemplateTask ...");
            new FinalizeTemplateTask(result.getPayload().getResult().getTemplateId(), correctedTemplateFile).execute();
        }
    }

    class FinalizeTemplateTask extends OmrAsyncTask<Void, OMRResponse>
    {
        public FinalizeTemplateTask(String templateId, File correctedTemplateFile) {
            super("FinalizeTemplate");
            this.templateId = templateId;
            this.correctedTemplateFile = correctedTemplateFile;
        }
        String templateId;
        File correctedTemplateFile;

        @Override
        protected OMRResponse doInBackground(Void... params) {
            try {
                OmrDemo demo = new OmrDemo(omrApi, storageApi, this, dataFolder);

                log(getName() + " ...");
                return demo.FinalizeTemplate(templateId, correctedTemplateFile);
            } catch (Exception exc) {
                setError(exc);
            }
            return null;
        }

        @Override
        protected void onExecuteNextTask(OMRResponse result){
            log(getName() + " -> RecognizeImageTask ...");
            new RecognizeImageTask(templateId, new File(dataFolder, "photo.jpg")).execute();
        }
    }

    class RecognizeImageTask extends OmrAsyncTask<Void, OMRResponse>
    {
        public RecognizeImageTask(String templateId, File imagePath) {
            super("RecognizeImage");
            this.templateId = templateId;
            this.imagePath = imagePath;
            this.outputFile = null;
        }
        String templateId;
        File imagePath;
        File outputFile;
        @Override
        protected OMRResponse doInBackground(Void... params) {
            try {
                OmrDemo demo = new OmrDemo(omrApi, storageApi, this, dataFolder);

                log(getName() + " ...");
                OMRResponse response =  demo.RecognizeImage(templateId, imagePath);
                for ( FileInfo info : response.getPayload().getResult().getResponseFiles()) {
                    File tmp = demo.deserializeFile(info, dataFolder);
                    if (0 == getFileExt(tmp).compareToIgnoreCase("dat")) {
                        outputFile = tmp;
                        log("Output file " + tmp.toString());
                    }
                }
                return response;
            } catch (Exception exc) {
                setError(exc);
            }
            return null;
        }

        @Override
        protected void onExecuteNextTask(OMRResponse result){
            log("OMRDemo Done");
            if (null != outputFile)
                displayResult(readAllLines(outputFile));

        }
    }
}
