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


/*
 * Aspose.OMR for Cloud API Reference
 * Aspose.OMR for Cloud helps performing optical mark recognition in the cloud
 *
 * OpenAPI spec version: 1.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.aspose.omr.model;

import java.util.Objects;
import com.aspose.omr.model.FileInfo;
import com.aspose.omr.model.OmrResponseInfo;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Represents information about part of the text.
 */
@ApiModel(description = "Represents information about part of the text.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-05-18T16:51:05.078+03:00")
public class OmrResponseContent {
  @SerializedName("TemplateId")
  private String templateId = null;

  @SerializedName("ExecutionTime")
  private Double executionTime = null;

  @SerializedName("ResponseFiles")
  private java.util.List<FileInfo> responseFiles = null;

  @SerializedName("Info")
  private OmrResponseInfo info = null;

  public OmrResponseContent templateId(String templateId) {
    this.templateId = templateId;
    return this;
  }

   /**
   * GUID string that is used to identify template on server This value is assigned after Template Correction and used later in Template Finalization and Image Recognition
   * @return templateId
  **/
  @ApiModelProperty(value = "GUID string that is used to identify template on server This value is assigned after Template Correction and used later in Template Finalization and Image Recognition")
  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public OmrResponseContent executionTime(Double executionTime) {
    this.executionTime = executionTime;
    return this;
  }

   /**
   * Indicates how long it took to perform task on server.
   * @return executionTime
  **/
  @ApiModelProperty(required = true, value = "Indicates how long it took to perform task on server.")
  public Double getExecutionTime() {
    return executionTime;
  }

  public void setExecutionTime(Double executionTime) {
    this.executionTime = executionTime;
  }

  public OmrResponseContent responseFiles(java.util.List<FileInfo> responseFiles) {
    this.responseFiles = responseFiles;
    return this;
  }

  public OmrResponseContent addResponseFilesItem(FileInfo responseFilesItem) {
    if (this.responseFiles == null) {
      this.responseFiles = new java.util.ArrayList<FileInfo>();
    }
    this.responseFiles.add(responseFilesItem);
    return this;
  }

   /**
   * This structure holds array of files returned in response Type and content of files differes depending on action
   * @return responseFiles
  **/
  @ApiModelProperty(value = "This structure holds array of files returned in response Type and content of files differes depending on action")
  public java.util.List<FileInfo> getResponseFiles() {
    return responseFiles;
  }

  public void setResponseFiles(java.util.List<FileInfo> responseFiles) {
    this.responseFiles = responseFiles;
  }

  public OmrResponseContent info(OmrResponseInfo info) {
    this.info = info;
    return this;
  }

   /**
   * Get info
   * @return info
  **/
  @ApiModelProperty(value = "")
  public OmrResponseInfo getInfo() {
    return info;
  }

  public void setInfo(OmrResponseInfo info) {
    this.info = info;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OmrResponseContent omrResponseContent = (OmrResponseContent) o;
    return Objects.equals(this.templateId, omrResponseContent.templateId) &&
        Objects.equals(this.executionTime, omrResponseContent.executionTime) &&
        Objects.equals(this.responseFiles, omrResponseContent.responseFiles) &&
        Objects.equals(this.info, omrResponseContent.info);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templateId, executionTime, responseFiles, info);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OmrResponseContent {\n");
    
    sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
    sb.append("    executionTime: ").append(toIndentedString(executionTime)).append("\n");
    sb.append("    responseFiles: ").append(toIndentedString(responseFiles)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

