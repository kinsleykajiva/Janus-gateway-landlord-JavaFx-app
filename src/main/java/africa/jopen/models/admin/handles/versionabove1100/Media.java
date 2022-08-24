package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Media {
	Audio audio;

	@JsonProperty ("audio")
	public Audio getAudio () {
		return this.audio;
	}

	public void setAudio (Audio audio) {
		this.audio = audio;
	}
}