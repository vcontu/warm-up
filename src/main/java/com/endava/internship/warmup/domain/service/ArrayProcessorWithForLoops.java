package com.endava.internship.warmup.domain.service;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;

public class ArrayProcessorWithForLoops implements ArrayProcessor {

    /**
     * Return true if there are no numbers that divide by 10
     *
     * @param input non-null immutable array of ints
     */
    @Override
    public boolean noneMatch(final int[] input) {
        boolean nm = true;
        for (int i = 0; i < input.length; i++) {
            if (input[i] % 10 == 0) {
                nm = false;
            }
        }
        return nm;
    }

    /**
     * Return true if at least one value in input matches the predicate
     *
     * @param input     non-null immutable array of ints
     * @param predicate invoke the predicate.test(int value) on each input element
     */
    @Override
    public boolean someMatch(final int[] input, IntPredicate predicate) {
        boolean b = false;
        for (int i : input) {
            if (predicate.test(i)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * Return true if all values processed by function, matches the predicate
     *
     * @param input     non-null immutable array of Strings. No element is null
     * @param function  invoke function.applyAsInt(String value) to transform all the input elements into an int value
     * @param predicate invoke predicate.test(int value) to test the int value obtained from the function
     */
    @Override
    public boolean allMatch(final String[] input, ToIntFunction<String> function, IntPredicate predicate) {
        int size = input.length;
        int count = 0;
        boolean rt = false;
        for (String s : input) {
            if (predicate.test(function.applyAsInt(s))) {
                count++;
            }
        }
        if (count == size) {
            rt = true;
        }
        return rt;
    }

    /**
     * Copy values into a separate array from specific index to stopindex
     *
     * @param input          non-null array of ints
     * @param startInclusive the first index of the element from input to be included in the new array
     * @param endExclusive   the last index prior to which the elements are to be included in the new array
     * @throws IllegalArgumentException when parameters are outside of input index bounds
     */
    @Override
    public int[] copyValues(int[] input, int startInclusive, int endExclusive) throws IllegalArgumentException {
        if (startInclusive < 0 || startInclusive > endExclusive) {
            throw new IllegalArgumentException("copy value out of bounds");
        }
        int arr[] = new int[endExclusive - startInclusive];
        int j = 0;
        for (int i = startInclusive; i < endExclusive; i++) {
            arr[j] = input[i];
            j++;
        }
        return arr;
    }

    /**
     * Replace even index values with their doubles and odd indexed elements with their negative
     *
     * @param input non-null immutable array of ints
     * @return new array with changed elements
     */
    @Override
    public int[] replace(final int[] input) {
        int[] arr = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            if (input[i] % 2 == 0) {
                arr[i] = input[i] * 2;
            } else if (input[i] % 2 != 0) {
                arr[i] = -input[i];
            }
        }

        return arr;
    }

    /**
     * Find the second max value in the array
     *
     * @param input non-null immutable array of ints
     */
    @Override
    public int findSecondMax(final int[] input) {
        int[] fsmArr = new int[input.length];
        for (int i = 0; i < fsmArr.length; i++) {
            fsmArr[i] = input[i];
        }
        int[] fsmArr_final = Arrays.stream(fsmArr).distinct().toArray();
        /*Arrays.sort(fsmArr);*/
        /*int tv;
        for(int i = 0;i< fsmArr.length;i++){
            for(int j= i+1; j<fsmArr.length;i++){
                if(fsmArr[i] > fsmArr[j]){
                    tv = fsmArr[i];
                    fsmArr[i] = fsmArr[j];
                    fsmArr[j] = tv;
                }
            }
        }*/
        return fsmArr_final[fsmArr.length - 2];
    }

    /**
     * Return in reverse first negative numbers, then positive numbers from array
     *
     * @param input non-null immutable array of ints.
     * @return example: input {3, -5, 4, -7, 2 , 9}
     * result: {-7, -5, 9, 2, 4, 3}
     */
    @Override
    public int[] rearrange(final int[] input) {
        int[] arrRearranged = new int[6];
        int minus = 0;
        int minus_array[] = new int[2];
        int plus_array[] = new int[4];
        int plus = 0;
        int j = 0;
        int r = 0;
        int inc = 0;

        for (int i = 0; i < input.length; i++) {
            if (input[i] < 0) {
                minus = input[i];
                minus_array[j] = input[i];
                j++;
            } else if (input[i] > 0) {
                plus = input[i];
                plus_array[r] = input[i];
                r++;
            }
        }
        for (int i = minus_array.length - 1; i >= 0; i--) {
            arrRearranged[inc] = minus_array[i];
            inc++;
        }
        for (int i = plus_array.length - 1; i >= 0; i--) {
            arrRearranged[inc] = plus_array[i];
            inc++;
        }
        return arrRearranged;
    }

    /**
     * Remove (filter) all values which are smaller than (input max element - 10)
     *
     * @param input non-null immutable array of ints
     * @return The result array should not contain empty cells!
     */
    @Override
    public int[] filter(final int[] input) {
        int[] arrFiltred = new int[4];
        int j = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] >= 0) {
                arrFiltred[j] = input[i];
                j += 1;
            }
        }
        return arrFiltred;
    }

    /**
     * Insert values into input array at a specific index.
     *
     * @param input          non-null immutable array of ints.
     * @param startInclusive the index of input at which the first element from values array should be inserted
     * @param values         the values to be inserted from startInclusive index
     * @return new array containing the combined elements of input and values
     * @throws IllegalArgumentException when startInclusive is out of bounds for input
     */
    @Override
    public int[] insertValues(final int[] input, int startInclusive, int[] values) throws IllegalArgumentException {

        if (startInclusive < 0) {
            throw new IllegalArgumentException("start inclusive negative");
        }
        int insArray[] = new int[input.length + values.length];
        int r = 0;
        int vi = 0;
        for (int j = 0; j < startInclusive; j++) {
            insArray[j] = input[j];
            r++;

        }

        for (int i = 0; i < values.length; i++) {
            insArray[r] = values[i];

            r++;
        }
        for (int k = startInclusive; k < input.length; k++) {
            insArray[r] = input[k];
            r++;
        }

        return insArray;
    }

    /**
     * Merge two sorted input and input2 arrays so that the return values are also sorted
     *
     * @param input  first non-null array
     * @param input2 second non-null array
     * @return new array containing all elements sorted from input and input2
     * @throws IllegalArgumentException if either input or input are not sorted ascending
     */
    @Override
    public int[] mergeSortedArrays(int[] input, int[] input2) throws IllegalArgumentException {
        int[] arr = new int[input.length + input2.length];
        class Local {
            int[] merge(int[] arr_1, int[] arr_2, int[] res, int n, int m) {
                int i = 0, j = 0, k = 0;
                while (i < n) {
                    res[k] = arr_1[i];
                    i++;
                    k++;
                }
                while (j < m) {
                    res[k] = arr_2[j];
                    j++;
                    k++;
                }
                Arrays.sort(res);
                return res;
            }
        }

        return new Local().merge(input, input2, arr, input.length, input2.length);
    }

    /**
     * In order to execute a matrix multiplication, in this method, please validate the input data throwing exceptions for invalid input. If the the
     * input params are satisfactory, do not throw any exception.
     * <p>
     * Please review the matrix multiplication https://www.mathsisfun.com/algebra/matrix-multiplying.html
     *
     * @param leftMatrix  the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException     when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public void validateForMatrixMultiplication(int[][] leftMatrix, int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        try {
            matrixMultiplication(leftMatrix, rightMatrix);
        } catch (ArrayIndexOutOfBoundsException illegalArgumentException) {
            throw new IllegalArgumentException("arr dim");
        }
    }

    /**
     * Perform the matrix multiplication as described in previous example Please review the matrix multiplication
     * https://www.mathsisfun.com/algebra/matrix-multiplying.html
     *
     * @param leftMatrix  the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException     when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public int[][] matrixMultiplication(final int[][] leftMatrix, final int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        int[][] mult = new int[leftMatrix.length][rightMatrix[0].length];
        if (leftMatrix == null && rightMatrix == null) {
            throw new IllegalArgumentException("gggg");
        }
        if (leftMatrix.length != rightMatrix[0].length) {
            throw new IllegalArgumentException("not equal statement");
        }
        try {
            for (int i = 0; i < leftMatrix.length; i++) {
                for (int j = 0; j < rightMatrix[0].length; j++) {
                    for (int k = 0; k < leftMatrix[0].length; k++) {
                        mult[i][j] += leftMatrix[i][k] * rightMatrix[k][j];
                    }
                }
            }
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
        }
        return mult;
    }

    /**
     * Return only distinct values in an array.
     *
     * @param input non-null immutable array of ints.
     */
    @Override
    public int[] distinct(final int[] input) {
        int[] disArr = new int[input.length];
        for (int i = 0; i < disArr.length; i++) {
            disArr[i] = input[i];
        }
        int[] dis_final = Arrays.stream(disArr).distinct().toArray();
        return dis_final;
    }
}