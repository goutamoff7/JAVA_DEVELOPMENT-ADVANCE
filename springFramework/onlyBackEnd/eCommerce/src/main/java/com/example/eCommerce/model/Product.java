package com.example.eCommerce.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product
{
    @Id
    private int prodId;
    private String prodName;
    private int price;

}
