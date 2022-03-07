package com.example.converter.controller;

import com.example.converter.constants.Constants;
import com.example.converter.model.entity.Valute;
import com.example.converter.model.input.ValuteInput;
import com.example.converter.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataBaseController {
    final DataBaseService dataBaseService;

// CONTROLLER FOR TEST DB
    @Autowired
    public DataBaseController(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    @GetMapping(Constants.FIND_VALUTE)
    public Valute findCurrency(@PathVariable String charCode) {
        return dataBaseService.findCurrency(charCode);
    }

    @PostMapping(Constants.ADD_VALUTE)
    public void addValute(@RequestBody ValuteInput valuteInput) {
        dataBaseService.save(valuteInput);
    }

}
