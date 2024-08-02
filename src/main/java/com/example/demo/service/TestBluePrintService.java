package com.example.demo.service;

import com.example.demo.dataTransferObject.TestBlueprintResponse;
import com.example.demo.models.TestBlueprint;
import org.springframework.data.domain.Pageable;

public interface TestBluePrintService {
    TestBlueprintResponse getTestBluePrint(Pageable pageable);
    int createTestBluePrint(TestBlueprint testBlueprint);
}
