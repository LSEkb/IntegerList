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
            arr[i] = random.nextInt(100000) + 1;
        }
        return arr;
    }

    public static void test_sort() {
        int k = 100000;
        Integer[] testArray1 = generateRandomArray(k);
        Integer[] testArray2 = Arrays.copyOf(testArray1, k);
        Integer[] testArray3 = Arrays.copyOf(testArray1, k);

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

        //по итогу нескольких запусков устойчиво и заметно быстрее работает сортировка выбором
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
            // Вытаскиваем значение элемента
            int value = array[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            array[i + 1] = value;
        }

    }
}