package com.example.eCommerceFB.controller;

import com.example.eCommerceFB.model.Product;
import com.example.eCommerceFB.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
// Cross-Origin Resource Sharing, to handle CORS error which arises when we use different port no.
//for backEnd and frontEnd
@RequestMapping("/api")
public class ProductController
{
    @Autowired
    ProductService service;

    //localhost:8080/api/products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);

    }

    //localhost:8080/api/products/1
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id)
    {
        Product product = service.getProductById(id);
        if(product!=null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //localhost:8080/api/product
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile)
    {
        try
        {
            Product product1 = service.addProduct(product,imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    localhost:8080/api/product/{productId}/image
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId)
    {
        Product product = service.getProductById(productId);
        byte[] imageData = product.getImageData();
        String imageType = product.getImageType();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(imageType))
                .body(imageData);

    }

//    @PutMapping("/product/{id}")
//    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
//                                                 @RequestPart MultipartFile imageFile )
//    {
//        try {
//            Product product1 = service.UpdateProduct(id,product,imageFile);
//            if(product1!=null)
//                return new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK);
//            else
//                throw new IOException();
//        } catch (IOException e) {
//            return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
//        }
//    }
//    localhost:8080/api/product/1
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
                                                 @RequestPart MultipartFile imageFile )
    {
        Product productToBeUpdated = service.getProductById(id);
        System.out.println(productToBeUpdated);
        try {
            if(productToBeUpdated!=null)
            {
                Product product1 = service.UpdateProduct(id,product,imageFile);
                return new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK);
            }
            else
                throw new IOException("Product Not Found");
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to Update "/*+e.getMessage()*/,HttpStatus.BAD_REQUEST);
        }
    }
    //    localhost:8080/api/product/1
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    {
        Product productToBeDeleted =service.getProductById(id);
        if(productToBeDeleted!=null)
        {
            service.deleteProduct(id);
            return new ResponseEntity<>("Product Deleted",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not Found",HttpStatus.NOT_FOUND);
    }
    //    localhost:8080/api/products?search=vivo
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword )
    {
        List<Product> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
