package africa.jopen.utils;

public class ConstantReference {
	public static final String PROCESS_RESULT      = " Process Result";
	public static final String EXECUTE_TASK_FAILED = "Execute task failed ";
	public static final String JANUS_CONFIG        = "JanusConfig";
	public static final String SETTINGS            = "Settings";
	public static final String SESSIONS            = "Sessions";
	public static final String JSON_LINE_VALUE    = "lineValue";
	public static final String CONFIG_KEY_DEFAULT = "default";
	public static String  JANUS_SERVER_BASE_URL                     = "http://0.0.0.0";
	public static int     JANUS_SERVER_HTTP_PORT                    = 8088;
	public static String  JANUS_SERVER_ADMIN_BASE_PATH              = "/admin";
	public static String  LANDLORDWEBAPP_SERVER_BASIC_AUTH_USERNAME = "";
	public static String  LANDLORDWEBAPP_SERVER_BASIC_AUTH_PASSWORD = "";
	public static int     LANDLORDWEBAPP_SERVER_PORT                = 2087;
	public static int     JANUS_ADMIN_SESSION_INTERVAL_DELAY        = 20;
	public static Integer JANUS_SERVER_ADMIN_PORT                   = 7088;
	public static String  JANUS_SERVER__HTTP_URL                    = JANUS_SERVER_BASE_URL + ":" + JANUS_SERVER_HTTP_PORT;
	public static String  JANUS_SERVER_URL                          = JANUS_SERVER_BASE_URL + ":" + JANUS_SERVER_ADMIN_PORT;
	public static String  LANDLORDWEBAPP_SERVER_URL                 = JANUS_SERVER_BASE_URL + ":" + LANDLORDWEBAPP_SERVER_PORT;/*this port is the port of the server app running on the server where janus is hosted*/
	private ConstantReference () {
	}


}
