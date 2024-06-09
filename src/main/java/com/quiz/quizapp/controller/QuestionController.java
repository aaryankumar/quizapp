package com.quiz.quizapp.controller;

import com.quiz.quizapp.Question;
import com.quiz.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question") //by-default "/" added after the "question" here
public class QuestionController {
//this is the class where we want to accept the request. This is the question controller.
// Responsible only for the questions not the quizapp

    @Autowired
    QuestionService questionService;


//    public List<Question> getAllQuestions(){
//        return questionService.getAllQuestions();
//    }
//    for handling the errors and exceptions we will do the same thing by below method
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
        //ResponseEntity<> takes 2 parameter. 1. data 2. the status code
    }

    //the variables in the annotation are written inside {} 'double curly braces'
    @GetMapping("category/{category}") //whatever value we will pass in this category variable will be transfer to the category parameter in below fn by the help of @PathVariable annotation
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
    //@RequestBody tells springboot that the client is sending(which is Question in below case) data to the server in the body of request
    //just like @GetMapping for fetching data from database we have @PostMapping for posting data in database
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){

        return questionService.addQuestion(question);
        //
    }

    //@DeleteMapping is similar to @PostMapping and @GetMapping
    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
        //similarly we can perform update also using save() function.
    }
}
