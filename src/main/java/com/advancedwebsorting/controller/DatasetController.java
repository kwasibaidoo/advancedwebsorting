package com.advancedwebsorting.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advancedwebsorting.services.DatasetService;
import com.advancedwebsorting.utils.CustomResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")
public class DatasetController {

    private final DatasetService datasetService;

    @Autowired
    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }
    
    @PostMapping("/addtodataset")
    public ResponseEntity<CustomResponse> addToDataset(@RequestBody Integer number) {
        datasetService.addNumber(number);
        CustomResponse response = new CustomResponse("Number added successfully", true, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getdataset")
    public ResponseEntity<EntityModel<CustomResponse>> getDataset() {
        CustomResponse response = new CustomResponse("Numbers retrieved successfully", true, datasetService.getDataset());

        // Create the response model with links
        EntityModel<CustomResponse> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/clear")
    public ResponseEntity<CustomResponse> clearDataset() {
        datasetService.clearDataset();
        CustomResponse response = new CustomResponse("Dataset cleared", true, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse> updateDataset(@PathVariable("id") Integer id, @RequestBody Integer number) {
        System.out.println(id);
        datasetService.updateNumber(number, id);
        CustomResponse response = new CustomResponse("Dataset updated", true, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete/{position}")
    public ResponseEntity<CustomResponse> delete(@PathVariable("position") Integer position) {
        boolean success = datasetService.removeNumber(position);
        if(success) {
            CustomResponse response = new CustomResponse("Number removed from dataset", true, "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            CustomResponse response = new CustomResponse("Operation failed", false, "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
    }
    
    
    
    
}
