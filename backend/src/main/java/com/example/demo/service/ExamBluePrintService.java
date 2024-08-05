package com.example.demo.service;

import com.example.demo.dataTransferObject.ExamBlueprintDto;
import com.example.demo.dataTransferObject.ExamBlueprintResponse;
import com.example.demo.models.ExamBlueprint;
import org.springframework.data.domain.Pageable;

public interface ExamBluePrintService {
    ExamBlueprintResponse getTestBluePrint(Pageable pageable);
    ExamBlueprintDto getTestBluePrintById(int id);
    int createTestBluePrint(ExamBlueprint examBlueprint);
    void editTestBluePrint(int id, ExamBlueprint examBlueprint);
    void deleteTestBluePrintById(int id);
}
