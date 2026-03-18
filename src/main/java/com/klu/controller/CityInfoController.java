package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.CityInfo;
import com.klu.service.CityInfoService;
@RestController
@RequestMapping("/citycontroller")
public class CityInfoController {

    @Autowired
    private CityInfoService cityInfoService;

    @PostMapping("/add")
    public CityInfo addCityInfo(@RequestBody CityInfo cityinfo) {
        return cityInfoService.addCityInfo(cityinfo);
    }

    @GetMapping("/getAll")
    public List<CityInfo> getAllCityInfo() {
        return cityInfoService.getAllCityInfo();
    }

    @GetMapping("/getById/{id}")
    public CityInfo getCityInfoById(@PathVariable Long id) {
        return cityInfoService.getCityInfoById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCityInfo(@PathVariable Long id) {
        cityInfoService.deleteCityInfo(id);
        System.out.println("Deleted that id "+id);
    }
    
}