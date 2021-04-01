package com.endava.internship.warmup.domain.service;

import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

class QuickSort {

    int[] arrayToSort;
    BiPredicate<Integer, Integer> evaluateItems;

    private QuickSort(int[] arrayToSort, boolean ascendingSort) {
        if(ascendingSort){
            evaluateItems = (arg, arg1) -> arg <= arg1;
        } else {
            evaluateItems = (arg, arg1) -> arg >= arg1;
        }
        this.arrayToSort = arrayToSort;
    }
    
    public static QuickSort withArray(int[] arrayToSort, boolean ascendingSort){
        return new QuickSort(arrayToSort, ascendingSort);
    }

    private void random(int leftIndex, int rightIndex) {
        Random rand = new Random();
        int pivot = rand.nextInt(rightIndex - leftIndex) + leftIndex;
        int temp = arrayToSort[pivot];
        arrayToSort[pivot] = arrayToSort[rightIndex];
        arrayToSort[rightIndex] = temp;
    }

    private int sortPartitions(int leftIndex, int rightIndex) {
        random(leftIndex, rightIndex);
        int pivot = arrayToSort[rightIndex];
        int i = (leftIndex - 1);
        for (int j = leftIndex; j < rightIndex; j++) {
            if (evaluateItems.test(arrayToSort[j], pivot)) {
                i++;
                swapElements(i, j);
            }
        }
        swapElements(i + 1, rightIndex);
        return i + 1;
    }

    private void swapElements (int leftIndex, int rightIndex) {
        int temp = arrayToSort[leftIndex];
        arrayToSort[leftIndex] = arrayToSort[rightIndex];
        arrayToSort[rightIndex] = temp;
    }

    private void sort(int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int pi = sortPartitions(leftIndex, rightIndex);
            sort(leftIndex, pi - 1);
            sort(pi + 1, rightIndex);
        }
    }

    public int[] sort() {
        sort(0, arrayToSort.length - 1);
        return arrayToSort;
    }

    public boolean isSorted(){
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            if (!evaluateItems.test(arrayToSort[i], arrayToSort[i+1])) {
                return false;
            }
        }
        return true;
    }

}
