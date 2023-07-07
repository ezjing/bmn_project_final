package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.ReviewCntDto;

import java.util.List;

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
}
