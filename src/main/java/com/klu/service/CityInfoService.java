package com.klu.service;

import com.klu.model.CityInfo;
import java.util.List;

public interface CityInfoService {

    CityInfo addCityInfo(CityInfo cityinfo);

    List<CityInfo> getAllCityInfo();

    CityInfo getCityInfoById(Long id);

    void deleteCityInfo(Long id);
}


