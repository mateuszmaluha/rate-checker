package com.searchmetrics.tools.ratechecker.service;

import com.searchmetrics.tools.ratechecker.model.Rate;
import com.searchmetrics.tools.ratechecker.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import static com.searchmetrics.tools.ratechecker.utils.DateHelper.getCurrentTime;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class RateProvider {
    private final RateRepository rateRepository;
    private final BitcoinRateClient bitcoinRateClient;

    @Scheduled(fixedRateString = "${updateRates}")
    public void updateRates() {
        Double rate = bitcoinRateClient.getRate();
        if (rate == null) return;
        rateRepository.save(new Rate(getCurrentTime(), rate));
        System.out.println("Updating");
    }





}
