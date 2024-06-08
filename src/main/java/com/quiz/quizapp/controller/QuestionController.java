package com.quiz.quizapp.controller;

import com.quiz.quizapp.Question;
import com.quiz.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question") //by-default "/" added after the "question" here
public class QuestionController {
//this is the class where we want to accept the request. This is the question controller.
// Responsible only for the questions not the quizapp

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){

        return questionService.getAllQuestions();
    }
    //the variables in the annotation are written inside {} 'double curly braces'
    @GetMapping("category/{category}") //whatever value we will pass in this category variable will be transfer to the category parameter in below fn by the help of @PathVariable annotation
    public List<Question> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
    //@RequestBody tells springboot that the client is sending(which is Question in below case) data to the server in the body of request
    //just like @GetMapping for fetching data from database we have @PostMapping for posting data in database
    @PostMapping("add")
    public String addQuestion(@RequestBody Question question){

        return questionService.addQuestion(question);
    }

}
