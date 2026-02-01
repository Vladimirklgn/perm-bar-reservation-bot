package org.vladimirklgn.iikoreservationbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.vladimirklgn.iikoreservationbot.PermBarBot;

@Configuration
public class TelegramConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(PermBarBot permBarBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(permBarBot);
        System.out.println("Бот успешно зарегистрирован! Username: " + permBarBot.getBotUsername());
        return botsApi;
    }
}