package com.stackroute.customresponse.controller;
import java.util.List;

import com.stackroute.customresponse.exception.ResourceNotFoundException;
import com.stackroute.customresponse.model.Product;
import com.stackroute.customresponse.response.ResponseHandler;
import com.stackroute.customresponse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //use try catch and return ResponseHandler with "Successfully retrieved data!" message
    @GetMapping("/products")
    public ResponseEntity<Object> getAllProduct() {
        try {
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, productService.getAllProduct());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Error retrieving data!", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    //use try catch and return ResponseHandler with "Successfully retrieved data!" message
    @GetMapping("/products/{id}")
    public ResponseEntity < Object > getProductById(@PathVariable long id) {
        try {
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, productService.getProductById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Error retrieving data!", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    //use try catch and return ResponseHandler with "Successfully added data!" message
    @PostMapping("/products")
    public ResponseEntity < Object > createProduct(@RequestBody Product product) {
        try {
            // Return HttpStatus.OK to match the test case expectation
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, productService.createProduct(product));
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Error adding data!", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    //use try catch and return ResponseHandler with "Deleted!" message
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable long id) {
        try {
            productService.deleteProduct(id);
            return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, null);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Error deleting data!", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
