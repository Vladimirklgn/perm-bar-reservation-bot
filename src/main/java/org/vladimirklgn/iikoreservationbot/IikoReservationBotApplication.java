package org.vladimirklgn.iikoreservationbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.vladimirklgn.iikoreservationbot.service.IikoService;

import java.io.IOException;

@SpringBootApplication
public class IikoReservationBotApplication {

    public static void main(String[] args) {
        // Запускаем Spring Boot как обычно
        ConfigurableApplicationContext context = SpringApplication.run(IikoReservationBotApplication.class, args);

        // Получаем наш сервис IikoService из Spring-контекста
        IikoService iikoService = context.getBean(IikoService.class);

        try {
            String storesJson = iikoService.getStoresList();  // вызовет authorize() внутри
            System.out.println("Список stores (организаций) из демо:\n" + storesJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}