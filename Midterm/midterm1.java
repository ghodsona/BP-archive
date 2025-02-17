import java.util.Scanner;

public class midterm1 {
    public static long jomle(int i) {
        int j = i % 1000007; 
        return (long)Math.pow(j, 4);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt(); 
        long answer = 0; 
        for (int k = 1; k<n+1; k++) {
            answer += jomle(k); 
            answer = answer % 1000007;
        }

        System.out.println(answer%1000007);
        scanner.close();
        
    }
}
