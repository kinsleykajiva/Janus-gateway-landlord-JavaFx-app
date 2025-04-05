package africa.jopen.landlord.network;

import africa.jopen.landlord.models.admin.responses.ActionRequests;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class JanusAPIUtils {
    private static final String ADMIN_ENDPOINT = "admin";
    private static final String CORE_ENDPOINT = "janus";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Utility to generate transaction ID
    private static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    // Core API Method: Create a new session
    public static Long sendCreateSession(JanusAdminHttpClient httpClient) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        ActionRequests.CreateSessionRequest request = new ActionRequests.CreateSessionRequest("create", generateTransactionId());
        String response = httpClient.sendPostRequest(CORE_ENDPOINT, request);
        JsonNode responseNode = objectMapper.readTree(response);
        if ("success".equals(responseNode.get("janus").asText())) {
            return responseNode.get("data").get("id").asLong();
        } else {
            throw new IOException("Failed to create session: " + response);
        }
    }

    // Generic Requests (No session_id required - server-wide info)
    public static String sendInfo(JanusAdminHttpClient httpClient) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        ActionRequests.InfoRequest request = new ActionRequests.InfoRequest("info", generateTransactionId());
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendPing(JanusAdminHttpClient httpClient) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        ActionRequests.PingRequest request = new ActionRequests.PingRequest("ping", generateTransactionId());
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendLoopsInfo(JanusAdminHttpClient httpClient, String adminSecret) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        ActionRequests.LoopsInfoRequest request = new ActionRequests.LoopsInfoRequest("loops_info", generateTransactionId(), adminSecret);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    // Configuration-related Requests (No session_id required - global settings)
    public static String sendGetStatus(JanusAdminHttpClient httpClient, String adminSecret) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        ActionRequests.GetStatusRequest request = new ActionRequests.GetStatusRequest("get_status", generateTransactionId(), adminSecret);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }



    public static String sendSetLogLevel(JanusAdminHttpClient httpClient, String adminSecret, Integer level) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(level, "level must not be null");
        ActionRequests.SetLogLevelRequest request = new ActionRequests.SetLogLevelRequest("set_log_level", generateTransactionId(), adminSecret, level);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendSetLogTimestamps(JanusAdminHttpClient httpClient, String adminSecret, Boolean enable) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(enable, "enable must not be null");
        ActionRequests.SetLogTimestampsRequest request = new ActionRequests.SetLogTimestampsRequest("set_log_timestamps", generateTransactionId(), adminSecret, enable);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendSetLogColors(JanusAdminHttpClient httpClient, String adminSecret, Boolean enable) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(enable, "enable must not be null");
        ActionRequests.SetLogColorsRequest request = new ActionRequests.SetLogColorsRequest("set_log_colors", generateTransactionId(), adminSecret, enable);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendSetLockingDebug(JanusAdminHttpClient httpClient, String adminSecret, Boolean enable) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(enable, "enable must not be null");
        ActionRequests.SetLockingDebugRequest request = new ActionRequests.SetLockingDebugRequest("set_locking_debug", generateTransactionId(), adminSecret, enable);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendSetRefcountDebug(JanusAdminHttpClient httpClient, String adminSecret, Boolean enable) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(enable, "enable must not be null");
        ActionRequests.SetRefcountDebugRequest request = new ActionRequests.SetRefcountDebugRequest("set_refcount_debug", generateTransactionId(), adminSecret, enable);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendSetLibniceDebug(JanusAdminHttpClient httpClient, String adminSecret, Boolean enable) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(enable, "enable must not be null");
        ActionRequests.SetLibniceDebugRequest request = new ActionRequests.SetLibniceDebugRequest("set_libnice_debug", generateTransactionId(), adminSecret, enable);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendSetMinNackQueue(JanusAdminHttpClient httpClient, String adminSecret, Integer value) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(value, "value must not be null");
        ActionRequests.SetMinNackQueueRequest request = new ActionRequests.SetMinNackQueueRequest("set_min_nack_queue", generateTransactionId(), adminSecret, value);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendSetNoMediaTimer(JanusAdminHttpClient httpClient, String adminSecret, Integer value) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(value, "value must not be null");
        ActionRequests.SetNoMediaTimerRequest request = new ActionRequests.SetNoMediaTimerRequest("set_no_media_timer", generateTransactionId(), adminSecret, value);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendSetSlowlinkThreshold(JanusAdminHttpClient httpClient, String adminSecret, Integer value) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(value, "value must not be null");
        ActionRequests.SetSlowlinkThresholdRequest request = new ActionRequests.SetSlowlinkThresholdRequest("set_slowlink_threshold", generateTransactionId(), adminSecret, value);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    // Token-related Requests (No session_id required - token management)
    public static String sendAddToken(JanusAdminHttpClient httpClient, String adminSecret, String token) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(token, "token must not be null");
        ActionRequests.AddTokenRequest request = new ActionRequests.AddTokenRequest("add_token", generateTransactionId(), adminSecret, token);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendAllowToken(JanusAdminHttpClient httpClient, String adminSecret, String token, String plugin) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(token, "token must not be null");
        Objects.requireNonNull(plugin, "plugin must not be null");
        ActionRequests.AllowTokenRequest request = new ActionRequests.AllowTokenRequest("allow_token", generateTransactionId(), adminSecret, token, plugin);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendDisallowToken(JanusAdminHttpClient httpClient, String adminSecret, String token, String plugin) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(token, "token must not be null");
        Objects.requireNonNull(plugin, "plugin must not be null");
        ActionRequests.DisallowTokenRequest request = new ActionRequests.DisallowTokenRequest("disallow_token", generateTransactionId(), adminSecret, token, plugin);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendListTokens(JanusAdminHttpClient httpClient, String adminSecret) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        ActionRequests.ListTokensRequest request = new ActionRequests.ListTokensRequest("list_tokens", generateTransactionId(), adminSecret);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendRemoveToken(JanusAdminHttpClient httpClient, String adminSecret, String token) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(token, "token must not be null");
        ActionRequests.RemoveTokenRequest request = new ActionRequests.RemoveTokenRequest("remove_token", generateTransactionId(), adminSecret, token);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    // Session-related Requests (session_id required where applicable)
    public static String sendAcceptNewSessions(JanusAdminHttpClient httpClient, String adminSecret, Boolean accept) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(accept, "accept must not be null");
        ActionRequests.AcceptNewSessionsRequest request = new ActionRequests.AcceptNewSessionsRequest("accept_new_sessions", generateTransactionId(), adminSecret, accept);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendListSessions(JanusAdminHttpClient httpClient, String adminSecret) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        ActionRequests.ListSessionsRequest request = new ActionRequests.ListSessionsRequest("list_sessions", generateTransactionId(), adminSecret);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendSetSessionTimeout(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId, Integer timeout) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(timeout, "timeout must not be null");
        ActionRequests.SetSessionTimeoutRequest request = new ActionRequests.SetSessionTimeoutRequest("set_session_timeout", generateTransactionId(), adminSecret, sessionId, timeout);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendDestroySession(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        ActionRequests.DestroySessionRequest request = new ActionRequests.DestroySessionRequest("destroy_session", generateTransactionId(), adminSecret, sessionId);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    // Handle- and WebRTC-related Requests (session_id and handle_id required)
    public static String sendListHandles(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        ActionRequests.ListHandlesRequest request = new ActionRequests.ListHandlesRequest("list_handles", generateTransactionId(), adminSecret, sessionId);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendHandleInfo(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId, Long handleId, Boolean pluginOnly) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(handleId, "handleId must not be null");
        Objects.requireNonNull(pluginOnly, "pluginOnly must not be null");
        ActionRequests.HandleInfoRequest request = new ActionRequests.HandleInfoRequest("handle_info", generateTransactionId(), adminSecret, sessionId, handleId, pluginOnly);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendStartPcap(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId, Long handleId, String filename) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(handleId, "handleId must not be null");
        Objects.requireNonNull(filename, "filename must not be null");
        ActionRequests.StartPcapRequest request = new ActionRequests.StartPcapRequest("start_pcap", generateTransactionId(), adminSecret, sessionId, handleId, filename);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendStopPcap(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId, Long handleId) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(handleId, "handleId must not be null");
        ActionRequests.StopPcapRequest request = new ActionRequests.StopPcapRequest("stop_pcap", generateTransactionId(), adminSecret, sessionId, handleId);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendStartText2Pcap(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId, Long handleId, String filename) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(handleId, "handleId must not be null");
        Objects.requireNonNull(filename, "filename must not be null");
        ActionRequests.StartText2PcapRequest request = new ActionRequests.StartText2PcapRequest("start_text2pcap", generateTransactionId(), adminSecret, sessionId, handleId, filename);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendStopText2Pcap(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId, Long handleId) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(handleId, "handleId must not be null");
        ActionRequests.StopText2PcapRequest request = new ActionRequests.StopText2PcapRequest("stop_text2pcap", generateTransactionId(), adminSecret, sessionId, handleId);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendMessagePlugin(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId, Long handleId, Object body) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(handleId, "handleId must not be null");
        Objects.requireNonNull(body, "body must not be null");
        ActionRequests.MessagePluginRequest request = new ActionRequests.MessagePluginRequest("message_plugin", generateTransactionId(), adminSecret, sessionId, handleId, body);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendHangupWebrtc(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId, Long handleId) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(handleId, "handleId must not be null");
        ActionRequests.HangupWebrtcRequest request = new ActionRequests.HangupWebrtcRequest("hangup_webrtc", generateTransactionId(), adminSecret, sessionId, handleId);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendDetachHandle(JanusAdminHttpClient httpClient, String adminSecret, Long sessionId, Long handleId) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(handleId, "handleId must not be null");
        ActionRequests.DetachHandleRequest request = new ActionRequests.DetachHandleRequest("detach_handle", generateTransactionId(), adminSecret, sessionId, handleId);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    // Transport-related Requests (No session_id required - transport-specific)
    public static String sendQueryTransport(JanusAdminHttpClient httpClient, String adminSecret, String transport, Object requestBody) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(transport, "transport must not be null");
        Objects.requireNonNull(requestBody, "requestBody must not be null");
        ActionRequests.QueryTransportRequest request = new ActionRequests.QueryTransportRequest("query_transport", generateTransactionId(), adminSecret, transport, requestBody);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    // Event Handlers-related Requests (No session_id required - event handler-specific)
    public static String sendQueryEventHandler(JanusAdminHttpClient httpClient, String adminSecret, String eventHandler, Object requestBody) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(eventHandler, "eventHandler must not be null");
        Objects.requireNonNull(requestBody, "requestBody must not be null");
        ActionRequests.QueryEventHandlerRequest request = new ActionRequests.QueryEventHandlerRequest("query_eventhandler", generateTransactionId(), adminSecret, eventHandler, requestBody);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendCustomEvent(JanusAdminHttpClient httpClient, String adminSecret, Object event) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(event, "event must not be null");
        ActionRequests.CustomEventRequest request = new ActionRequests.CustomEventRequest("custom_event", generateTransactionId(), adminSecret, event);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    // Custom Logging-related Requests (No session_id required - logging-specific)
    public static String sendCustomLogLine(JanusAdminHttpClient httpClient, String adminSecret, Integer level, String line) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(level, "level must not be null");
        Objects.requireNonNull(line, "line must not be null");
        ActionRequests.CustomLogLineRequest request = new ActionRequests.CustomLogLineRequest("custom_logline", generateTransactionId(), adminSecret, level, line);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    // Helper Requests (No session_id required - diagnostic tools)
    public static String sendResolveAddress(JanusAdminHttpClient httpClient, String adminSecret, String address) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(address, "address must not be null");
        ActionRequests.ResolveAddressRequest request = new ActionRequests.ResolveAddressRequest("resolve_address", generateTransactionId(), adminSecret, address);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }

    public static String sendTestStun(JanusAdminHttpClient httpClient, String adminSecret, String server, Integer port) throws IOException, InterruptedException {
        Objects.requireNonNull(httpClient, "httpClient must not be null");
        Objects.requireNonNull(adminSecret, "adminSecret must not be null");
        Objects.requireNonNull(server, "server must not be null");
        Objects.requireNonNull(port, "port must not be null");
        ActionRequests.TestStunRequest request = new ActionRequests.TestStunRequest("test_stun", generateTransactionId(), adminSecret, server, port);
        return httpClient.sendPostRequest(ADMIN_ENDPOINT, request);
    }
}