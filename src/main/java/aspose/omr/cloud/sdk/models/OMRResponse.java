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
import java.util.Objects;

/**
 * OMRResponse
 */
public class OMRResponse {
  @SerializedName("id")
  private String id = null;

  @SerializedName("responseStatusCode")
  private ResponseStatusCode responseStatusCode = null;

  @SerializedName("results")
  private List<OMRResult> results = null;

  @SerializedName("error")
  private OMRError error = null;

  public OMRResponse id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public OMRResponse responseStatusCode(ResponseStatusCode responseStatusCode) {
    this.responseStatusCode = responseStatusCode;
    return this;
  }

   /**
   * Get responseStatusCode
   * @return responseStatusCode
  **/
  public ResponseStatusCode getResponseStatusCode() {
    return responseStatusCode;
  }

  public void setResponseStatusCode(ResponseStatusCode responseStatusCode) {
    this.responseStatusCode = responseStatusCode;
  }

  public OMRResponse results(List<OMRResult> results) {
    this.results = results;
    return this;
  }

  public OMRResponse addResultsItem(OMRResult resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<OMRResult>();
    }
    this.results.add(resultsItem);
    return this;
  }

   /**
   * Get results
   * @return results
  **/
  public List<OMRResult> getResults() {
    return results;
  }

  public void setResults(List<OMRResult> results) {
    this.results = results;
  }

  public OMRResponse error(OMRError error) {
    this.error = error;
    return this;
  }

   /**
   * Get error
   * @return error
  **/
  public OMRError getError() {
    return error;
  }

  public void setError(OMRError error) {
    this.error = error;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OMRResponse omRResponse = (OMRResponse) o;
    return Objects.equals(this.id, omRResponse.id) &&
        Objects.equals(this.responseStatusCode, omRResponse.responseStatusCode) &&
        Objects.equals(this.results, omRResponse.results) &&
        Objects.equals(this.error, omRResponse.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, responseStatusCode, results, error);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OMRResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    responseStatusCode: ").append(toIndentedString(responseStatusCode)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
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
