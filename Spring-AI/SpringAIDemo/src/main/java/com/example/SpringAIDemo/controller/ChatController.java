package com.example.SpringAIDemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ChatController {


    ChatClient chatClient;

//    only one AI credentials should be in application.properties
//    public ChatController(ChatClient.Builder chatClientBuilder) {
//        this.chatClient = chatClientBuilder.build();
//    }

//  requires both AI credentials in application.properties and configuration for AI chat model
//    public ChatController(ChatClient chatClient) {
//        this.chatClient = chatClient;
//    }

//    with Advisors:
/*  Flow Summary
    User sends a message → via our ChatController.
    ChatClient pipeline runs → advisors are applied.
    The memory advisor fetches previous messages from chatMemory.
    It injects them into the prompt so the LLM sees the context.
    Chat request goes to model (e.g., OpenAI, Vertex AI, etc.).
    Model returns a response.
    Advisor saves both request and response into chatMemory.
    Next time → history is already there, making it a stateful conversation.

            ✅ So, the important idea is:
    1. MessageWindowChatMemory is an implementation of ChatMemory that maintains a
    sliding window of messages (like a fixed-sized buffer of past conversation turns).
    2. chatMemory stores past messages.
    3. MessageChatMemoryAdvisor connects that memory to your ChatClient.
    4. This makes our ChatClient capable of multi-turn conversations without manually managing state.
    */

    ChatMemory chatMemory = MessageWindowChatMemory.builder().build();

public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor
                        .builder(chatMemory)
                        .build())
                .build();
    }

    @GetMapping("/api/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message) {
        String response =
                chatClient
                .prompt(message)
                .call()
                .content();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api-2/{message}")
    public ResponseEntity<String> getAnswer2(@PathVariable String message) {

        ChatResponse chatResponse = chatClient
                .prompt(message)
                .call()
                .chatResponse();

        System.out.println(chatResponse.getMetadata().getModel()); // Not works for VertexAiGemini
        System.out.println(chatResponse.getMetadata().getUsage());

        String response = chatResponse.getResult().getOutput().getText();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api-3/{message}")
    public ResponseEntity<String> getAnswer3(@PathVariable String message) {

        ChatResponse chatResponse = chatClient
                .prompt(message)
                .call()
                .chatClientResponse()
                .chatResponse();

        String response = chatResponse.getResult().getOutput().getText();

        return ResponseEntity.ok(response);
    }
}


