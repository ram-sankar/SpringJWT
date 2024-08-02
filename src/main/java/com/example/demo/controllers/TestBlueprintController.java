package com.example.demo.controllers;

import com.example.demo.dataTransferObject.ApiResponse;
import com.example.demo.dataTransferObject.TestBlueprintResponse;
import com.example.demo.service.TestBluePrintService;
import com.example.demo.util.constants.ResponseMessages;
import io.swagger.v3.oas.annotations.Parameter;
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
    public ApiResponse<TestBlueprintResponse> getTestBlueprints(@ParameterObject Pageable pageable) {
        TestBlueprintResponse response = testBluePrintService.getTestBluePrint(pageable);
        return new ApiResponse<>(response, ResponseMessages.DATA_FETCHED_SUCCESSFULLY);
    }

//    @PostMapping
//    public ResponseEntity
}
