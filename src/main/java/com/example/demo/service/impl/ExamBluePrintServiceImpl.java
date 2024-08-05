package com.example.demo.service.impl;

import com.example.demo.dataTransferObject.ExamBlueprintDto;
import com.example.demo.dataTransferObject.ExamBlueprintResponse;
import com.example.demo.exceptions.ExamBluePrintExsistException;
import com.example.demo.exceptions.ExamBluePrintNotFoundException;
import com.example.demo.models.ExamBlueprint;
import com.example.demo.models.MultipleChoiceAnswer;
import com.example.demo.models.Question;
import com.example.demo.repository.ExamBluePrintRepository;
import com.example.demo.repository.MultipleChoiceAnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.ExamBluePrintService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExamBluePrintServiceImpl implements ExamBluePrintService {

    @Autowired
    private ExamBluePrintRepository examBluePrintRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;

    @Autowired
    private ModelMapper mapper;

//    public ExamBluePrintServiceImpl (ModelMapper mapper) {
//        this.mapper = mapper;
//    }


    @Override
    public ExamBlueprintResponse getTestBluePrint(Pageable pageable) {
        Page<ExamBlueprint> examBlueprintPage = examBluePrintRepository.findAll(pageable);
//        List<ExamBlueprintDto> examBlueprints = examBlueprintPage.getContent();
        List<ExamBlueprintDto> examBlueprintDtos = examBlueprintPage.getContent().stream()
                .map(examBlueprint -> mapper.map(examBlueprint, ExamBlueprintDto.class))
                .collect(Collectors.toList());

        ExamBlueprintResponse response = new ExamBlueprintResponse();
        response.setContent(examBlueprintDtos);
        response.setPageSize(pageable.getPageSize());
        response.setPageNo(pageable.getPageNumber());
        response.setTotalElements(response.getTotalElements());
        return response;
    }

    @Override
    public ExamBlueprintDto getTestBluePrintById(int id) {
        ExamBlueprint examBlueprint = examBluePrintRepository.findById((long) id).orElseThrow(
                () -> new ExamBluePrintNotFoundException("Test BluePrint Not Found")
        );
        return mapper.map(examBlueprint, ExamBlueprintDto.class);
    }

    @Override
    public int createTestBluePrint(ExamBlueprint payload) {
        if (examBluePrintRepository.existsByTitle(payload.getTitle())) {
            throw new ExamBluePrintExsistException("An exam blueprint with the same title already exists.");
        }

        ExamBlueprint newExamBlueprint = new ExamBlueprint();
        newExamBlueprint.setTitle(payload.getTitle());
        newExamBlueprint.setDescription(payload.getDescription());

        List<Question> questions = payload.getQuestions();
        if (questions == null) {
            questions = Collections.emptyList(); // Initialize as empty list if null
        }

        for (Question q : questions) {
            q.setExamBlueprint(newExamBlueprint);

            List<MultipleChoiceAnswer> mcqAnswers = q.getMultipleChoiceAnswers();
            if (mcqAnswers == null) {
                mcqAnswers = Collections.emptyList(); // Initialize as empty list if null
            }

            for (MultipleChoiceAnswer mcq : mcqAnswers) {
                mcq.setQuestion(q);
                System.out.println("Setting isCorrect for answer: " + mcq.getAnswerText() + " to " + mcq.isCorrect());

            }

            q.setMultipleChoiceAnswers(mcqAnswers);
        }

        newExamBlueprint.setQuestions(questions);

        ExamBlueprint createdExamBlueprint = examBluePrintRepository.save(newExamBlueprint);
        return createdExamBlueprint.getId();
    }

    @Override
    @Transactional
    public void editTestBluePrint(int id, ExamBlueprint payload) {
        ExamBlueprint existingBlueprint = examBluePrintRepository.findById((long) id).orElseThrow(
                () -> new ExamBluePrintNotFoundException("Test BluePrint Not Found")
        );;
        validateTitleUniqueness(existingBlueprint, payload.getTitle());
        updateBlueprintDetails(existingBlueprint, payload);
        updateQuestions(existingBlueprint, payload.getQuestions());
        ExamBlueprint updatedBlueprint = examBluePrintRepository.save(existingBlueprint);
    }

    private void validateTitleUniqueness(ExamBlueprint existingBlueprint, String newTitle) {
        if (!existingBlueprint.getTitle().equals(newTitle) &&
                examBluePrintRepository.existsByTitle(newTitle)) {
            throw new ExamBluePrintExsistException("An exam blueprint with the same title already exists.");
        }
    }

    private void updateBlueprintDetails(ExamBlueprint existingBlueprint, ExamBlueprint payload) {
        existingBlueprint.setTitle(payload.getTitle());
        existingBlueprint.setDescription(payload.getDescription());
    }

    private void updateQuestions(ExamBlueprint existingBlueprint, List<Question> newQuestions) {
        if (newQuestions == null) {
            newQuestions = Collections.emptyList(); // Initialize as empty list if null
        }

        // Create a map of existing questions for quick lookup
        Map<Integer, Question> existingQuestionsMap = existingBlueprint.getQuestions().stream()
                .collect(Collectors.toMap(Question::getId, Function.identity()));

        List<Question> updatedQuestions = new ArrayList<>();

        for (Question newQuestion : newQuestions) {
            newQuestion.setExamBlueprint(existingBlueprint);

            if (newQuestion.getId() != null && existingQuestionsMap.containsKey(newQuestion.getId())) {
                // Update existing question
                Question existingQuestion = existingQuestionsMap.get(newQuestion.getId());
                existingQuestion.setQuestionText(newQuestion.getQuestionText());

                // Update multiple-choice answers
                updateMultipleChoiceAnswers(newQuestion.getMultipleChoiceAnswers(), existingQuestion);
            } else {
                // Add new question
                updateMultipleChoiceAnswers(newQuestion.getMultipleChoiceAnswers(), newQuestion);
                existingBlueprint.getQuestions().add(newQuestion);
            }
        }

        // Remove questions that are not in the new payload
        Set<Integer> newQuestionIds = newQuestions.stream()
                .map(Question::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        existingBlueprint.getQuestions().removeIf(existingQuestion ->
                !newQuestionIds.contains(existingQuestion.getId())
        );
    }

    private void updateMultipleChoiceAnswers(List<MultipleChoiceAnswer> newAnswers, Question existingQuestion) {
        if (newAnswers == null) {
            newAnswers = Collections.emptyList();
        }

        // Create a map of existing answers for quick lookup
        Map<Integer, MultipleChoiceAnswer> existingAnswersMap = existingQuestion.getMultipleChoiceAnswers().stream()
                .filter(answer -> answer.getId() != null)
                .collect(Collectors.toMap(MultipleChoiceAnswer::getId, Function.identity()));

        Set<Integer> newAnswerIds = newAnswers.stream()
                .filter(answer -> answer.getId() != null)
                .map(MultipleChoiceAnswer::getId)
                .collect(Collectors.toSet());

        // Remove answers that are not present in the new list
        existingQuestion.getMultipleChoiceAnswers().removeIf(existingAnswer ->
                existingAnswer.getId() != null && !newAnswerIds.contains(existingAnswer.getId())
        );

        for (MultipleChoiceAnswer newAnswer : newAnswers) {
            Integer newAnswerId = newAnswer.getId();
            if (newAnswerId != null && existingAnswersMap.containsKey(newAnswerId)) {
                // Update existing answer
                MultipleChoiceAnswer existingAnswer = existingAnswersMap.get(newAnswerId);
                existingAnswer.setAnswerText(newAnswer.getAnswerText());
                existingAnswer.setCorrect(newAnswer.isCorrect());
            } else {
                // Add new answer
                newAnswer.setQuestion(existingQuestion);
                existingQuestion.getMultipleChoiceAnswers().add(newAnswer);
            }
        }
    }

    @Override
    public void deleteTestBluePrintById(int id) {
        getTestBluePrintById(id);
        examBluePrintRepository.deleteById((long)id);
    }
}
