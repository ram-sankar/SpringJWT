package com.example.demo.controllers;

import com.example.demo.dataTransferObject.ApiResponse;
import com.example.demo.dataTransferObject.TestBlueprintResponse;
import com.example.demo.models.TestBlueprint;
import com.example.demo.service.TestBluePrintService;
import com.example.demo.util.constants.ResponseMessages;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-blueprint")
public class TestBlueprintController {

    TestBluePrintService testBluePrintService;

    @Autowired
    public TestBlueprintController (TestBluePrintService testBluePrintService) {
        this.testBluePrintService = testBluePrintService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<TestBlueprintResponse>> getTestBlueprints(@ParameterObject Pageable pageable) {
        TestBlueprintResponse response = testBluePrintService.getTestBluePrint(pageable);
        ApiResponse<TestBlueprintResponse> apiResponse = new ApiResponse<>(response, ResponseMessages.DATA_FETCHED_SUCCESSFULLY);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> createTestBluePrint(@RequestBody TestBlueprint testBlueprint) {
        int id = testBluePrintService.createTestBluePrint(testBlueprint);
        ApiResponse<Integer> apiResponse = new ApiResponse<>(id, ResponseMessages.CREATED_SUCCESSFULLY);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
