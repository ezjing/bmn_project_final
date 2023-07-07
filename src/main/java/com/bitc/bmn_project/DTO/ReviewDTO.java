package com.bitc.bmn_project.DTO;

import lombok.Data;

@Data
public class ReviewDTO {
    private int reviewIdx;
    private int customerIdx;
    private String customerNick;
    private int ceoIdx;
    private String ceoStore;
    private String reviewTitle;
    private String reviewContents;
    private String reviewImg;
    private String reviewDate;
    private double reviewScore;
    private String reviewDelete;
}
