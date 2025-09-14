package trade.tradenotifier.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class SingleOrderResponseDto {
    private String symbol;
    private BigDecimal price;
    @JsonProperty("qty")
    private BigDecimal quantity;
    private String side;
    private String category;
    @JsonProperty("createdTime")
    private String timeWhenCreated;
}
