import java.util.Scanner;

public class E2_3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        int n = scan.nextInt();

        if (n==0) {
            System.out.println("* * ");
            System.out.println("* * ");
        }

        else if (n==1) {
            System.out.println("* * * ");
            System.out.println("*   * ");
            System.out.println("* * * ");
        }

        else if (n==2) {
            System.out.println("* * * * ");
            System.out.println("*     * ");
            System.out.println("*     * ");
            System.out.println("* * * * ");
        }

        else if (n==3) {
            System.out.println("* * * * * ");
            System.out.println("* @   @ * ");
            System.out.println("*       * ");
            System.out.println("* @   @ * ");
            System.out.println("* * * * * ");
        }

        else if (n%2 != 0) {
            
            //line 0 
            for (int i = 0; i<n+2; i++) {
                System.out.print("* ");
            }
            System.out.println();
            
            //line avval
            System.out.print("* @ ");
            for (int i = 0; i<n-2; i++) System.out.print("  ");
            System.out.println("@ * ");

            for (int i = 1; i<(n-1)/2; i++) {

                if (i%2 == 1) System.out.print("*   ");
                else System.out.print("*     ");

                for (int j = 0; j<(int)((i-1)/2); j++) System.out.print("!   ");
                System.out.print("@   ");
                for (int j = 0; j<(int)((n-3)/2)-i; j++) System.out.print("!   ");
                System.out.print("@   ");
                for (int j = 0; j<(int)((i-1)/2); j++) System.out.print("?   ");

                if (i%2 == 1) System.out.print("*");
                else System.out.print("  *");

                System.out.println();

            }

            // line vasat
            if ((n+1) % 4 == 0) {
                System.out.print("*   ");
                for (int i = 0; i<(n-5)/4 + 1; i++) {
                    System.out.print("!   ");
                }
                System.out.print(" ");
                for (int i = 0; i<(n-5)/4 + 1; i++) {
                    System.out.print("   ?");
                }
                System.out.println("   *");
            }
            else {
                System.out.print("*     ");
                for (int i = 0; i<(n-5)/4; i++) {
                    System.out.print("!   ");
                }
                System.out.print(" ");
                for (int i = 0; i<(n-5)/4; i++) {
                    System.out.print("   ?");
                }
                System.out.println("     *");
            }
            
            for (int i = (n-1)/2 - 1; i>0; i--) {
                if (i%2 == 1) System.out.print("*   ");
                else System.out.print("*     ");

                for (int j = 0; j<(int)((i-1)/2); j++) System.out.print("!   ");
                System.out.print("@   ");
                for (int j = 0; j<(int)((n-3)/2)-i; j++) System.out.print("?   ");
                System.out.print("@   ");
                for (int j = 0; j<(int)((i-1)/2); j++) System.out.print("?   ");

                if (i%2 == 1) System.out.print("*");
                else System.out.print("  *");

                System.out.println();
            }
            
            //line akhar
            System.out.print("* @ ");
            for (int l = 0; l<n-2; l++) System.out.print("  ");
            System.out.println("@ * ");
    
    
            // final line
            for (int l = 0; l<n+2; l++) {
                System.out.print("* ");
            }
        }

        else {
            //line 0 
            for (int i = 0; i<n+2; i++) {
                System.out.print("* ");
            }
            System.out.println();
            
            //line avval
            System.out.print("* @ ");
            for (int i = 0; i<n-2; i++) System.out.print("  ");
            System.out.println("@ * ");

            for (int i = 1; i<(n-2)/2; i++) {

                if (i%2 == 1) System.out.print("*  ");
                else System.out.print("*");

                for (int j = 0; j<(int)((i)/2); j++) System.out.print("   !");
                System.out.print(" @ ");
                for (int j = 0; j<(int)((n-2)/2)-i; j++) System.out.print("!   ");
                System.out.print("@   ");
                for (int j = 0; j<(int)((i-1)/2); j++) System.out.print("?   ");

                if (i%2 == 1) System.out.print("*");
                else System.out.print("  *");

                System.out.println();
            }
            
            
            if (n%4 == 0) {
                // line vasat 1
                System.out.print("*  ");
                for (int i = 0; i<(n)/5; i++) {
                    System.out.print("   !");
                }
                System.out.print("    ");
                for (int i = 0; i<(n)/5; i++) {
                    System.out.print("   ?");
                }
                System.out.println("   *");
    
                // line vasat 2
                System.out.print("*   ");
                for (int i = 0; i<(n)/5; i++) {
                    System.out.print("!   ");
                }
                System.out.print(" ");
                for (int i = 0; i<(n)/5; i++) {
                    System.out.print("   ?");
                }
                System.out.println("     *");
            }
            else {
                // line vasat 1
                System.out.print("*");
                for (int i = 0; i<(n)/5; i++) {
                    System.out.print("   !");
                }
                System.out.print("    ");
                for (int i = 0; i<(n)/5-1; i++) {
                    System.out.print("   ?");
                }
                System.out.println("     *");
    
                // line vasat 2
                System.out.print("*     ");
                for (int i = 0; i<(n)/5 - 1; i++) {
                    System.out.print("!   ");
                }
                System.out.print("   ");
                for (int i = 0; i<(n)/5; i++) {
                    System.out.print(" ?  ");
                }
                System.out.println(" *");
            }

            // taghriban akhar
            for (int i = (n-1)/2 - 1; i>0; i--) {
                if (i%2 == 1) System.out.print("*");
                else System.out.print("*  ");

                for (int j = 0; j<(int)((i-1)/2); j++) System.out.print("   !");
                System.out.print("   @");
                for (int j = 0; j<(int)((n-2)/2)-i; j++) System.out.print("   ?");
                System.out.print(" @ ");
                for (int j = 0; j<(int)((i)/2); j++) System.out.print("?   ");

                if (i%2 == 1) System.out.print("  *");
                else System.out.print("*");

                System.out.println();
                
            }
            
            //line akhar
            System.out.print("* @ ");
            for (int l = 0; l<n-2; l++) System.out.print("  ");
            System.out.println("@ * ");
    
    
            // final line
            for (int l = 0; l<n+2; l++) {
                System.out.print("* ");
            }
        }

    
        scan.close();
    }
}
