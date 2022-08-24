package africa.jopen.models.admin.handles;


import com.fasterxml.jackson.annotation.JsonProperty;

public class InStats {
	int     audio_packets;
	int     audio_bytes;
	int     audio_bytes_lastsec;
	boolean do_audio_nacks;
	int     data_packets;
	int     data_bytes;

	@JsonProperty ("audio_packets")
	public int getAudio_packets () {
		return this.audio_packets;
	}

	public void setAudio_packets (int audio_packets) {
		this.audio_packets = audio_packets;
	}

	@JsonProperty ("audio_bytes")
	public int getAudio_bytes () {
		return this.audio_bytes;
	}

	public void setAudio_bytes (int audio_bytes) {
		this.audio_bytes = audio_bytes;
	}

	@JsonProperty ("audio_bytes_lastsec")
	public int getAudio_bytes_lastsec () {
		return this.audio_bytes_lastsec;
	}

	public void setAudio_bytes_lastsec (int audio_bytes_lastsec) {
		this.audio_bytes_lastsec = audio_bytes_lastsec;
	}

	@JsonProperty ("do_audio_nacks")
	public boolean getDo_audio_nacks () {
		return this.do_audio_nacks;
	}

	public void setDo_audio_nacks (boolean do_audio_nacks) {
		this.do_audio_nacks = do_audio_nacks;
	}

	@JsonProperty ("data_packets")
	public int getData_packets () {
		return this.data_packets;
	}

	public void setData_packets (int data_packets) {
		this.data_packets = data_packets;
	}

	@JsonProperty ("data_bytes")
	public int getData_bytes () {
		return this.data_bytes;
	}

	public void setData_bytes (int data_bytes) {
		this.data_bytes = data_bytes;
	}
}