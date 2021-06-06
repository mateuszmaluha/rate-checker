package com.searchmetrics.tools.ratechecker.web.api;

import com.searchmetrics.tools.ratechecker.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.searchmetrics.tools.ratechecker.web.validation.DateValidator.parseDate;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class RateController {

    private final RateService rateService;

    @GetMapping(path = "latest-rate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> rate() {
        return ResponseEntity.ok().body(Map.of("rate", rateService.getRate()));
    }

    @GetMapping(path = "historical-rates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, Object>>> rates(@RequestParam(name = "startDate") String start, @RequestParam(name = "endDate") String end) {
        Date startDate = parseDate(start);
        Date endDate = parseDate(end);
        return ResponseEntity.ok().body(rateService.getRates(startDate, endDate));
    }

}
