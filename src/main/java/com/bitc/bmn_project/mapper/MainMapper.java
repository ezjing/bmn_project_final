package com.bitc.bmn_project.mapper;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.FilterDTO;
import com.bitc.bmn_project.DTO.ReviewCntDto;
import com.bitc.bmn_project.DTO.ceoCustomDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MainMapper {
    List<CeoDTO> storeLists(String keyWorld) throws Exception;

    List<CeoDTO> followLists(String keyWorld) throws Exception;

    // 한식
    List<CeoDTO> selectKFood() throws Exception;
    // 일식
    List<CeoDTO> selectJFoods() throws Exception;
    // 중식
    List<CeoDTO> selectCFoods() throws Exception;
    // 양식
    List<CeoDTO> selectWFoods() throws Exception;

    List<CeoDTO> scorePlacingLists() throws Exception;

    List<CeoDTO> followPlacingLists() throws Exception;

    List<ReviewCntDto> reviewCntLists(String keyWorld) throws Exception;

    List<ceoCustomDTO> ageFilterLists(
            @Param("keyWorld") String keyWorld,
            @Param("customAge") String customAge) throws Exception;

    List<ceoCustomDTO> genderFilterLists(@Param("keyWorld") String keyWorld,@Param("genderKey") String genderKey) throws Exception;

    List<ceoCustomDTO> categoryFoods(@Param("keyWorld") String keyWorld,@Param("categoryFoods") String categoryFoods) throws Exception;

    List<ReviewCntDto> reviewCntOuterLists(String keyWorld) throws Exception;

    List<FilterDTO> filterAllSearch(Map<String, Object> params) throws Exception;
}
