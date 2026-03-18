package com.klu.serviceImple;

import com.klu.model.CityInfo;
import com.klu.repository.CityInfoRepo;
import com.klu.service.CityInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityInfoServiceImple implements CityInfoService {

    @Autowired
    private CityInfoRepo repo;

    @Override
    public CityInfo addCityInfo(CityInfo cityInfo) {
        return repo.save(cityInfo);
    }

    @Override
    public List<CityInfo> getAllCityInfo() {
        return repo.findAll();
    }

    @Override
    public CityInfo getCityInfoById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteCityInfo(Long id) {
        repo.deleteById(id);
    }
}