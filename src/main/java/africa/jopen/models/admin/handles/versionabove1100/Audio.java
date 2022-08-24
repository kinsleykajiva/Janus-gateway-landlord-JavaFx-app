package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Audio {
	String    type;
	int       mindex;
	String    mid;
	boolean   do_nacks;
	int       nackQueueMs;
	Ssrc      ssrc;
	Direction direction;
	Rtcp      rtcp;
	Stats     stats;

	@JsonProperty ("type")
	public String getType () {
		return this.type;
	}

	public void setType (String type) {
		this.type = type;
	}

	@JsonProperty ("mindex")
	public int getMindex () {
		return this.mindex;
	}

	public void setMindex (int mindex) {
		this.mindex = mindex;
	}

	@JsonProperty ("mid")
	public String getMid () {
		return this.mid;
	}

	public void setMid (String mid) {
		this.mid = mid;
	}

	@JsonProperty ("do_nacks")
	public boolean getDo_nacks () {
		return this.do_nacks;
	}

	public void setDo_nacks (boolean do_nacks) {
		this.do_nacks = do_nacks;
	}

	@JsonProperty ("nack-queue-ms")
	public int getNackQueueMs () {
		return this.nackQueueMs;
	}

	public void setNackQueueMs (int nackQueueMs) {
		this.nackQueueMs = nackQueueMs;
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

	@JsonProperty ("rtcp")
	public Rtcp getRtcp () {
		return this.rtcp;
	}

	public void setRtcp (Rtcp rtcp) {
		this.rtcp = rtcp;
	}

	@JsonProperty ("stats")
	public Stats getStats () {
		return this.stats;
	}

	public void setStats (Stats stats) {
		this.stats = stats;
	}
}