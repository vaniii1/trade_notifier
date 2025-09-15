package trade.tradenotifier.mapper;

import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import trade.tradenotifier.config.MapperConfig;
import trade.tradenotifier.dto.external.SingleOrderResponseDto;
import trade.tradenotifier.dto.internal.order.InternalOrderResponseDto;
import trade.tradenotifier.model.Order;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    Order toModel(SingleOrderResponseDto orderDto);

    @Mapping(target = "price", source = "order", qualifiedByName = "resolvePrice")
    InternalOrderResponseDto toDto(Order order);

    @Named("resolvePrice")
    default BigDecimal resolvePrice(Order order) {
        if (order.getPrice() != null && order.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            return order.getPrice();
        }
        return order.getTriggerPrice();
    }
}
