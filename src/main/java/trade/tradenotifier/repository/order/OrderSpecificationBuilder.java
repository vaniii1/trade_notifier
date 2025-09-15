package trade.tradenotifier.repository.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import trade.tradenotifier.dto.internal.order.OrderSearchParameter;
import trade.tradenotifier.model.Order;
import trade.tradenotifier.repository.SpecificationBuilder;

@Component
@RequiredArgsConstructor
public class OrderSpecificationBuilder implements
        SpecificationBuilder<Order, OrderSearchParameter> {
    private final OrderSpecificationProviderManager manager;

    @Override
    public Specification<Order> buildSpecification(OrderSearchParameter searchParams) {
        Specification<Order> spec = Specification.where(null);
        if (searchParams.getCategory() != null
                && searchParams.getCategory().length > 0) {
            spec = spec.and(manager
                    .getSpecificationProvider("category")
                    .getSpecification(searchParams.getCategory()));
        }
        if (searchParams.getSymbol() != null
                && searchParams.getSymbol().length > 0) {
            spec = spec.and(manager
                    .getSpecificationProvider("symbol")
                    .getSpecification(searchParams.getSymbol()));
        }
        return spec;
    }
}
