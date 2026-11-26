package com.andres.course.springai.apps.mcpclient.controllers;

import com.andres.course.springai.apps.mcpclient.services.WeatherAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherAiService weatherAiService;
    public WeatherController(WeatherAiService weatherAiService) {
        this.weatherAiService = weatherAiService;
    }

    @GetMapping("/api/weather/ask")
    public String askWeather(@RequestParam String question) {
        return weatherAiService.ask(question);
    }
}
