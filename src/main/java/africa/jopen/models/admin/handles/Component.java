package africa.jopen.models.admin.handles;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Component {
	int               id;
	String            state;
	long              failedDetected;
	boolean           icetimerStarted;
	ArrayList<String> localCandidates;
	ArrayList<String> remoteCandidates;
	Dtls              dtls;
	InStats           in_stats;
	OutStats          out_stats;

	@JsonProperty ("id")
	public int getId () {
		return this.id;
	}

	public void setId (int id) {
		this.id = id;
	}

	@JsonProperty ("state")
	public String getState () {
		return this.state;
	}

	public void setState (String state) {
		this.state = state;
	}

	@JsonProperty ("failed-detected")
	public long getFailedDetected () {
		return this.failedDetected;
	}

	public void setFailedDetected (long failedDetected) {
		this.failedDetected = failedDetected;
	}

	@JsonProperty ("icetimer-started")
	public boolean getIcetimerStarted () {
		return this.icetimerStarted;
	}

	public void setIcetimerStarted (boolean icetimerStarted) {
		this.icetimerStarted = icetimerStarted;
	}

	@JsonProperty ("local-candidates")
	public ArrayList<String> getLocalCandidates () {
		return this.localCandidates;
	}

	public void setLocalCandidates (ArrayList<String> localCandidates) {
		this.localCandidates = localCandidates;
	}

	@JsonProperty ("remote-candidates")
	public ArrayList<String> getRemoteCandidates () {
		return this.remoteCandidates;
	}

	public void setRemoteCandidates (ArrayList<String> remoteCandidates) {
		this.remoteCandidates = remoteCandidates;
	}

	@JsonProperty ("dtls")
	public Dtls getDtls () {
		return this.dtls;
	}

	public void setDtls (Dtls dtls) {
		this.dtls = dtls;
	}

	@JsonProperty ("in_stats")
	public InStats getIn_stats () {
		return this.in_stats;
	}

	public void setIn_stats (InStats in_stats) {
		this.in_stats = in_stats;
	}

	@JsonProperty ("out_stats")
	public OutStats getOut_stats () {
		return this.out_stats;
	}

	public void setOut_stats (OutStats out_stats) {
		this.out_stats = out_stats;
	}
}