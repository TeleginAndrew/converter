package com.example.converter.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Valute {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "NumCode")
    private String numCode;
    @JsonProperty(value = "CharCode")
    private String charCode;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Value")
    private BigDecimal value;
    @JsonProperty(value = "Nominal")
    private BigDecimal nominal;




}