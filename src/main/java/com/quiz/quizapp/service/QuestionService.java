package com.quiz.quizapp.service;

import com.quiz.quizapp.Question;
import com.quiz.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
        //findAll() is the in-built function which returns the list (list of question from database).
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    // save() is the in-built function which saves a row in the table
    public String addQuestion(Question question) {
        questionDao.save(question);
        return "success";
    }
}
