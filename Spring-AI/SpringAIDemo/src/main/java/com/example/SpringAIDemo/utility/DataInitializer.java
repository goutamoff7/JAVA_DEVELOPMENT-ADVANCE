package com.example.SpringAIDemo.utility;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private VectorStore vectorStore;

    public DataInitializer(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void init(){
        TextReader textReader = new TextReader(new ClassPathResource("product_details.txt"));

//        TokenTextSplitter splitter = new TokenTextSplitter();
        TokenTextSplitter splitter = new TokenTextSplitter(400, 20, 5, 3000, true);

//        chunkSize = 500
//        chunkOverlap = 50
//        minChunkSize = 5
//        maxTokenSize = 3072 (matches embedding model limit)

//        List<Document> documents = splitter.apply(textReader.get());
//        vectorStore.accept(documents);
//                      or
//        vectorStore.write(documents);

        List<Document> documents = splitter.split(textReader.get());
        vectorStore.write(documents);
    }
}


