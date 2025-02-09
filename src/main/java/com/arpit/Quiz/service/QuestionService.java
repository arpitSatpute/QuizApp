package com.arpit.Quiz.service;

import com.arpit.Quiz.dto.QuestionDTO;
import com.arpit.Quiz.entity.Question;
import com.arpit.Quiz.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionRepository questionRepository;
    private final ModelMapper modelmapper;

    public QuestionService(QuestionRepository questionRepository, ModelMapper modelmapper) {
        this.questionRepository = questionRepository;
        this.modelmapper = modelmapper;
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestion(QuestionDTO questionDTO) {
        logger.info("Received QuestionDTO: {}", questionDTO);

        Question question = modelmapper.map(questionDTO, Question.class);
        logger.info("Mapped Question entity: {}", question);

        Question savedQuestion = questionRepository.save(question);
        return question;
    }


    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findQuestionByCategory(category);
    }
}
