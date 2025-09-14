package trade.tradenotifier.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderResponseWrapper {
    @JsonProperty("result")
    private ListOrderResponsesDto ordersAndCategory;
}
