package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ssrc {
	int  ssrc;
	long ssrcPeer;

	@JsonProperty ("ssrc")
	public int getSsrc () {
		return this.ssrc;
	}

	public void setSsrc (int ssrc) {
		this.ssrc = ssrc;
	}

	@JsonProperty ("ssrc-peer")
	public long getSsrcPeer () {
		return this.ssrcPeer;
	}

	public void setSsrcPeer (long ssrcPeer) {
		this.ssrcPeer = ssrcPeer;
	}
}
