import java.util.Scanner;

public class E3_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt();
        int[] cgr = new int[n]; 

        for (int i=0; i<n; i++) {
            int numberBefore = scanner.nextInt();
            cgr[numberBefore] = scanner.nextInt();
        }

        for (int i=0; i<n; i++) {
            System.out.println(cgr[i]);
        }

        scanner.close();
    }
    
}
