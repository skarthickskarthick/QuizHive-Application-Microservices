package com.karthick.quizservice.controller;

import com.karthick.quizservice.model.QuestionWrapper;
import com.karthick.quizservice.model.QuizDto;
import com.karthick.quizservice.model.Response;
import com.karthick.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
    {
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
    }//http://localhost:8080/quiz/create?category=java&numQ=1&title=JQuiz



    @GetMapping("get/{id}")

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
    {
        return quizService.getQuizQuestions(id);
    }//http://localhost:8080/quiz/get/1
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses)
    {
        return quizService.calculateResult(id,responses);
    }//http://localhost:8080/quiz/submit/1
}