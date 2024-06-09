package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    //the category field is available in Question class hence we need not to define the whole function below.
    //The JPA is smart enough to understand from the function declaration itself that  we want data from database based on the category field.
    // For doing more customization in fetching the data from database this trick will not work, we have to use HQL(Hibernate query Language) or
    // JPQL(JPA Query Language) in the below function
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category= :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory( String category, int numQ);
}
