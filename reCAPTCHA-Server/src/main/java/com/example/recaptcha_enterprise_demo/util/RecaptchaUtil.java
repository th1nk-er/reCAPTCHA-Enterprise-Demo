package com.example.recaptcha_enterprise_demo.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class RecaptchaUtil {

    @Value("${reCAPTCHA.api-key}")
    private String apiKey;
    @Value("${reCAPTCHA.project-id}")
    private String projectID;
    @Value("${reCAPTCHA.site-key}")
    private String recaptchaSiteKey;
    @Value("${reCAPTCHA.http-proxy.ip}")
    private String proxyIP;
    @Value("${reCAPTCHA.http-proxy.port}")
    private Integer proxyPort;
    @Getter
    private static String errorMessage;

    public boolean verify(String token, String action) {
        String body = String.format("""
                {
                  "event": {
                    "token": "%s",
                    "expectedAction": "%s",
                    "siteKey": "%s",
                  }
                }
                """, token, action, recaptchaSiteKey
        );
        try {
            HttpClient client;
            if (!proxyIP.isEmpty() && proxyPort != null)
                client = HttpClient.newBuilder().proxy(ProxySelector.of(new InetSocketAddress(proxyIP, proxyPort))).build();
            else
                client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(body))
                    .uri(URI.create(String.format("https://recaptchaenterprise.googleapis.com/v1/projects/%s/assessments?key=%s", projectID, apiKey)))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("response: {}", response.body());
            JSONObject json = (JSONObject) JSON.parse(response.body());
            if (!json.containsKey("tokenProperties")) {
                if (json.containsKey("error")) {
                    errorMessage = (String) json.getByPath("error.message");
                }
                return false;
            }
            boolean valid = (Boolean) json.getByPath("tokenProperties.valid");
            if (valid) return true;
            else {
                errorMessage = (String) json.getByPath("tokenProperties.invalidReason");
                return false;
            }
        } catch (Exception e) {
            log.error("error", e);
            return false;
        }
    }
}
