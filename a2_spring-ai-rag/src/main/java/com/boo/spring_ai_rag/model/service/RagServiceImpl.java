package com.boo.spring_ai_rag.model.service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RagServiceImpl implements RagService {

    private final VectorStore vectorStore;

    private final ChatClient chatClient;

    public RagServiceImpl(VectorStore vectorStore, ChatClient.Builder chatClientBuilder) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String ask(String question) {
        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(question)
                        .topK(4) //mejores 4 resultados
                        .build()
        );

        String context = results.stream().map(Document::getText).collect(Collectors.joining("\n\n---\n\n"));

        String prompt = """
                Responde solo con la informacion del contexto.
                Si no esta en el contexto, di: "No encontre esa informacion en los documentos."

                CONTEXTO:
                %s
                
                PREGUNTA:
                %s
               """.formatted(context, question);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
