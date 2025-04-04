package africa.jopen.landlord.models.admin;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.util.List;
public record SessionsResponse (
        @JsonProperty("janus") @Nullable String janus,
        @JsonProperty("transaction") @Nullable String transaction,
        @JsonProperty("sessions") @Nullable List<Long> sessions
){
}
