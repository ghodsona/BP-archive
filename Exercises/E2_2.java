import java.util.Scanner; 

public class E2_2  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt(); 
        int[] a = new int[n]; 
        int[] b = new int[n]; 

        boolean isThat = true; 
        for (int i = 0; i<n; i++) {
            a[i] = scanner.nextInt(); 
        }
        for (int i = 0; i<n; i++) {
            b[i] = scanner.nextInt(); 
            isThat = isThat && (a[i] == b[i]);
        }

        if (isThat) System.out.println("YES"); 
        else if (n == 1) {
            System.out.println("NO");
        }
        else {
            
            int aXOR = 0;
            int bXOR = 0; 

            for (int i = 0; i<n; i++) {
                aXOR = aXOR ^ a[i];
            }
            for (int i = 0; i<n; i++) {
                bXOR = bXOR ^ b[i];
            }

            if (aXOR == bXOR) System.out.println("YES");
            else System.out.println("NO");
        }

        scanner.close();
    }
}