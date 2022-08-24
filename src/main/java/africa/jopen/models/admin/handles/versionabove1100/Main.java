package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {
	int base;
	int rtt;
	int lost;
	int lostByRemote;
	int jitterLocal;
	int jitterRemote;
	int inLinkQuality;
	int inMediaLinkQuality;
	int outLinkQuality;
	int outMediaLinkQuality;

	@JsonProperty ("base")
	public int getBase () {
		return this.base;
	}

	public void setBase (int base) {
		this.base = base;
	}

	@JsonProperty ("rtt")
	public int getRtt () {
		return this.rtt;
	}

	public void setRtt (int rtt) {
		this.rtt = rtt;
	}

	@JsonProperty ("lost")
	public int getLost () {
		return this.lost;
	}

	public void setLost (int lost) {
		this.lost = lost;
	}

	@JsonProperty ("lost-by-remote")
	public int getLostByRemote () {
		return this.lostByRemote;
	}

	public void setLostByRemote (int lostByRemote) {
		this.lostByRemote = lostByRemote;
	}

	@JsonProperty ("jitter-local")
	public int getJitterLocal () {
		return this.jitterLocal;
	}

	public void setJitterLocal (int jitterLocal) {
		this.jitterLocal = jitterLocal;
	}

	@JsonProperty ("jitter-remote")
	public int getJitterRemote () {
		return this.jitterRemote;
	}

	public void setJitterRemote (int jitterRemote) {
		this.jitterRemote = jitterRemote;
	}

	@JsonProperty ("in-link-quality")
	public int getInLinkQuality () {
		return this.inLinkQuality;
	}

	public void setInLinkQuality (int inLinkQuality) {
		this.inLinkQuality = inLinkQuality;
	}

	@JsonProperty ("in-media-link-quality")
	public int getInMediaLinkQuality () {
		return this.inMediaLinkQuality;
	}

	public void setInMediaLinkQuality (int inMediaLinkQuality) {
		this.inMediaLinkQuality = inMediaLinkQuality;
	}

	@JsonProperty ("out-link-quality")
	public int getOutLinkQuality () {
		return this.outLinkQuality;
	}

	public void setOutLinkQuality (int outLinkQuality) {
		this.outLinkQuality = outLinkQuality;
	}

	@JsonProperty ("out-media-link-quality")
	public int getOutMediaLinkQuality () {
		return this.outMediaLinkQuality;
	}

	public void setOutMediaLinkQuality (int outMediaLinkQuality) {
		this.outMediaLinkQuality = outMediaLinkQuality;
	}
}