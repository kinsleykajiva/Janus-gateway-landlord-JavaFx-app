package africa.jopen.models.forms.janusconfig.janus;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Loggers {
	Disable disable;

	@JsonProperty ("disable")
	public Disable getDisable () {
		return this.disable;
	}

	public void setDisable (Disable disable) {
		this.disable = disable;
	}
}