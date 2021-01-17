package fruso.com.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;

@SpringBootApplication(exclude = TelegramBotStarterConfiguration.class)
public class BotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotApplication.class, args);
	}

}
