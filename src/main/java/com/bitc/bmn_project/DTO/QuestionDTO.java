package com.bitc.bmn_project.DTO;

import lombok.Data;

@Data
public class QuestionDTO {
    private int questionIdx;
    private int customerIdx;
    private int ceoIdx;
    private String questionComplete;
    private String questionTitle;
    private String questionContents;
    private String questionDate;
    private String answerContents;
    private String answerDate;

}
