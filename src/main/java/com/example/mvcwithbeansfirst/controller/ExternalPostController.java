package com.example.mvcwithbeansfirst.controller;

import com.example.mvcwithbeansfirst.service.ExternalApiClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external")
public class ExternalPostController {

    private final ExternalApiClient externalApiClient;

    public ExternalPostController(ExternalApiClient externalApiClient) {
        this.externalApiClient = externalApiClient;
    }

    // Контроллер только принимает ID из URL и передает его в сервис
    @GetMapping("/posts/{id}")
    public String getPostFromExternalApi(@PathVariable Long id) {
        // Вся грязная работа с URL и RestTemplate спрятана внутри сервиса
        return externalApiClient.getExternalPostById(id);
    }
}