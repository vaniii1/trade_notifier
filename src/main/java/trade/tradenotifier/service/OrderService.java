package trade.tradenotifier.service;

import java.util.List;
import trade.tradenotifier.dto.internal.order.InternalOrderResponseDto;

public interface OrderService {
    List<InternalOrderResponseDto> getSpotOrders();

    List<InternalOrderResponseDto> getLinearOrders();

    List<InternalOrderResponseDto> getInverseOrders();
}
