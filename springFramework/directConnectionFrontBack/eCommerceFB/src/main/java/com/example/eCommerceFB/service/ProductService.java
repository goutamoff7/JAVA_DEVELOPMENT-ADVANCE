package com.example.eCommerceFB.service;

import com.example.eCommerceFB.model.Product;
import com.example.eCommerceFB.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    ProductRepository repository;

    public List<Product> getAllProducts()
    {
        return repository.findAll();
    }

    public Product getProductById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException
    {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repository.save(product);
    }

//    public Product UpdateProduct(int id, Product product, MultipartFile imageFile) throws IOException
//    {
//        Product productToBeUpdated = getProductById(id);
//        if(productToBeUpdated==null)
//            return null;
//        else
//            return addProduct(product,imageFile);
//    }

    public Product UpdateProduct(int id, Product product, MultipartFile imageFile) throws IOException
    {
            return addProduct(product,imageFile);
    }


    public void deleteProduct(int id)
    {
        repository.deleteById(id);
    }

    public List<Product> searchProducts(String keyword)
    {
        return repository.searchProducts(keyword);
    }
}
