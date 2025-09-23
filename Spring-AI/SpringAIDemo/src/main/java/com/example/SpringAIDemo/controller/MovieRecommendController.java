package com.example.SpringAIDemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class MovieRecommendController {

    ChatClient chatClient;

//    requires both AI credentials in application.properties and configuration for AI chat model
    public MovieRecommendController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("api/recommend")
    public ResponseEntity<String> recommend(@RequestParam String type,
                                            @RequestParam String year,
                                            @RequestParam String language) {

        String temp = """
                You are a movie recommendation assistant.
                
                I want to watch a {type} movie tonight,
                The language I am looking for is {language},
                and preferably released in {year}.
                
                Your task:
                1. Suggest more than one movie title only.
                2. If you cannot find a movie from the given year, choose the closest year you have data for.
                3. Do NOT provide plot, cast, length, or IMDB rating.
                
                Response format should be:
                Sl no.
                 Movie Name :
                 Movie Type :
                 Released year :
                """;

        PromptTemplate promptTemplate = new PromptTemplate(temp);
        Prompt prompt = promptTemplate.create(Map.of("type", type,"year",year, "language", language));


        String response =
                chatClient
                        .prompt(prompt)
                        .call()
                        .content();
        return ResponseEntity.ok(response);
    }
}


