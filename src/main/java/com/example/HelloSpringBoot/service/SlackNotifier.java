package com.example.HelloSpringBoot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SlackNotifier {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${slack.webhook-url}")
    private String slackWebhookUrl;

    public void sendMessage(String message) {
        System.out.println("슬랙 전송 시도: " + message);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> payload = Map.of("text", message);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);
            restTemplate.postForEntity(slackWebhookUrl, request, String.class);
            System.out.println("슬랙 전송 성공");
        } catch (Exception e) {
            System.err.println("슬랙 전송 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 