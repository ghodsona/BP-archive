import java.util.Scanner;

public class T2_2 {
    static int N = 2000001;
    static boolean[] isPrime = new boolean[N];
    static int[] primeCount = new int[N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        for (int i = 2; i < N; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i < N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N-1; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        if (isPrime[n]) System.out.println(n + " 1"); 
        else {
            for (int i=0; i<n+1; i++) {
                primeCount[i] = 0;
            }
    
            int orginaln = n; 
            for (int i=2; i*i<=n; i++) {
                if (isPrime[i]) {
                    primeCount[i] =0;
                    while(n%i == 0) {
                        n = (int)n/i;
                        primeCount[i]++;
                    }
                    if (n == 1) break;
                }
            }

            if (n > 1) {
                primeCount[n]++;
            }
    
            for (int i=1; i<=orginaln; i++) {
                if (primeCount[i] != 0) {
                    System.out.println(i + " " + primeCount[i]);
                }
            }
        }

        scanner.close();
    }
}
