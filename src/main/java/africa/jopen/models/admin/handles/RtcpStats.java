package africa.jopen.models.admin.handles;


import com.fasterxml.jackson.annotation.JsonProperty;


public class RtcpStats {
	Audio audio;

	@JsonProperty ("audio")
	public Audio getAudio () {
		return this.audio;
	}

	public void setAudio (Audio audio) {
		this.audio = audio;
	}
}
