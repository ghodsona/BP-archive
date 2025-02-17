import java.util.*;

public class final5 {
    static int[] numbers;

    public static int value(int start, int end) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (numbers[i] < min) min = numbers[i];
            if (numbers[i] > max) max = numbers[i];
        }
        return max - min;
    }

    public static int maxPoint(int start, int end) {
        int n = end - start;

        if (n == 1) {
            return 0; 
        }

        int mid = start + n / 2;

        return Math.max(
            value(start, mid) + maxPoint(mid, end),
            value(mid, end) + maxPoint(start, mid)
        );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println(maxPoint(0, n));

        scanner.close();
    }
}

