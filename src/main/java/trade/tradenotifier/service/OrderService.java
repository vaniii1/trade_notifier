package trade.tradenotifier.service;

import java.util.List;
import trade.tradenotifier.dto.internal.InternalOrderResponseDto;

public interface OrderService {
    List<InternalOrderResponseDto> getSpotOrders();
}
