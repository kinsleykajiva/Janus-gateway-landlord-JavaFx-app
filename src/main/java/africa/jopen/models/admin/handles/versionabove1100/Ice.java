package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Ice {
	int               stream_id;
	int               component_id;
	String            state;
	ArrayList<String> localCandidates;
	ArrayList<String> remoteCandidates;
	int               ready;

	@JsonProperty ("stream_id")
	public int getStream_id () {
		return this.stream_id;
	}

	public void setStream_id (int stream_id) {
		this.stream_id = stream_id;
	}

	@JsonProperty ("component_id")
	public int getComponent_id () {
		return this.component_id;
	}

	public void setComponent_id (int component_id) {
		this.component_id = component_id;
	}

	@JsonProperty ("state")
	public String getState () {
		return this.state;
	}

	public void setState (String state) {
		this.state = state;
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

	@JsonProperty ("ready")
	public int getReady () {
		return this.ready;
	}

	public void setReady (int ready) {
		this.ready = ready;
	}
}

