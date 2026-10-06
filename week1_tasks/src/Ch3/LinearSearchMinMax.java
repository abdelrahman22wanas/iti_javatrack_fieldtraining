package Ch3;

import java.util.Random;

public class LinearSearchMinMax {

    public static int[] findMinMax(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return new int[]{min, max};
    }

    public static void main(String[] args) {
        int size = 1000;
        int[] arr = new int[size];

        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }

        long start = System.nanoTime();
        int[] minMax = findMinMax(arr);
        long end = System.nanoTime();

        System.out.println("Min = " + minMax[0] + ", Max = " + minMax[1]);
        System.out.println("Linear scan running time: " + (end - start) + " ns");
    }
}
