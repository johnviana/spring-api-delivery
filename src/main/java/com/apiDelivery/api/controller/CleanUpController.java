package com.apiDelivery.api.controller;

import com.apiDelivery.api.domain.service.CleanUpJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CleanUpController {

    @Autowired
    private CleanUpJob cleanUpService;

    @PostMapping("/clean-up")
    public void initiateCleanUp() {
        cleanUpService.cleanUpTables();
    }
}