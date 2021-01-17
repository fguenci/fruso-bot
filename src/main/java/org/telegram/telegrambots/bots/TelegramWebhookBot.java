package org.telegram.telegrambots.bots;

import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.Webhook;
import org.telegram.telegrambots.meta.generics.WebhookBot;
import org.telegram.telegrambots.util.WebhookUtils;

@SuppressWarnings("WeakerAccess")
public abstract class TelegramWebhookBot extends DefaultAbsSender implements WebhookBot {

	public TelegramWebhookBot() {
		this(new DefaultBotOptions());
	}

	public TelegramWebhookBot(DefaultBotOptions options) {
		super(options);
	}

	@Override
	public void setWebhook(SetWebhook setWebhook) throws TelegramApiException {
		WebhookUtils.setWebhook(this, setWebhook);
	}

//	public SetWebhook getSetWebhook() {
//		return createSetWebhook();
//	}
	
	private SetWebhook createSetWebhook() {
		SetWebhook setWebhook = new SetWebhook();
		setWebhook.setUrl(getBaseUrl());
		return setWebhook;
	}

}