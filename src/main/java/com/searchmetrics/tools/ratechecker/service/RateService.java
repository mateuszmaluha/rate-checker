package com.searchmetrics.tools.ratechecker.service;

import com.searchmetrics.tools.ratechecker.model.Rate;
import com.searchmetrics.tools.ratechecker.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RateService {

    private final RateRepository rateRepository;

    public Double getRate() {
        return rateRepository.findTopByOrderByCreationDateDesc().getValue();
    }

    public List<Map<String, Object>> getRates(Date startTime, Date endTime) {

        List<Rate> allByCreationDateBetween = rateRepository.findAllByCreationDateBetween(startTime, endTime);
        return allByCreationDateBetween.stream().map(rate -> toMap(rate)).collect(Collectors.toList());
    }

    private Map<String, Object> toMap(Rate rate) {
        return Map.of("date", rate.getCreationDate(), "rate", rate.getValue());
    }

}
