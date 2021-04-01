package com.endava.internship.warmup.domain.service;

import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;

public class ArrayProcessorWithForLoops implements ArrayProcessor {

    /**
     * Return true if there are no numbers that divide by 10
     * @param input non-null immutable array of ints
     */
    @Override
    public boolean noneMatch(final int[] input) {
        for (int number : input) {
            if (number % 10 == 0)
                return false;
        }
        return true;
    }

    /**
     * Return true if at least one value in input matches the predicate
     * @param input non-null immutable array of ints
     * @param predicate invoke the predicate.test(int value) on each input element
     */
    @Override
    public boolean someMatch(final int[] input, IntPredicate predicate) {
        for (int number : input) {
            if (predicate.test(number)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if all values processed by function, matches the predicate
     * @param input non-null immutable array of Strings. No element is null
     * @param function invoke function.applyAsInt(String value) to transform all the input elements into an int value
     * @param predicate invoke predicate.test(int value) to test the int value obtained from the function
     */
    @Override
    public boolean allMatch(final String[] input,
                            ToIntFunction<String> function,
                            IntPredicate predicate) {
        for (String string : input) {
            if (predicate.negate().test(function.applyAsInt(string))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copy values into a separate array from specific index to stopindex
     * @param input non-null array of ints
     * @param startInclusive the first index of the element from input to be included in the new array
     * @param endExclusive the last index prior to which the elements are to be included in the new array
     * @throws IllegalArgumentException when parameters are outside of input index bounds
     */
    @Override
    public int[] copyValues(int[] input, int startInclusive, int endExclusive) throws IllegalArgumentException {
        if (startInclusive < 0
            || endExclusive < 0
            || startInclusive > input.length - 1
            || endExclusive > input.length - 1
            || startInclusive > endExclusive
        ) {
            throw new IllegalArgumentException (String.format("Invalid boundaries: %d, %d", startInclusive, endExclusive));
        }
        int[] tempArray = new int[0];
        for (int i = startInclusive; i < endExclusive; i++) {
            tempArray= addElementsToArray(tempArray, input[i]);
        }
        return tempArray;
    }

    /**
     * Replace even index values with their doubles and odd indexed elements with their negative
     * @param input non-null immutable array of ints
     * @return new array with changed elements
     */
    @Override
    public int[] replace(final int[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = i % 2 == 0 ? input[i] * 2 : input[i] * -1;
        }
        return input;
    }

    /**
     * Find the second max value in the array
     * @param input non-null immutable array of ints
     */
    @Override
    public int findSecondMax(final int[] input) {
        int previousMax = input[0];
        int max = previousMax;
        for (int number : input) {
            if (number > max) {
                previousMax = max;
                max = number;
            } else if (number < max && number > previousMax)  {
                previousMax = number;
            }
        }
        return previousMax;
    }

    /**
     * Return in reverse first negative numbers, then positive numbers from array
     * @param input non-null immutable array of ints.
     * @return example: input {3, -5, 4, -7, 2 , 9}
     *                  result: {-7, -5, 9, 2, 4, 3}
     */
    @Override
    public int[] rearrange(final int[] input) {
        int[] tempArray = new int[0];
        for (int i = input.length - 1; i >= 0; i--) {
            if(input[i] < 0) {
                tempArray = addElementsToArray(tempArray, input[i]);
            }
        }
        for (int i = input.length - 1; i >= 0; i--) {
            if(input[i] > 0) {
                tempArray = addElementsToArray(tempArray, input[i]);
            }
        }
        return tempArray;
    }

    /**
     * Remove (filter) all values which are smaller than (input max element - 10)
     * @param input non-null immutable array of ints
     * @return The result array should not contain empty cells!
     */
    @Override
    public int[] filter(final int[] input) {
        int[] array = new int[0];
        int maxElement = input[0];
        for (int number : input) {
            if (number > maxElement) {
                maxElement = number;
            }
        }
        for (int number : input) {
            if ((number > maxElement - 10)) {
                array = addElementsToArray(array, number);
            }
        }
        return array;
    }

    private int[] addElementsToArray(int[] original, int elementToAdd) {
        int[] tempArray = new int[original.length + 1];
        if (original.length == 0) {
            tempArray[0] = elementToAdd;
        } else {
            for (int i = 0; i < original.length; i++) {
                tempArray[i] = original[i];
            }
        }
        tempArray[tempArray.length - 1] = elementToAdd;
        return tempArray;
    }

    /**
     * Insert values into input array at a specific index.
     * @param input non-null immutable array of ints.
     * @param startInclusive the index of input at which the first element from values array should be inserted
     * @param values the values to be inserted from startInclusive index
     * @return new array containing the combined elements of input and values
     * @throws IllegalArgumentException when startInclusive is out of bounds for input
     */
    @Override
    public int[] insertValues(final int[] input, int startInclusive, int[] values) throws IllegalArgumentException {
        if (startInclusive < 0 || startInclusive > input.length) {
            throw new IllegalArgumentException("startInclusive is out of bounds");
        }
        int[] tempArray = new int[0];
        for (int i = 0; i < input.length; i++) {
            if (i == startInclusive) {
                for (int value : values) {
                    tempArray = addElementsToArray(tempArray, value);
                }
            }
            tempArray = addElementsToArray(tempArray,  input[i]);
        }
        return tempArray;
    }

    /**
     * Merge two sorted input and input2 arrays so that the return values are also sorted
     * @param input first non-null array
     * @param input2 second non-null array
     * @return new array containing all elements sorted from input and input2
     * @throws IllegalArgumentException if either input or input are not sorted ascending
     */
    @Override
    public int[] mergeSortedArrays(int[] input, int[] input2) throws IllegalArgumentException {
        if (!QuickSort.withArray(input, true).isSorted() || !QuickSort.withArray(input2, true).isSorted()) {
            throw new IllegalArgumentException("Arrays are not sorted ascending");
        }
        int[] finalArrayToSort = mergeArrays(input, input2);
        return QuickSort.withArray(finalArrayToSort, true).sort();
    }

    private int[] mergeArrays(int[] array, int[] array2){
        int[] tempArray = new int[0];
        for (int i = 0; i < array.length; i++) {
            tempArray = addElementsToArray(tempArray, array[i]);
        }
        for (int i = 0; i < array2.length; i++) {
            tempArray = addElementsToArray(tempArray, array2[i]);
        }
        return tempArray;
    }

    /**
     * In order to execute a matrix multiplication, in this method, please validate the input data throwing exceptions for invalid input. If the the
     * input params are satisfactory, do not throw any exception.
     *
     * Please review the matrix multiplication https://www.mathsisfun.com/algebra/matrix-multiplying.html
     * @param leftMatrix the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public void validateForMatrixMultiplication(int[][] leftMatrix, int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        if (leftMatrix.length == rightMatrix.length && rightMatrix.length == 0) {
            throw new IllegalArgumentException("Can't multiply empty matrix");
        }
        checkMatrixInitialization(leftMatrix);
        checkMatrixInitialization(rightMatrix);
        if (leftMatrix.length != rightMatrix[0].length){
            throw new IllegalArgumentException("A matrix can be multiplied by any other matrix that has the same number of rows"
                                                   + " as the first has columns");
        }
    }

    private void checkMatrixInitialization(int[][] matrix) throws IllegalArgumentException {
        int previousLength = matrix[0].length;
        for (int[] ints : matrix) {
            if (ints.length != previousLength){
                throw new IllegalArgumentException("Expecting all the rows in a matrix to have the same dimension");
            }
        }
    }

    /**
     * Perform the matrix multiplication as described in previous example Please review the matrix multiplication
     * https://www.mathsisfun.com/algebra/matrix-multiplying.html
     * @param leftMatrix the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public int[][] matrixMultiplication(final int[][] leftMatrix, final int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        // cant see if input is null in case of primitive type
        validateForMatrixMultiplication(leftMatrix, rightMatrix);
        int length = leftMatrix.length;
        int width = leftMatrix[0].length;
        int[][] result = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < width; k++) {
                    result[i][j] += leftMatrix[i][k] * rightMatrix[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Return only distinct values in an array.
     * @param input non-null immutable array of ints.
     */
    @Override
    public int[] distinct(final int[] input) {
        int[] tempArray = new int[] {input[0]};
        for (int i = 1; i < input.length; i++) {
            if (!arrayContains(tempArray, input[i])){
                tempArray = addElementsToArray(tempArray, input[i]);
            }
        }
        return tempArray;
    }

    private boolean arrayContains(int[] array, int valueToSearch){
        for (int number : array) {
            if (number == valueToSearch) {
                return true;
            }
        }
        return false;
    }
}
