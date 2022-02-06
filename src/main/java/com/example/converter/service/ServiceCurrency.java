package com.example.converter.service;

import com.example.converter.model.Example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.converter.Constants.URL_API;


@Service
public class ServiceCurrency {




    public Example getValute() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String value = restTemplate.getForObject(URL_API,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Example example = objectMapper.readValue(value.toLowerCase(),Example.class);
        return example;
    }

    public Example parseValutes() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL_API, String.class);
        result = result.replace("\n", "").replace("  ","");
        String z = result.replaceAll("},\"[A-Z]{3}\": \\{","},{").replace("\"Valute\": {\"AUD\": ","\"Valute\": [").trim().replace("}}","}]");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(z);
        Example example = objectMapper.readValue(z, Example.class);
        System.out.println(example);
        return example;
    }


}
