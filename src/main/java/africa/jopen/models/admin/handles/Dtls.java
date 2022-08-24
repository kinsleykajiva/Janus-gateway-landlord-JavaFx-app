package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Dtls {
	String  fingerprint;
	String  remoteFingerprint;
	String  remoteFingerprintHash;
	String  dtlsRole;
	String  dtlsState;
	int     retransmissions;
	boolean valid;
	String  srtpProfile;
	boolean ready;
	boolean sctpAssociation;

	@JsonProperty ("fingerprint")
	public String getFingerprint () {
		return this.fingerprint;
	}

	public void setFingerprint (String fingerprint) {
		this.fingerprint = fingerprint;
	}

	@JsonProperty ("remote-fingerprint")
	public String getRemoteFingerprint () {
		return this.remoteFingerprint;
	}

	public void setRemoteFingerprint (String remoteFingerprint) {
		this.remoteFingerprint = remoteFingerprint;
	}

	@JsonProperty ("remote-fingerprint-hash")
	public String getRemoteFingerprintHash () {
		return this.remoteFingerprintHash;
	}

	public void setRemoteFingerprintHash (String remoteFingerprintHash) {
		this.remoteFingerprintHash = remoteFingerprintHash;
	}

	@JsonProperty ("dtls-role")
	public String getDtlsRole () {
		return this.dtlsRole;
	}

	public void setDtlsRole (String dtlsRole) {
		this.dtlsRole = dtlsRole;
	}

	@JsonProperty ("dtls-state")
	public String getDtlsState () {
		return this.dtlsState;
	}

	public void setDtlsState (String dtlsState) {
		this.dtlsState = dtlsState;
	}

	@JsonProperty ("retransmissions")
	public int getRetransmissions () {
		return this.retransmissions;
	}

	public void setRetransmissions (int retransmissions) {
		this.retransmissions = retransmissions;
	}

	@JsonProperty ("valid")
	public boolean getValid () {
		return this.valid;
	}

	public void setValid (boolean valid) {
		this.valid = valid;
	}

	@JsonProperty ("srtp-profile")
	public String getSrtpProfile () {
		return this.srtpProfile;
	}

	public void setSrtpProfile (String srtpProfile) {
		this.srtpProfile = srtpProfile;
	}

	@JsonProperty ("ready")
	public boolean getReady () {
		return this.ready;
	}

	public void setReady (boolean ready) {
		this.ready = ready;
	}

	@JsonProperty ("sctp-association")
	public boolean getSctpAssociation () {
		return this.sctpAssociation;
	}

	public void setSctpAssociation (boolean sctpAssociation) {
		this.sctpAssociation = sctpAssociation;
	}
}
