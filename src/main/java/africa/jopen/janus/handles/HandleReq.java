package africa.jopen.janus.handles;

import africa.jopen.janus.settings.SettingsReq;
import africa.jopen.models.admin.handles.HandleInfoRoot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HandleReq {

	static Logger logger = Logger.getLogger( HandleReq.class.getName() );
	private static List<BigInteger>                    lastSessionsList     = new ArrayList<>();
	public static HashMap<BigInteger , HandleInfoRoot> LAST_HANDLESINFO_MAP =new HashMap<>();
	public static List<BigInteger> getSessions(){
		logger.info("getSessions()");
		List<BigInteger> sessions = new ArrayList<>();
		var json = SettingsReq.postJanusRequest("list_sessions","");

		if(json != null){
			JSONObject jsonObject = new JSONObject(json);
			if(jsonObject.getString("janus").equals("success")){
				JSONArray sessionsArray = jsonObject.getJSONArray("sessions");
				for(int i = 0; i < sessionsArray.length(); i++){
					BigInteger session = sessionsArray.getBigInteger(i);
					sessions.add(session);
				}
				return sessions;
			}
		}
		return Collections.emptyList();

	}
	public static List<BigInteger> getHandle(final BigInteger session){
		List<BigInteger> handles = new ArrayList<>();
		var json = SettingsReq.postJanusRequest("list_handles","/".concat(String.valueOf(session)));
		if(json != null){
			JSONObject jsonObject = new JSONObject(json);
			if(jsonObject.getString("janus").equals("success")){
				JSONArray handlesArray = jsonObject.getJSONArray("handles");
				for(int i = 0; i < handlesArray.length(); i++){
					BigInteger handle = handlesArray.getBigInteger(i);
					handles.add(handle);
				}
				lastSessionsList.clear();
				lastSessionsList.addAll(handles);
				return handles;
			}
		}
		return Collections.emptyList();
	}

	public static HandleInfoRoot getHandleInfo(final BigInteger handle,final BigInteger session){

		var json = SettingsReq.postJanusRequest("handle_info","/".concat(String.valueOf(session)).concat("/").concat(String.valueOf(handle)));
		if(json != null){
			JSONObject jsonObject = new JSONObject(json);
			if(jsonObject.getString("janus").equals("success")){
			ObjectMapper om = new ObjectMapper();
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				JSONObject infoObject = jsonObject.getJSONObject("info");
				HandleInfoRoot handleInfoRoot = om.readValue(infoObject.toString(), HandleInfoRoot.class);
				return handleInfoRoot;
			} catch (JsonProcessingException e) {
				logger.severe("Error processing JSON: " +  e.getMessage());
			}
			}
		}
		return null;
	}


public static CompletableFuture<HashMap<BigInteger, HandleInfoRoot>> getSessionsL(){
	HashMap<BigInteger , HandleInfoRoot> handlesInfoMap =new HashMap<>();
	return CompletableFuture.supplyAsync(() -> {
		var sessionsList = getSessions();
		LAST_HANDLESINFO_MAP.clear();
		sessionsList.forEach(s-> getHandle( s).forEach(h->{
			var reslt = getHandleInfo(h,s);
			LAST_HANDLESINFO_MAP.put(s,reslt) ;
			handlesInfoMap.put(s,reslt);
		}));

		return handlesInfoMap;
	});
	/*// Attach a callback to the Future using thenApply()
	CompletableFuture<String> greetingFuture = list_handles.thenApply(name -> {
		return "Hello " + name;
	});*/
}








}
