package com.example.SpringAIDemo.controller;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class EmbeddingController {

    EmbeddingModel embeddingModel;

    public EmbeddingController(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @GetMapping("/embedding")
    public float[] embedding(@RequestParam String text) {
        return embeddingModel.embed(text);
    }

    /*
      What is Cosine Similarity?
      Cosine similarity measures how similar two vectors are by looking at the angle between them,
      not their length.
      If vectors point in the same direction → similarity = 1
      If vectors are orthogonal (90° apart, unrelated) → similarity = 0
      If vectors point in opposite directions → similarity = -1

     Method to calculate cosine similarity between two vectors
     @param vectorA - first vector (double[])
     @param vectorB - second vector (double[])
     @return cosine similarity value between -1 and 1

     2. Formula

        For two vectors A and B:
        cosine similarity = A.B / ||A|| ||B||
        Where :
                A.B = ∑i=1 to n (Ai * Bi) - Dot Product
                ||A|| = sqrt [∑i=1 to n (Ai*Ai)] - (magnitude of vector A)
                ||B|| = sqrt [∑i=1 to n (Bi*Bi)] - (magnitude of vector B)
    */

    @GetMapping("/similarity")
    public double getSimilarity(@RequestParam String textA, @RequestParam String textB) {

        float[] vectorA = embeddingModel.embed(textA);
        float[] vectorB = embeddingModel.embed(textB);

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        // Calculate dot product and magnitudes simultaneously
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];  // sum of products
            normA += Math.pow(vectorA[i], 2);      // sum of squares for vectorA
            normB += Math.pow(vectorB[i], 2);      // sum of squares for vectorB
        }

        // Cosine similarity formula
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}




