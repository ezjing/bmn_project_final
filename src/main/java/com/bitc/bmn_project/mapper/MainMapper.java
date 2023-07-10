package com.bitc.bmn_project.mapper;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.ReviewCntDto;
import com.bitc.bmn_project.DTO.ceoCustomDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
            @Param("customAge1") String customAge1,
            @Param("customAge2") String customAge2,
            @Param("customAge3") String customAge3,
            @Param("customAge4") String customAge4,
            @Param("customAge5") String customAge5,
            @Param("customAge6") String customAge6) throws Exception;

    List<ceoCustomDTO> genderFilterLists(@Param("keyWorld") String keyWorld,@Param("genderKey") String genderKey) throws Exception;

    List<ceoCustomDTO> categoryFoods(@Param("keyWorld") String keyWorld,@Param("categoryFoods") String categoryFoods) throws Exception;

    ;
}
