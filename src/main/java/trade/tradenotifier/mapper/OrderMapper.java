package trade.tradenotifier.mapper;

import org.mapstruct.Mapper;
import trade.tradenotifier.config.MapperConfig;
import trade.tradenotifier.dto.external.SingleOrderResponseDto;
import trade.tradenotifier.dto.internal.InternalOrderResponseDto;
import trade.tradenotifier.model.Order;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    Order toModel(SingleOrderResponseDto orderDto);

    InternalOrderResponseDto toDto(Order order);
}
