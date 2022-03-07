package com.example.converter.service;

import com.example.converter.model.entity.ExchangeRates;
import com.example.converter.model.entity.Valute;
import com.example.converter.repository.CBCurrencyRepository;
import com.example.converter.repository.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class ServiceCurrency {

    final CBCurrencyRepository cbCurrencyRepository;
    final CurrencyRepository currencyRepository;

    @Autowired
    public ServiceCurrency(CBCurrencyRepository cbCurrencyRepository, CurrencyRepository currencyRepository) {
        this.cbCurrencyRepository = cbCurrencyRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<Valute> getValutes() throws JsonProcessingException {
        List<Valute> valutes = currencyRepository.findAllByDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if (!valutes.isEmpty()) {
            return valutes;
        } else {
            Valute[] cbValutes = cbCurrencyRepository.parseValutes().getValute(); // доделать добавление массива из цб в базу
            for (Valute valute:cbValutes) {
                valute.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                currencyRepository.save(valute);
            }
            return currencyRepository.findAllByDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
    }

    // получаем валюту от ЦБ
    public ExchangeRates parseValutes() throws JsonProcessingException {
        return cbCurrencyRepository.parseValutes();
    }

    public List<Valute> findAllByDate() throws JsonProcessingException {
        return currencyRepository.findAllByDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }

    // ковертация валют выбранными пользователями
    public BigDecimal convertValute(BigDecimal amount, String valuteFrom, String valuteTo) throws JsonProcessingException {
        Valute firstValute = new Valute();
        Valute secondValute = new Valute();
        List<Valute> list = getValutes();

        // конвуртируем в рубль
        if (valuteTo.equals("RUB")) {
            for (int i = 0; i < list.size(); i++) {
                if (valuteFrom.equals(list.get(i).getCharCode())) {
                    firstValute = list.get(i);
                }
            }
            return amount.multiply(firstValute.getValue()).setScale(2, RoundingMode.HALF_UP);
        }

        // конвуртируем рубль в другую валюту
        if (valuteFrom.equals("RUB")) {
            for (int i = 0; i < list.size(); i++) {
                if (valuteTo.equals(list.get(i).getCharCode())) {
                    secondValute = list.get(i);
                }
            }
            return amount.divide(secondValute.getValue(), 10, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
        }

        // конвуртируем валюты, кроме рубля
        for (int i = 0; i < list.size(); i++) {
            if (valuteFrom.equals(list.get(i).getCharCode())) {
                firstValute = list.get(i);
            }
            if (valuteTo.equals(list.get(i).getCharCode())) {
                secondValute = list.get(i);
            }
        }
        return amount.multiply(firstValute.getValue().divide(firstValute.getNominal()).multiply(secondValute.getNominal().divide(secondValute.getValue(), 10, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP));
    }


}
