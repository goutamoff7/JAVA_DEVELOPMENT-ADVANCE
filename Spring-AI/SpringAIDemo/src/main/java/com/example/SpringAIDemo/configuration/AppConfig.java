package com.example.SpringAIDemo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {

    public static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    ChatClient chatClient(VertexAiGeminiChatModel chatModel){
        return  ChatClient.create(chatModel);
    }

//    @Bean
//    VectorStore vectorStore(EmbeddingModel embeddingModel){
//        return  SimpleVectorStore.builder(embeddingModel).build();
//    }

    @Bean
    VectorStore vectorStore(JdbcTemplate jdbcTemplate, EmbeddingModel embeddingModel){
        LOGGER.info("called custom vectorStore");
        return  PgVectorStore.builder(jdbcTemplate,embeddingModel)
                .dimensions(768)
                .distanceType(PgVectorStore.PgDistanceType.COSINE_DISTANCE)
                .indexType(PgVectorStore.PgIndexType.HNSW)
                .initializeSchema(true)
                .build();
    }

}
