// hoo.

import java.util.Scanner; 

public class E1_66 {

    public static void main(String[] args) {
         
        Scanner scan = new Scanner(System.in); 
        long a = scan.nextLong(); 
        long b = scan.nextLong();    
        char c = scan.next().charAt(0); 
        
        if (c == '+'){

            if (a == -1*b) System.out.println(0);
            else if (a<0 && b>0)  {
                System.out.println(b - Math.abs(a));
            }
            else if (a>0 && b<0)  {
                System.out.println(a - Math.abs(b));
            }
            else if (a==0) {
                System.out.println(b);
            }
            else if (b==0) {
                System.out.println(a);
            }

            else {
                int sign = 1;
                if (a>=0 && b>=0) { 
                    sign = 1; 
                }
                else if (a<0 && b<0) { 
                    sign = -1; 
                }  
                a = Math.abs(a); 
                b = Math.abs(b);
                long N = 1000000000000000000L;
                long s1 = (a % N) + (b % N);
                long r1 = s1 / N;
                
                a = a / N;
                b = b / N;
                
                long s2 = a + b + r1;
                
                String output; 
                if (s2 != 0 ) {
                    output = String.format("%d", s2) + String.format("%018d", s1 % N);
                }
                else {
                    output = String.format("%d", s1 % N);           
                }

                if (sign == 1) { // 15, 16
                    System.out.println(output);
                }
                else if (sign == -1) { //5
                    System.out.println("-" + output);
                }   
                
            }
        }

        else if (c == '-') {
            if (a==b) System.out.println(0);
            else if (a<0 && b<0) {
                System.out.println(Math.abs(b) - Math.abs(a));
            }
            else if (a>0 && b>0) {
                System.out.println(a - b);
            }
            else if (a==0) {
                System.out.println(-1*b);
            }
            else if (b==0) {
                System.out.println(a);
            }
            else if (a<=0 && b>=0) {
                a = Math.abs(a);
                long N = 1000000000000000000L;
                long s1 = (a % N) + (b % N);
                long r1 = s1 / N;
                
                a = a / N;
                b = b / N;
                
                long s2 = a + b + r1;
                
                String output; 
                if (s2 != 0 ) {
                    output = String.format("%d", s2) + String.format("%018d", s1 % N);
                }
                else {
                    output = String.format("%d", s1 % N);           
                }
                System.out.println("-" + output);
            }

            else if (a>0 && b<0) {
                b = Math.abs(b);
                long N = 1000000000000000000L;
                long s1 = (a % N) + (b % N);
                long r1 = s1 / N;
                
                a = a / N;
                b = b / N;
                
                long s2 = a + b + r1;
                
                String output; 
                if (s2 != 0 ) {
                    output = String.format("%d", s2) + String.format("%018d", s1 % N);
                }
                else {
                    output = String.format("%d", s1 % N);           
                }
                System.out.println(output);                
            }
        }
        
        else if (c == '/') {
            System.out.println((a+b-1)/b);
        }

        else if (c == '*') {
            
            long sign = 1; 
            if (a>0 && b>0) {
            }
            else if (a<0 && b<0) {
                a = Math.abs(a);
                b = Math.abs(b); 
            }
            else if (a==0 || b==0) {
                sign = 0; 
            }
            else {
                a = Math.abs(a);
                b = Math.abs(b);
                sign = -1; 
            }
                    
            long N = 1000000;

            long a1 = a % N;
            long a2 = (long)(a / N) % N;
            long a3 = (long)(a / (N * N));

            long b1 = b % N;
            long b2 = (long)(b / N) % N;
            long b3 = (long)(b / (N * N));

            long s0 = a1 * b1;
            long s6 = a1 * b2 + a2 * b1;
            long s12 = a1 * b3 + a2 * b2 + a3 * b1;
            long s18 = a2 * b3 + a3 * b2;
            long s24 = a3 * b3;

            long final_0 = s0 % N;
            long final_6 = (s0 / N + s6) % N;
            long final_12 = ((s0 / N + s6) / N + s12) % N;
            long final_18 = (((s0 / N + s6) / N + s12) / N + s18) % N;
            long final_24 = (((s0 / N + s6) / N + s12) / N + s18) / N + s24;

            String part0 = String.format("%06d", final_0);
            String part6 = String.format("%06d", final_6);
            String part12 = String.format("%06d", final_12);
            String part18 = String.format("%06d", final_18);
            String part24 = String.format("%d", final_24);

            String result = part24 + part18 + part12 + part6 + part0;
            result = result.replaceFirst("^0*", ""); 

            if (sign == 0) {
                System.out.println(0);
            }
            else if (sign == 1) {
                System.out.println(result);
            }
            else {
                System.out.println("-" + result);
            }
        }

        scan.close();
    }
}

// besoozad zendan, sookht jegaram :)))