package trade.tradenotifier.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trade.tradenotifier.dto.internal.order.InternalOrderResponseDto;
import trade.tradenotifier.mapper.OrderMapper;
import trade.tradenotifier.model.Order;
import trade.tradenotifier.repository.order.OrderRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final BybitClient bybitClient;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    public List<InternalOrderResponseDto> getSpotOrders() {
        List<Order> orders =
                bybitClient.getSpotOrders()
                        .stream()
                        .map(orderMapper::toModel)
                        .toList();
        return saveAndReturnOrderDtos(orders);
    }

    @Override
    public List<InternalOrderResponseDto> getLinearOrders() {
        List<Order> orders =
                bybitClient.getLinearOrders()
                        .stream()
                        .map(orderMapper::toModel)
                        .toList();
        return saveAndReturnOrderDtos(orders);
    }

    @Override
    public List<InternalOrderResponseDto> getInverseOrders() {
        List<Order> orders =
                bybitClient.getInverseOrders()
                        .stream()
                        .map(orderMapper::toModel)
                        .toList();
        return saveAndReturnOrderDtos(orders);
    }

    private List<InternalOrderResponseDto> saveAndReturnOrderDtos(
            List<Order> orders
    ) {
        return orderRepository.saveAll(orders)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }
}
