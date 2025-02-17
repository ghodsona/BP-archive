import java.util.Scanner;

public class E1_666 {
    
    private static String removeLeadingZeros(String str) {
        return str.replaceFirst("^0+(?!$)", "");
    }
    
    private static String multiply(long a, long b) {
        long N = 1000000;
    
        long a1 = a % N;
        long a2 = (a / N) % N;
        long a3 = (a / (N * N));
    
        long b1 = b % N;
        long b2 = (b / N) % N;
        long b3 = (b / (N * N));
        
        long s0 = a1 * b1;
        long s1 = a1 * b2 + a2 * b1;
        long s2 = a1 * b3 + a2 * b2 + a3 * b1;
        long s3 = a2 * b3 + a3 * b2;
        long s4 = a3 * b3;
    
        long r0 = s0 % N;
        long r1 = (s0 / N + s1) % N;
        long r2 = (s0 / N + s1) / N + s2 % N;
        long r3 = (s0 / N + s1) / N + s2 / N + s3 % N;
        long r4 = (s0 / N + s1) / N + s2 / N + s3 / N + s4;
    
        String part0 = String.format("%06d", r0);
        String part1 = String.format("%06d", r1);
        String part2 = String.format("%06d", r2);
        String part3 = String.format("%06d", r3);
        String part4 = Long.toString(r4);
    
        return removeLeadingZeros(part4 + part3 + part2 + part1 + part0);
    }

    public static String sumOf(long a, long b) {
        long N = 10000000000000000L;
        long s1 = (a % N) + (b % N);
        long r = s1 / N;

        a /= N;
        b /= N;
        long s2 = a + b + r;

        return String.format("%d%017d", s2, s1 % N);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        long a = scan.nextLong();
        long b = scan.nextLong();
        char c = scan.next().charAt(0);


        if (c == '+') {
            System.out.println(sumOf(a, b));
        }
        else if (c == '-') {
            System.out.println(sumOf(a, -b));
        }
        else if (c == '*') {
            System.out.println(multiply(a, b));
        }
        else if (c == '/') {
            System.out.println(a / b);
        }
        scan.close();
    }
}
