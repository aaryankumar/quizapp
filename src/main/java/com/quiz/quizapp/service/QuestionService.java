package com.quiz.quizapp.service;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //it will print the exception message
        }
        //if the there is an error then we will return an empty array and http Status code like below
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

        //findAll() is the in-built function which returns the list (list of question from database).
        //ResponseEntity<> takes 2 parameter. 1. data 2. the status code
        //for handling the errors and exceptions we will use ResponseEntity
    }
    //we will do the same thing for every function

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // save() is the in-built function which saves a row in the table
    public ResponseEntity<String> addQuestion(Question question) {
        //save can be used for update also
        try {
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
    }


    // deleteById() is the in-built function which saves a row in the table
    public String deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "success";
    }

}