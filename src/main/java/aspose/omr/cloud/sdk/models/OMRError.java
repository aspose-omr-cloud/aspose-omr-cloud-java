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

import java.util.List;
import java.util.Objects;

/**
 * OMRError
 */

public class OMRError {
  @SerializedName("messages")

  private List<String> messages = null;
  @SerializedName("warnings")

  private List<String> warnings = null;

   /**
   * Get messages
   * @return messages
  **/
  public List<String> getMessages() {
    return messages;
  }

   /**
   * Get warnings
   * @return warnings
  **/
  public List<String> getWarnings() {
    return warnings;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OMRError omRError = (OMRError) o;
    return Objects.equals(this.messages, omRError.messages) &&
        Objects.equals(this.warnings, omRError.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messages, warnings);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OMRError {\n");
    
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
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
