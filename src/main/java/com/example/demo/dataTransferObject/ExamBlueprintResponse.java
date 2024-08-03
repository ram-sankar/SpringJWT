package com.example.demo.dataTransferObject;
import com.example.demo.models.ExamBlueprint;
import lombok.Data;

import java.util.List;

@Data
public class ExamBlueprintResponse {
    private List<ExamBlueprint> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
}
