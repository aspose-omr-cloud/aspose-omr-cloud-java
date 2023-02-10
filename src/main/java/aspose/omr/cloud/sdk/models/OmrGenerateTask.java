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

package aspose.omr.cloud.sdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * OmrGenerateTask
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-06-18T15:36:35.385Z[GMT]")
public class OmrGenerateTask {
  @SerializedName("markupFile")
  private String markupFile = null;

  @SerializedName("settings")
  private PageSettings settings = null;

  @SerializedName("images")
  private Map<String, String> images = new HashMap<String, String>();

  @SerializedName("outputFormat")
  private FileExtension outputFormat = null;

  public OmrGenerateTask markupFile(String markupFile) {
    this.markupFile = markupFile;
    return this;
  }

   /**
   * Get markupFile
   * @return markupFile
  **/
  public String getMarkupFile() {
    return markupFile;
  }

  public void setMarkupFile(String markupFile) {
    this.markupFile = markupFile;
  }

  public OmrGenerateTask settings(PageSettings settings) {
    this.settings = settings;
    return this;
  }

   /**
   * Get settings
   * @return settings
  **/
  public PageSettings getSettings() {
    return settings;
  }

  public void setSettings(PageSettings settings) {
    this.settings = settings;
  }

  public OmrGenerateTask images(Map<String, String> images) {
    this.images = images;
    return this;
  }

  public OmrGenerateTask putImagesItem(String key, String imagesItem) {
    this.images.put(key, imagesItem);
    return this;
  }

   /**
   * Get images
   * @return images
  **/
  public Map<String, String> getImages() {
    return images;
  }

  public void setImages(Map<String, String> images) {
    this.images = images;
  }

  public OmrGenerateTask outputFormat(FileExtension outputFormat) {
    this.outputFormat = outputFormat;
    return this;
  }

   /**
   * Get outputFormat
   * @return outputFormat
  **/
  public FileExtension getOutputFormat() {
    return outputFormat;
  }

  public void setOutputFormat(FileExtension outputFormat) {
    this.outputFormat = outputFormat;
  }





  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OmrGenerateTask {\n");
    
    sb.append("    markupFile: ").append(toIndentedString(markupFile)).append("\n");
    sb.append("    settings: ").append(toIndentedString(settings)).append("\n");
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
    sb.append("    outputFormat: ").append(toIndentedString(outputFormat)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
