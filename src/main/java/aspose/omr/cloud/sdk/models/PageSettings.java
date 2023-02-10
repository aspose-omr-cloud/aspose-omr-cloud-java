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

import java.util.Objects;

/**
 * PageSettings
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-06-18T15:36:35.385Z[GMT]")
public class PageSettings {
  @SerializedName("fontFamily")
  private String fontFamily = null;

  @SerializedName("fontStyle")
  private FontStyle fontStyle = null;

  @SerializedName("fontSize")
  private Integer fontSize = null;

  @SerializedName("paperSize")
  private PaperSize paperSize = null;

  @SerializedName("bubbleColor")
  private Color bubbleColor = null;

  @SerializedName("pageMarginLeft")
  private Integer pageMarginLeft = null;

  @SerializedName("orientation")
  private Orientation orientation = null;

  @SerializedName("bubbleSize")
  private BubbleSize bubbleSize = null;

  @SerializedName("outputFormat")
  private FileExtension outputFormat = null;

  public PageSettings fontFamily(String fontFamily) {
    this.fontFamily = fontFamily;
    return this;
  }

   /**
   * Get fontFamily
   * @return fontFamily
  **/
  public String getFontFamily() {
    return fontFamily;
  }

  public void setFontFamily(String fontFamily) {
    this.fontFamily = fontFamily;
  }

  public PageSettings fontStyle(FontStyle fontStyle) {
    this.fontStyle = fontStyle;
    return this;
  }

   /**
   * Get fontStyle
   * @return fontStyle
  **/
  public FontStyle getFontStyle() {
    return fontStyle;
  }

  public void setFontStyle(FontStyle fontStyle) {
    this.fontStyle = fontStyle;
  }

  public PageSettings fontSize(Integer fontSize) {
    this.fontSize = fontSize;
    return this;
  }

   /**
   * Get fontSize
   * @return fontSize
  **/
  public Integer getFontSize() {
    return fontSize;
  }

  public void setFontSize(Integer fontSize) {
    this.fontSize = fontSize;
  }

  public PageSettings paperSize(PaperSize paperSize) {
    this.paperSize = paperSize;
    return this;
  }

   /**
   * Get paperSize
   * @return paperSize
  **/
  public PaperSize getPaperSize() {
    return paperSize;
  }

  public void setPaperSize(PaperSize paperSize) {
    this.paperSize = paperSize;
  }

  public PageSettings bubbleColor(Color bubbleColor) {
    this.bubbleColor = bubbleColor;
    return this;
  }

   /**
   * Get bubbleColor
   * @return bubbleColor
  **/
  public Color getBubbleColor() {
    return bubbleColor;
  }

  public void setBubbleColor(Color bubbleColor) {
    this.bubbleColor = bubbleColor;
  }

  public PageSettings pageMarginLeft(Integer pageMarginLeft) {
    this.pageMarginLeft = pageMarginLeft;
    return this;
  }

   /**
   * Get pageMarginLeft
   * @return pageMarginLeft
  **/
  public Integer getPageMarginLeft() {
    return pageMarginLeft;
  }

  public void setPageMarginLeft(Integer pageMarginLeft) {
    this.pageMarginLeft = pageMarginLeft;
  }

  public PageSettings orientation(Orientation orientation) {
    this.orientation = orientation;
    return this;
  }

   /**
   * Get orientation
   * @return orientation
  **/
  public Orientation getOrientation() {
    return orientation;
  }

  public void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }

  public PageSettings bubbleSize(BubbleSize bubbleSize) {
    this.bubbleSize = bubbleSize;
    return this;
  }

   /**
   * Get bubbleSize
   * @return bubbleSize
  **/
  public BubbleSize getBubbleSize() {
    return bubbleSize;
  }

  public void setBubbleSize(BubbleSize bubbleSize) {
    this.bubbleSize = bubbleSize;
  }

  public PageSettings outputFormat(FileExtension outputFormat) {
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageSettings pageSettings = (PageSettings) o;
    return Objects.equals(this.fontFamily, pageSettings.fontFamily) &&
        Objects.equals(this.fontStyle, pageSettings.fontStyle) &&
        Objects.equals(this.fontSize, pageSettings.fontSize) &&
        Objects.equals(this.paperSize, pageSettings.paperSize) &&
        Objects.equals(this.bubbleColor, pageSettings.bubbleColor) &&
        Objects.equals(this.pageMarginLeft, pageSettings.pageMarginLeft) &&
        Objects.equals(this.orientation, pageSettings.orientation) &&
        Objects.equals(this.bubbleSize, pageSettings.bubbleSize) &&
        Objects.equals(this.outputFormat, pageSettings.outputFormat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fontFamily, fontStyle, fontSize, paperSize, bubbleColor, pageMarginLeft, orientation, bubbleSize, outputFormat);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageSettings {\n");
    
    sb.append("    fontFamily: ").append(toIndentedString(fontFamily)).append("\n");
    sb.append("    fontStyle: ").append(toIndentedString(fontStyle)).append("\n");
    sb.append("    fontSize: ").append(toIndentedString(fontSize)).append("\n");
    sb.append("    paperSize: ").append(toIndentedString(paperSize)).append("\n");
    sb.append("    bubbleColor: ").append(toIndentedString(bubbleColor)).append("\n");
    sb.append("    pageMarginLeft: ").append(toIndentedString(pageMarginLeft)).append("\n");
    sb.append("    orientation: ").append(toIndentedString(orientation)).append("\n");
    sb.append("    bubbleSize: ").append(toIndentedString(bubbleSize)).append("\n");
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
