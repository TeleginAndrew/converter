package com.example.converter.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Entity
@Table(name = "valute")
public class Valute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty(value = "NumCode")
    @Column(name = "numcode")
    private String numCode;
    @JsonProperty(value = "CharCode")
    @Column(name = "charcode")
    private String charCode;
    @JsonProperty(value = "Name")
    @Column(name = "name")
    private String name;
    @JsonProperty(value = "Value")
    @Column(name = "value")
    private BigDecimal value;
    @JsonProperty(value = "Nominal")
    @Column(name = "nominal")
    private BigDecimal nominal;
    @Column(name = "date")
    private String date;

}