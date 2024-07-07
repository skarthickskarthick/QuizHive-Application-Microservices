package com.karthick.questionservice.controller;

import com.karthick.questionservice.model.Question;
import org.springframework.core.env.Environment;

import com.karthick.questionservice.model.QuestionWrapper;
import com.karthick.questionservice.model.Response;
import com.karthick.questionservice.service.QuestionService;
//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("Questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    Environment environment;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }//http://localhost:8080/Questions/allQuestions
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }//http://localhost:8080/Questions/category/java
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        questionService.addQuestion(question);
        return  questionService.addQuestion(question);
    }
//here we are adding the following to make question-service independent
    @GetMapping("Generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions)
    {
        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }//http://localhost:8080/Questions/Generate?categoryName=java&numQuestions=2
   @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds)
   {
       //loadbalancer-which port the client is running
       System.out.println(environment.getProperty("local.server.port"));
       return questionService.getQuestionsFromIds(questionIds);
   }//[1,5]  http://localhost:8080/Questions/getQuestions

   @PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
   {
       return questionService.getScore(responses);
   }/*http://localhost:8080/Questions/getscore[
    {
        "id":5,
            "response":"extends"
    },
    {
        "id":1,
            "response":127
    }
]*/

}