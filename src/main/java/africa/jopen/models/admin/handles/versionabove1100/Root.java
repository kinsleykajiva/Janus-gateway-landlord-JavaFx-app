package africa.jopen.models.admin.handles.versionabove1100;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {
	long           session_id;
	long           session_last_activity;
	int            session_timeout;
	String         session_transport;
	long           handle_id;
	boolean        loopRunning;
	long           created;
	long           current_time;
	String         plugin;
	PluginSpecific plugin_specific;
	Flags          flags;
	long           agentCreated;
	String         iceMode;
	String         iceRole;
	Sdps           sdps;
	int            queuedPackets;
	Webrtc         webrtc;

	@JsonProperty ("session_id")
	public long getSession_id () {
		return this.session_id;
	}

	public void setSession_id (long session_id) {
		this.session_id = session_id;
	}

	@JsonProperty ("session_last_activity")
	public long getSession_last_activity () {
		return this.session_last_activity;
	}

	public void setSession_last_activity (long session_last_activity) {
		this.session_last_activity = session_last_activity;
	}

	@JsonProperty ("session_timeout")
	public int getSession_timeout () {
		return this.session_timeout;
	}

	public void setSession_timeout (int session_timeout) {
		this.session_timeout = session_timeout;
	}

	@JsonProperty ("session_transport")
	public String getSession_transport () {
		return this.session_transport;
	}

	public void setSession_transport (String session_transport) {
		this.session_transport = session_transport;
	}

	@JsonProperty ("handle_id")
	public long getHandle_id () {
		return this.handle_id;
	}

	public void setHandle_id (long handle_id) {
		this.handle_id = handle_id;
	}

	@JsonProperty ("loop-running")
	public boolean getLoopRunning () {
		return this.loopRunning;
	}

	public void setLoopRunning (boolean loopRunning) {
		this.loopRunning = loopRunning;
	}

	@JsonProperty ("created")
	public long getCreated () {
		return this.created;
	}

	public void setCreated (long created) {
		this.created = created;
	}

	@JsonProperty ("current_time")
	public long getCurrent_time () {
		return this.current_time;
	}

	public void setCurrent_time (long current_time) {
		this.current_time = current_time;
	}

	@JsonProperty ("plugin")
	public String getPlugin () {
		return this.plugin;
	}

	public void setPlugin (String plugin) {
		this.plugin = plugin;
	}

	@JsonProperty ("plugin_specific")
	public PluginSpecific getPlugin_specific () {
		return this.plugin_specific;
	}

	public void setPlugin_specific (PluginSpecific plugin_specific) {
		this.plugin_specific = plugin_specific;
	}

	@JsonProperty ("flags")
	public Flags getFlags () {
		return this.flags;
	}

	public void setFlags (Flags flags) {
		this.flags = flags;
	}

	@JsonProperty ("agent-created")
	public long getAgentCreated () {
		return this.agentCreated;
	}

	public void setAgentCreated (long agentCreated) {
		this.agentCreated = agentCreated;
	}

	@JsonProperty ("ice-mode")
	public String getIceMode () {
		return this.iceMode;
	}

	public void setIceMode (String iceMode) {
		this.iceMode = iceMode;
	}

	@JsonProperty ("ice-role")
	public String getIceRole () {
		return this.iceRole;
	}

	public void setIceRole (String iceRole) {
		this.iceRole = iceRole;
	}

	@JsonProperty ("sdps")
	public Sdps getSdps () {
		return this.sdps;
	}

	public void setSdps (Sdps sdps) {
		this.sdps = sdps;
	}

	@JsonProperty ("queued-packets")
	public int getQueuedPackets () {
		return this.queuedPackets;
	}

	public void setQueuedPackets (int queuedPackets) {
		this.queuedPackets = queuedPackets;
	}

	@JsonProperty ("webrtc")
	public Webrtc getWebrtc () {
		return this.webrtc;
	}

	public void setWebrtc (Webrtc webrtc) {
		this.webrtc = webrtc;
	}
}
