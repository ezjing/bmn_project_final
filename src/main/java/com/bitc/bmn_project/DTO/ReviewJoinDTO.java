package com.bitc.bmn_project.DTO;

import lombok.Data;

@Data
public class ReviewJoinDTO {
  private int reviewIdx;
  private int customerIdx;
  private String customerNick;
  private int ceoIdx;
  private String reviewTitle;
  private String reviewContents;
  private String reviewImg;
  private String reviewDate;
  private double reviewScore;
  private String reviewDelete;
  private int reviewTagIdx;
  private String reviewTag1;
  private String reviewTag2;
  private String reviewTag3;
  private String reviewTag4;
  private String reviewTag5;
  private String reviewTag6;
  private String reviewTag7;
  private String reviewTag8;
}
