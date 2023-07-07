package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.*;
import com.github.pagehelper.Page;

import java.util.List;

public interface BaeService {

  CeoDTO selectCeoDetail(int ceoIdx) throws Exception;

  int getFollows(String ceoStore) throws Exception;

  void updateFollow(int customerIdx, String ceoStore) throws Exception;

  CustomerDTO selectCustomerInfo(int customerIdx) throws Exception;

  void deleteFollow(int customerIdx, String ceoStore) throws Exception;

  Page<QuestionDTO> selectQuestionList(int ceoIdx, int pageNum) throws Exception;

  void insertQuestion(QuestionDTO questionDTO) throws Exception;

  void answerQuestion(QuestionDTO questionDTO) throws Exception;

  void updateCeoTpFollows(int followCnt, int ceoIdx) throws Exception;

  int getReviewCnt(int ceoIdx) throws Exception;

  List<ReviewJoinDTO> selectReviewList(int ceoIdx) throws Exception;

  void reviewDelete(int reviewIdx) throws Exception;

  List<CommentJoinDTO> selectCommentList(int ceoIdx) throws Exception;

  void commentInsert(CommentJoinDTO commentJoinDTO) throws Exception;
}
