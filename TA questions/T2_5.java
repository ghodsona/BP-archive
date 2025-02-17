// import java.util.Scanner;

// public class T2_5 {

//     static int determinane = 0; 

//     public static int det(int[][] Matrix, int n, int deletedRow, int deletedClmn) {
//         if (n == 2) {
//             return (Matrix[0][0]*Matrix[1][1] - Matrix[0][1]*Matrix[1][0]);
//         }
//         else {
//             int[][] newMatrix = new int[n-1][n-1]; 

//             for (int i=0; i<n-1; i++) {
//                 for (int j=0; j<n-1; j++) {
//                     if (j<deletedClmn) newMatrix[i][j] = Matrix[i+1][j];
//                     else if (j>deletedClmn) newMatrix[i][j] = Matrix[i+1][j+1]; 
//                 }
//             }

//             for (int i=0; i<n; i++) {
//                 determinane += (int)pow(-1, 1+i) * Matrix[0][i] * det(newMatrix, n-1, 0, i);
//             }

//             return determinane;
//         }
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in); 
//         int n = scanner.nextInt(); 
//         int[][] a = new int[n+1][n+1];

//         for (int i=0; i<n; i++) {
//             for (int j=0; j<n; j++) {
//                 a[i][j] = scanner.nextInt();
//             }
//         }

//         System.out.println(det(a, n, n, n));

//         scanner.close();
//     }
    
// }

import java.util.Scanner;

public class T2_5 {

    public static int det(int[][] Matrix, int n) {
        if (n == 2) {
            return (Matrix[0][0] * Matrix[1][1] - Matrix[0][1] * Matrix[1][0]);

        }
        else {
            int determinant = 0;
            for (int i = 0; i < n; i++) {
                int[][] newMatrix = new int[n - 1][n - 1];

                for (int j = 1; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (k < i) {
                            newMatrix[j - 1][k] = Matrix[j][k];
                        }
                        else if (k > i) {
                            newMatrix[j - 1][k - 1] = Matrix[j][k];
                        }
                    }
                }

                determinant += (int)Math.pow(-1, i) * Matrix[0][i] * det(newMatrix, n - 1);
            }
            return determinant;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextInt();
            }
        }

        System.out.println(det(a, n));

        scanner.close();
    }
}
