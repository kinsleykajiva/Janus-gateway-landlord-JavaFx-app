package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Sdps {
	String profile;
	String local;
	String remote;

	@JsonProperty ("profile")
	public String getProfile () {
		return this.profile;
	}

	public void setProfile (String profile) {
		this.profile = profile;
	}

	@JsonProperty ("local")
	public String getLocal () {
		return this.local;
	}

	public void setLocal (String local) {
		this.local = local;
	}

	@JsonProperty ("remote")
	public String getRemote () {
		return this.remote;
	}

	public void setRemote (String remote) {
		this.remote = remote;
	}
}

