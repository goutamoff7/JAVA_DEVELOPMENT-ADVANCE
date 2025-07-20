package com.example.eCommerceFB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private float price;
    private String category;

    //if we use Date class, we can use this format @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy" )
    private LocalDate releaseDate;
    private boolean productAvailable;
    private int stockQuantity;

    private String imageName;
    private String imageType;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] imageData;


}
