# Aspose.OMR for Cloud

[Aspose.OMR for Cloud](https://products.aspose.cloud/omr/cloud) is a REST API that helps you to perform optical mark recognition in the cloud. We provide a series of [SDKs](https://github.com/aspose-omr-cloud). Along with that, you can get [binaries](https://github.com/aspose-omr-cloud/aspose-omr-cloud-dotnet/releases) to start working immediately and recognize various OMR forms.

Developers can embed [optical recognition](https://en.wikipedia.org/wiki/Optical_mark_recognition) in any type of application to extract data from images of tests, exams, questionnaires, surveys, etc. In the repository you can find examples on how to start using [Aspose.OMR API](https://docs.aspose.cloud/display/omrcloud/OMR+API+Specification) in your project.

## Quickstart

You can perform tasks out of the box without writing a single line of code with our [GUI client](https://github.com/aspose-omr-cloud/aspose-omr-cloud-dotnet/releases). You can also refer to the [client documentation](https://docs.aspose.cloud/display/omrcloud/Aspose.OMR.Client+Application).

## Using OMR Cloud API in your Java projects

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

## Optional requirements

To take full advantage of Aspose.OMR for Cloud, _aspose-cloud-storage_ is required. You may refer to official [Aspose Storage SDK](https://github.com/aspose-storage-cloud/aspose-storage-cloud-java) to get more information.

## Usage

### Receive Cloud Keys
Aspose.Cloud credentials are required to use Aspose.OMR for Cloud API. You can acquire App SID and App Key by registrating at [Aspose Cloud Dashboard](https://dashboard.aspose.cloud). It will take only a couple of minutes.


### Gradle / Android Studio users

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

### Code example
You can check out [OMR Demo](https://github.com/aspose-omr-cloud/aspose-omr-cloud-java/tree/master/java_demo) project to get started with Aspose.OMR for Cloud.

## Documentation for API Endpoints

All URIs are relative to *https://api.aspose.cloud/v1.1*

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




