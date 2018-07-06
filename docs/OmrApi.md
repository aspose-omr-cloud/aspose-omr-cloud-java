# OmrApi

All URIs are relative to *https://localhost/v1.1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**postRunOmrTask**](OmrApi.md#postRunOmrTask) | **POST** /omr/{name}/runOmrTask | Run specific OMR task


<a name="postRunOmrTask"></a>
# **postRunOmrTask**
> OMRResponse postRunOmrTask(name, actionName, param, storage, folder)

Run specific OMR task


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| Name of the file to recognize. |
 **actionName** | **String**| Action name [&#39;CorrectTemplate&#39;, &#39;FinalizeTemplate&#39;, &#39;RecognizeImage&#39;] |
 **param** | [**OMRFunctionParam**](OMRFunctionParam.md)| Function params, specific for each actionName | [optional]
 **storage** | **String**| Image&#39;s storage. | [optional]
 **folder** | **String**| Image&#39;s folder. | [optional]

### Return type

[**OMRResponse**](OMRResponse.md)

### Authorization

Library uses OAUTH2 authorization internally

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

