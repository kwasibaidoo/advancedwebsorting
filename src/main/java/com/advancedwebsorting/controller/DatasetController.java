package com.advancedwebsorting.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advancedwebsorting.services.DatasetService;
import com.advancedwebsorting.utils.CustomResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



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
    public ResponseEntity<CustomResponse> getDataset() {
        CustomResponse response = new CustomResponse("Numbers retrieved successfully", true, datasetService.getDataset());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/clear")
    public ResponseEntity<CustomResponse> clearDataset() {
        datasetService.clearDataset();
        CustomResponse response = new CustomResponse("Dataset cleared", true, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    
}