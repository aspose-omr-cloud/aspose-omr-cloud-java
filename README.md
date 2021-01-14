![](https://img.shields.io/badge/api-v3.0-lightgrey) [![GitHub license](https://img.shields.io/github/license/aspose-omr-cloud/aspose-omr-cloud-java)](https://github.com/aspose-omr-cloud/aspose-omr-cloud-java/blob/master/LICENSE) ![GitHub last commit](https://img.shields.io/github/last-commit/Aspose-omr-Cloud/aspose-omr-cloud-java)

# Java REST API for OMR Processing
[Aspose.OMR for Cloud](https://products.aspose.cloud/omr/) is a REST API that helps you to perform optical mark recognition in the cloud. We provide a series of [SDKs](https://github.com/aspose-omr-cloud). Along with that, you can get [binaries](https://github.com/aspose-omr-cloud/aspose-omr-cloud-dotnet/releases) to start working immediately and recognize various OMR forms.

Developers can embed [optical recognition](https://en.wikipedia.org/wiki/Optical_mark_recognition) in any type of application to extract data from images of tests, exams, questionnaires, surveys, etc. In the repository you can find examples on how to start using [Aspose.OMR API](https://docs.aspose.cloud/omr/omr-api-specification/) in your project.

## OMR Processing Features

- Perform recognition of scanned photos and images for OMR operations.
- Ability to perform OMR on rotated & perspective (within 25 deg) photos.
- Extract & recognize human-marked data from scanned tests, exams, surveys, etc.
- Supports the export of OMR results to CSV file format.
- Use textual markup to generate OMR templates, generate surveys, and test sheets.
- Availability of GUI application for managing OMR templates.
- Specify the number of OMR based questions & answers in the template.
- Availability of GUI OMR editor as a cloud client.
- Provide JSON rules to perform OMR answer grading.
- Clip an area of interest from an image, save it as JPEG & perform OMR on it.
- Perform highly accurate optical mark recognition (OMR).

## Save OMR As
CSV

## Read OMR Formats
JPEG, PNG, BMP, TIFF, PDF
## Quickstart

You can perform tasks out of the box without writing a single line of code with our [GUI client](https://github.com/aspose-omr-cloud/aspose-omr-cloud-dotnet/releases). You can also refer to the [client documentation](https://docs.aspose.cloud/omr/aspose-omr-client-application/).

## Using OMR Cloud API in your Java projects

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## GitHub Repository

Source code available in our GitHub repository https://github.com/aspose-omr-cloud/aspose-omr-cloud-java.git

## Installation

### Clone repository

Clone `aspose-omr-cloud` **with submodules**:
```sh
git clone https://github.com/aspose-omr-cloud/aspose-omr-cloud-java.git --recurse-submodules
```


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
    <artifactId>aspose-omr-cloud</artifactId>
    <version>18.8.0</version>
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
implementation "com.aspose:aspose-omr-cloud:18.8.0"
```

For older Gradle projects add this dependency to your project's build file:

```groovy
compile "com.aspose:aspose-omr-cloud:18.8.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/aspose-omr-cloud-18.8.0.jar
* target/lib/*.jar

## Getting Started
### Update submodule
Make sure you've cloned [aspose-omr-cloud-demo-data](https://github.com/aspose-omr-cloud/aspose-omr-cloud-demo-data) submodule, that contains all data required to run demo.
In case you are missing submodules use the following git commands to initialize and update them:
```sh
git submodule init
git submodule update --remote --merge
```


### Code example
You can check out [OMR Demo](java_demo) project to get started with Aspose.OMR for Cloud.

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

## Aspose.OMR Cloud SDKs in Popular Languages

| .NET | Java | PHP | Python | Ruby | Node.js |
|---|---|---|---|---|---|
| [GitHub](https://github.com/aspose-omr-cloud/aspose-omr-cloud-dotnet) | [GitHub](https://github.com/aspose-omr-cloud/aspose-omr-cloud-java) | [GitHub](https://github.com/aspose-omr-cloud/aspose-omr-cloud-php) | [GitHub](https://github.com/aspose-omr-cloud/aspose-omr-cloud-python) | [GitHub](https://github.com/aspose-omr-cloud/aspose-omr-cloud-ruby)  | [GitHub](https://github.com/aspose-omr-cloud/aspose-omr-cloud-node) |
| [NuGet](https://www.nuget.org/packages/Aspose.omr-Cloud/) | [Maven](https://repository.aspose.cloud/webapp/#/artifacts/browse/tree/General/repo/com/aspose/aspose-omr-cloud) | [Composer](https://packagist.org/packages/aspose/aspose-omr-cloud) | [PIP](https://pypi.org/project/aspose.omr-cloud/) | [GEM](https://rubygems.org/gems/aspose_omr_cloud)  | [NPM](https://www.npmjs.com/package/@asposecloud/aspose-omr-cloud) |

[Product Page](https://products.aspose.cloud/omr/java) | [Documentation](https://docs.aspose.cloud/display/omrcloud/Home) | [API Reference](https://apireference.aspose.cloud/omr/) | [Code Samples](https://github.com/aspose-omr-cloud/aspose-omr-cloud-java) | [Blog](https://blog.aspose.cloud/category/omr/) | [Free Support](https://forum.aspose.cloud/c/omr) | [Free Trial](https://dashboard.aspose.cloud/#/apps)|
