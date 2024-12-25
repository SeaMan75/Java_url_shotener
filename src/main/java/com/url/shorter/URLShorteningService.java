package com.url.shorter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service

public class URLShorteningService {

    private final Map<String, String> urlMap = new HashMap<>();
    private final Map<String, Integer> clickLimitMap = new HashMap<>();
    private final Map<String, Integer> clickCountMap = new HashMap<>();
    private final Map<String, LocalDateTime> expirationTimeMap = new HashMap<>();

    public String shortenURL(String longURL, String userUUID, int clickLimit, int expirationTimeMinutes) {
        String shortURL = UUID.randomUUID().toString().substring(0, 8);
        urlMap.put(shortURL, longURL);
        clickLimitMap.put(shortURL, clickLimit);
        clickCountMap.put(shortURL, 0); // Инициализация счетчика переходов
        expirationTimeMap.put(shortURL, LocalDateTime.now().plusMinutes(expirationTimeMinutes)); // Устанавливаем срок действия в минутах
        return "http://clck.ru/" + shortURL;
    }

    public String getLongURL(String shortURL) {
        if (!urlMap.containsKey(shortURL)) {
            return "Ссылка не найдена.";
        }

        LocalDateTime expirationTime = expirationTimeMap.get(shortURL);
        LocalDateTime currentTime = LocalDateTime.now();
        
        System.out.println ("Expiration Time: " + expirationTime);
        System.out.println ("Current Time: " + currentTime);
        
        
        if (expirationTime.isBefore(currentTime)) {
            return "Срок действия ссылки истек.";
        }

        int clickLimit = clickLimitMap.get(shortURL);
        int clickCount = clickCountMap.get(shortURL);

        if (clickCount >= clickLimit) {
            return "Лимит переходов исчерпан.";
        }

        clickCountMap.put(shortURL, clickCount + 1); // Увеличение счетчика переходов
        return urlMap.get(shortURL);
    }
}
