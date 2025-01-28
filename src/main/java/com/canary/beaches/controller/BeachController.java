package com.canary.beaches.controller;

import com.canary.beaches.model.Beach;
import com.canary.beaches.service.BeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beaches")
public class BeachController {

    private final BeachService beachService;

    @Autowired
    public BeachController(BeachService beachService) {
        this.beachService = beachService;
    }

    @GetMapping
    public ResponseEntity<List<Beach>> findAll() {
        List<Beach> beaches = beachService.findAll();
        return ResponseEntity.ok(beaches);
    }
}
