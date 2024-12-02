package com.advancedwebsorting.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class SortingService {

    // Heap Sort
    public List<Integer> heapSort(List<Integer> dataset) {
        int n = dataset.size();

        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(dataset, n, i);
        }

        // One by one extract elements
        for (int i = n - 1; i > 0; i--) {
            Collections.swap(dataset, 0, i);  // Move current root to end
            heapify(dataset, i, 0);  // Heapify the reduced heap
        }
        return dataset;
    }

    // Heapify a subtree rooted at index i
    private void heapify(List<Integer> dataset, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && dataset.get(left) > dataset.get(largest)) {
            largest = left;
        }
        if (right < n && dataset.get(right) > dataset.get(largest)) {
            largest = right;
        }

        if (largest != i) {
            Collections.swap(dataset, i, largest);
            heapify(dataset, n, largest);
        }
    }

    // Quick Sort
    public List<Integer> quickSort(List<Integer> dataset) {
        if (dataset.size() < 2) return dataset;

        int pivot = dataset.get(dataset.size() / 2);
        List<Integer> less = dataset.stream().filter(i -> i < pivot).collect(Collectors.toList());
        List<Integer> greater = dataset.stream().filter(i -> i > pivot).collect(Collectors.toList());
        List<Integer> equals = dataset.stream().filter(i -> i == pivot).collect(Collectors.toList());

        List<Integer> sorted = new ArrayList<>();
        sorted.addAll(quickSort(less));
        sorted.addAll(equals);
        sorted.addAll(quickSort(greater));

        return sorted;
    }

    // Merge Sort
    public List<Integer> mergeSort(List<Integer> dataset) {
        if (dataset.size() < 2) return dataset;

        int mid = dataset.size() / 2;
        List<Integer> left = dataset.subList(0, mid);
        List<Integer> right = dataset.subList(mid, dataset.size());

        return merge(mergeSort(left), mergeSort(right));
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));

        return result;
    }

    // Radix Sort
    public List<Integer> radixSort(List<Integer> dataset) {
        // Find the maximum number to know the number of digits
        int max = Collections.max(dataset);
        int exp = 1;
        while (max / exp > 0) {
            dataset = countingSortByDigit(dataset, exp);
            exp *= 10;
        }
        return dataset;
    }

    // A function to do counting sort of dataset according to the digit represented by exp
    private List<Integer> countingSortByDigit(List<Integer> dataset, int exp) {
        int n = dataset.size();
        int[] output = new int[n];
        int[] count = new int[10];

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            count[(dataset.get(i) / exp) % 10]++;
        }

        // Change count[i] so that it now contains actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(dataset.get(i) / exp) % 10] - 1] = dataset.get(i);
            count[(dataset.get(i) / exp) % 10]--;
        }

        // Copy the output array to dataset[], so that dataset now contains sorted numbers
        List<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sorted.add(output[i]);
        }
        return sorted;
    }

    // Bucket Sort
    public List<Integer> bucketSort(List<Integer> dataset) {
        if (dataset.isEmpty()) return dataset;

        // Find maximum value to determine number of buckets
        int max = Collections.max(dataset);
        int bucketCount = (int) Math.sqrt(dataset.size());

        // Create buckets and distribute elements into them
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int num : dataset) {
            int bucketIndex = (int) ((double) num / max * (bucketCount - 1));
            buckets.get(bucketIndex).add(num);
        }

        // Sort each bucket and concatenate the results
        List<Integer> sorted = new ArrayList<>();
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            sorted.addAll(bucket);
        }

        return sorted;
    }
}
