package org.laserova;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        test_sort();
    }

    public static Integer[] generateRandomArray(int n) {
        java.util.Random random = new java.util.Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000000) + 100000;
        }
        return arr;
    }

    public static void test_sort() {
        int k = 100000;
        Integer[] testArray1 = generateRandomArray(k);
        Integer[] testArray2 = Arrays.copyOf(testArray1, k);
        Integer[] testArray3 = Arrays.copyOf(testArray1, k);
        Integer[] testArray4 = Arrays.copyOf(testArray1, k);
        Integer[] testArray5 = Arrays.copyOf(testArray1, k);


        System.out.println("пузырьковая сортировка");
        long start1 = System.currentTimeMillis();
        bubble_sort(testArray1);
        System.out.println(System.currentTimeMillis() - start1);

        System.out.println("сортировка выбором");
        long start2 = System.currentTimeMillis();
        selection_sort(testArray2);
        System.out.println(System.currentTimeMillis() - start2);

        System.out.println("сортировка вставкой");
        long start3 = System.currentTimeMillis();
        insertion_sort(testArray3);
        System.out.println(System.currentTimeMillis() - start3);

        System.out.println("быстрая сортировка");
        long start4 = System.currentTimeMillis();
        quickSort(testArray4, 0, testArray4.length - 1);
        System.out.println(System.currentTimeMillis() - start4);

        System.out.println("сортировка слиянием");
        long start5 = System.currentTimeMillis();
        mergeSort(testArray5);
        System.out.println(System.currentTimeMillis() - start5);

        //по итогу нескольких запусков устойчиво и заметно быстрее работает сортировка выбором;
        //из сортировок с рекурсией чуть быстрее быстрая, и обе на два порядка быстрее выбора
    }

    public static void bubble_sort(Integer[] array) {
        for (int out = array.length - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    int transit = array[in];
                    array[in] = array[in + 1];
                    array[in + 1] = transit;
                }
            }
        }
    }

    public static void selection_sort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minItemIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minItemIndex]) {
                    minItemIndex = j;
                }
            }
            int transit = array[i];
            array[i] = array[minItemIndex];
            array[minItemIndex] = transit;
        }
    }

    public static void insertion_sort(Integer[] array) {
        for (int left = 0; left < array.length; left++) {
            int value = array[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    break;
                }
            }
            array[i + 1] = value;
        }
    }
    // Сортировки с рекурсией
    // quick sorting

    public static void quickSort(Integer[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);

            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;

                swapElements(array, i, j);
            }
        }

        swapElements(array, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }


    // merge sorting
    public static void mergeSort(Integer[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[array.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = array[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = array[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);
    }

    public static void merge(Integer[] array, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                array[mainP++] = left[leftP++];
            } else {
                array[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            array[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            array[mainP++] = right[rightP++];
        }
    }
}