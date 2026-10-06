package Ch3;

import java.util.Random;

public class BinarySearchMinMax {

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int size = 1000;
        int[] arr = new int[size];

        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }

        java.util.Arrays.sort(arr);

        int min = arr[0];
        int max = arr[size - 1];

        long start = System.nanoTime();
        int minIndex = binarySearch(arr, min);
        long end = System.nanoTime();
        System.out.println("Binary search for MIN (" + min + ") found at index " + minIndex
                + " in " + (end - start) + " ns");

        start = System.nanoTime();
        int maxIndex = binarySearch(arr, max);
        end = System.nanoTime();
        System.out.println("Binary search for MAX (" + max + ") found at index " + maxIndex
                + " in " + (end - start) + " ns");
    }
}
