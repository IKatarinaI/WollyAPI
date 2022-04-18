package com.backend.backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/test")
public class TestController {


    @GetMapping
    public ResponseEntity login(Map map) {
        System.out.println(map);
        return ResponseEntity.ok("success");
    }

}
