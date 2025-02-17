import java.util.Scanner;

public class E1_6 {
    public static void main(String[] args) {

        long N = 1000000;

        Scanner scan = new Scanner(System.in);
 
        long a = scan.nextLong();
        long b = scan.nextLong();
        char c = scan.next().charAt(0);
        
        if (c == '+') {

            if (a>0 && b>0) {
                
                long s1 = (a % N) + (b % N);
                long r1 = (long)Math.floor(s1 / N);
                String paddeds1 = String.format("%05d", (s1 % N));

                a = (long)(a / N);
                b = (long)(b / N); 

                long s2 = (a % N) + (b % N) + r1;
                long r2 = (long)Math.floor(s2 / N);
                String paddeds2 = String.format("%05d", (s2 % N));
                a = (long)(a / N);
                b = (long)(b / N); 

                String paddeds3 = String.format("%d", (a % N) + (b % N) + r2) ; 
                a = (long)(a / N);
                b = (long)(b / N); 

                String output = paddeds3 + paddeds2 + paddeds1; 
                if (output.compareTo("999999999999999999") < 0) {
                    long finaloutput = Long.parseLong(output);
                    System.out.println(finaloutput);
                }
                else {
                    System.out.println(output);
                }

            }

            if (a<0 && b<0) {
                    a = Math.abs(a); 
                    b = Math.abs(b); 

                    long s1 = (a % N) + (b % N);
                    long r1 = (long)Math.floor(s1 / N);
                    String paddeds1 = String.format("%05d", (s1 % N));

                    a = (long)(a / N);
                    b = (long)(b / N); 

                    long s2 = (a % N) + (b % N) + r1;
                    long r2 = (long)Math.floor(s2 / N);
                    String paddeds2 = String.format("%05d", (s2 % N));
                    a = (long)(a / N);
                    b = (long)(b / N); 

                    String paddeds3 = String.format("%d", (a % N) + (b % N) + r2) ; 
                    a = (long)(a / N);
                    b = (long)(b / N); 

                    String output = paddeds3 + paddeds2 + paddeds1; 
                    if (output.compareTo("999999999999999999") < 0) {
                        long finaloutput = Long.parseLong(output);
                        System.out.println(-1*finaloutput);
                    }
                    else {
                        System.out.println("-" + output);
                    }
            }   

        else if (c == '-') {

            b = -1*b; 
            
            long s1 = (a % N) + (b % N);
            long r1 = (long)Math.floor(s1 / N);
            String paddeds1 = String.format("%05d", (s1 % N));

            a = (long)(a / N);
            b = (long)(b / N); 

            long s2 = (a % N) + (b % N) + r1;
            long r2 = (long)Math.floor(s2 / N);
            String paddeds2 = String.format("%05d", (s2 % N));
            a = (long)(a / N);
            b = (long)(b / N); 

            String paddeds3 = String.format("%d", (a % N) + (b % N) + r2) ; 
            a = (long)(a / N);
            b = (long)(b / N); 

            String output = paddeds3 + paddeds2 + paddeds1; 
            if (output.compareTo("999999999999999999") < 0) {
                long finaloutput = Long.parseLong(output);
                System.out.println(finaloutput);
            }
            else {
                System.out.println(output);
            }

            
            // long s1 = (a % N) - (b % N);
            // long r1 = (long)Math.floor(s1 / N);
            // String paddeds1 = String.format("%05d", (s1 % N));

            // a = (long)(a / N);
            // b = (long)(b / N); 

            // long s2 = (a % N) - (b % N) - r1;
            // long r2 = (long)Math.floor(s2 / N);
            // String paddeds2 = String.format("%05d", (s2 % N));
            // a = (long)(a / N);
            // b = (long)(b / N); 

            // String paddeds3 = String.format("%d", (a % N) - (b % N) - r2) ; 
            // a = (long)(a / N);
            // b = (long)(b / N); 

            // String output = paddeds3 + paddeds2 + paddeds1; 
            // if (output.compareTo("999999999999999999") < 0) {
            //     long finaloutput = Long.parseLong(output);
            //     System.out.println(finaloutput);
            // }
            // else {
            //     System.out.println(output);
            // }
        }
        
        else if (c == '/') {
            if (a>0 && b>0) {
                long output = a / b;
                if (a % b == 0) System.out.println(output);
                else System.out.println(output + 1);
            }
            else {
                long output = Math.abs(a) / Math.abs(b);
                if (a % b == 0) System.out.println(output);
                else System.out.println(output + 1);
                System.out.println(-1*output - 1);
            }
        }

        else if (c == '*') {

        }

        scan.close();
        
        }
    }
}
