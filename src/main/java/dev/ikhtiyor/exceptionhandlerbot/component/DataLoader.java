package dev.ikhtiyor.exceptionhandlerbot.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${telegram.bot.username}")
    String tgBotUsername;

    @Value("${telegram.bot.botToken}")
    String tgBotToken;

    @Value("${telegram.bot.webHookPath}")
    String tgBotWebHookPath;

    @Value("${spring.profiles.active}")
    String profile;

    @Override
    public void run(String... args) {

        if (Objects.equals(profile, "prod")) {
            //  telegram webhook register
            setWebhook();
        }
    }

    public void setWebhook() {
        String telegramBotAPI = "https://api.telegram.org/bot" + tgBotToken + "/setWebhook?url=" + tgBotWebHookPath;
        System.err.println("telegramBotAPI => " + telegramBotAPI);

        RestTemplate restTemplate = new RestTemplate();

        URI uri = null;
        try {
            uri = new URI(telegramBotAPI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        System.err.println("result => " + result);

        if (result.getStatusCodeValue() != 200) {
            System.exit(1);
        }
    }
}
