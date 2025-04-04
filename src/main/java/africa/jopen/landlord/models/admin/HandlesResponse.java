package africa.jopen.landlord.models.admin;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.util.List;
public record HandlesResponse(
        @JsonProperty("janus") @Nullable String janus,
        @JsonProperty("session_id") @Nullable Long sessionId,
        @JsonProperty("transaction") @Nullable String transaction,
        @JsonProperty("handles") @Nullable List<Long> handles
) {
}
