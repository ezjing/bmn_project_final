package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.FilterDTO;
import com.bitc.bmn_project.DTO.ReviewCntDto;
import com.bitc.bmn_project.DTO.ceoCustomDTO;

import java.util.List;
import java.util.Map;

public interface MainService {
    List<CeoDTO> selectKFood() throws Exception;

    List<CeoDTO> storeLists(String keyWorld) throws Exception;

    List<CeoDTO> followLists(String keyWorld) throws Exception;

    List<CeoDTO> selectJFoods() throws Exception;

    List<CeoDTO> selectCFoods() throws Exception;

    List<CeoDTO> selectWFoods() throws Exception;

    List<CeoDTO> scorePlacingLists() throws Exception;

    List<CeoDTO> followPlacingLists() throws Exception;

    List<ReviewCntDto> reviewCntLists(String keyWorld) throws Exception;

    List<ceoCustomDTO> ageFilterLists(String keyWorld, String customAge) throws Exception;

    List<ceoCustomDTO> genderFilterLists(String keyWorld, String genderKey) throws Exception;

    List<ceoCustomDTO> categoryFoods(String keyWorld, String categoryFoods) throws Exception;

    List<ReviewCntDto> reviewCntOuterLists(String keyWorld) throws Exception;

    // List<FilterDTO> filterAllSearch(String keyWorld, String genderKey, String customAge, String categoryFoods) throws Exception;
    List<FilterDTO> filterAllSearch(Map<String, Object> params) throws Exception;
}
