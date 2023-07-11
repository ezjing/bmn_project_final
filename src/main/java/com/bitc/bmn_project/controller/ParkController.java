package com.bitc.bmn_project.controller;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.CustomerDTO;
import com.bitc.bmn_project.DTO.ReviewCntDto;
import com.bitc.bmn_project.DTO.ceoCustomDTO;
import com.bitc.bmn_project.service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
//@RequestMapping("/bmn")
public class ParkController {
    @Autowired
    private MainService mainService;
    @RequestMapping(value = "/bmn/bmnMain", method = RequestMethod.GET)
    public ModelAndView bmnMainGet(HttpServletRequest req) throws Exception{
        ModelAndView mv = new ModelAndView("main/bmnMain");
        List<CeoDTO> coeDtoList = mainService.selectKFood();
        List<CeoDTO> selectJFood = mainService.selectJFoods();
        List<CeoDTO> selectCFood = mainService.selectCFoods();
        List<CeoDTO> selectWFood = mainService.selectWFoods();
        List<CeoDTO> scorePlacingList = mainService.scorePlacingLists();
        List<CeoDTO> followPlacingList = mainService.followPlacingLists();



//        CustomerDTO customer = new CustomerDTO();
//        CeoDTO ceo = new CeoDTO();
//        HttpSession session = req.getSession();
//        customer.setCustomerNick((String) session.getAttribute("customerNick"));
//        ceo.setCeoStore((String) session.getAttribute("ceoStore"));

        // 평점순 List
        mv.addObject("scorePlacingList",scorePlacingList);
        // 팔로우 순 List
        mv.addObject("followPlacingList",followPlacingList);
        // 한식 List
        mv.addObject("ceoDtoList",coeDtoList);
        // 일식 List
        mv.addObject("selectJFood",selectJFood);
        // 중식 List
        mv.addObject("selectCFood",selectCFood);
        // 양식 List
        mv.addObject("selectWFood",selectWFood);

//        mv.addObject("ceo", ceo);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/bmn/bmnSearchList", method = RequestMethod.GET)
    public ModelAndView searchListGet(
            @RequestParam(value = "keyWorld", required = false) String keyWorld
        ) throws Exception{
        ModelAndView mv = new ModelAndView("main/bmnSearchList");
        // keyWorld 값 가져 오는 지 확인
        System.out.println("key값::"+keyWorld);
//        System.out.println("wo"+customAge.get(0).toString());
        // 평점 순위 검색 리스트
        List<CeoDTO> storeList = mainService.storeLists(keyWorld);
        // 리뷰 순 검색 리스트
        List<ReviewCntDto> reviewCntList = mainService.reviewCntLists(keyWorld);
        // 팔로우 순위 검색 리스트
        List<CeoDTO> followList = mainService.followLists(keyWorld);

        // 성별만 검색 시

        // 카테고리만 검색 시

        // 전체 카테로기 클릭 시

        mv.addObject("storeList",storeList);
        mv.addObject("reviewCntList",reviewCntList);
        mv.addObject("followList",followList);
        mv.addObject("keyWorld",keyWorld);
        return mv;
    }

    @RequestMapping(value="/bmn/bmnSearchList", method = RequestMethod.POST)
    public void searchListPost() throws Exception{

    }

    // 연령층 필터
    @ResponseBody
    @RequestMapping(value="/bmn/bmnFilterList", method = RequestMethod.POST)
    public Object filterListPost(
            @RequestParam(value = "keyWorld", required = false) String keyWorld,
            @RequestParam(value = "customAge[]", required = false) List<String> customAge
    ) throws Exception{
        ModelAndView mv = new ModelAndView("main/bmnSearchList");
        System.out.println("keyWorld:::"+keyWorld);

        // 이용자 층 검색 시
        List<ceoCustomDTO> ageFilterList = mainService.ageFilterLists(keyWorld, customAge);

        return ageFilterList;
    }

    // 성별 필터 기능
    @ResponseBody
    @RequestMapping(value="/bmn/bmnFilterGender", method = RequestMethod.POST)
    public Object filterGenderPost(
            @RequestParam(value = "keyWorld", required = false) String keyWorld,
            @RequestParam(value = "genderKey", required = false) String genderKey
    ) throws Exception{
        ModelAndView mv = new ModelAndView("main/bmnSearchList");
        System.out.println("genderKey::"+genderKey);

        List<ceoCustomDTO> genderFilterList = mainService.genderFilterLists(keyWorld,genderKey);
        return genderFilterList;
    }

    @ResponseBody
    @RequestMapping(value="/bmn/bmnFilterFood", method = RequestMethod.POST)
    public Object filterFoodPost(
            @RequestParam(value="keyWorld", required = false) String keyWorld,
            @RequestParam(value ="categoryFoods", required = false) String categoryFoods
    ) throws Exception{
        ModelAndView mv = new ModelAndView("main/bmnSearchList");

        List<ceoCustomDTO> categoryFood = mainService.categoryFoods(keyWorld,categoryFoods);


        return categoryFood;
    }

}
