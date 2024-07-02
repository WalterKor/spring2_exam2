package com.walter.baseApi.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TestController {
    @PostMapping("/test")
    public ResponseEntity<?> products() {
        Map<String, String> rMap = new HashMap<>();
        rMap.put("test", "OK");
        System.out.println("여기까지 옴");
        return new ResponseEntity<>(rMap, HttpStatus.OK);
    }
}
