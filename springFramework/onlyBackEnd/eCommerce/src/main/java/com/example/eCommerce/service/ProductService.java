package com.example.eCommerce.service;

import com.example.eCommerce.model.Product;
import com.example.eCommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;

//    List<Product> products = new ArrayList<>
//            (Arrays.asList
//                    (       new Product(101, "Iphone", 50000),
//                            new Product(102, "Canon Camera", 70000),
//                            new Product(103, "Shure Mic", 10000)
//                    )
//            );

    public List<Product> getProducts()
    {
        return repo.findAll();
    }

    public Product getProductById(int prodId)
    {
        return repo.findById(prodId).orElse(new Product());
    }

    public void addProduct(Product product)
    {
        repo.save(product);
    }

    public void updateProduct(Product prod)
    {
        repo.save(prod);
    }

    public void deleteProduct(int prodId)
    {
        repo.deleteById(prodId);

    }
    public List<Product> searchProducts(String keyword)
    {
        return repo.searchProducts(keyword);
    }
}
