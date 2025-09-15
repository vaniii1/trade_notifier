package trade.tradenotifier.repository.order;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import trade.tradenotifier.exception.ProviderNotFoundException;
import trade.tradenotifier.model.Order;
import trade.tradenotifier.repository.SpecificationProvider;
import trade.tradenotifier.repository.SpecificationProviderManager;

@Component
@RequiredArgsConstructor
public class OrderSpecificationProviderManager implements SpecificationProviderManager<Order> {
    private final List<SpecificationProvider<Order>> specificationProviders;

    @Override
    public SpecificationProvider<Order> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(spec -> spec.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new ProviderNotFoundException("Provider not found for key: " + key));
    }
}
