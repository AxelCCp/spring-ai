package com.boo.spring_ai_rag.model.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentIndexService {

    void indexDocument(MultipartFile multipartFile) throws IOException;

}
