package africa.jopen.models.admin.handles;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Flags {
	boolean gotOffer;
	boolean gotAnswer;
	boolean negotiated;
	boolean processingOffer;
	boolean starting;
	boolean iceRestart;
	boolean ready;
	boolean stopped;
	boolean alert;
	boolean trickle;
	boolean allTrickles;
	boolean resendTrickles;
	boolean trickleSynced;
	boolean dataChannels;
	boolean hasAudio;
	boolean hasVideo;
	boolean newDatachanSdp;
	boolean rfc4588Rtx;
	boolean cleaning;
	boolean e2ee;

	@JsonProperty ("got-offer")
	public boolean getGotOffer () {
		return this.gotOffer;
	}

	public void setGotOffer (boolean gotOffer) {
		this.gotOffer = gotOffer;
	}

	@JsonProperty ("got-answer")
	public boolean getGotAnswer () {
		return this.gotAnswer;
	}

	public void setGotAnswer (boolean gotAnswer) {
		this.gotAnswer = gotAnswer;
	}

	@JsonProperty ("negotiated")
	public boolean getNegotiated () {
		return this.negotiated;
	}

	public void setNegotiated (boolean negotiated) {
		this.negotiated = negotiated;
	}

	@JsonProperty ("processing-offer")
	public boolean getProcessingOffer () {
		return this.processingOffer;
	}

	public void setProcessingOffer (boolean processingOffer) {
		this.processingOffer = processingOffer;
	}

	@JsonProperty ("starting")
	public boolean getStarting () {
		return this.starting;
	}

	public void setStarting (boolean starting) {
		this.starting = starting;
	}

	@JsonProperty ("ice-restart")
	public boolean getIceRestart () {
		return this.iceRestart;
	}

	public void setIceRestart (boolean iceRestart) {
		this.iceRestart = iceRestart;
	}

	@JsonProperty ("ready")
	public boolean getReady () {
		return this.ready;
	}

	public void setReady (boolean ready) {
		this.ready = ready;
	}

	@JsonProperty ("stopped")
	public boolean getStopped () {
		return this.stopped;
	}

	public void setStopped (boolean stopped) {
		this.stopped = stopped;
	}

	@JsonProperty ("alert")
	public boolean getAlert () {
		return this.alert;
	}

	public void setAlert (boolean alert) {
		this.alert = alert;
	}

	@JsonProperty ("trickle")
	public boolean getTrickle () {
		return this.trickle;
	}

	public void setTrickle (boolean trickle) {
		this.trickle = trickle;
	}

	@JsonProperty ("all-trickles")
	public boolean getAllTrickles () {
		return this.allTrickles;
	}

	public void setAllTrickles (boolean allTrickles) {
		this.allTrickles = allTrickles;
	}

	@JsonProperty ("resend-trickles")
	public boolean getResendTrickles () {
		return this.resendTrickles;
	}

	public void setResendTrickles (boolean resendTrickles) {
		this.resendTrickles = resendTrickles;
	}

	@JsonProperty ("trickle-synced")
	public boolean getTrickleSynced () {
		return this.trickleSynced;
	}

	public void setTrickleSynced (boolean trickleSynced) {
		this.trickleSynced = trickleSynced;
	}

	@JsonProperty ("data-channels")
	public boolean getDataChannels () {
		return this.dataChannels;
	}

	public void setDataChannels (boolean dataChannels) {
		this.dataChannels = dataChannels;
	}

	@JsonProperty ("has-audio")
	public boolean getHasAudio () {
		return this.hasAudio;
	}

	public void setHasAudio (boolean hasAudio) {
		this.hasAudio = hasAudio;
	}

	@JsonProperty ("has-video")
	public boolean getHasVideo () {
		return this.hasVideo;
	}

	public void setHasVideo (boolean hasVideo) {
		this.hasVideo = hasVideo;
	}

	@JsonProperty ("new-datachan-sdp")
	public boolean getNewDatachanSdp () {
		return this.newDatachanSdp;
	}

	public void setNewDatachanSdp (boolean newDatachanSdp) {
		this.newDatachanSdp = newDatachanSdp;
	}

	@JsonProperty ("rfc4588-rtx")
	public boolean getRfc4588Rtx () {
		return this.rfc4588Rtx;
	}

	public void setRfc4588Rtx (boolean rfc4588Rtx) {
		this.rfc4588Rtx = rfc4588Rtx;
	}

	@JsonProperty ("cleaning")
	public boolean getCleaning () {
		return this.cleaning;
	}

	public void setCleaning (boolean cleaning) {
		this.cleaning = cleaning;
	}

	@JsonProperty ("e2ee")
	public boolean getE2ee () {
		return this.e2ee;
	}

	public void setE2ee (boolean e2ee) {
		this.e2ee = e2ee;
	}
}

