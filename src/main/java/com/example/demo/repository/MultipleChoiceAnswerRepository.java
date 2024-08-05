package com.example.demo.repository;

import com.example.demo.models.MultipleChoiceAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultipleChoiceAnswerRepository extends JpaRepository<MultipleChoiceAnswer, Long> {
}
