package com.example.demo.dataTransferObject;

import lombok.Data;

@Data
public class MultipleChoiceAnswerRequestDto {
    private Integer id;
    private String answerText;
    private boolean isCorrect;
}
