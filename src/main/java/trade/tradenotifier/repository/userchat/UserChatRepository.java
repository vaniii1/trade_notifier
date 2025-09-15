package trade.tradenotifier.repository.userchat;

import org.springframework.data.jpa.repository.JpaRepository;
import trade.tradenotifier.model.UserChat;

public interface UserChatRepository extends JpaRepository<UserChat, Long> {
    boolean existsUserChatByChatId(Long chatId);
}
