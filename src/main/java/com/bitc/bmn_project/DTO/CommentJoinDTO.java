package com.bitc.bmn_project.DTO;

import lombok.Data;

@Data
public class CommentJoinDTO {
  private int commentIdx;
  private int reviewIdx;
  private int ceoIdx;
  private int customerIdx;
  private String commentContents;
  private String commentDate;
  private String customerNick;
}
