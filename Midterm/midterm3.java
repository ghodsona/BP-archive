import java.util.Scanner;

public class midterm3 {
    static long[] sums; 

    public static long sumOf(int number) {
        long sum = number; 
        for (int i = 1; i<(number/2)+1; i++) {
            if (number%i == 0) {
                sum += i; 
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int m = scanner.nextInt(); 
        int n = scanner.nextInt(); 
        sums = new long[n-m];
        // [m,n)

        int indexMax = 0; 
        for (int k = 0; k<n-m; k++) {
            sums[k] = sumOf(k+m); 
            // System.out.println(k+m + " " + sums[k]);
            if (sums[k] > sums[indexMax]) {
                indexMax = k;
            }
        }

        for (int k = 0; k<n-m; k++) {
            if (sums[k] == sums[indexMax]) {
                System.out.println(k+m);
                break;
            }
        }
        scanner.close();
    }
}