package trade.tradenotifier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE user_chats SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "user_chats")
public class UserChat {
    @Id
    private Long chatId;
    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted;
}
