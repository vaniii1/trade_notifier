package trade.tradenotifier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Accessors(chain = true)
@SQLDelete(sql = "UPDATE orders SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String symbol;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false, name = "trigger_price")
    private BigDecimal triggerPrice;
    @Column(nullable = false, name = "order_type")
    private String orderType;
    @Column(nullable = false)
    private double quantity;
    @Column(nullable = false)
    private String side;
    @Column(nullable = false, name = "time_when_created")
    private Date timeWhenCreated;
    private String category;
    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted;
}
