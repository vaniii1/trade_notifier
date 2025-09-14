package trade.tradenotifier.dto.internal;

import java.math.BigDecimal;

public record InternalOrderResponseDto(
        String symbol,
        BigDecimal price,
        BigDecimal quantity,
        String side,
        String timeWhenCreated,
        String category
) {
}
