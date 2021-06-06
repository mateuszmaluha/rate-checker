package com.searchmetrics.tools.ratechecker.repository;

import com.searchmetrics.tools.ratechecker.model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {
    Rate findTopByOrderByCreationDateDesc();
    List<Rate> findAllByCreationDateBetween(Date creationDateStart, Date creationDateEnd);
}
