package com.bitc.bmn_project.controller;

import com.bitc.bmn_project.DTO.*;
import com.bitc.bmn_project.service.BaeService;
import com.bitc.bmn_project.service.SimService;
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
//@RequestMapping("/bmn1")/
public class BaeController {
  @Autowired
  private BaeService baeService;

  @Autowired
  private SimService simService;


  @RequestMapping(value = "/bmn/viewDetail/{ceoIdx}", method = RequestMethod.GET)
  public ModelAndView viewDetail(@PathVariable("ceoIdx") int ceoIdx, HttpServletRequest req, @RequestParam(required = false, defaultValue = "1") int pageNum, HttpServletResponse resp) throws Exception {
    ModelAndView mv = null;

    CeoDTO ceoDto;

    HttpSession session = req.getSession();
    String user = (String) session.getAttribute("user");
    if (user == "admin") {
      // 가게정보 조회(관리자)
      ceoDto = baeService.selectCeoDetail(ceoIdx);
    }
    // 가게정보 조회(비회원, 손님, 사장)
    else {
      ceoDto = baeService.selectCeoDetail2(ceoIdx);
    }

    if (ceoDto != null) {
      mv = new ModelAndView("bmn/viewDetail");
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
      List<CommentDTO> commentList = baeService.selectCommentList(ceoIdx);

      // 사장님 댓글 리스트 조회
//    List<CommentDTO> commentListCeo = baeService.selectCommentListCeo(ceoIdx);


      // 문의 게시판(페이징 처리)~
      PageInfo<QuestionDTO> questionList = new PageInfo<>(baeService.selectQuestionList(ceoIdx, pageNum), 5);
      mv.addObject("ceoDto", ceoDto);
      mv.addObject("followCnt", followCnt);
      mv.addObject("reviewCnt", reviewCnt);
      mv.addObject("reviewList", reviewList);
      mv.addObject("commentList", commentList);
      mv.addObject("questionList", questionList);
    }
    else {
      resp.setContentType("text/html; charset=UTF-8");
      PrintWriter out = resp.getWriter();

      String script = "<script>";
      script += "alert('식당 정보를 조회할 수 없습니다.');";
      script += "history.back();";
      script += "</script>";

      out.println(script);
      out.close();
    }

    return mv;
  }


  // 리뷰에 댓글 달기
  @ResponseBody
  @RequestMapping(value = "/bmn/commentInsert", method = RequestMethod.POST)
  public Object commentInsert(CommentDTO commentDTO) throws Exception {
    baeService.commentInsert(commentDTO);
    return "success";
  }

  // 리뷰에 달린 댓글 삭제(본인만 보임)
  @ResponseBody
  @RequestMapping(value = "/bmn/commentDelete", method = RequestMethod.PUT)
  public Object commentDelete(@RequestParam("commentIdx") int commentIdx) throws Exception {
    baeService.commentDelete(commentIdx);

    return "success";
  }


  // 관리자 리뷰 삭제
  @ResponseBody
  @RequestMapping(value = "/bmn/reviewDelete", method = RequestMethod.DELETE)
  public Object reviewDelete(@RequestParam("reviewIdx") int reviewIdx, @RequestParam("ceoIdx") int ceoIdx) throws Exception {
    baeService.reviewDelete(reviewIdx);

// 평점 값을 가져와서 평균 내는 서비스
    double avg = simService.getAverage(ceoIdx);
    System.out.println(avg);
    // 평균을 ceoDTO에 update 해주는 서비스
    simService.updateScore(avg, ceoIdx);

    return "success";
  }

  
  // 팔로우 추가/삭제
  @ResponseBody
  @RequestMapping(value = "/bmn/updateFollow", method = RequestMethod.PUT)
  public Object updateFollow(@RequestParam("ceoStore") String ceoStore, @RequestParam("customerIdx") int customerIdx, @RequestParam("ceoIdx") int ceoIdx) throws Exception {
    int result = 0;

    CustomerDTO customerDTO = baeService.selectCustomerInfo(customerIdx);
    String customerNick = customerDTO.getCustomerNick();

    if (customerDTO.getCustomerFollow().contains(ceoStore)) {
      baeService.deleteFollowStore(customerIdx, ceoStore);
      baeService.deleteFollowNick(ceoIdx, customerNick);
    }
    else {
      baeService.updateFollowStore(customerIdx, ceoStore);
      baeService.updateFollowNick(ceoIdx, customerNick);

    }
    result = baeService.getFollows(ceoStore);

    return result;
  }

  // 사장님에게 문의하기
  @RequestMapping(value = "/bmn/insertQuestion", method = RequestMethod.POST)
  public String insertQuestion(QuestionDTO questionDTO) throws Exception {

    baeService.insertQuestion(questionDTO);

    return "redirect:/bmn/viewDetail/" + questionDTO.getCeoIdx();
  }


  // 문의하기에 대한 사장님 답변
  @ResponseBody
  @RequestMapping(value = "/bmn/updateAnswer", method = RequestMethod.PUT)
  public Object answerQuestion(QuestionDTO questionDTO) throws Exception {
    baeService.answerQuestion(questionDTO);

    return "success";
//    return "redirect:/bmn/viewDetail/" + questionDTO.getCeoIdx();
  }

//   페이징 처리를 위한 PageInfo 타입 리스트 가져오기
  @ResponseBody
  @RequestMapping(value = "/bmn/questionPaging", method = RequestMethod.GET)
  public Object questionPageControl(@RequestParam("ceoIdx") int ceoIdx, @RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
    PageInfo<QuestionDTO> questionList = new PageInfo<>(baeService.selectQuestionList(ceoIdx, pageNum), 5);

    return questionList;
  }
  
}



















