package africa.jopen.models.admin.handles.versionabove1100;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Webrtc {
	Ice        ice;
	Dtls       dtls;
	Extensions extensions;
	Bwe        bwe;
	Media      media;

	@JsonProperty ("ice")
	public Ice getIce () {
		return this.ice;
	}

	public void setIce (Ice ice) {
		this.ice = ice;
	}

	@JsonProperty ("dtls")
	public Dtls getDtls () {
		return this.dtls;
	}

	public void setDtls (Dtls dtls) {
		this.dtls = dtls;
	}

	@JsonProperty ("extensions")
	public Extensions getExtensions () {
		return this.extensions;
	}

	public void setExtensions (Extensions extensions) {
		this.extensions = extensions;
	}

	@JsonProperty ("bwe")
	public Bwe getBwe () {
		return this.bwe;
	}

	public void setBwe (Bwe bwe) {
		this.bwe = bwe;
	}

	@JsonProperty ("media")
	public Media getMedia () {
		return this.media;
	}

	public void setMedia (Media media) {
		this.media = media;
	}
}
