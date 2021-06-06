package com.searchmetrics.tools.ratechecker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BlockchainTickerClient implements BitcoinRateClient {
    private static final String URL = "https://blockchain.info/ticker";

    private final RestTemplate restTemplate;

    public Double getRate() {
        try {
            Map<String, Map<String, Object>> response = restTemplate.getForObject(URL, Map.class);
            return (Double) response.get("USD").get("last");
        } catch (Exception e) {
            return null;
        }
    }
}
