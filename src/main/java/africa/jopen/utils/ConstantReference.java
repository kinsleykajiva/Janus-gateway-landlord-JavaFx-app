package africa.jopen.utils;

public class ConstantReference {
	private ConstantReference(){}

	public static final String JSON_LINE_VALUE="lineValue";
	public static final String CONFIG_KEY_DEFAULT="default";

	public static  String JANUS_SERVER_BASE_URL="http://0.0.0.0";
	public static  Integer JANUS_SERVER_ADMIN_PORT=7088;
	public static  String JANUS_SERVER_URL=JANUS_SERVER_BASE_URL+":"+JANUS_SERVER_ADMIN_PORT;
	public static  String LANDLORDWEBAPP_SERVER_URL=JANUS_SERVER_BASE_URL+":2087";/*this port is the port of the server app running on the server where janus is hosted*/


}
