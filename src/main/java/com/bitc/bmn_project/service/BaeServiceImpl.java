package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.*;
import com.bitc.bmn_project.mapper.BaeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaeServiceImpl implements BaeService {
  @Autowired
  private BaeMapper baeMapper;

  // 가게정보 조회
  @Override
  public CeoDTO selectCeoDetail(int ceoIdx) throws Exception {
    CeoDTO ceoDTO = baeMapper.selectCeoDetail(ceoIdx);

    return ceoDTO;
  }

  // 팔로워 수 조회
  @Override
  public int getFollows(String ceoStore) throws Exception {
    int result = baeMapper.getFollows(ceoStore);
    return result;
  }

  @Override
  public void updateFollowStore(int customerIdx, String ceoStore) throws Exception {
    baeMapper.updateFollowStore(customerIdx, ceoStore);
  }

  @Override
  public CustomerDTO selectCustomerInfo(int customerIdx) throws Exception {
    CustomerDTO customerDTO = baeMapper.selectCustomerInfo(customerIdx);
    return customerDTO;
  }

  @Override
  public void deleteFollowStore(int customerIdx, String ceoStore) throws Exception {
    baeMapper.deleteFollowStore(customerIdx, ceoStore);
  }

  @Override
  public Page<QuestionDTO> selectQuestionList(int ceoIdx, int pageNum) throws Exception {

    PageHelper.startPage(pageNum, 5);

    return baeMapper.selectQuestionList(ceoIdx);
  }

  @Override
  public void insertQuestion(QuestionDTO questionDTO) throws Exception {
    baeMapper.insertQuestion(questionDTO);
  }

  @Override
  public void answerQuestion(QuestionDTO questionDTO) throws Exception {
    baeMapper.answerQuestion(questionDTO);
  }

  @Override
  public void updateCeoTpFollows(int followCnt, int ceoIdx) throws Exception {
    baeMapper.updateCeoTpFollows(followCnt, ceoIdx);
  }

  @Override
  public int getReviewCnt(int ceoIdx) throws Exception {
    int reviewCnt = baeMapper.getReviewCnt(ceoIdx);
    return reviewCnt;
  }

  @Override
  public List<ReviewJoinDTO> selectReviewList(int ceoIdx) throws Exception {
    List<ReviewJoinDTO> reviewList = baeMapper.selectReviewList(ceoIdx);

    return reviewList;
  }

  @Override
  public void reviewDelete(int reviewIdx) throws Exception {
    baeMapper.reviewDelete(reviewIdx);
  }

  @Override
  public List<CommentDTO> selectCommentList(int ceoIdx) throws Exception {
    List<CommentDTO> commentList = baeMapper.selectCommentList(ceoIdx);
    return commentList;
  }

  @Override
  public void commentInsert(CommentDTO commentDTO) throws Exception {
    baeMapper.commentInsert(commentDTO);
  }

  @Override
  public void commentDelete(int commentIdx) throws Exception {
    baeMapper.commentDelete(commentIdx);
  }

  @Override
  public void updateFollowNick(int ceoIdx, String customerNick) throws Exception {
    baeMapper.updateFollowNick(ceoIdx, customerNick);
  }

  @Override
  public void deleteFollowNick(int ceoIdx, String customerNick) throws Exception {
    baeMapper.deleteFollowNick(ceoIdx, customerNick);
  }

//  @Override
//  public List<CommentDTO> selectCommentListCeo(int ceoIdx) throws Exception {
//    List<CommentDTO> commentListCeo = baeMapper.selectCommentListCeo(ceoIdx);
//    return commentListCeo;
//  }

}
