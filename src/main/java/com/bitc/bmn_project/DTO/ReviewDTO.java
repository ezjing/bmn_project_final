package com.bitc.bmn_project.DTO;

import lombok.Data;

@Data
public class ReviewDTO {
    private int reviewIdx;
    private int customerIdx;
    private int ceoIdx;
    private String reviewTitle;
    private String reviewContents;
    private String reviewImg;
    private String reviewDate;
    private double reviewScore;
    private String reviewDelete;
}
