package com.example.OnlineQuiz.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerDto {

    private Long questionId;
    private String option;

}
