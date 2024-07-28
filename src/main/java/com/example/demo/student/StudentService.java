package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = this.studentRepository.findByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException("Student already exists");
        }
        this.studentRepository.save(student);
    }

    public void deleteStudent(Long studentID) {
        boolean studentExists = this.studentRepository.existsById(studentID);
        if (!studentExists) {
            throw new IllegalArgumentException("Student does not exist");
        }
        this.studentRepository.deleteById(studentID);
    }

    @Transactional
    public void updateStudent(Long studentID, Student student) {
        Student studentOptional = this.studentRepository.findById(studentID).orElse(null);
        if (studentOptional == null) {
            throw new IllegalArgumentException("Student does not exist");
        }
        String name = student.getName();
        String email = student.getEmail();
        if (name != null && !name.equals(studentOptional.getName())) {
            System.out.println(name);
            studentOptional.setName(name);
        }
        if (email != null && !email.equals(studentOptional.getEmail())) {
            studentOptional.setEmail(email);
        }
        studentRepository.save(studentOptional);
    }
}
