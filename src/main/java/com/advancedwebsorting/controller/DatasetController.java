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
    public ResponseEntity<EntityModel<CustomResponse>> addToDataset(@RequestBody String input) {
        
        String[] numbersArray = input.split(",");
        for (String numStr : numbersArray) {
            try {
                Integer number = Integer.parseInt(numStr.trim());
                datasetService.addNumber(number);
            } catch (NumberFormatException e) {
                
                CustomResponse errorResponse = new CustomResponse("Invalid number format", false, "The input contains a non-integer value: " + numStr);
                EntityModel<CustomResponse> errorResource = EntityModel.of(errorResponse);
                return new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
            }
        }
        CustomResponse response = new CustomResponse("Number added successfully", true, "");

        EntityModel<CustomResponse> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null,null)).withRel("updateNumber"));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("/getdataset")
    public ResponseEntity<EntityModel<CustomResponse>> getDataset() {
        CustomResponse response = new CustomResponse("Numbers retrieved successfully", true, datasetService.getDataset());

        
        EntityModel<CustomResponse> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null,null)).withRel("updateNumber"));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/clear")
    public ResponseEntity<EntityModel<CustomResponse>> clearDataset() {
        datasetService.clearDataset();
        CustomResponse response = new CustomResponse("Dataset cleared", true, "");

        EntityModel<CustomResponse> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null,null)).withRel("updateNumber"));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityModel<CustomResponse>> updateDataset(@PathVariable("id") Integer id, @RequestBody Integer number) {
        boolean success = datasetService.updateNumber(number, id);
        if(success){
            CustomResponse response = new CustomResponse("Dataset updated", true, "");

            EntityModel<CustomResponse> resource = EntityModel.of(response);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));
    
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        else {
            CustomResponse response = new CustomResponse("Operation failed", false, "");

            EntityModel<CustomResponse> resource = EntityModel.of(response);

            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        
    }

    @PostMapping("/delete/{position}")
    public ResponseEntity<EntityModel<CustomResponse>> delete(@PathVariable("position") Integer position) {
        boolean success = datasetService.removeNumber(position);
        if(success) {
            CustomResponse response = new CustomResponse("Number removed from dataset", true, "");

            EntityModel<CustomResponse> resource = EntityModel.of(response);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null, null)).withRel("updateNumber"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));
            
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        else{
            CustomResponse response = new CustomResponse("Operation failed", false, "");
            EntityModel<CustomResponse> resource = EntityModel.of(response);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null, null)).withRel("updateNumber"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));
            
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        
    }
    
    
    
    
}
