package com.boo.spring_ai_openai.model.service;

import java.util.Map;

import com.boo.spring_ai_openai.model.CityInfo;
import com.boo.spring_ai_openai.model.CodeDto;
import com.boo.spring_ai_openai.model.CodeExplanation;
import com.boo.spring_ai_openai.model.CodeGeneration;
import com.boo.spring_ai_openai.model.Requirement;
import com.boo.spring_ai_openai.model.TicketClassification;

public interface AiService {

    String greeting();

    String greeString_2(String nombre);

    String chat(String prompt);

    String chatExpertSpring(String promt);

    CodeDto generateCode(Requirement requirement);    

    String explain(String code);

    String chatFormat(String topic);

    String analize(String text);

    String analize_json(String text);
    
    String city_info(String text);

    CityInfo city_info_dto(String text);

    TicketClassification classifyType(String text);

    CodeGeneration generateCodeTyped(String requirement);

    CodeExplanation explainCodeTyped(String code);

    Map<String, Object>chatMetadata(String prompt);

}

