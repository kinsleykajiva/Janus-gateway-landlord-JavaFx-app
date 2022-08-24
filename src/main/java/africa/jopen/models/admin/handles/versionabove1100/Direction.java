package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Direction {
	boolean send;
	boolean recv;

	@JsonProperty ("send")
	public boolean getSend () {
		return this.send;
	}

	public void setSend (boolean send) {
		this.send = send;
	}

	@JsonProperty ("recv")
	public boolean getRecv () {
		return this.recv;
	}

	public void setRecv (boolean recv) {
		this.recv = recv;
	}
}