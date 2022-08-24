package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bwe {
	boolean twcc;
	int     twccExtId;

	@JsonProperty ("twcc")
	public boolean getTwcc () {
		return this.twcc;
	}

	public void setTwcc (boolean twcc) {
		this.twcc = twcc;
	}

	@JsonProperty ("twcc-ext-id")
	public int getTwccExtId () {
		return this.twccExtId;
	}

	public void setTwccExtId (int twccExtId) {
		this.twccExtId = twccExtId;
	}
}
