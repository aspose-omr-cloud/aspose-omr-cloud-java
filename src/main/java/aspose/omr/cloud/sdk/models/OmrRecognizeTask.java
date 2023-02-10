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
import java.util.ArrayList;
import java.util.List;

/**
 * OmrRecognizeTask
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-06-18T15:36:35.385Z[GMT]")
public class OmrRecognizeTask {
  @SerializedName("images")
  private List<String> images = new ArrayList<String>();

  @SerializedName("omrFile")
  private String omrFile = null;

  @SerializedName("outputFormat")
  private FileExtension outputFormat = null;

  @SerializedName("recognitionThreshold")
  private Integer recognitionThreshold = null;

  public OmrRecognizeTask images(List<String> images) {
    this.images = images;
    return this;
  }

  public OmrRecognizeTask addImagesItem(String imagesItem) {
    this.images.add(imagesItem);
    return this;
  }

   /**
   * Get images
   * @return images
  **/
  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> images) {
    this.images = images;
  }

  public OmrRecognizeTask omrFile(String omrFile) {
    this.omrFile = omrFile;
    return this;
  }

   /**
   * Get omrFile
   * @return omrFile
  **/
  public String getOmrFile() {
    return omrFile;
  }

  public void setOmrFile(String omrFile) {
    this.omrFile = omrFile;
  }

  public OmrRecognizeTask outputFormat(FileExtension outputFormat) {
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

  public OmrRecognizeTask recognitionThreshold(Integer recognitionThreshold) {
    this.recognitionThreshold = recognitionThreshold;
    return this;
  }

   /**
   * Get recognitionThreshold
   * @return recognitionThreshold
  **/
  public Integer getRecognitionThreshold() {
    return recognitionThreshold;
  }

  public void setRecognitionThreshold(Integer recognitionThreshold) {
    this.recognitionThreshold = recognitionThreshold;
  }



  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OmrRecognizeTask {\n");
    
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
    sb.append("    omrFile: ").append(toIndentedString(omrFile)).append("\n");
    sb.append("    outputFormat: ").append(toIndentedString(outputFormat)).append("\n");
    sb.append("    recognitionThreshold: ").append(toIndentedString(recognitionThreshold)).append("\n");
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
