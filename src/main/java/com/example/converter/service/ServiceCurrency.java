package com.example.converter.service;

import com.example.converter.model.Example;
import com.example.converter.model.Valute;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.example.converter.constants.Constants.URL_API;


@Service
public class ServiceCurrency {

    RestTemplate restTemplate;

    @Autowired
    public ServiceCurrency(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Example getValute() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String value = restTemplate.getForObject(URL_API,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Example example = objectMapper.readValue(value.toLowerCase(),Example.class);
        return example;
    }

    public Example parseValutes() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL_API, String.class);
        result = result.replace("\n", "").replace("  ","");
        String z = result.replaceAll("},\"[A-Z]{3}\": \\{","},{").replace("\"Valute\": {\"AUD\": ","\"Valute\": [").trim().replace("}}","}]");
        ObjectMapper objectMapper = new ObjectMapper();
        Example example = objectMapper.readValue(z, Example.class);
        return example;
    }

    public BigDecimal convertValute(BigDecimal amount, String valuteFrom, String valuteTo) throws JsonProcessingException {
        Valute firstValute = new Valute();
        Valute secondValute = new Valute();
        Example example = parseValutes();

        for (int i =0;i<example.getValute().length;i++) {
            if (valuteFrom.equals(example.getValute()[i].getCharCode())) {
                firstValute = example.getValute()[i];
            }
            if (valuteTo.equals(example.getValute()[i].getCharCode())) {
                secondValute = example.getValute()[i];
            }
        }
        return amount.multiply(firstValute.getValue().divide(firstValute.getNominal()).multiply(secondValute.getNominal().divide(secondValute.getValue(),10, RoundingMode.HALF_UP)).setScale(2,RoundingMode.HALF_UP));
    }


}
