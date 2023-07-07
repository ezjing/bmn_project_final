package com.bitc.bmn_project.DTO;

import lombok.Data;

@Data
public class CommentDTO {
    private int commentIdx;
    private int customerIdx;
    private int ceoIdx;
    private String commentContents;
    private String commentDate;
    private String commentDelete;
}
