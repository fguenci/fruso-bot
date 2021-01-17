package fruso.com.bot.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.starter.TelegramBotInitializer;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultWebhook;

@Configuration
@ConditionalOnProperty(prefix = "telegrambots", name = "enabled", havingValue = "true", matchIfMissing = true)
public class BotSpringConfig {

    @Bean
    @ConditionalOnMissingBean(TelegramBotsApi.class)
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
    	DefaultWebhook webhook = new DefaultWebhook();
    	webhook.setInternalUrl("fruso-bot.herokuapp.com");
        return new TelegramBotsApi(DefaultBotSession.class, webhook);
    }

    @Bean
    @ConditionalOnMissingBean
    public TelegramBotInitializer telegramBotInitializer(TelegramBotsApi telegramBotsApi,
                                                         ObjectProvider<List<LongPollingBot>> longPollingBots,
                                                         ObjectProvider<List<TelegramWebhookBot>> webHookBots) {
        return new TelegramBotInitializer(telegramBotsApi,
                longPollingBots.getIfAvailable(Collections::emptyList),
                webHookBots.getIfAvailable(Collections::emptyList));
    }
	
	@Bean
	public SetWebhook getSetWebhook() {
		SetWebhook setWebhook = new SetWebhook();
		setWebhook.setUrl("fruso-bot.herokuapp.com/" + BotConfig.WEBHOOK_TOKEN);
		return setWebhook;
	}
}
