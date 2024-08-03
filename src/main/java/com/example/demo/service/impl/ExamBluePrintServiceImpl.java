package com.example.demo.service.impl;

import com.example.demo.dataTransferObject.ExamBlueprintResponse;
import com.example.demo.exceptions.ExamBluePrintNotFoundException;
import com.example.demo.models.ExamBlueprint;
import com.example.demo.repository.ExamBluePrintRepository;
import com.example.demo.service.ExamBluePrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamBluePrintServiceImpl implements ExamBluePrintService {
    private final ExamBluePrintRepository examBluePrintRepository;

    @Autowired
    public ExamBluePrintServiceImpl(ExamBluePrintRepository examBluePrintRepository) {
        this.examBluePrintRepository = examBluePrintRepository;
    }

    @Override
    public ExamBlueprintResponse getTestBluePrint(Pageable pageable) {
        Page<ExamBlueprint> testBlueprint = examBluePrintRepository.findAll(pageable);
        List<ExamBlueprint> examBlueprints = testBlueprint.getContent();

        ExamBlueprintResponse response = new ExamBlueprintResponse();
        response.setContent(examBlueprints);
        response.setPageSize(pageable.getPageSize());
        response.setPageNo(pageable.getPageNumber());
        response.setTotalElements(response.getTotalElements());
        return response;
    }

    @Override
    public ExamBlueprint getTestBluePrintById(int id) {
        return examBluePrintRepository.findById((long) id).orElseThrow(
                () -> new ExamBluePrintNotFoundException("Test BluePrint Not Found")
        );
    }

    @Override
    public int createTestBluePrint(ExamBlueprint payload) {
        ExamBlueprint newExamBlueprint = new ExamBlueprint();
        newExamBlueprint.setTitle(payload.getTitle());
        newExamBlueprint.setDescription(payload.getDescription());
        ExamBlueprint createdExamBlueprint = examBluePrintRepository.save(newExamBlueprint);
        return createdExamBlueprint.getId();
    }

    @Override
    public void editTestBluePrint(int id, ExamBlueprint payload) {
        ExamBlueprint examBlueprint = getTestBluePrintById(id);
        examBlueprint.setTitle(payload.getTitle());
        examBlueprint.setDescription(payload.getDescription());
        examBluePrintRepository.save(examBlueprint);
    }

    @Override
    public void deleteTestBluePrintById(int id) {
        getTestBluePrintById(id);
        examBluePrintRepository.deleteById((long)id);
    }
}
