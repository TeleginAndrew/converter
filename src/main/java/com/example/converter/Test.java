package com.example.converter;

import com.example.converter.model.Example;
import com.example.converter.model.Valute;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import static com.example.converter.Constants.URL_API;

public class Test {

    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL_API, String.class);
        result = result.replace("\n", "").replace("  ","");
        String z = result.replaceAll("},\"[A-Z]{3}\": \\{","},{").replace("\"Valute\": {\"AUD\": ","\"Valute\": [").trim().replace("}}","}]");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(z);
        Example example = objectMapper.readValue(z, Example.class);
        System.out.println(example);

        String formula = "amount * course1Valute/nominal(1)*(nominal2Valute/course2Valute";
    }
}
