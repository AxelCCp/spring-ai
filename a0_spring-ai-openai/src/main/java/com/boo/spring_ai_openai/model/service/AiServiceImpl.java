package com.boo.spring_ai_openai.model.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service 
public class AiServiceImpl implements AiService{

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient.Builder chatClientbBuilder) {
        this.chatClient = chatClientbBuilder.build();
    }

    @Override
    public String greeting() {
        return this.chatClient.prompt()
            //se le da un contexto
            .system("responde siempre en español, en una sola linea y al final de tu respuesta agrega 123")
            .user("Dime... hola mundo!").call().content();
    }

    @Override
    public String greeString_2(String nombre) {
        return this.chatClient.prompt()
        //se le da un contexto
        .system("responde siempre en español, en una sola linea y al final de tu respuesta agrega ###")
        .user("Dime: hola %s".formatted(nombre)).call().content();
    }

    @Override
    public String chat(String prompt) {
        return chatClient.prompt().user(prompt).call().content();
    }

    @Override
    public String chatExpertSpring(String promt) {
        return chatClient.prompt().system("Eres un experto en java... responde de forma clara y simple. Tambien al inicio de cada respuesta pone JavaExpert:").user(promt).call().content();        
    }

}
