package africa.jopen.landlord.models.admin.responses;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ActionResponses {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record BaseAdminRequest(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("admin_secret") String adminSecret
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record BaseAdminResponse(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record InfoResponse(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("info") Map<String, Object> info
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record PingResponse(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("pong") String pong
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record LoopsInfoResponse(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("loops") List<Map<String, Integer>> loops
    ) {
    }

    // Configuration-related Responses
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record GetStatusResponse(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("status") Map<String, Object> status
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record SetSessionTimeoutResponse(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty("timeout") Integer timeout
    ) {
    }

// ... Add more specific response public records as needed ...

    // Generic Response for Complex or Dynamic Responses
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record GenericAdminResponse(
            @JsonProperty("janus") String janus,
            @JsonProperty("transaction") String transaction,
            @JsonProperty Map<String, Object> additionalProperties
    ) {
    }

}
