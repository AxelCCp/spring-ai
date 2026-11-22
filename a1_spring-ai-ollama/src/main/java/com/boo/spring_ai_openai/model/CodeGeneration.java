package com.boo.spring_ai_openai.model;

import java.util.List;

public record CodeGeneration(String requirement, String summary, String code, List<String>notes) {

}
