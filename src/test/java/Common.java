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

import aspose.omr.cloud.sdk.models.Config;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Paths;

public class Common {
    private String basePath;

    public String getAuthPath() {
        return authPath;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    private String authPath;
    private String clientId;
    private String clientSecret;
    private String demoDataSubmoduleName = "aspose-omr-cloud-demo-data";
    private String configFileName = "test_config.json";

    private Config config = new Config();

    void init() {
        File currentDirParent = Paths.get("").toAbsolutePath().getParent().toFile();

        basePath=Paths.get(currentDirParent.getAbsolutePath().toString(),demoDataSubmoduleName).toAbsolutePath().toString();
        File configFilePath = Paths.get(currentDirParent.getAbsolutePath().toString(),demoDataSubmoduleName,configFileName).toFile();
        Gson g = new Gson();
        try {
            config = g.fromJson(FileUtils.readFileToString(configFilePath,"UTF-8"), Config.class);
            authPath = config.getAuthUrl();
            clientId = config.getClientId();
            clientSecret = config.getClientSecret();
            System.out.println(configFilePath);
        }
        catch (Exception e){
            System.out.println("Unable to find file");
        }
    }
    String GetDataFolderDir() {
        return Paths.get(this.basePath, config.getDataFolder()).toAbsolutePath().toString();
    }

    String GetResultFolderDir() {
        return Paths.get(this.basePath, config.getResultFolder()).toAbsolutePath().toString();
    }

    String GetURL() {
        return this.config.getBasePath();
    }
}
