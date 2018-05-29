# aspose-cloud-omr

## Overview

This is `aspose-cloud-omr` Client SDK for Java

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## GitHub Repository

Source code available in our GitHub repository https://github.com/aspose-omr-cloud/aspose-omr-cloud-java.git

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.aspose</groupId>
    <artifactId>aspose-cloud-omr</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

## Usage

### Gradle users

For Gradle v3.0 and up add this dependency to your project's build file:

```groovy
implementation "com.aspose:aspose-cloud-omr:1.0.0"
```

For older Gradle projects add this dependency to your project's build file:

```groovy
compile "com.aspose:aspose-cloud-omr:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/aspose-cloud-omr-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import com.aspose.omr.client.*;
import com.aspose.omr.client.auth.*;
import com.aspose.omr.model.*;
import com.aspose.omr.api.OmrApi;

import java.io.File;
import java.util.*;

public class OmrApiExample {

    public static void main(String[] args) {
        // You can acquire App SID and App Key by registrating at Aspose Cloud Dashboard https://dashboard.aspose.cloud
        String APP_KEY = "xxxxx";
        String APP_SID = "xxxxx";

        OmrApi apiInstance = new OmrApi(APP_KEY, APP_SID, "https://api.aspose.cloud/v1.1");
        String name = "name_example"; // String | Name of the file to recognize.
        String actionName = "actionName_example"; // String | Action name ['CorrectTemplate', 'FinalizeTemplate', 'RecognizeImage']
        OMRFunctionParam param = new OMRFunctionParam(); // OMRFunctionParam | Function params, specific for each actionName
        String storage = "storage_example"; // String | Image's storage.
        String folder = "folder_example"; // String | Image's folder.
        try {
            OMRResponse result = apiInstance.postRunOmrTask(name, actionName, param, storage, folder);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling OmrApi#postRunOmrTask");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://localhost/v1.1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*OmrApi* | [**postRunOmrTask**](docs/OmrApi.md#postRunOmrTask) | **POST** /omr/{name}/runOmrTask | Run specific OMR task


## Documentation for Models

 - [AsposeResponse](docs/AsposeResponse.md)
 - [FileInfo](docs/FileInfo.md)
 - [OMRFunctionParam](docs/OMRFunctionParam.md)
 - [OMRResponseDetails](docs/OMRResponseDetails.md)
 - [OmrResponseContent](docs/OmrResponseContent.md)
 - [OmrResponseInfo](docs/OmrResponseInfo.md)
 - [Payload](docs/Payload.md)
 - [RecognitionStatistics](docs/RecognitionStatistics.md)
 - [ServerStat](docs/ServerStat.md)
 - [OMRResponse](docs/OMRResponse.md)



## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Authorization

Library uses OAUTH2 internally

## Author

Aspose Pty Ltd (https://www.aspose.com)




