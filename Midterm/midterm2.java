import java.util.Scanner;

public class midterm2 {
    public static long jomle(int i) {
        int j = i % 1000007; 
        return (long)Math.pow(j, 4);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt(); 
        
        /// 
        for (int i = 0; i<n; i++) {
            System.out.print("*");
        }
        System.out.println();

        for (int i = 1; i<n-1; i++) {
            for (int j = 0; j<i; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int j = 0; j<n-2; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }

        ///
        if (n!=1) {
                for (int i = 0; i<n-1; i++) {
                    System.out.print(" ");
                }
                for (int i = 0; i<n; i++) {
                    System.out.print("*");
                }
                System.out.println();
            }

        scanner.close();
        
    }
}