import java.util.Scanner;

public class E2_1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = 0; 
        int n = scan.nextInt(); 
        long[] a = new long[1000000]; 

        for (int i = 0; i<n; i++) {
            a[i] = scan.nextLong(); 
        }

        for (int j = 0; j<n; j++) {
            long sqrt = (long)Math.sqrt(a[j]);
            if ((sqrt*sqrt == a[j]) || ((sqrt-1)*(sqrt-1) == a[j]) || ((sqrt+1)*(sqrt+1) == a[j])) {
                count++;
            }
        }

        System.out.println(count);
        scan.close();
    }
}