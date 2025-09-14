package trade.tradenotifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trade.tradenotifier.model.FuturesPosition;

public interface FuturesPositionRepository extends
        JpaRepository<FuturesPosition, Long> {
}
