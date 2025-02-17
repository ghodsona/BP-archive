import java.util.Scanner;

public class T2_4 {

    public static Boolean isEqual(int[] array, int n) {
        for (int i=0; i<n-1; i++) {
            if (array[i] != array[i+1]) {
                return false; 
            }
        }
        return true; 
    }

    public static int indexMax(int[] array, int n) {
        int index = 0; 
        for (int i=0; i<n; i++) {
            if (array[i] > array[index]) {
                index = i; 
            }
        }
        return index; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] array = new int[n]; 
        int count = 0; 

        for (int i=0; i<n; i++) {
            array[i] = scanner.nextInt();
        }
        
        int maxIterations = 1000; 
        int iterations = 0;
        
        while (!isEqual(array, n) && iterations < maxIterations) {
            int index = indexMax(array, n);
            for (int i=0; i<n; i++) {
                if (i != index) {
                    array[i]++; 
                }
            }
            count++; 
            iterations++;
        }
        
        System.out.println(count);
        
        scanner.close();
    }
}
