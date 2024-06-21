package com.quiz.quizapp.service;

import com.quiz.quizapp.dao.QuestionDao;
import com.quiz.quizapp.dao.QuizDao;
import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.QuestionWrapper;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
       List<Question> questions=questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        //Optional<> is a class which is used to handle the null values. Here it is possible that quizDao.findById(id) may return null value
        Optional<Quiz> quiz =quizDao.findById(id); //findById() function gives optional data
        List<Question> questionfromDB= quiz.get().getQuestions();
        //Now we have to convert each object of Question in the list questionfromDB into the
        List<QuestionWrapper> questionForUser =new ArrayList<>();
        for (Question q: questionfromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);

        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz =quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right =0;
        int i=0;
        for (Response response : responses){
            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;

        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
