import java.util.Scanner; 

public class quiz2 {
    public static long LaLa(int i) {
        if (i==1) return 2;
        if (i==2) return 3; 
        return LaLa(i-1) + LaLa(i-2); 
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(LaLa(n));
        scanner.close(); 
    }
}
