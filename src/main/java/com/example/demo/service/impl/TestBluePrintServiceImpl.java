package com.example.demo.service.impl;

import com.example.demo.dataTransferObject.TestBlueprintResponse;
import com.example.demo.models.TestBlueprint;
import com.example.demo.repository.TestBluePrintRepository;
import com.example.demo.service.TestBluePrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestBluePrintServiceImpl implements TestBluePrintService {
    private final TestBluePrintRepository testBluePrintRepository;

    @Autowired
    public TestBluePrintServiceImpl (TestBluePrintRepository testBluePrintRepository) {
        this.testBluePrintRepository = testBluePrintRepository;
    }

    @Override
    public TestBlueprintResponse getTestBluePrint(Pageable pageable) {
        Page<TestBlueprint> testBlueprint = this.testBluePrintRepository.findAll(pageable);
        List<TestBlueprint> testBlueprints = testBlueprint.getContent();

        TestBlueprintResponse response = new TestBlueprintResponse();
        response.setContent(testBlueprints);
        response.setPageSize(pageable.getPageSize());
        response.setPageNo(pageable.getPageNumber());
        response.setTotalElements(response.getTotalElements());
        return response;
    }

    @Override
    public int createTestBluePrint(TestBlueprint testBlueprint) {
        TestBlueprint newTestBlueprint = new TestBlueprint();
        newTestBlueprint.setTitle(testBlueprint.getTitle());
        newTestBlueprint.setDescription(testBlueprint.getDescription());
        TestBlueprint createdTestBlueprint = testBluePrintRepository.save(newTestBlueprint);
        return createdTestBlueprint.getId();
    }
}
