package com.example.converter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Example {
    @JsonProperty(value = "Date")
    private String date;
    @JsonProperty(value = "Valute")
    private Valute[] valute;
}
