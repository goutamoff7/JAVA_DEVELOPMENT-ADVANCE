package com.example.eCommerceFB.controller;

//import com.example.eCommerceFB.model.Product;
//import com.example.eCommerceFB.service.ProductService;

import com.example.eCommerceFB.model.Product;
import com.example.eCommerceFB.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
// Cross-Origin Resource Sharing, to handle CORS error which arises when we use different port no.
//for backEnd and frontEnd
@RequestMapping("/api")
public class ProductController {

    ProductService productService;

    @GetMapping("/hello")
    public String greet() {
        return "Welcome to My E-Commerce Project";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {
        try {
            Product savedProduct = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart Product product,
                                           @RequestPart MultipartFile imageFile) {
        try {
            Product updatedProduct = productService.UpdateProduct(id, product, imageFile);
            if (updatedProduct == null)
                return new ResponseEntity<>("Product Not Found", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to Update", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product productToBeDeleted = productService.getProductById(id);
        if (productToBeDeleted == null)
            return new ResponseEntity<>("Product not Found", HttpStatus.NOT_FOUND);
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword )
    {
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}



