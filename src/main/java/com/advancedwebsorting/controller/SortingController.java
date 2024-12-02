package com.advancedwebsorting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advancedwebsorting.services.DatasetService;
import com.advancedwebsorting.services.SortingService;
import com.advancedwebsorting.utils.CustomResponse;

@RestController
@RequestMapping("/api/sort")
public class SortingController {

    private final SortingService sortingService;
    private final DatasetService datasetService;

    @Autowired
    public SortingController(SortingService sortingService, DatasetService datasetService) {
        this.sortingService = sortingService;
        this.datasetService = datasetService;
    }
    
    @PostMapping("/bucket")
    public ResponseEntity<CustomResponse> bucketSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.bucketSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using bucket sort", true, sortedDataset);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/heap")
    public ResponseEntity<CustomResponse> heapSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.heapSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using heap sort", true, sortedDataset);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/quick")
    public ResponseEntity<CustomResponse> quickSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.quickSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using quick sort", true, sortedDataset);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/merge")
    public ResponseEntity<CustomResponse> mergeSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.mergeSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using merge sort", true, sortedDataset);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/radix")
    public ResponseEntity<CustomResponse> radixSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.radixSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using radix sort", true, sortedDataset);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
