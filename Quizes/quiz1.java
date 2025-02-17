import java.util.Scanner; 

public class quiz1 {
    static int m; 
    static int n; 
    static long[][] matrix; 
    public static boolean isGholle(int i, int j) {
        return ((matrix[i-1][j-1] < matrix[i][j]) &&
                (matrix[i-1][j] < matrix[i][j]) &&
                (matrix[i-1][j+1] < matrix[i][j]) &&
                (matrix[i][j-1] < matrix[i][j]) &&
                (matrix[i][j+1] < matrix[i][j]) &&
                (matrix[i+1][j-1] < matrix[i][j]) &&
                (matrix[i+1][j] < matrix[i][j]) &&
                (matrix[i+1][j+1] < matrix[i][j])
                );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt(); 
        n = scanner.nextInt();
        matrix = new long[m][n];
        boolean googooli = false; 

        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                matrix[i][j] = scanner.nextLong();
            }
        }

        for (int i = 1; i<m-1; i++) {
            for (int j = 1; j<n-1; j++) {
                if (isGholle(i, j)) {
                    System.out.print((i+1) + " " + (j+1));
                    System.out.println();
                    googooli = true;
                }
            }
        }

        if (!googooli) System.out.println("No Gholle Found");

        scanner.close();
    }
}