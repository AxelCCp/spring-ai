package com.boo.spring_ai_openai.model.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.boo.spring_ai_openai.model.CityInfo;
import com.boo.spring_ai_openai.model.CodeDto;
import com.boo.spring_ai_openai.model.CodeExplanation;
import com.boo.spring_ai_openai.model.CodeGeneration;
import com.boo.spring_ai_openai.model.Requirement;
import com.boo.spring_ai_openai.model.TicketClassification;

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

    @Override
    public CodeDto generateCode(Requirement requirement) {
        String code = chatClient.prompt().system("Eres un experto nivel senior en java, jakarta y spring boot 4").user(requirement.requirement()).call().content();
        return new CodeDto(code);
    }

    @Override
    public String explain(String code) {

        PromptTemplate promptTemplate = new PromptTemplate("explica el código linea por linea: {code}");

        String userPrompt = promptTemplate.render(Map.of("code", code));

        return chatClient.prompt().system("Eres un profesor experto en programación, explica paso a paso de forma sencilla.")

        .user(userPrompt).call().content();
    }

    @Override
    public String chatFormat(String topic) {
        return chatClient.prompt()
                            .system("""
                                    eres un experto en tecnología.
                                    responde usando:
                                    -titulo
                                    -lista de 3 puntos importantes
                                    -un ejemplo practico                                
                            """).user(topic).call().content();
    }

    @Override
    public String analize(String text) {
        return chatClient.prompt()
                .system("eres un experto en analisis de texto.")
                .user("""
                    resume el siguiente texto en 3 puntos claves:
                    %s
                    """.formatted(text))
                .call().content();
    }

    @Override
    public String analize_json(String text) {
        return chatClient.prompt()
                .system("""
                    eres un experto en analisis de texto.
                    resume el siguiente texto en 3 puntos claves:
                    devuelve solo json valido.
                    Formato exacto:
                    {
                        "summary": "string",
                        "key_points": ["string", "string", "string"],
                        "sentiment": "positive|neutral|negative"
                    }
                    """)
                .user(text)
                .call().content();
    }

    @Override
    public String city_info(String text) {
        return  chatClient.prompt()
                        .system("""
                            eres un asistente experto en geografia.
                            responde solo con la informacion correcta y en formato json.
                        """)
                        .user("""
                            devuelve la informacion en español de la ciudad %s con este formato:
                        {
                            "city": "string",
                            "country": "string",
                            "populacion": number,
                            "descripcion": "string"
                        }
                        """.formatted(text)).call().content();
    }

    @Override
    public CityInfo city_info_dto(String text) {
        return  chatClient.prompt()
                        .system("""
                            eres un asistente experto en geografia.
                            responde solo con la informacion correcta y en formato json.
                        """)
                        .user("""
                            devuelve la informacion en español de la ciudad %s con este formato:
                        {
                            "city": "string",
                            "country": "string",
                            "population": number,
                            "description": "string"
                        }
                        """.formatted(text)).call().entity(CityInfo.class);
    }

    @Override
    public TicketClassification classifyType(String text) {
        return chatClient.prompt()
        .system("""
            Eres un sistema de clasificacion de tickets.
            Reponde solo en json valido.
            """)
            .user("""
                Clasifica el siguiente texto en una categoria y prioridad.
                Categorias:
                - Soporte
                - Ventas
                - Reclamo

                Formato:
                {
                    "category": "string",
                    "reason": "string",
                    "priority": number
                }
                
                Texto:
                %s
            """.formatted(text)).call().entity(TicketClassification.class);
    }

    @Override
    public CodeGeneration generateCodeTyped(String requirement) {
        return chatClient.prompt().system("""
            Eres un desarrollador experto y senior en java y spring boot.
            Genera una solucion limpia con buenas practicas.
            Devuelve solo json valido.
            No uses markdown.
            Ni pongas ```java ni ```json.
            Con el formato exacto del json:
            {
                "requirement": "string",
                "summary": "string",
                "code": "string",
                "notes": ["string", "string"]
            }
            
        """).user(requirement).call().entity(CodeGeneration.class);
    }

    @Override
    public CodeExplanation explainCodeTyped(String code) {
        return chatClient.prompt()
                .system("""
                    Eres un profesor experto en programacion.
                    Explica el código de forma simple paso a paso y liena por linea.
                    Devualve solo el json valido.
                    No uses markdown.
                    Formato exacto:
                    {
                        "lenguage": "string",
                        "summary": "string",
                        "line_by_line": [
                            {
                                "line": 1,
                                "explanation": "string"
                            }
                        ],
                        "final_explanation": "string"
                    }
                    """).user(code).call().entity(CodeExplanation.class);
    }

    @Override
    public Map<String, Object> chatMetadata(String prompt) {
        ChatResponse response = chatClient.prompt().user(prompt).call().chatResponse();
        return  Map.of("answer", response.getResult().getOutput().getText(), "metadata", response.getMetadata());
    }


}
