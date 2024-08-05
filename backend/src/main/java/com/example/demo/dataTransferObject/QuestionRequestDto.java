package com.example.demo.dataTransferObject;

import com.example.demo.models.QuestionType;
import lombok.Data;
import java.util.List;

@Data
public class QuestionRequestDto {
    private Integer id;
    private String questionText;
    private QuestionType questionType;
    private List<MultipleChoiceAnswerRequestDto> multipleChoiceAnswers;
}
