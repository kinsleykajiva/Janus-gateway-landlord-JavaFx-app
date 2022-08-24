package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Direction {
	boolean audioSend;
	boolean audioRecv;
	boolean videoSend;
	boolean videoRecv;

	@JsonProperty ("audio-send")
	public boolean getAudioSend () {
		return this.audioSend;
	}

	public void setAudioSend (boolean audioSend) {
		this.audioSend = audioSend;
	}

	@JsonProperty ("audio-recv")
	public boolean getAudioRecv () {
		return this.audioRecv;
	}

	public void setAudioRecv (boolean audioRecv) {
		this.audioRecv = audioRecv;
	}

	@JsonProperty ("video-send")
	public boolean getVideoSend () {
		return this.videoSend;
	}

	public void setVideoSend (boolean videoSend) {
		this.videoSend = videoSend;
	}

	@JsonProperty ("video-recv")
	public boolean getVideoRecv () {
		return this.videoRecv;
	}

	public void setVideoRecv (boolean videoRecv) {
		this.videoRecv = videoRecv;
	}
}
