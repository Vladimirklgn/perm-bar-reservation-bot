package org.vladimirklgn.iikoreservationbot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IikoService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${iiko.demo.base-url}")
    private String baseUrl;

    @Value("${iiko.demo.login}")
    private String login;

    @Value("${iiko.demo.password}")
    private String password;

    private String accessToken;

    public void authorize() throws IOException {

        Request getRequest = new Request.Builder()
                .url(baseUrl + "/auth")
                .get()
                .build();

        try (Response getResponse = client.newCall(getRequest).execute()) {
            System.out.println("Предварительный GET /auth выполнен. Код: " + getResponse.code());
            // Не нужно читать body — главное, что OkHttp сохранит cookie из ответа (Set-Cookie)
        } catch (IOException e) {
            System.out.println("Ошибка предварительного GET: " + e.getMessage());
        }

        // Теперь основной POST /auth с login/password (form-urlencoded — самый рабочий вариант)
        RequestBody body = new FormBody.Builder()
                .add("login", "demo")
                .build();

        Request postRequest = new Request.Builder()
                .url(baseUrl + "/auth")
                .post(body)
                .build();

        try (Response postResponse = client.newCall(postRequest).execute()) {
            if (!postResponse.isSuccessful()) {
                String errorBody = postResponse.body() != null ? postResponse.body().string() : "no body";
                throw new IOException("Ошибка авторизации демо: " + postResponse.code() + " - " + errorBody);
            }

            String jsonResponse = postResponse.body().string();
            System.out.println("Ответ от POST /auth: " + jsonResponse);

            System.out.println("Авторизация в демо успешна! (сессия создана)");
        }
    }

    public String getStoresList() throws IOException {
        authorize();  // ← сначала авторизуемся

        Request request = new Request.Builder()
                .url(baseUrl + "/stores/list")
                .header("Content-Type", "application/json")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Ошибка /stores/list: " + response.code() + " - " + response.body().string());
            }

            String json = response.body().string();
            System.out.println("Список stores: " + json);
            return json;
        }
    }
}