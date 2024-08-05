package com.example.demo.dataTransferObject;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ExamBlueprintDto {
    private Integer id;

    @NotBlank(message = "title is required")
    private String title;

    private String description;
    private List<QuestionRequestDto> questions;

    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
}
