package com.url.shorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class URLShorteningController {

    @Autowired
    private URLShorteningService urlShorteningService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenURL(@RequestParam String longURL, @RequestParam String userUUID, @RequestParam int clickLimit, @RequestParam int expirationTime) {
        String shortURL = urlShorteningService.shortenURL(longURL, userUUID, clickLimit, expirationTime);
        return ResponseEntity.ok(shortURL);
    }

    @GetMapping("/{shortURL}")
    public ResponseEntity<String> getLongURL(@PathVariable String shortURL) {
        String longURL = urlShorteningService.getLongURL(shortURL);
        if (longURL.equals("Ссылка не найдена.") || longURL.equals("Срок действия ссылки истек.") || longURL.equals("Лимит переходов исчерпан.")) {
            return ResponseEntity.status(404).body(longURL);
        }
        return ResponseEntity.ok(longURL);
    }
}
