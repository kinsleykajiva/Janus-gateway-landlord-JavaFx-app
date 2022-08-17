package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Extensions {
	@JsonProperty ("urn:ietf:params:rtp-hdrext:ssrc-audio-level")
	public int getUrnIetfParamsRtpHdrextSsrcAudioLevel () {
		return this.urnIetfParamsRtpHdrextSsrcAudioLevel;
	}

	public void setUrnIetfParamsRtpHdrextSsrcAudioLevel (int urnIetfParamsRtpHdrextSsrcAudioLevel) {
		this.urnIetfParamsRtpHdrextSsrcAudioLevel = urnIetfParamsRtpHdrextSsrcAudioLevel;
	}

	int urnIetfParamsRtpHdrextSsrcAudioLevel;
}