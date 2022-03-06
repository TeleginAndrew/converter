package com.example.converter.controller;

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

    @GetMapping("/findValute/{charCode}")
    public Valute findCurrency(@PathVariable String charCode) {
        return dataBaseService.findCurrency(charCode);
    }

    @PostMapping("/addValute")
    public void addValute(@RequestBody ValuteInput valuteInput) {
        dataBaseService.save(valuteInput);
    }

}
