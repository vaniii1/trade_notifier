package trade.tradenotifier.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.Data;

@Data
public class ListOrderResponsesDto {
    @JsonProperty("list")
    private Set<SingleOrderResponseDto> orders;
}
