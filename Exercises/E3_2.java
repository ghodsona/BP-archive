import java.util.Scanner;

public class E3_2 {

    public static int c(int n, int k) {
        if (n==k) return 1; 
        if (k==0) return 1; 
        return c(n-1, k-1) + c(n-1, k); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt();
        int salem = scanner.nextInt();
        int partab = scanner.nextInt();
        int khordan = scanner.nextInt();

        if (khordan>salem) System.out.println(0);
        else if (partab>n-salem) System.out.println(0);
        else System.out.println(c(salem, khordan)*c(n-salem, partab));
        scanner.close();
    }
}
