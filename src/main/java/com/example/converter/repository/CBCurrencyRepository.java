package com.example.converter.repository;

import com.example.converter.model.entity.ExchangeRates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static com.example.converter.constants.Constants.URL_API;

@Repository
public class CBCurrencyRepository {

    final RestTemplate restTemplate;

    @Autowired
    public CBCurrencyRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRates parseValutes() throws JsonProcessingException {
        String result = restTemplate.getForObject(URL_API, String.class);
        result = result.replace("\n", "").replace("  ", "");
        String z = result.replaceAll("},\"[A-Z]{3}\": \\{", "},{").replace("\"Valute\": {\"AUD\": ", "\"Valute\": [").trim().replace("}}", "}]");
        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRates exchangeRates = objectMapper.readValue(z, ExchangeRates.class);
        return exchangeRates;
    }

}
