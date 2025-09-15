package trade.tradenotifier.dto.internal.order;

import lombok.Data;
import lombok.experimental.Accessors;
import trade.tradenotifier.dto.internal.SearchParams;

@Data
@Accessors(chain = true)
public class OrderSearchParameter extends SearchParams {
    private String[] category;
    private String[] symbol;
}
