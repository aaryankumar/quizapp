package com.quiz.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

// @Data annotation automatically generates the getter and setter methods for all the fields of the class.
@Data
@RequiredArgsConstructor
public class Response {

    private Integer id;
    private String response;

}
