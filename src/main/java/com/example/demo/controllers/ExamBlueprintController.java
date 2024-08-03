package com.example.demo.controllers;

import com.example.demo.dataTransferObject.ApiResponse;
import com.example.demo.dataTransferObject.ExamBlueprintResponse;
import com.example.demo.models.ExamBlueprint;
import com.example.demo.service.ExamBluePrintService;
import com.example.demo.util.constants.ResponseMessages;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam-blueprint")
public class ExamBlueprintController {

    ExamBluePrintService examBluePrintService;

    @Autowired
    public ExamBlueprintController(ExamBluePrintService examBluePrintService) {
        this.examBluePrintService = examBluePrintService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ExamBlueprintResponse>> getTestBlueprints(@ParameterObject Pageable pageable) {
        ExamBlueprintResponse response = examBluePrintService.getTestBluePrint(pageable);
        ApiResponse<ExamBlueprintResponse> apiResponse = new ApiResponse<>(response, ResponseMessages.DATA_FETCHED_SUCCESSFULLY);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ExamBlueprint>> getTestBlueprints(@PathVariable int id) {
        ExamBlueprint response = examBluePrintService.getTestBluePrintById(id);
        ApiResponse<ExamBlueprint> apiResponse = new ApiResponse<>(response, ResponseMessages.DATA_FETCHED_SUCCESSFULLY);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> createTestBluePrint(@RequestBody ExamBlueprint examBlueprint) {
        int id = examBluePrintService.createTestBluePrint(examBlueprint);
        ApiResponse<Integer> apiResponse = new ApiResponse<>(id, ResponseMessages.CREATED_SUCCESSFULLY);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Integer>> editTestBluePrint(@PathVariable int id, @RequestBody ExamBlueprint examBlueprint) {
        examBluePrintService.editTestBluePrint(id, examBlueprint);
        ApiResponse<Integer> apiResponse = new ApiResponse<>(id, ResponseMessages.UPDATED_SUCCESSFULLY);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Integer>> deleteTestBluePrint(@PathVariable int id) {
        examBluePrintService.deleteTestBluePrintById(id);
        ApiResponse<Integer> apiResponse = new ApiResponse<>(id, ResponseMessages.DELETED_SUCCESSFULLY);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
