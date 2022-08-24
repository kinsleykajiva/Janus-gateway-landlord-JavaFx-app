package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rtcp {
	Main main;

	@JsonProperty ("main")
	public Main getMain () {
		return this.main;
	}

	public void setMain (Main main) {
		this.main = main;
	}
}

