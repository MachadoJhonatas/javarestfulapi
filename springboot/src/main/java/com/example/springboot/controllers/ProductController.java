package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct (@RequestBody @Valid ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        List<ProductModel> productList = productRepository.findAll();
        if(!productList.isEmpty())
            for (ProductModel product: productList )
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(product.getIdProduct())).withSelfRel());


        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID idProduct){
        Optional<ProductModel> product0 = productRepository.findById(idProduct);


        product0.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products"));

        return product0.<ResponseEntity<Object>>map(productModel
                                -> ResponseEntity.status(HttpStatus.OK).body(productModel)).orElseGet(()
                                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!"));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID idProduct,
                                                @RequestBody @Valid ProductRecordDto productRecordDto){

        Optional<ProductModel> product0 = productRepository.findById(idProduct);

        if(product0.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");

        var productModel = product0.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProducts(@PathVariable(value = "id") UUID idProduct){
        Optional<ProductModel> product0 = productRepository.findById(idProduct);

        if (product0.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");

        productRepository.delete(product0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully!");
    }
}
