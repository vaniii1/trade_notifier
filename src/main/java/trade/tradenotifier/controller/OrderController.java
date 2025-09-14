package trade.tradenotifier.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trade.tradenotifier.dto.internal.InternalOrderResponseDto;
import trade.tradenotifier.service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get_all")
    public List<InternalOrderResponseDto> getOrders() {
        return orderService.getSpotOrders();
    }
}
