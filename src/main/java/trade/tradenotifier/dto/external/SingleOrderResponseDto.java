package trade.tradenotifier.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class SingleOrderResponseDto {
    private String symbol;
    private BigDecimal price;
    private BigDecimal triggerPrice;
    @JsonProperty("qty")
    private BigDecimal quantity;
    private String orderType;
    private String side;
    private String category;
    @JsonProperty("createdTime")
    private Date timeWhenCreated;
}
