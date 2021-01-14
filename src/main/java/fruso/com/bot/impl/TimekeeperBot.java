package fruso.com.bot.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class TimekeeperBot extends TelegramWebhookBot {

	@Override
	public BotApiMethod<Message> onWebhookUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText("Well, all information looks like noise until you break the code.");
            return sendMessage;
        }
        return null;
	}

	@Override
	public String getBotPath() {
		return BotConfig.WEBHOOK_USER;
	}

	@Override
	public String getBotUsername() {
		return BotConfig.WEBHOOK_USER;
	}

	@Override
	public String getBotToken() {
		return BotConfig.WEBHOOK_TOKEN;
	}

}
