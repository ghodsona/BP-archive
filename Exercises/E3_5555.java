import java.util.Arrays;
import java.util.Scanner;

public class E3_5555 {
    static int[][] wall;       
    static int h; 
    static boolean[][] status; 
    static boolean[][] isChecked; 
    static int row; 
    static int clm; 
    static int[] mx = {-1, 0, 0, 1};
    static int[] my = {0, -1, 1, 0}; 
    static int[] answer; 

    public static int aajorCount() {
        int count = 0; 
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < clm; j++) {
                count += wall[i][j]; 
            }
        }
        return count; 
    }

    public static boolean isPaydar(int i, int j) {
        isChecked[i][j] = true; 
        if (i==0 && wall[i][j]==1) return true; 
        else if (wall[i][j]==0) return false; 
        else {
            boolean b = false; 
            for (int d=0; d<4; d++) {
                int di = i + mx[d]; 
                int dj = j + my[d]; 

                if (di>=0 && di<row && dj>=0 && dj<clm) {
                    isChecked[i][j] = true; 
                    b = b || isPaydar(di, dj); 
                }
            }

            return b;
        }
    }

    // public static void mark(int i, int j) {
    //     status[i][j] = true; 
    //     isChecked[i][j] = true;

    //     for (int d = 0; d < 4; d++) {
    //         int iCheck = i + mx[d]; 
    //         int jCheck = j + my[d]; 
    //         if (iCheck < row && jCheck < clm && iCheck > -1 && jCheck > -1 && wall[iCheck][jCheck] == 1) {
    //             if (!isChecked[iCheck][jCheck]) {
    //                 mark(iCheck, jCheck);
    //             } else { 
    //                 isChecked[iCheck][jCheck] = true;
    //             }
    //         }
    //     }
    // }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        
        row = scanner.nextInt(); 
        clm = scanner.nextInt(); 
        wall = new int[row][clm];       
        status = new boolean[row][clm];
        isChecked = new boolean[row][clm];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < clm; j++) {
                wall[i][j] = scanner.nextInt(); 
            }
        }
        
        h = scanner.nextInt();
        answer = new int[h];         

        for (int i = 0; i < h; i++) {
            int hx = scanner.nextInt();
            int hy = scanner.nextInt();

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < clm; c++) {
                    
                }
            }
            for (int k = 0; k < clm; k++) {
                if (wall[0][k] == 1) {
                    mark(0, k);
                }
            }
            for (int rr = 0; rr < row; rr++) {
                for (int cc = 0; cc < clm; cc++) {
                    wall[rr][cc] = status[rr][cc] ? 1 : 0;
                }
            }
            int oldCount = aajorCount();

            //---- واردکردن ضربه ----
            wall[hx][hy] = 0;

            //---- پیمایش دوم: پیدا کردن تعداد سلول‌های پایدار پس از ضربه ----
            // (4) ریست status و isChecked
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < clm; c++) {
                    status[r][c] = false;
                    isChecked[r][c] = false;
                }
            }
            // (5) پیمایش مجدد از سطر بالایی
            for (int k = 0; k < clm; k++) {
                if (wall[0][k] == 1) {
                    mark(0, k);
                }
            }
            int newCount = aajorCount();
            
            for (int rr = 0; rr < row; rr++) {
                for (int cc = 0; cc < clm; cc++) {
                    wall[rr][cc] = status[rr][cc] ? 1 : 0;
                }
            }
            
            //---- محاسبهٔ تعداد خانه‌های ناپایدارشده ----
            // -۱ برای خانه‌ای که خودمان صفر کرده‌ایم
            answer[i] = (oldCount - newCount) - 1;
        }


        System.out.println(Arrays.toString(answer));
        scanner.close(); 
    }
}
