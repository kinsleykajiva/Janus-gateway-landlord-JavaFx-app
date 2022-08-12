package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Dtls{
	@JsonProperty("fingerprint")
	public String getFingerprint() {
		return this.fingerprint; }
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint; }
	String fingerprint;
	@JsonProperty("remote-fingerprint")
	public String getRemoteFingerprint() {
		return this.remoteFingerprint; }
	public void setRemoteFingerprint(String remoteFingerprint) {
		this.remoteFingerprint = remoteFingerprint; }
	String remoteFingerprint;
	@JsonProperty("remote-fingerprint-hash")
	public String getRemoteFingerprintHash() {
		return this.remoteFingerprintHash; }
	public void setRemoteFingerprintHash(String remoteFingerprintHash) {
		this.remoteFingerprintHash = remoteFingerprintHash; }
	String remoteFingerprintHash;
	@JsonProperty("dtls-role")
	public String getDtlsRole() {
		return this.dtlsRole; }
	public void setDtlsRole(String dtlsRole) {
		this.dtlsRole = dtlsRole; }
	String dtlsRole;
	@JsonProperty("dtls-state")
	public String getDtlsState() {
		return this.dtlsState; }
	public void setDtlsState(String dtlsState) {
		this.dtlsState = dtlsState; }
	String dtlsState;
	@JsonProperty("retransmissions")
	public int getRetransmissions() {
		return this.retransmissions; }
	public void setRetransmissions(int retransmissions) {
		this.retransmissions = retransmissions; }
	int retransmissions;
	@JsonProperty("valid")
	public boolean getValid() {
		return this.valid; }
	public void setValid(boolean valid) {
		this.valid = valid; }
	boolean valid;
	@JsonProperty("srtp-profile")
	public String getSrtpProfile() {
		return this.srtpProfile; }
	public void setSrtpProfile(String srtpProfile) {
		this.srtpProfile = srtpProfile; }
	String srtpProfile;
	@JsonProperty("ready")
	public boolean getReady() {
		return this.ready; }
	public void setReady(boolean ready) {
		this.ready = ready; }
	boolean ready;
	@JsonProperty("sctp-association")
	public boolean getSctpAssociation() {
		return this.sctpAssociation; }
	public void setSctpAssociation(boolean sctpAssociation) {
		this.sctpAssociation = sctpAssociation; }
	boolean sctpAssociation;
}
