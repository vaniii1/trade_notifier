package trade.tradenotifier.repository.specification;

import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import trade.tradenotifier.model.Order;
import trade.tradenotifier.repository.SpecificationProvider;

@Component
public class SymbolSpecificationProvider implements SpecificationProvider<Order> {
    @Override
    public String getKey() {
        return "symbol";
    }

    @Override
    public Specification<Order> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get("symbol").in(Arrays.stream(params).toArray());
    }
}
