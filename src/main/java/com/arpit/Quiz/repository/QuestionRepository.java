package com.arpit.Quiz.repository;

import com.arpit.Quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findQuestionByCategory(String category);

    @Query(value = "SELECT * FROM question WHERE category=:category ORDER BY RANDOM() LIMIT :numOfQuestions", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numOfQuestions);
}