package com.bitc.bmn_project.mapper;

import com.bitc.bmn_project.DTO.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BaeMapper {

  CeoDTO selectCeoDetail(int ceoIdx) throws Exception;

  int getFollows(@Param("ceoStore") String ceoStore) throws Exception;

  void updateFollow(int customerIdx, String ceoStore) throws Exception;

  CustomerDTO selectCustomerInfo(int customerIdx) throws Exception;

  void deleteFollow(int customerIdx, String ceoStore) throws Exception;

  Page<QuestionDTO> selectQuestionList(int ceoIdx) throws Exception;

  void insertQuestion(QuestionDTO questionDTO) throws Exception;

  void answerQuestion(QuestionDTO questionDTO) throws Exception;

  void updateCeoTpFollows(int followCnt, int ceoIdx) throws Exception;

  int getReviewCnt(int ceoIdx) throws Exception;

  List<ReviewJoinDTO> selectReviewList(int ceoIdx) throws Exception;

  void reviewDelete(int reviewIdx) throws Exception;

  List<CommentJoinDTO> selectCommentList(int ceoIdx) throws Exception;

  void commentInsert(CommentJoinDTO commentJoinDTO) throws Exception;
}
