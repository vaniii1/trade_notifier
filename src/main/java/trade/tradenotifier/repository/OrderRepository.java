package trade.tradenotifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trade.tradenotifier.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
