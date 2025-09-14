package trade.tradenotifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trade.tradenotifier.model.SpotPosition;

public interface SpotPositionRepository extends
        JpaRepository<SpotPosition, Long> {
}
