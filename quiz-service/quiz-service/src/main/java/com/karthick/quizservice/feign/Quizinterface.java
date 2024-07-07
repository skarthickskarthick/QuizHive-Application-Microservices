package com.karthick.quizservice.feign;

import com.karthick.quizservice.model.QuestionWrapper;
import com.karthick.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface Quizinterface {
    @GetMapping("Questions/Generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);
    //http://localhost:8080/Questions/Generate?categoryName=java&numQuestions=2
    @PostMapping("Questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);
    //[1,5]  http://localhost:8080/Questions/getQuestions

    @PostMapping("Questions/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
    //http://localhost:8080/Questions/getscore
}
