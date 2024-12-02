package com.advancedwebsorting.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.advancedwebsorting.model.Dataset;

@Service
public class DatasetService {
    
    private Dataset dataset;

    public DatasetService() {
        this.dataset = new Dataset();
    }

    public void addNumber(int number) {
        dataset.getData().add(number);
    }

    public List<Integer> getDataset() {
        return dataset.getData();
    }

    public boolean removeNumber(int position) {
        return dataset.getData().remove(Integer.valueOf(position));
    }

    public void clearDataset(){
        dataset.getData().clear();
    }

    public void updateNumber(int number, int position) {
        dataset.getData().set(position, number);
    }
}
