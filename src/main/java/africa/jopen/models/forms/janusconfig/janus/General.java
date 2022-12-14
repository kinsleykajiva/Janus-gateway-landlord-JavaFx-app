package africa.jopen.models.forms.janusconfig.janus;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties (ignoreUnknown = true)
public class General {
	ConfigsFolder         configs_folder;
	PluginsFolder         plugins_folder;
	TransportsFolder      transports_folder;
	EventsFolder          events_folder;
	LoggersFolder         loggers_folder;
	LogToStdout           log_to_stdout;
	LogToFile             log_to_file;
	DebugLevel            debug_level;
	DebugTimestamps       debug_timestamps;
	DebugLocks            debug_locks;
	LogPrefix             log_prefix;
	Daemonize             daemonize;
	PidFile               pid_file;
	ApiSecret             api_secret;
	TokenAuth             token_auth;
	TokenAuthSecret       token_auth_secret;
	AdminSecret           admin_secret;
	Interface             myinterface;
	ServerName            server_name;
	SessionTimeout        session_timeout;
	CandidatesTimeout     candidates_timeout;
	ReclaimSessionTimeout reclaim_session_timeout;
	RecordingsTmpExt      recordings_tmp_ext;
	EventLoops            event_loops;
	OpaqueidInApi         opaqueid_in_api;
	HideDependencies      hide_dependencies;
	NoWebrtcEncryption    no_webrtc_encryption;
	ProtectedFolders      protected_folders;

	@JsonProperty ("configs_folder")
	public ConfigsFolder getConfigs_folder () {
		return this.configs_folder;
	}

	public void setConfigs_folder (ConfigsFolder configs_folder) {
		this.configs_folder = configs_folder;
	}

	@JsonProperty ("plugins_folder")
	public PluginsFolder getPlugins_folder () {
		return this.plugins_folder;
	}

	public void setPlugins_folder (PluginsFolder plugins_folder) {
		this.plugins_folder = plugins_folder;
	}

	@JsonProperty ("transports_folder")
	public TransportsFolder getTransports_folder () {
		return this.transports_folder;
	}

	public void setTransports_folder (TransportsFolder transports_folder) {
		this.transports_folder = transports_folder;
	}

	@JsonProperty ("events_folder")
	public EventsFolder getEvents_folder () {
		return this.events_folder;
	}

	public void setEvents_folder (EventsFolder events_folder) {
		this.events_folder = events_folder;
	}

	@JsonProperty ("loggers_folder")
	public LoggersFolder getLoggers_folder () {
		return this.loggers_folder;
	}

	public void setLoggers_folder (LoggersFolder loggers_folder) {
		this.loggers_folder = loggers_folder;
	}

	@JsonProperty ("log_to_stdout")
	public LogToStdout getLog_to_stdout () {
		return this.log_to_stdout;
	}

	public void setLog_to_stdout (LogToStdout log_to_stdout) {
		this.log_to_stdout = log_to_stdout;
	}

	@JsonProperty ("log_to_file")
	public LogToFile getLog_to_file () {
		return this.log_to_file;
	}

	public void setLog_to_file (LogToFile log_to_file) {
		this.log_to_file = log_to_file;
	}

	@JsonProperty ("debug_level")
	public DebugLevel getDebug_level () {
		return this.debug_level;
	}

	public void setDebug_level (DebugLevel debug_level) {
		this.debug_level = debug_level;
	}

	@JsonProperty ("debug_timestamps")
	public DebugTimestamps getDebug_timestamps () {
		return this.debug_timestamps;
	}

	public void setDebug_timestamps (DebugTimestamps debug_timestamps) {
		this.debug_timestamps = debug_timestamps;
	}

	@JsonProperty ("debug_locks")
	public DebugLocks getDebug_locks () {
		return this.debug_locks;
	}

	public void setDebug_locks (DebugLocks debug_locks) {
		this.debug_locks = debug_locks;
	}

	@JsonProperty ("log_prefix")
	public LogPrefix getLog_prefix () {
		return this.log_prefix;
	}

	public void setLog_prefix (LogPrefix log_prefix) {
		this.log_prefix = log_prefix;
	}

	@JsonProperty ("daemonize")
	public Daemonize getDaemonize () {
		return this.daemonize;
	}

	public void setDaemonize (Daemonize daemonize) {
		this.daemonize = daemonize;
	}

	@JsonProperty ("pid_file")
	public PidFile getPid_file () {
		return this.pid_file;
	}

	public void setPid_file (PidFile pid_file) {
		this.pid_file = pid_file;
	}

	@JsonProperty ("api_secret")
	public ApiSecret getApi_secret () {
		return this.api_secret;
	}

	public void setApi_secret (ApiSecret api_secret) {
		this.api_secret = api_secret;
	}

	@JsonProperty ("token_auth")
	public TokenAuth getToken_auth () {
		return this.token_auth;
	}

	public void setToken_auth (TokenAuth token_auth) {
		this.token_auth = token_auth;
	}

	@JsonProperty ("token_auth_secret")
	public TokenAuthSecret getToken_auth_secret () {
		return this.token_auth_secret;
	}

	public void setToken_auth_secret (TokenAuthSecret token_auth_secret) {
		this.token_auth_secret = token_auth_secret;
	}

	@JsonProperty ("admin_secret")
	public AdminSecret getAdmin_secret () {
		return this.admin_secret;
	}

	public void setAdmin_secret (AdminSecret admin_secret) {
		this.admin_secret = admin_secret;
	}

	@JsonProperty ("interface")
	public Interface getMyinterface () {
		return this.myinterface;
	}

	public void setMyinterface (Interface myinterface) {
		this.myinterface = myinterface;
	}

	@JsonProperty ("server_name")
	public ServerName getServer_name () {
		return this.server_name;
	}

	public void setServer_name (ServerName server_name) {
		this.server_name = server_name;
	}

	@JsonProperty ("session_timeout")
	public SessionTimeout getSession_timeout () {
		return this.session_timeout;
	}

	public void setSession_timeout (SessionTimeout session_timeout) {
		this.session_timeout = session_timeout;
	}

	@JsonProperty ("candidates_timeout")
	public CandidatesTimeout getCandidates_timeout () {
		return this.candidates_timeout;
	}

	public void setCandidates_timeout (CandidatesTimeout candidates_timeout) {
		this.candidates_timeout = candidates_timeout;
	}

	@JsonProperty ("reclaim_session_timeout")
	public ReclaimSessionTimeout getReclaim_session_timeout () {
		return this.reclaim_session_timeout;
	}

	public void setReclaim_session_timeout (ReclaimSessionTimeout reclaim_session_timeout) {
		this.reclaim_session_timeout = reclaim_session_timeout;
	}

	@JsonProperty ("recordings_tmp_ext")
	public RecordingsTmpExt getRecordings_tmp_ext () {
		return this.recordings_tmp_ext;
	}

	public void setRecordings_tmp_ext (RecordingsTmpExt recordings_tmp_ext) {
		this.recordings_tmp_ext = recordings_tmp_ext;
	}

	@JsonProperty ("event_loops")
	public EventLoops getEvent_loops () {
		return this.event_loops;
	}

	public void setEvent_loops (EventLoops event_loops) {
		this.event_loops = event_loops;
	}

	@JsonProperty ("opaqueid_in_api")
	public OpaqueidInApi getOpaqueid_in_api () {
		return this.opaqueid_in_api;
	}

	public void setOpaqueid_in_api (OpaqueidInApi opaqueid_in_api) {
		this.opaqueid_in_api = opaqueid_in_api;
	}

	@JsonProperty ("hide_dependencies")
	public HideDependencies getHide_dependencies () {
		return this.hide_dependencies;
	}

	public void setHide_dependencies (HideDependencies hide_dependencies) {
		this.hide_dependencies = hide_dependencies;
	}

	@JsonProperty ("no_webrtc_encryption")
	public NoWebrtcEncryption getNo_webrtc_encryption () {
		return this.no_webrtc_encryption;
	}

	public void setNo_webrtc_encryption (NoWebrtcEncryption no_webrtc_encryption) {
		this.no_webrtc_encryption = no_webrtc_encryption;
	}

	@JsonProperty ("protected_folders")
	public ProtectedFolders getProtected_folders () {
		return this.protected_folders;
	}

	public void setProtected_folders (ProtectedFolders protected_folders) {
		this.protected_folders = protected_folders;
	}
}
