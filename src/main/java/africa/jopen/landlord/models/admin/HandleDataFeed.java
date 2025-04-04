package africa.jopen.landlord.models.admin;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.util.Map;
public class HandleDataFeed {
    public record Response(
            @JsonProperty("janus") @Nullable String janus,
            @JsonProperty("session_id") @Nullable Long sessionId,
            @JsonProperty("transaction") @Nullable String transaction,
            @JsonProperty("handle_id") @Nullable Long handleId,
            @JsonProperty("info") @Nullable Info info
    ) {}

    record Info(
            @JsonProperty("session_id") @Nullable Long sessionId,
            @JsonProperty("session_last_activity") @Nullable Long sessionLastActivity,
            @JsonProperty("session_timeout") @Nullable Integer sessionTimeout,
            @JsonProperty("session_transport") @Nullable String sessionTransport,
            @JsonProperty("handle_id") @Nullable Long handleId,
            @JsonProperty("loop-running") @Nullable Boolean loopRunning,
            @JsonProperty("created") @Nullable Long created,
            @JsonProperty("current_time") @Nullable Long currentTime,
            @JsonProperty("plugin") @Nullable String plugin,
            @JsonProperty("plugin_specific") @Nullable PluginSpecific pluginSpecific,
            @JsonProperty("flags") @Nullable Flags flags,
            @JsonProperty("sdps") @Nullable Map<String, Object> sdps,
            @JsonProperty("queued-packets") @Nullable Integer queuedPackets
    ) {}

    record PluginSpecific(
            @JsonProperty("username") @Nullable String username,
            @JsonProperty("authuser") @Nullable String authUser,
            @JsonProperty("secret") @Nullable String secret,
            @JsonProperty("display_name") @Nullable String displayName,
            @JsonProperty("user_agent") @Nullable String userAgent,
            @JsonProperty("identity") @Nullable String identity,
            @JsonProperty("registration_status") @Nullable String registrationStatus,
            @JsonProperty("call_status") @Nullable String callStatus,
            @JsonProperty("establishing") @Nullable Integer establishing,
            @JsonProperty("established") @Nullable Integer established,
            @JsonProperty("hangingup") @Nullable Integer hangingUp,
            @JsonProperty("destroyed") @Nullable Integer destroyed
    ) {}
    record Flags(
            @JsonProperty("got-offer") @Nullable Boolean gotOffer,
            @JsonProperty("got-answer") @Nullable Boolean gotAnswer,
            @JsonProperty("negotiated") @Nullable Boolean negotiated,
            @JsonProperty("processing-offer") @Nullable Boolean processingOffer,
            @JsonProperty("starting") @Nullable Boolean starting,
            @JsonProperty("ice-restart") @Nullable Boolean iceRestart,
            @JsonProperty("ready") @Nullable Boolean ready,
            @JsonProperty("stopped") @Nullable Boolean stopped,
            @JsonProperty("alert") @Nullable Boolean alert,
            @JsonProperty("trickle") @Nullable Boolean trickle,
            @JsonProperty("all-trickles") @Nullable Boolean allTrickles,
            @JsonProperty("resend-trickles") @Nullable Boolean resendTrickles,
            @JsonProperty("trickle-synced") @Nullable Boolean trickleSynced,
            @JsonProperty("data-channels") @Nullable Boolean dataChannels,
            @JsonProperty("has-audio") @Nullable Boolean hasAudio,
            @JsonProperty("has-video") @Nullable Boolean hasVideo,
            @JsonProperty("new-datachan-sdp") @Nullable Boolean newDataChanSdp,
            @JsonProperty("rfc4588-rtx") @Nullable Boolean rfc4588Rtx,
            @JsonProperty("cleaning") @Nullable Boolean cleaning,
            @JsonProperty("e2ee") @Nullable Boolean e2ee
    ) {}
}
