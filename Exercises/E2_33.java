import java.util.Scanner;

public class E2_33 {
    
    public static void main(String[] args) {
        int n; 

        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); 

        char[][] x = new char[n+2][n+2]; 

        // make all ' '
        for (int i=0; i<n+1; i++) {
            for (int j=0; j<n+1; j++) {
                x[i][j] = ' '; 
            }
        }

        
        if (n%2==0) {
            // make '!' s 
            for (int i=1; i<n; i++) {
                for (int j=0; j<n-1; j++) {
                    if ((i%2 != j%2) && (j < n+1-i)) {
                        x[i][j] = '!'; 
                    }
                }
            }
            
            // make '?' s
            for (int i=1; i<n; i++) {
                for (int j=1; j<n; j++) {
                    if ((i%2 != j%2) && (j > n+1-i)) {
                        x[i][j] = '?'; 
                    }
                }
            }
        }
        else {
            // make '!' s 
            for (int i=1; i<n; i++) {
                for (int j=0; j<n-1; j++) {
                    if ((i%2 == j%2) && (j < n+1-i)) {
                        x[i][j] = '!'; 
                    }
                }
            }
            
            // make '?' s
            for (int i=1; i<n; i++) {
                for (int j=1; j<n; j++) {
                    if ((i%2 == j%2) && (j > n+1-i)) {
                        x[i][j] = '?'; 
                    }
                }
            }
        }
        
        // eslah laye 2
        for (int i=0; i<n+1; i++) {
            x[1][i] = ' '; 
            x[n+1][i] = ' ';
            x[i][1] = ' '; 
            x[i][n+1] = ' '; 
        }
        
        // make '@'
        for (int i=1; i<n+1; i++) {
            x[i][i] = '@'; 
            x[i][n+1-i] = '@'; 
        }
        if (n%2 == 0) {
            x[n/2][n/2] = ' '; 
            x[n/2+1][n/2] = ' '; 
            x[n/2+1][n/2+1] = ' '; 
            x[n/2][n/2+1] = ' '; 
        }
        else {
            x[(n+1)/2][(n+1)/2] = ' '; 
        }
        
        // make '*'
        for (int i=0; i<n+2; i++) {
            x[0][i] = '*'; 
            x[n+1][i] = '*';
            x[i][0] = '*'; 
            x[i][n+1] = '*'; 
        }

        // print them
        for (int i=0; i<n+2; i++) {
            for (int j=0; j<n+2; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
        scanner.close();
    }  
}