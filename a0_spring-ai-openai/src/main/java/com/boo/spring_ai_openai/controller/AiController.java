package com.boo.spring_ai_openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RestController;

import com.boo.spring_ai_openai.model.service.AiService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController 
public class AiController {

    
    //private final ChatClient chatClient;
    private final AiService aiService;

    public AiController(/*ChatClient.Builder chatClientBuilder*/ AiService aiService) {
        //this.chatClient = chatClientBuilder.build();
        this.aiService = aiService;
    }
        

    @GetMapping("greeting")
    public String greeting() {
        //return this.chatClient.prompt().user("Dime hola mundo, en una sola linea").call().content();
        return this.aiService.greeting();
    }

    @GetMapping("greeting2")
    public String greeting_2(@RequestParam (defaultValue = "Vegeta") String nombre) {
        return this.aiService.greeString_2(nombre);
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        return  this.aiService.chat(prompt);
    }

    @PostMapping("/chat2")
    public String chat_2(@RequestBody String prompt) {
        return this.aiService.chat(prompt);
    }

    @PostMapping("/chat-expert")
    public String chat_expert(@RequestBody String prompt) {
        return this.aiService.chatExpertSpring(prompt);
    }
    

}
