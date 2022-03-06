package com.example.converter.service;

import com.example.converter.model.entity.Valute;
import com.example.converter.model.input.ValuteInput;
import com.example.converter.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DataBaseService {
    final CurrencyRepository currencyRepository;

    @Autowired
    public DataBaseService(CurrencyRepository valuteRepository) {
        this.currencyRepository = valuteRepository;
    }

    public Valute findCurrency(String charCode) {
        return currencyRepository.findCurrencyByCharCode(charCode);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void save(ValuteInput valuteInput) {
        Valute valute = new Valute();
        valute.setNumCode(valuteInput.getNumCode());
        valute.setCharCode(valuteInput.getCharCode());
        valute.setName(valuteInput.getName());
        valute.setValue(valuteInput.getValue());
        valute.setNominal(valuteInput.getNominal());
        valute.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        currencyRepository.save(valute);
    }

}
