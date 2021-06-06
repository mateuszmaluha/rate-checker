package com.searchmetrics.tools.ratechecker.integration;

import com.searchmetrics.tools.ratechecker.repository.RateRepository;
import com.searchmetrics.tools.ratechecker.service.BitcoinRateClient;
import com.searchmetrics.tools.ratechecker.service.RateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateCheckerIntegrationTest {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private BitcoinRateClient bitcoinRateClient;

    @Autowired
    private RateProvider rateProvider;

    @Autowired
    private RateRepository rateRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void cleanUp() {
        rateRepository.deleteAll();
    }

    @Test
    public void currentRateEndpointReturnsProperValue() {
        when(bitcoinRateClient.getRate()).thenReturn(Double.valueOf("1.0"));
        rateProvider.updateRates();
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:" + port + "/api/latest-rate", String.class);
        assertEquals("{\"rate\":1.0}", response.getBody());
    }

    @Test
    public void historicalRatesEndpointReturnsAllValues() {
        String startDate = LocalDateTime.now().format(FORMATTER);
        String endDate = LocalDateTime.now().plusDays(1).format(FORMATTER);
        when(bitcoinRateClient.getRate()).thenReturn(Double.valueOf("1.0"));
        rateProvider.updateRates();
        rateProvider.updateRates();
        ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:" + port + "/api/historical-rates?startDate=" + startDate + "&endDate=" + endDate, List.class);
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void historicalRatesEndpointReturnsEmptyListWhenNoData() {
        String startDate = LocalDateTime.now().minusDays(2).format(FORMATTER);
        String endDate = LocalDateTime.now().minusDays(1).format(FORMATTER);
        when(bitcoinRateClient.getRate()).thenReturn(Double.valueOf("1.0"));
        rateProvider.updateRates();
        rateProvider.updateRates();
        ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:" + port + "/api/historical-rates?startDate=" + startDate + "&endDate=" + endDate, List.class);
        assertTrue(response.getBody().isEmpty());
    }

}