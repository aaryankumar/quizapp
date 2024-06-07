package com.quiz.quizapp.controller;

import com.quiz.quizapp.Question;
import com.quiz.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
//this is the class where we want to accept the request. This is the question controller.
// Responsible only for the questions not the quizapp

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){

        return questionService.getAllQuestions();
    }

}
