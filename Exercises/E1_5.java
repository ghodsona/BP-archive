import java.util.Scanner;

// idea 1 : 

public class E1_5 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong(); 

        boolean is_square1 = false; 
        boolean is_square2 = false; 

        long sqrt = (long) Math.sqrt(5 * n * n + 4);
        if (sqrt*sqrt == 5*n*n+4) is_square1 = true; 

        sqrt = (long) Math.sqrt(5 * n * n - 4);
        if (sqrt*sqrt == 5*n*n-4) is_square1 = true; 

        if (is_square1 || is_square2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        scan.close();
    }
}


/* 
public class E1_5 {

    private static boolean isFibonachi(int x, int a, int b, int c) {
        if (x > b) { 
            a = c;
            c = b; 
            b += a;
            return isFibonachi(x, a, b, c);
        }
        else if (x < b) return false;
        else return true;
        }
    

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        int x = scan.nextInt();

        if ((isFibonachi(x, 1, 1, 1) == true) || (x == 0) || (x == 1)) System.out.println("YES");
        else if (isFibonachi(x, 1, 1, 1) == false) System.out.println("NO");

        scan.close();
    }

}
*/



/*
// failed idea!
public class E1_5 {
    public static void main(String[], args) {
        Scanner scan = new Scanner(System.in); 
        double n = scan.nextDouble();

        phi = (1 + math.sqrt(5))/2;

        if (n == 2 || n == 1) System.out.println(YES);
        else if (n/phi == 2) 
    }
}
*/

/* 
public class E1_5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int n = scan.nextInt();
        
        double x = Math.floor(Math.sqrt(5*n*n - 4)); 
        double y = Math.floor(Math.sqrt(5*n*n + 4)); 
        
        if ((x*x == 5*n*n - 4) || (y*y == 5*n*n + 4)) {
            System.out.println("YES");
        }
        else System.out.println("NO");
        
        scan.close();
    }
}
*/