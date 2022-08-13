package africa.jopen.models.forms.janusconfig.janus;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
 

 
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransportsFolder{
    boolean required;
    boolean commented;
    String comment;

    @JsonProperty("lineValue")
    String lineValue;

    @JsonProperty("required")
    public boolean getRequired() {
        return this.required; }

    public void setRequired(boolean required) {
        this.required = required; }

    @JsonProperty("commented")
    public boolean getCommented() {
        return this.commented; }

    public void setCommented(boolean commented) {
        this.commented = commented; }

    @JsonProperty("comment")
    public String getComment() {
        return this.comment; }

    public void setComment(String comment) {
        this.comment = comment; }


    public String getLineValue() {
        return this.lineValue; }

    public void setLineValue(String lineValue) {
        this.lineValue = lineValue; }
}