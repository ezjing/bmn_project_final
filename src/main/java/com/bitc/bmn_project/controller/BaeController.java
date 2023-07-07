package com.bitc.bmn_project.controller;

import com.bitc.bmn_project.DTO.*;
import com.bitc.bmn_project.service.BaeService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/bmn")
public class BaeController {
  @Autowired
  private BaeService baeService;


  @RequestMapping(value = "/viewDetail/{ceoIdx}", method = RequestMethod.GET)
  public ModelAndView viewDetail(@PathVariable("ceoIdx") int ceoIdx, HttpServletRequest req, @RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
    ModelAndView mv = new ModelAndView("bmn/viewDetail");

    HttpSession session = req.getSession();

    // 가게정보 조회
    CeoDTO ceoDto = baeService.selectCeoDetail(ceoIdx);

    // 팔로워수 조회
    String ceoStore = ceoDto.getCeoStore();
    int followCnt = baeService.getFollows(ceoStore);

    // 팔로워 수 ceoTp 연동
    baeService.updateCeoTpFollows(followCnt, ceoIdx);

    // 총 리뷰수 조회
    int reviewCnt = baeService.getReviewCnt(ceoIdx);

    // 리뷰정보 조회
    List<ReviewJoinDTO> reviewList = baeService.selectReviewList(ceoIdx);

    // 리뷰 태그 조회
//    List<ReviewJoinDTO> reviewJoinList = baeService.selectReviewTagList(ceoIdx);

    // 리뷰 댓글 리스트 조회
    List<CommentJoinDTO> commentList = baeService.selectCommentList(ceoIdx);


    // 문의 게시판(페이징 처리)
    PageInfo<QuestionDTO> questionList = new PageInfo<>(baeService.selectQuestionList(ceoIdx, pageNum), 5);

    // 문의 게시판(페이징 전)
//    List<QuestionDTO> questionList = baeService.selectQuestionList(ceoIdx, pageNum);

    session.setAttribute("customerIdx", 3);
    session.setAttribute("customerNick", "아이유");

    mv.addObject("ceoDto", ceoDto);
    mv.addObject("followCnt", followCnt);
    mv.addObject("reviewCnt", reviewCnt);
    mv.addObject("reviewList", reviewList);
    mv.addObject("commentList", commentList);
    mv.addObject("questionList", questionList);

    return mv;
  }

// 스크롤 저장 후 불러오기로 시도한 것

//  @RequestMapping(value = "/pageControl/{ceoIdx}", method = RequestMethod.GET)
//  public ModelAndView pageControll(@PathVariable("ceoIdx") int ceoIdx, HttpServletRequest req, @RequestParam(required = false, defaultValue = "1") int pageNum, HttpServletResponse resp) throws Exception {
//    ModelAndView mv = new ModelAndView("bmn/viewDetail");
//
//    HttpSession session = req.getSession();
//
//    // 가게정보 조회
//    CeoDTO ceoDto = baeService.selectCeoDetail(ceoIdx);
//
//    // 팔로워수 조회
//    String ceoStore = ceoDto.getCeoStore();
//    int followCnt = baeService.getFollows(ceoStore);
//
//    // 팔로워 수 ceoTp 연동
//    baeService.updateCeoTpFollows(followCnt, ceoIdx);
//
//    // 총 리뷰수 조회
//    int reviewCnt = baeService.getReviewCnt(ceoIdx);
//
//    // 리뷰정보 조회
//    List<ReviewDTO> reviewList = baeService.selectReviewList(ceoIdx);
//
//
//    // 문의 게시판(페이징 처리)
//    PageInfo<QuestionDTO> questionList = new PageInfo<>(baeService.selectQuestionList(ceoIdx, pageNum), 5);
//
//    // 문의 게시판(페이징 전)
////    List<QuestionDTO> questionList = baeService.selectQuestionList(ceoIdx, pageNum);
//
//    session.setAttribute("customerIdx", 3);
//    session.setAttribute("customerNick", "아이유");
//
//    mv.addObject("ceoDto", ceoDto);
//    mv.addObject("followCnt", followCnt);
//    mv.addObject("reviewCnt", reviewCnt);
//    mv.addObject("reviewList", reviewList);
//    mv.addObject("questionList", questionList);
//
//    String scrollY = (String) session.getAttribute("scrollY");
//    resp.setContentType("text/html; charset=UTF-8");
//    PrintWriter out = resp.getWriter();
//
//    String js = "<script>";
//    js += "window.scrollTo('" + scrollY + "');";
//    js += "</script>";
//
//    out.println(js);
//
//    return mv;
//  }


  // 리뷰에 댓글 달기
  @ResponseBody
  @RequestMapping(value = "/commentInsert", method = RequestMethod.POST)
  public Object commentInsert(CommentJoinDTO commentJoinDTO) throws Exception {
    baeService.commentInsert(commentJoinDTO);
    return "success";
  }



  // 관리자 리뷰 삭제
  @ResponseBody
  @RequestMapping(value = "/reviewDelete", method = RequestMethod.DELETE)
  public Object reviewDelete(@RequestParam("reviewIdx") int reviewIdx) throws Exception {
    baeService.reviewDelete(reviewIdx);

    return "success";
  }

  
  // 팔로우 추가/삭제
  @ResponseBody
  @RequestMapping(value = "/updateFollow", method = RequestMethod.PUT)
  public Object updateFollow(@RequestParam("ceoStore") String ceoStore, @RequestParam("customerIdx") int customerIdx) throws Exception {
    int result = 0;

    CustomerDTO customerDTO = baeService.selectCustomerInfo(customerIdx);

    if (customerDTO.getCustomerFollow().contains(ceoStore)) {
      baeService.deleteFollow(customerIdx, ceoStore);
    }
    else {
      baeService.updateFollow(customerIdx, ceoStore);
    }
    result = baeService.getFollows(ceoStore);

    return result;
  }

  // 사장님에게 문의하기
  @RequestMapping(value = "/insertQuestion", method = RequestMethod.POST)
  public String insertQuestion(QuestionDTO questionDTO) throws Exception {
    baeService.insertQuestion(questionDTO);

    return "redirect:/bmn/viewDetail/" + questionDTO.getCeoIdx();
  }


  // 문의하기에 대한 사장님 답변
  @ResponseBody
  @RequestMapping(value = "/updateAnswer", method = RequestMethod.PUT)
  public String answerQuestion(QuestionDTO questionDTO) throws Exception {
    baeService.answerQuestion(questionDTO);

    return "success";
//    return "redirect:/bmn/viewDetail/" + questionDTO.getCeoIdx();
  }

  // 페이징 처리를 위한 PageInfo 타입 리스트 가져오기
//  @ResponseBody
//  @RequestMapping(value = "/viewDetail/{ceoIdx}", method = RequestMethod.POST)
//  public Object questionPageControll(@PathVariable("ceoIdx") int ceoIdx, @RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
//    PageInfo<QuestionDTO> questionList = new PageInfo<>(baeService.selectQuestionList(ceoIdx, pageNum), 5);
//
//    return questionList;
//  }
  
}



















