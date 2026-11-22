package com.boo.spring_ai_openai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CodeExplanation(String lenguage, String summary, 
                              @JsonProperty("line_by_line")  
                              List<LineExplanation> lineByLine, 
                              @JsonProperty("final_explanation")
                              String finalExplanation) {

    public record LineExplanation(Integer line, String explanation){}

}
