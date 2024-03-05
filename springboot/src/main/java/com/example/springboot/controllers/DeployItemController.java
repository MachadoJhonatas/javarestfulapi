package com.example.springboot.controllers;

import com.example.springboot.dtos.DeployItemRecordDto;
import com.example.springboot.models.DeployItemModel;
import com.example.springboot.repositories.DeployItemRepository;
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
public class DeployItemController {

    @Autowired
    DeployItemRepository deployItemRepository;

    @SuppressWarnings("null")
    @PostMapping("/deployItem")
    public ResponseEntity<DeployItemModel> saveProduct (@RequestBody @Valid DeployItemRecordDto DeployItemRecordDto){
        var deployItemModel = new DeployItemModel();

        BeanUtils.copyProperties(DeployItemRecordDto, deployItemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(deployItemRepository.save(deployItemModel));
    }

    @SuppressWarnings("null")
    @GetMapping("/deployItem")
    public ResponseEntity<List<DeployItemModel>> getAllDeployItems(){
        List<DeployItemModel> deployItemList = deployItemRepository.findAll();
        if(!deployItemList.isEmpty())
            for (DeployItemModel deployItem: deployItemList )
                deployItem.add(linkTo(methodOn(DeployItemController.class).getOneProduct(deployItem.getIdProduct())).withSelfRel());


        return ResponseEntity.status(HttpStatus.OK).body(deployItemList);
    }

    @SuppressWarnings("null")
    @GetMapping("/deployItem/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID idProduct){
        Optional<DeployItemModel> deployItem0 = deployItemRepository.findById(idProduct);


        deployItem0.get().add(linkTo(methodOn(DeployItemController.class).getAllDeployItems()).withRel("DeployItems"));

        return deployItem0.<ResponseEntity<Object>>map(DeployItemModel
                                -> ResponseEntity.status(HttpStatus.OK).body(DeployItemModel)).orElseGet(()
                                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!"));
    }

    @SuppressWarnings("null")
    @PutMapping("/deployItem/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID idProduct,
                                                @RequestBody @Valid DeployItemRecordDto DeployItemRecordDto){

        Optional<DeployItemModel> deployItem0 = deployItemRepository.findById(idProduct);

        if(deployItem0.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");

        var DeployItemModel = deployItem0.get();
        BeanUtils.copyProperties(DeployItemRecordDto, DeployItemModel);
        return ResponseEntity.status(HttpStatus.OK).body(deployItemRepository.save(DeployItemModel));
    }

    @SuppressWarnings("null")
    @DeleteMapping("/deployItem/{id}")
    public ResponseEntity<Object> deleteDeployItems(@PathVariable(value = "id") UUID idProduct){
        Optional<DeployItemModel> deployItem0 = deployItemRepository.findById(idProduct);

        if (deployItem0.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");

        deployItemRepository.delete(deployItem0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully!");
    }
}
