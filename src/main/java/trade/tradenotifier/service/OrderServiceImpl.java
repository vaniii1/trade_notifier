package trade.tradenotifier.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trade.tradenotifier.dto.internal.InternalOrderResponseDto;
import trade.tradenotifier.mapper.OrderMapper;
import trade.tradenotifier.model.Order;
import trade.tradenotifier.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final BybitClient bybitClient;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public List<InternalOrderResponseDto> getSpotOrders() {
        List<Order> orders =
                bybitClient.getSpotOrders()
                        .stream()
                        .map(orderMapper::toModel)
                        .toList();
        return orderRepository.saveAll(orders)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }
}
