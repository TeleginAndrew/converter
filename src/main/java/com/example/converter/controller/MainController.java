package com.example.converter.controller;

import com.example.converter.constants.Constants;
import com.example.converter.model.entity.ExchangeRates;
import com.example.converter.model.entity.Valute;
import com.example.converter.service.ServiceCurrency;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class MainController {
    ServiceCurrency serviceCurrency;

    @Autowired
    public MainController(ServiceCurrency serviceCurrency) {
        this.serviceCurrency = serviceCurrency;
    }


    @GetMapping(Constants.MAIN_PAGE)
    public String generalPage() {
        return "main";
    }

    @GetMapping(Constants.TABLE_PAGE)
    public String getTablePage(Model model) throws JsonProcessingException {
        List<Valute> valutes = serviceCurrency.getValutes();
        model.addAttribute(valutes);
        return "table";
    }

    @GetMapping(Constants.CONVERTER_PAGE)
    public String getConverter(Model model) throws JsonProcessingException {
        ExchangeRates exchangeRates = serviceCurrency.parseValutes();
        model.addAttribute(exchangeRates);
        return "converter";
    }

    @PostMapping(Constants.CONVERTER_PAGE)
    public String convert(@RequestParam BigDecimal amount, @RequestParam String valuteFrom, @RequestParam String valuteTo, Model model) throws Exception {
        if (amount == null || valuteFrom == null || valuteTo == null) {
            throw new Exception(Constants.EXC_PARAMS);
        }
        BigDecimal result = serviceCurrency.convertValute(amount,valuteFrom,valuteTo);
        model.addAttribute("result",result);
        model.addAttribute("valuteResult",valuteTo);
        return "converter";
    }

}
