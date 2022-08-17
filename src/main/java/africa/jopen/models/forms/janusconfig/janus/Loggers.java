package africa.jopen.models.forms.janusconfig.janus;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Loggers {
	@JsonProperty ("disable")
	public Disable getDisable () {
		return this.disable;
	}

	public void setDisable (Disable disable) {
		this.disable = disable;
	}

	Disable disable;
}