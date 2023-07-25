package com.transactions.app.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonFileService {
  private final ObjectMapper objectMapper = new ObjectMapper();

  public <T> T readJsonFile(String filePath, Class<T> valueType) throws IOException {
    File file = new File(filePath);
    T data = objectMapper.readValue(file, valueType);
    return data;
  }
}
