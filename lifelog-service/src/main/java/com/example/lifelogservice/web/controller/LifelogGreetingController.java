package com.example.lifelogservice.web.controller;

import com.example.common.http.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LifelogGreetingController {

    @GetMapping("/health")
    public ResponseEntity<ResponseObject<String>> greeting() {
        return ResponseEntity.ok().body(ResponseObject.of("Lifelog Service is up and running"));
    }
}
