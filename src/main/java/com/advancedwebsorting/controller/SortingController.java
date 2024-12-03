package com.advancedwebsorting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public ResponseEntity<EntityModel<CustomResponse>> bucketSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.bucketSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using bucket sort", true, sortedDataset);

        EntityModel<CustomResponse> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null,null)).withRel("updateNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/heap")
    public ResponseEntity<EntityModel<CustomResponse>> heapSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.heapSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using heap sort", true, sortedDataset);

        EntityModel<CustomResponse> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null,null)).withRel("updateNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/quick")
    public ResponseEntity<EntityModel<CustomResponse>> quickSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.quickSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using quick sort", true, sortedDataset);

        EntityModel<CustomResponse> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null,null)).withRel("updateNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/merge")
    public ResponseEntity<EntityModel<CustomResponse>> mergeSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.mergeSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using merge sort", true, sortedDataset);
        
        EntityModel<CustomResponse> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null,null)).withRel("updateNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/radix")
    public ResponseEntity<EntityModel<CustomResponse>> radixSort(){
        List<Integer> dataset = datasetService.getDataset();
        List<Integer> sortedDataset = sortingService.radixSort(dataset);
        CustomResponse response = new CustomResponse("Dataset sorted using radix sort", true, sortedDataset);

        EntityModel<CustomResponse> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).addToDataset(null)).withRel("addToDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).getDataset()).withRel("getDataset"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).delete(null)).withRel("removeNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).updateDataset(null,null)).withRel("updateNumber"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class).clearDataset()).withRel("clearDataset"));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

}
