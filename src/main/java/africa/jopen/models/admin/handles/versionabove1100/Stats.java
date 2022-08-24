package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
	In  in;
	Out out;

	@JsonProperty ("in")
	public In getIn () {
		return this.in;
	}

	public void setIn (In in) {
		this.in = in;
	}

	@JsonProperty ("out")
	public Out getOut () {
		return this.out;
	}

	public void setOut (Out out) {
		this.out = out;
	}
}
