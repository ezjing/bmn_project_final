package com.bitc.bmn_project.DTO;

import lombok.Data;

// 맛, 만족도, 서비스, 음식량, 대기시간, 청결도, 목적, 가격
@Data
public class ReviewTagDTO {
  private int reviewTagIdx;
  private int reviewIdx;
  private String reviewTag1;
  private String reviewTag2;
  private String reviewTag3;
  private String reviewTag4;
  private String reviewTag5;
  private String reviewTag6;
  private String reviewTag7;
  private String reviewTag8;
}
