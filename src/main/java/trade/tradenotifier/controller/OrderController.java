package trade.tradenotifier.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trade.tradenotifier.dto.internal.order.InternalOrderResponseDto;
import trade.tradenotifier.service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/spot")
    public List<InternalOrderResponseDto> getSpotOrders(
    ) {
        return orderService.getSpotOrders();
    }

    @GetMapping("/linear")
    public List<InternalOrderResponseDto> getLinearOrders(
    ) {
        return orderService.getLinearOrders();
    }

    @GetMapping("/inverse")
    public List<InternalOrderResponseDto> getInverseOrders(
    ) {
        return orderService.getInverseOrders();
    }
}
