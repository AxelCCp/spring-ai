package com.boo.spring_ai_openai.controller;

import org.springframework.web.bind.annotation.RestController;

import com.boo.spring_ai_openai.model.CityInfo;
import com.boo.spring_ai_openai.model.CodeDto;
import com.boo.spring_ai_openai.model.CodeExplanation;
import com.boo.spring_ai_openai.model.CodeGeneration;
import com.boo.spring_ai_openai.model.Requirement;
import com.boo.spring_ai_openai.model.TicketClassification;
import com.boo.spring_ai_openai.model.service.AiService;

import java.util.Map;

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
    

    @PostMapping("/generate-code")
    public CodeDto generateCode(@RequestBody Requirement requirement) {
        return aiService.generateCode(requirement);
    }


    @PostMapping("/explain-code")
    public String explainCode(@RequestBody String code) {
        return aiService.explain(code);
    }

    @PostMapping("/chat-format")
    public String charFormat(@RequestBody String prompt) {
        return aiService.chatFormat(prompt);
    }

    @PostMapping("/analyze")
    public String analyze(@RequestBody String text) {
        return aiService.analize(text);
    }

    @PostMapping("/analyze-json")
    public String analyzeJson(@RequestBody String text) {
        return aiService.analize_json(text);
    }

    @PostMapping("/city-json")
    public String cityJson(@RequestBody String text) {
        return aiService.city_info(text);
    }

    @PostMapping("/city-json-dto")
    public CityInfo cityJsonDto(@RequestBody String text) {
        return aiService.city_info_dto(text);
    }

    @PostMapping("/classify-type")
    public TicketClassification classify(@RequestBody String text) {
        return aiService.classifyType(text);
    }

    @PostMapping("/generate-code-json")
    public CodeGeneration generateCodeGeneration(@RequestBody String requirement) {
        return aiService.generateCodeTyped(requirement);
    }

    @PostMapping("/explain-code-json")
    public CodeExplanation explainCodeJson(@RequestBody String code) {
        return aiService.explainCodeTyped(code);
    }

    @GetMapping("chat-metadata")
    public Map<String, Object> chatMetadata(@RequestParam String prompt) {
        return aiService.chatMetadata(prompt);
    }
    
}


