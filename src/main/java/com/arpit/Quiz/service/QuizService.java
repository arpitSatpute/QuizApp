package com.arpit.Quiz.service;


import com.arpit.Quiz.entity.Question;
import com.arpit.Quiz.entity.QuestionWrapper;
import com.arpit.Quiz.entity.Quiz;
import com.arpit.Quiz.entity.Response;
import com.arpit.Quiz.repository.QuestionRepository;
import com.arpit.Quiz.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public QuizService(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }


    public ResponseEntity<String> createQuiz(String category, int numOfQuestions, String title) {
        List<Question> question = questionRepository.findRandomQuestionsByCategory(category, numOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(question);
        quizRepository.save(quiz);

        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(int quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        List<Question> questionsfromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questions = new ArrayList<>();

        for(Question q: questionsfromDB){
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questions.add(questionWrapper);
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(int id, List<Response> response) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response res : response) {
            if(res.getResponse().equals(questions.get(i).getOption1())) {
                right++;
            }
            i++;

        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
