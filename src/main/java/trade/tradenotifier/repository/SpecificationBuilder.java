package trade.tradenotifier.repository;

import org.springframework.data.jpa.domain.Specification;
import trade.tradenotifier.dto.internal.SearchParams;

public interface SpecificationBuilder<T, S extends SearchParams> {
    Specification<T> buildSpecification(S searchParams);
}
