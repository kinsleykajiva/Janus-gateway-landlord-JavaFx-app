package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bwe {
	boolean twcc;

	@JsonProperty ("twcc")
	public boolean getTwcc () {
		return this.twcc;
	}

	public void setTwcc (boolean twcc) {
		this.twcc = twcc;
	}
}
