package com.example.converter.repository;

import com.example.converter.model.entity.Valute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CurrencyRepository extends JpaRepository<Valute, Long> {
    Valute findCurrencyByCharCode(String charCode);
    List<Valute> findAllByDate(String date);



}
