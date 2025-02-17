import java.util.Arrays;
import java.util.Scanner;

public class E3_5 {

    // public static boolean isPaydar(int[][] wall, int row, int clm, int i, int j) {
    //     int[][] vazeiat = new int[row][clm]; // 0 khali, 1 paydar, 2 napaydar
    //     if (wall[0][j] == 0) vazeiat[0][j] = 0;
    //     else {
    //         if (i==0) {
    //             vazeiat[0][j] = 1;
    //             vazeiat[1][j] = wall[1][j]; 
    //         }
    //         else {
    //             if (wall[0][j] == 1)
    //         }
    //         return true; 
            
    //     }     
    // }

    public static void makeNeighbores(int[][] wall, int row, int clm, int i, int j, int[][] status, boolean[][] check) {
        status[i][j] = 1; 

        if ((i==0) && (j==0)) {
            if(!check[0][1]) {
                check[0][1] = true; 
                if (wall[0][1] == 1) makeNeighbores(wall, row, clm, 0, 1, status, check);
            }
            if(!check[1][0]) {
                check[1][0] = true; 
                if (wall[1][0] == 1) makeNeighbores(wall, row, clm, 1, 0, status, check);
            }
        }

        else if ((i==0) && (j>0) && (j<clm-1)) {
            if(!check[0][j-1]) {
                check[0][j-1] = true; 
                if (wall[0][j-1] == 1) makeNeighbores(wall, row, clm, 0, j-1, status, check);
            }
            if(!check[0][j+1]) {
                check[0][j+1] = true; 
                if (wall[0][j+1] == 1) makeNeighbores(wall, row, clm, 0, j+1, status, check);
            }
            if(!check[1][j]) {
                check[1][j] = true; 
                if (wall[1][j] == 1) makeNeighbores(wall, row, clm, 1, j, status, check);
            }
        }

        else if ((i==0) && (j==clm-1)) {
            if(!check[i][j-1]) {
                check[i][j-1] = true; 
                if (wall[i][j-1] == 1) makeNeighbores(wall, row, clm, i, j-1, status, check);
            }
            if(!check[i+1][j]) {
                check[i+1][j] = true; 
                if (wall[i+1][j] == 1) makeNeighbores(wall, row, clm, i+1, j, status, check);
            }
        }

        else if ((j==0) && (i>0) && (i<row-1)) {
            if(!check[i-1][j]) {
                check[i-1][j] = true; 
                if (wall[i-1][j] == 1) makeNeighbores(wall, row, clm, i-1, j, status, check);
            }
            if(!check[i+1][j]) {
                check[i+1][j] = true; 
                if (wall[i+1][j] == 1) makeNeighbores(wall, row, clm, i+1, j, status, check);
            }
            if(!check[i][j+1]) {
                check[i][j+1] = true; 
                if (wall[i][j+1] == 1) makeNeighbores(wall, row, clm, i, j+1, status, check);
            }
        }

        else if ((i>0) && (i<row-1) && (j==clm-1)) {
            if(!check[i-1][j]) {
                check[i-1][j] = true; 
                if (wall[i-1][j] == 1) makeNeighbores(wall, row, clm, i-1, j, status, check);
            }
            if(!check[i][j-1]) {
                check[i][j-1] = true; 
                if (wall[i][j-1] == 1) makeNeighbores(wall, row, clm, i, j-1, status, check);
            }
            if(!check[i+1][j]) {
                check[i+1][j] = true; 
                if (wall[i+1][j] == 1) makeNeighbores(wall, row, clm, i+1, j, status, check);
            }
        }

        else if ((i==row-1) && (j==0)) {
            if(!check[i-1][j]) {
                check[i-1][j] = true; 
                if (wall[i-1][j] == 1) makeNeighbores(wall, row, clm, i-1, j, status, check);
            }
            if(!check[i][j+1]) {
                check[i][j+1] = true; 
                if (wall[i][j+1] == 1) makeNeighbores(wall, row, clm, i, j+1, status, check);
            }
        }

        else if ((i==row-1) && (j>0) && (j<clm-1)) {
            if(!check[i-1][j]) {
                check[i-1][j] = true; 
                if (wall[i-1][j] == 1) makeNeighbores(wall, row, clm, i-1, j, status, check);
            }
            if(!check[i][j-1]) {
                check[i][j-1] = true; 
                if (wall[i][j-1] == 1) makeNeighbores(wall, row, clm, i, j-1, status, check);
            }
            if(!check[i][j+1]) {
                check[i][j+1] = true; 
                if (wall[i][j+1] == 1) makeNeighbores(wall, row, clm, i, j+1, status, check);
            }
        }

        else if ((i==row-1) && (j==clm-1)) {
            if(!check[i-1][j]) {
                check[i-1][j] = true; 
                if (wall[i-1][j] == 1) makeNeighbores(wall, row, clm, i-1, j, status, check);
            }
            if(!check[i][j-1]) {
                check[i][j-1] = true; 
                if (wall[i][j-1] == 1) makeNeighbores(wall, row, clm, i, j-1, status, check);
            }
        }

        else {
            if(!check[i-1][j]) {
                check[i-1][j] = true; 
                if (wall[i-1][j] == 1) makeNeighbores(wall, row, clm, i-1, j, status, check);
            }
            if(!check[i][j-1]) {
                check[i][j-1] = true; 
                if (wall[i][j-1] == 1) makeNeighbores(wall, row, clm, i, j-1, status, check);
            }
            if(!check[i][j+1]) {
                check[i][j+1] = true; 
                if (wall[i][j+1] == 1) makeNeighbores(wall, row, clm, i, j+1, status, check);
            }
            if(!check[i+1][j]) {
                check[i+1][j] = true; 
                if (wall[i+1][j] == 1) makeNeighbores(wall, row, clm, i+1, j, status, check);
            }
        }
    }

    public static void statusUpdate(int[][] wall, int row, int clm, int[][] status, boolean[][] check) {
        // int[][] vazeiat = new int[row][clm]; // 0 khali, 1 paydar, 2 napaydar
        for (int k=0; k<clm; k++) { 
            status[0][k] = wall[0][k];
            if (status[0][k] == 1) {
                makeNeighbores(wall, row, clm, 0, k, status, check);
            }
        }
    }

    public static int paydarCount(int[][] wall, int row, int clm) {
        int count = 0; 
        for (int i =0; i<row; i++) {
            for (int j =0; j<clm; j++) {
                if (wall[i][j] == 1) count++; 
            }
        }
        return count; 
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int clm = scanner.nextInt();
        int[][] wall1 = new int[row][clm];
        int[][] wall2 = new int[row][clm];

        for (int i=0; i<row; i++) {
            for (int j=0; j<clm; j++) {
                wall1[i][j] = scanner.nextInt();
                wall2[i][j] = wall1[i][j];
            }
        }

        int n_Attacks = scanner.nextInt();
        int[][] attack = new int[n_Attacks][2]; 
        for (int i=0; i<n_Attacks; i++) {
            attack[i][0] = scanner.nextInt();
            attack[i][1] = scanner.nextInt();
        }

        int[] result = new int[n_Attacks]; 
        int[][] status = new int[row][clm]; 
        boolean[][] check = new boolean[row][clm]; 

        for (int i = 0; i<n_Attacks; i++) {
            int paydar_count1 = paydarCount(wall1, row, clm); 
            wall1[attack[i][0]][attack[i][1]] = 0; 
            for (int ii=0; ii<row; ii++) {
                for (int jj=0; jj<clm; jj++) {
                    status[ii][jj] = 0;
                    check[ii][jj] = false;
                }
            }
            statusUpdate(wall1, row, clm, status, check);
            for (int j =0; j<row; j++) {
                for (int k=0; k<clm; k++) {
                    wall1[j][k] = status[j][k]; 
                }
            }
            int paydar_count2 = paydarCount(wall1, row, clm);
            result[i] = - paydar_count2 + paydar_count1 - 1; 
        }

        System.out.println(Arrays.toString(result));
        scanner.close();

    }
}
