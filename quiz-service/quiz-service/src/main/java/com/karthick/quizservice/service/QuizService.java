package com.karthick.quizservice.service;

import com.karthick.quizservice.dao.QuizDao;
import com.karthick.quizservice.feign.Quizinterface;
import com.karthick.quizservice.model.QuestionWrapper;
import com.karthick.quizservice.model.Quiz;
import com.karthick.quizservice.model.Response;
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
    Quizinterface quizinterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
     //feignclient-specifies the port and api by iteself that is instead of RestTemplate http://localhost:8080/Questions/Generate
        //requests the service directly with service name without port no or ip address
// servicediscovery-eureka from netflix-every service has to register here

     List<Integer> questions=quizinterface.getQuestionForQuiz(category,numQ).getBody();
     Quiz quiz=new Quiz();
     quiz.setTitle(title);
     quiz.setQuestionIds(questions);
    quizDao.save(quiz);


        return new ResponseEntity<>("success", HttpStatus.CREATED);


    }


   public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz=quizDao.findById(id).get();
        List<Integer> questionIds=quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions=quizinterface.getQuestionFromId(questionIds);
        return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
      ResponseEntity<Integer> score= quizinterface.getScore(responses);

        return score;
    }
}