package com.example.converter.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRates {
    @JsonProperty(value = "Date")
    private String date;
    @JsonProperty(value = "Valute")
    private Valute[] valute;
}
