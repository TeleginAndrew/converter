package com.example.converter.controller;

import com.example.converter.model.Example;
import com.example.converter.model.Valute;
import com.example.converter.service.ServiceCurrency;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
public class MainController {
    @Autowired
    ServiceCurrency serviceCurrency;



    @GetMapping("/")
    public String generalPage() {
        return "main";
    }

    @GetMapping("/table")
    public String getTablePage(Model model) throws JsonProcessingException {
        Example example = serviceCurrency.parseValutes();
        model.addAttribute(example);
        return "table";
    }

    @GetMapping("/converter")
    public String getConverter(Model model) throws JsonProcessingException {
        Example example = serviceCurrency.parseValutes();
        model.addAttribute(example);
        return "converter";
    }

    @PostMapping("/converter")
    public String convert(@RequestParam BigDecimal amount, @RequestParam String valuteFrom, @RequestParam String valuteTo, Model model) throws Exception {
        if (amount == null || valuteFrom == null || valuteTo == null) {
            throw new Exception("Params in method are null");
        }
        Valute firstValute = new Valute();
        Valute secondValute = new Valute();
        Example example = serviceCurrency.parseValutes();

        for (int i =0;i<example.getValute().length;i++) {
            if (valuteFrom.equals(example.getValute()[i].getCharCode())) {
                firstValute = example.getValute()[i];
            }
            if (valuteTo.equals(example.getValute()[i].getCharCode())) {
                secondValute = example.getValute()[i];
            }
        }

        BigDecimal result = amount.multiply(firstValute.getValue().divide(firstValute.getNominal()).multiply(secondValute.getNominal().divide(secondValute.getValue(),10, RoundingMode.HALF_UP)).setScale(2,RoundingMode.HALF_UP));
        model.addAttribute("result",result);

        return "converter";
    }

}