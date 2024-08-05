package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "exam_blueprint_id", nullable = false)
        @JsonBackReference
        private ExamBlueprint examBlueprint;

        @Column(nullable = false)
        private String questionText;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private QuestionType questionType;

        @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<MultipleChoiceAnswer> multipleChoiceAnswers;

}
