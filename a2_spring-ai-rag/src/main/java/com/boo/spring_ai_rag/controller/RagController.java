package com.boo.spring_ai_rag.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boo.spring_ai_rag.model.service.RagService;


@RestController
public class RagController {
    
    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/rag/ask")
    public String ask(@RequestBody String question) {
        return ragService.ask(question);
    }
}
