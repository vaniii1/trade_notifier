package trade.tradenotifier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import trade.tradenotifier.model.UserChat;
import trade.tradenotifier.repository.userchat.UserChatRepository;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;
    private final OrderService orderService;
    private final UserChatRepository userChatRepository;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (!userChatRepository.existsUserChatByChatId(chatId)) {
                UserChat userChat = new UserChat();
                userChat.setChatId(chatId);
                userChatRepository.save(userChat);
            }

            if (messageText.startsWith("/futures_positions")) {
                handleListFuturesPositions(chatId);
            } else if (messageText.startsWith("/spot_positions")) {
                handleListSpotPositions(chatId);
            } else if (messageText.startsWith("/spot_orders")) {
                handleListSpotOrders(chatId);
            } else if (messageText.startsWith("/futures_orders")) {
                handleListFuturesOrders(chatId);
            } else {
                sendMessage(
                        chatId,
                        "Unknown command. Use /futures, /spot, /spot_orders or /futures_orders");
            }
        }
    }

    public void sendNotification(long chatId, String message) {
        sendMessage(chatId, message);
    }

    private void handleListFuturesOrders(long chatId) {

    }

    private void handleListSpotOrders(long chatId) {
        sendMessage(chatId, "Active spot orders: ");
    }

    private void handleListSpotPositions(long chatId) {

    }

    private void handleListFuturesPositions(double chatId) {

    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Error while sending a message.", e);
        }
    }

}
