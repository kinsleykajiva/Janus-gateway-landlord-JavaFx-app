package africa.jopen.models.admin.handles;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Ssrc {
	@JsonProperty ("audio")
	public long getAudio () {
		return this.audio;
	}

	public void setAudio (long audio) {
		this.audio = audio;
	}

	long audio;

	@JsonProperty ("audio-peer")
	public int getAudioPeer () {
		return this.audioPeer;
	}

	public void setAudioPeer (int audioPeer) {
		this.audioPeer = audioPeer;
	}

	int audioPeer;
}