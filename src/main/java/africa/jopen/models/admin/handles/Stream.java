package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Stream {
	int                  id;
	int                  ready;
	Ssrc                 ssrc;
	Direction            direction;
	Extensions           extensions;
	Bwe                  bwe;
	int                  nackQueueMs;
	RtcpStats            rtcp_stats;
	ArrayList<Component> components;

	@JsonProperty ("id")
	public int getId () {
		return this.id;
	}

	public void setId (int id) {
		this.id = id;
	}

	@JsonProperty ("ready")
	public int getReady () {
		return this.ready;
	}

	public void setReady (int ready) {
		this.ready = ready;
	}

	@JsonProperty ("ssrc")
	public Ssrc getSsrc () {
		return this.ssrc;
	}

	public void setSsrc (Ssrc ssrc) {
		this.ssrc = ssrc;
	}

	@JsonProperty ("direction")
	public Direction getDirection () {
		return this.direction;
	}

	public void setDirection (Direction direction) {
		this.direction = direction;
	}

	@JsonProperty ("extensions")
	public Extensions getExtensions () {
		return this.extensions;
	}

	public void setExtensions (Extensions extensions) {
		this.extensions = extensions;
	}

	@JsonProperty ("bwe")
	public Bwe getBwe () {
		return this.bwe;
	}

	public void setBwe (Bwe bwe) {
		this.bwe = bwe;
	}

	@JsonProperty ("nack-queue-ms")
	public int getNackQueueMs () {
		return this.nackQueueMs;
	}

	public void setNackQueueMs (int nackQueueMs) {
		this.nackQueueMs = nackQueueMs;
	}

	@JsonProperty ("rtcp_stats")
	public RtcpStats getRtcp_stats () {
		return this.rtcp_stats;
	}

	public void setRtcp_stats (RtcpStats rtcp_stats) {
		this.rtcp_stats = rtcp_stats;
	}

	@JsonProperty ("components")
	public ArrayList<Component> getComponents () {
		return this.components;
	}

	public void setComponents (ArrayList<Component> components) {
		this.components = components;
	}
}


