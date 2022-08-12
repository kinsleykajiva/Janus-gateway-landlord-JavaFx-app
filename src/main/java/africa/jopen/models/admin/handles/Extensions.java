package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Extensions{
	@JsonProperty("urn:ietf:params:rtp-hdrext:ssrc-audio-level")
	public int getUrnIetfParamsRtpHdrextSsrcAudioLevel() {
		return this.urnIetfParamsRtpHdrextSsrcAudioLevel; }
	public void setUrnIetfParamsRtpHdrextSsrcAudioLevel(int urnIetfParamsRtpHdrextSsrcAudioLevel) {
		this.urnIetfParamsRtpHdrextSsrcAudioLevel = urnIetfParamsRtpHdrextSsrcAudioLevel; }
	int urnIetfParamsRtpHdrextSsrcAudioLevel;
}