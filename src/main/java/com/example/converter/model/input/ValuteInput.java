package com.example.converter.model.input;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class ValuteInput {
    private String numCode;
    private String charCode;
    private String name;
    private BigDecimal value;
    private BigDecimal nominal;
    private Date date;
}
