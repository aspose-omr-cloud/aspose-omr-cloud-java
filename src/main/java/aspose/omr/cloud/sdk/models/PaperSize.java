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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets PaperSize
 */
@JsonAdapter(PaperSize.Adapter.class)
public enum PaperSize {
  LETTER("Letter"),
  A4("A4"),
  LEGAL("Legal");

  private String value;

  PaperSize(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static PaperSize fromValue(String input) {
    for (PaperSize b : PaperSize.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<PaperSize> {
    @Override
    public void write(final JsonWriter jsonWriter, final PaperSize enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public PaperSize read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return PaperSize.fromValue((String)(value));
    }
  }
}
