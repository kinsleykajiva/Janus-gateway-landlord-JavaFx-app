package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Audio {
	@JsonProperty ("base")
	public int getBase () {
		return this.base;
	}

	public void setBase (int base) {
		this.base = base;
	}

	int base;

	@JsonProperty ("rtt")
	public int getRtt () {
		return this.rtt;
	}

	public void setRtt (int rtt) {
		this.rtt = rtt;
	}

	int rtt;

	@JsonProperty ("lost")
	public int getLost () {
		return this.lost;
	}

	public void setLost (int lost) {
		this.lost = lost;
	}

	int lost;

	@JsonProperty ("lost-by-remote")
	public int getLostByRemote () {
		return this.lostByRemote;
	}

	public void setLostByRemote (int lostByRemote) {
		this.lostByRemote = lostByRemote;
	}

	int lostByRemote;

	@JsonProperty ("jitter-local")
	public int getJitterLocal () {
		return this.jitterLocal;
	}

	public void setJitterLocal (int jitterLocal) {
		this.jitterLocal = jitterLocal;
	}

	int jitterLocal;

	@JsonProperty ("jitter-remote")
	public int getJitterRemote () {
		return this.jitterRemote;
	}

	public void setJitterRemote (int jitterRemote) {
		this.jitterRemote = jitterRemote;
	}

	int jitterRemote;

	@JsonProperty ("in-link-quality")
	public int getInLinkQuality () {
		return this.inLinkQuality;
	}

	public void setInLinkQuality (int inLinkQuality) {
		this.inLinkQuality = inLinkQuality;
	}

	int inLinkQuality;

	@JsonProperty ("in-media-link-quality")
	public int getInMediaLinkQuality () {
		return this.inMediaLinkQuality;
	}

	public void setInMediaLinkQuality (int inMediaLinkQuality) {
		this.inMediaLinkQuality = inMediaLinkQuality;
	}

	int inMediaLinkQuality;

	@JsonProperty ("out-link-quality")
	public int getOutLinkQuality () {
		return this.outLinkQuality;
	}

	public void setOutLinkQuality (int outLinkQuality) {
		this.outLinkQuality = outLinkQuality;
	}

	int outLinkQuality;

	@JsonProperty ("out-media-link-quality")
	public int getOutMediaLinkQuality () {
		return this.outMediaLinkQuality;
	}

	public void setOutMediaLinkQuality (int outMediaLinkQuality) {
		this.outMediaLinkQuality = outMediaLinkQuality;
	}

	int outMediaLinkQuality;
}

