package com.example.watchlist.services;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingService
{
    String apiUrl = "http://www.omdbapi.com/?apikey=7601914a&t=";
    public String getMovieRating(String title)
    {
        try
        {
            RestTemplate template = new RestTemplate();
            //api+title
            ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl+title,ObjectNode.class);
            ObjectNode jsonObject = response.getBody();
            String rating = jsonObject.path("imdbRating").asText();
            if(rating.isEmpty())
                throw new Exception();
            else
                return rating;
        }
        catch(Exception e)
        {
            //if exception arise for getting Imdb rating then user given rating will be taken
            System.out.println("Movie name is not available in Imdb or API is down "+e.getMessage());
            return null;
        }

    }

}
