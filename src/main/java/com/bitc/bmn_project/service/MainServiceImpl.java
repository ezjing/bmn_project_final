package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.FilterDTO;
import com.bitc.bmn_project.DTO.ReviewCntDto;
import com.bitc.bmn_project.DTO.ceoCustomDTO;
import com.bitc.bmn_project.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MainServiceImpl implements MainService{
    @Autowired
    private MainMapper mainMapper;
    @Override
    public List<CeoDTO> selectKFood() throws Exception {
        return mainMapper.selectKFood();
    }

    @Override
    public List<CeoDTO> storeLists(String keyWorld) throws Exception {
        return mainMapper.storeLists(keyWorld);
    }

    @Override
    public List<CeoDTO> followLists(String keyWorld) throws Exception {
        return mainMapper.followLists(keyWorld);
    }

    @Override
    public List<CeoDTO> selectJFoods() throws Exception {
        return mainMapper.selectJFoods();
    }

    @Override
    public List<CeoDTO> selectCFoods() throws Exception {
        return mainMapper.selectCFoods();
    }

    @Override
    public List<CeoDTO> selectWFoods() throws Exception {
        return mainMapper.selectWFoods();
    }

    @Override
    public List<CeoDTO> scorePlacingLists() throws Exception {
        return  mainMapper.scorePlacingLists();
    }

    @Override
    public List<CeoDTO> followPlacingLists() throws Exception {
        return mainMapper.followPlacingLists();
    }

    @Override
    public List<ReviewCntDto> reviewCntLists(String keyWorld) throws Exception {
        return mainMapper.reviewCntLists(keyWorld);
    }

    @Override
    public List<ceoCustomDTO> ageFilterLists(String keyWorld, String customAge) throws Exception {

            return mainMapper.ageFilterLists(keyWorld,customAge);
    }

    @Override
    public List<ceoCustomDTO> genderFilterLists(String keyWorld, String genderKey) throws Exception {
        return mainMapper.genderFilterLists(keyWorld,genderKey);
    }

    @Override
    public List<ceoCustomDTO> categoryFoods(String keyWorld, String categoryFoods) throws Exception {
        return mainMapper.categoryFoods(keyWorld,categoryFoods);
    }

    @Override
    public List<ReviewCntDto> reviewCntOuterLists(String keyWorld) throws Exception {
        return mainMapper.reviewCntOuterLists(keyWorld);
    }

    @Override
    public List<FilterDTO> filterAllSearch(Map<String, Object> params) throws Exception {

        System.out.println("keyWorld:::"+params.get("keyWorld"));
        System.out.println("genderKey:::"+params.get("genderKey"));
        System.out.println("customAge:::"+params.get("customAge"));
        System.out.println("categoryFoods:::"+params.get("categoryFoods"));

        try {
            return mainMapper.filterAllSearch(params);
//            if(keyWorld.equals(keyWorld) && genderKey.equals(genderKey)){
//                return mainMapper.filterAllSearch(keyWorld, genderKey, null, null);
//            }
//            else if(keyWorld.equals(keyWorld) && genderKey.equals(genderKey) && customAge.equals(customAge)){
//                return mainMapper.filterAllSearch(keyWorld, genderKey, customAge, null);
//            }
//            else if(keyWorld.equals(keyWorld) && customAge.equals(customAge)){
//                return mainMapper.filterAllSearch(keyWorld, null, customAge, null);
//            }
//            else if(keyWorld.equals(keyWorld) && customAge.equals(customAge) && categoryFoods.equals(categoryFoods)){
//                return mainMapper.filterAllSearch(keyWorld, null, customAge, categoryFoods);
//            }
//            else if(keyWorld.equals(keyWorld) && categoryFoods.equals(categoryFoods)){
//                return mainMapper.filterAllSearch(keyWorld, null, null, categoryFoods);
//            }
//            else if(keyWorld.equals(keyWorld) && genderKey.equals(genderKey) && categoryFoods.equals(categoryFoods)){
//                return mainMapper.filterAllSearch(keyWorld, genderKey, null, categoryFoods);
//            }
//            else{
//                return mainMapper.filterAllSearch(keyWorld, genderKey, customAge, categoryFoods);
//            }
        }
        catch (NullPointerException e){
            System.out.println("errorMessage"+ e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
