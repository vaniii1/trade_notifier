package trade.tradenotifier.dto.internal.order;

import java.math.BigDecimal;
import java.util.Date;

public record InternalOrderResponseDto(
        String symbol,
        BigDecimal price,
        BigDecimal quantity,
        String orderType,
        String side,
        Date timeWhenCreated,
        String category
) {
}
