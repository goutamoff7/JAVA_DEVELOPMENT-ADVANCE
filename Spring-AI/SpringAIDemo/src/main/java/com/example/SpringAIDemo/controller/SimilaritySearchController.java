package com.example.SpringAIDemo.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SimilaritySearchController {

    private VectorStore vectorStore;

    public SimilaritySearchController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @GetMapping("/search-similar-product")
    public List<Document> getProduct(@RequestParam String query) {
//        return vectorStore.similaritySearch(query);

        // return 2 matching element
        SearchRequest searchRequest = SearchRequest
                .builder()
                .query(query)
                .topK(2)
                .build();
        return vectorStore
                .similaritySearch(searchRequest);
    }
}
