package com.example.OnlineQuiz.Dto;

import com.example.OnlineQuiz.Entity.QuizEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QuestionDto {

   private Long id;
   @NotNull
   private Long quizId;
   @NotBlank
   private String question;
   @NotBlank
   private String option1;
   @NotBlank
   private String option2;
   @NotBlank
   private String option3;
   @NotBlank
   private String option4;
   @NotBlank
   private String correctOption;

   private List<QuizEntity> quiz;

    public List<QuizEntity> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<QuizEntity> quiz) {
        this.quiz = quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
}
