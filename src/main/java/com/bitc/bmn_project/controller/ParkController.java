package com.bitc.bmn_project.controller;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.CustomerDTO;
import com.bitc.bmn_project.DTO.ReviewCntDto;
import com.bitc.bmn_project.service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/bmn")
public class ParkController {
    @Autowired
    private MainService mainService;
    @RequestMapping(value = "/bmnMain", method = RequestMethod.GET)
    public ModelAndView bmnMainGet(HttpServletRequest req) throws Exception{
        ModelAndView mv = new ModelAndView("main/bmnMain");
        List<CeoDTO> coeDtoList = mainService.selectKFood();
        List<CeoDTO> selectJFood = mainService.selectJFoods();
        List<CeoDTO> selectCFood = mainService.selectCFoods();
        List<CeoDTO> selectWFood = mainService.selectWFoods();
        List<CeoDTO> scorePlacingList = mainService.scorePlacingLists();
        List<CeoDTO> followPlacingList = mainService.followPlacingLists();

        CustomerDTO customer = new CustomerDTO();
        CeoDTO ceo = new CeoDTO();
        HttpSession session = req.getSession();

        customer.setCustomerNick((String) session.getAttribute("customerNick"));
        ceo.setCeoStore((String) session.getAttribute("ceoStore"));

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
        mv.addObject("ceo", ceo);
        return mv;
    }

    @RequestMapping(value="/bmn/bmnSearchList", method = RequestMethod.GET)
    public ModelAndView searchListGet(
            @Param("keyWorld") String keyWorld,
            @Param("categoryFood") String categoryFood) throws Exception{
        ModelAndView mv = new ModelAndView("main/bmnSearchList");
        // keyWorld 값 가져 오는 지 확인

        // 평점 순위 검색 리스트
        List<CeoDTO> storeList = mainService.storeLists(keyWorld);
        // 리뷰 순 검색 리스트
        List<ReviewCntDto> reviewCntList = mainService.reviewCntLists(keyWorld);
        // 팔로우 순위 검색 리스트
        List<CeoDTO> followList = mainService.followLists(keyWorld);

        mv.addObject("storeList",storeList);
        mv.addObject("reviewCntList",reviewCntList);
        mv.addObject("followList",followList);
        mv.addObject("keyWorld",keyWorld);
        return mv;
    }

    @RequestMapping(value="/bmn/bmnSearchList", method = RequestMethod.POST)
    public void searchListPost() throws Exception{

    }
}
