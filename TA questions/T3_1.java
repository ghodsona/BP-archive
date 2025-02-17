import java.util.Scanner; 

public class T3_1 {
    public static int gcm(int a, int b) {
        if (b == 0) return a;
        return gcm(b, a%b); 
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt();
        int[] k = new int[n];
        for (int i=0; i<n; i++) {
            k[i] = scanner.nextInt(); 
        }

        int final_gcm = gcm(k[0], k[1]); 
        for (int i=2; i<n; i++) {
            final_gcm = gcm(final_gcm, k[i]);
        }

        // System.out.println(final_gcm);
        if (final_gcm == 1) System.out.println("GOOD");
        else System.out.println("BAD");

        scanner.close();
    }
}
