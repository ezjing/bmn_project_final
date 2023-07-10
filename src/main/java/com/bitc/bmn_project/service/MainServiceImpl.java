package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.ReviewCntDto;
import com.bitc.bmn_project.DTO.ceoCustomDTO;
import com.bitc.bmn_project.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<ceoCustomDTO> ageFilterLists(String keyWorld, List<String> customAge) throws Exception {

            List<String> arr1 = new ArrayList<String>();


            for(int i=0; i<customAge.size();i++){
                arr1.add(customAge.get(i).toString());
            }

            System.out.println(arr1.get(0).toString());
            String CustomAge1 = arr1.get(0).toString();
            String CustomAge2 = arr1.get(0).toString();
            String CustomAge3 = arr1.get(0).toString();
            String CustomAge4 = arr1.get(0).toString();
            String CustomAge5 = arr1.get(0).toString();
            String CustomAge6 = arr1.get(0).toString();

            System.out.println("null??:::"+CustomAge1);
            return mainMapper.ageFilterLists(keyWorld,CustomAge1,CustomAge2,CustomAge3,
                    CustomAge4,CustomAge5,CustomAge6);
    }

    @Override
    public List<ceoCustomDTO> genderFilterLists(String keyWorld, String genderKey) throws Exception {
        return mainMapper.genderFilterLists(keyWorld,genderKey);
    }

    @Override
    public List<ceoCustomDTO> categoryFoods(String keyWorld, String categoryFoods) throws Exception {
        return mainMapper.categoryFoods(keyWorld,categoryFoods);
    }
}
