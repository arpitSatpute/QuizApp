package com.arpit.Quiz.controller;

import com.arpit.Quiz.dto.QuestionDTO;
import com.arpit.Quiz.entity.Question;
import com.arpit.Quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionService.getQuestions();
        return ResponseEntity.ok(questions);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionDTO questionDTO) {
        Question question = questionService.addQuestion(questionDTO);
        return ResponseEntity.ok(question);
    }
    @GetMapping("/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        List<Question> questions = questionService.getQuestionByCategory(category);
        return ResponseEntity.ok(questions);
    }
}
