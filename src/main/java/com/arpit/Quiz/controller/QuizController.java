package com.arpit.Quiz.controller;

import com.arpit.Quiz.entity.QuestionWrapper;
import com.arpit.Quiz.entity.Response;
import com.arpit.Quiz.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numOfQuestions, @RequestParam String title) {
        return quizService.createQuiz(category, numOfQuestions, title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable int id) {
        return quizService.getQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> response) {
        return quizService.submitQuiz(id, response);
    }
}
