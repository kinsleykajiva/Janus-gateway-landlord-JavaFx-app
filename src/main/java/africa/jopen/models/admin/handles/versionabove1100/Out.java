package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Out {
	int packets;
	int bytes;
	int bytes_lastsec;
	int nacks;

	@JsonProperty ("packets")
	public int getPackets () {
		return this.packets;
	}

	public void setPackets (int packets) {
		this.packets = packets;
	}

	@JsonProperty ("bytes")
	public int getBytes () {
		return this.bytes;
	}

	public void setBytes (int bytes) {
		this.bytes = bytes;
	}

	@JsonProperty ("bytes_lastsec")
	public int getBytes_lastsec () {
		return this.bytes_lastsec;
	}

	public void setBytes_lastsec (int bytes_lastsec) {
		this.bytes_lastsec = bytes_lastsec;
	}

	@JsonProperty ("nacks")
	public int getNacks () {
		return this.nacks;
	}

	public void setNacks (int nacks) {
		this.nacks = nacks;
	}
}

