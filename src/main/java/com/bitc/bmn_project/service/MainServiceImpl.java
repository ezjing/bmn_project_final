package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.ReviewCntDto;
import com.bitc.bmn_project.DTO.ceoCustomDTO;
import com.bitc.bmn_project.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        try{
            if(customAge.size() == 0){
                System.out.println("값이 0입니다.");
                return null;
            }
            else if(customAge.size() > 0){
                System.out.println("값이 1개 이상 있습니다.");
                return null;
            }
        }
        catch (Exception e){
            System.out.println("에러가 발생했습니다."+e.getMessage());
            e.printStackTrace();
        }


        return null;
    }
}
