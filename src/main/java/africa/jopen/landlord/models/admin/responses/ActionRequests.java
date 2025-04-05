package africa.jopen.landlord.models.admin.responses;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ActionRequests {



    // Generic Requests

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record CreateSessionRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction
    ) {
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record InfoRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record PingRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record LoopsInfoRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret
    ) {
    }

    // Configuration-related Requests
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record GetStatusRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret
    ) {
    }

 /*   @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetSessionTimeoutRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("timeout") Integer timeout
    ) {
    }*/

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetLogLevelRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("level") Integer level
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetLogTimestampsRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("enable") Boolean enable
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetLogColorsRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("enable") Boolean enable
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetLockingDebugRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("enable") Boolean enable
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetRefcountDebugRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("enable") Boolean enable
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetLibniceDebugRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("enable") Boolean enable
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetMinNackQueueRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("value") Integer value
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetNoMediaTimerRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("value") Integer value
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetSlowlinkThresholdRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("value") Integer value
    ) {
    }

    // Token-related Requests
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record AddTokenRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("token") String token
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record AllowTokenRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("token") String token,
            @JsonProperty("plugin") String plugin
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record DisallowTokenRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("token") String token,
            @JsonProperty("plugin") String plugin
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record ListTokensRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RemoveTokenRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("token") String token
    ) {
    }

    // Session-related Requests
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record AcceptNewSessionsRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("accept") Boolean accept
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record ListSessionsRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetSessionTimeoutRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId,
            @JsonProperty("timeout") Integer timeout
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record DestroySessionRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId
    ) {
    }

    // Handle- and WebRTC-related Requests
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record ListHandlesRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record HandleInfoRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId,
            @JsonProperty("handle_id") Long handleId,
            @JsonProperty("plugin_only") Boolean pluginOnly
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record StartPcapRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId,
            @JsonProperty("handle_id") Long handleId,
            @JsonProperty("filename") String filename
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record StopPcapRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId,
            @JsonProperty("handle_id") Long handleId
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record StartText2PcapRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId,
            @JsonProperty("handle_id") Long handleId,
            @JsonProperty("filename") String filename
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record StopText2PcapRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId,
            @JsonProperty("handle_id") Long handleId
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record MessagePluginRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId,
            @JsonProperty("handle_id") Long handleId,
            @JsonProperty("body") Object body
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record HangupWebrtcRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId,
            @JsonProperty("handle_id") Long handleId
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record DetachHandleRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("session_id") Long sessionId,
            @JsonProperty("handle_id") Long handleId
    ) {
    }

    // Transport-related Requests
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record QueryTransportRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("transport") String transport,
            @JsonProperty("request") Object request
    ) {
    }

    // Event Handlers-related Requests
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record QueryEventHandlerRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("eventhandler") String eventHandler,
            @JsonProperty("request") Object request
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record CustomEventRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("event") Object event
    ) {
    }

    // Custom Logging-related Requests
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record CustomLogLineRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("level") Integer level,
            @JsonProperty("line") String line
    ) {
    }

    // Helper Requests
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record ResolveAddressRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("address") String address
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record TestStunRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret,
            @JsonProperty("server") String server,
            @JsonProperty("port") Integer port
    ) {
    }












}
