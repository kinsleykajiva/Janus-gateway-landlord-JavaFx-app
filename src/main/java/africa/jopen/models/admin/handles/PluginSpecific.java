package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PluginSpecific {
	String username;
	String authuser;
	String secret;
	String display_name;
	String user_agent;
	String identity;
	String registration_status;
	String call_status;
	String callee;
	String srtpRequired;
	String sdesLocalAudio;
	String sdesLocalVideo;
	String sdesRemoteAudio;
	String sdesRemoteVideo;
	int    establishing;
	int    established;
	int    hangingup;
	int    destroyed;

	@JsonProperty ("username")
	public String getUsername () {
		return this.username;
	}

	public void setUsername (String username) {
		this.username = username;
	}

	@JsonProperty ("authuser")
	public String getAuthuser () {
		return this.authuser;
	}

	public void setAuthuser (String authuser) {
		this.authuser = authuser;
	}

	@JsonProperty ("secret")
	public String getSecret () {
		return this.secret;
	}

	public void setSecret (String secret) {
		this.secret = secret;
	}

	@JsonProperty ("display_name")
	public String getDisplay_name () {
		return this.display_name;
	}

	public void setDisplay_name (String display_name) {
		this.display_name = display_name;
	}

	@JsonProperty ("user_agent")
	public String getUser_agent () {
		return this.user_agent;
	}

	public void setUser_agent (String user_agent) {
		this.user_agent = user_agent;
	}

	@JsonProperty ("identity")
	public String getIdentity () {
		return this.identity;
	}

	public void setIdentity (String identity) {
		this.identity = identity;
	}

	@JsonProperty ("registration_status")
	public String getRegistration_status () {
		return this.registration_status;
	}

	public void setRegistration_status (String registration_status) {
		this.registration_status = registration_status;
	}

	@JsonProperty ("call_status")
	public String getCall_status () {
		return this.call_status;
	}

	public void setCall_status (String call_status) {
		this.call_status = call_status;
	}

	@JsonProperty ("callee")
	public String getCallee () {
		return this.callee;
	}

	public void setCallee (String callee) {
		this.callee = callee;
	}

	@JsonProperty ("srtp-required")
	public String getSrtpRequired () {
		return this.srtpRequired;
	}

	public void setSrtpRequired (String srtpRequired) {
		this.srtpRequired = srtpRequired;
	}

	@JsonProperty ("sdes-local-audio")
	public String getSdesLocalAudio () {
		return this.sdesLocalAudio;
	}

	public void setSdesLocalAudio (String sdesLocalAudio) {
		this.sdesLocalAudio = sdesLocalAudio;
	}

	@JsonProperty ("sdes-local-video")
	public String getSdesLocalVideo () {
		return this.sdesLocalVideo;
	}

	public void setSdesLocalVideo (String sdesLocalVideo) {
		this.sdesLocalVideo = sdesLocalVideo;
	}

	@JsonProperty ("sdes-remote-audio")
	public String getSdesRemoteAudio () {
		return this.sdesRemoteAudio;
	}

	public void setSdesRemoteAudio (String sdesRemoteAudio) {
		this.sdesRemoteAudio = sdesRemoteAudio;
	}

	@JsonProperty ("sdes-remote-video")
	public String getSdesRemoteVideo () {
		return this.sdesRemoteVideo;
	}

	public void setSdesRemoteVideo (String sdesRemoteVideo) {
		this.sdesRemoteVideo = sdesRemoteVideo;
	}

	@JsonProperty ("establishing")
	public int getEstablishing () {
		return this.establishing;
	}

	public void setEstablishing (int establishing) {
		this.establishing = establishing;
	}

	@JsonProperty ("established")
	public int getEstablished () {
		return this.established;
	}

	public void setEstablished (int established) {
		this.established = established;
	}

	@JsonProperty ("hangingup")
	public int getHangingup () {
		return this.hangingup;
	}

	public void setHangingup (int hangingup) {
		this.hangingup = hangingup;
	}

	@JsonProperty ("destroyed")
	public int getDestroyed () {
		return this.destroyed;
	}

	public void setDestroyed (int destroyed) {
		this.destroyed = destroyed;
	}
}