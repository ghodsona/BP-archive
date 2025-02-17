// import java.util.Scanner; 

// public class T2_3 {
//     static int[] sectionScore = {20,1,18,4,13,6,10,15,2,17,3,19,7,16,8,11,14,9,12,5};

//     public static int section(int x, int y) {
//         // if (x==0) {
//         //     if (y>0) return sectionScore[0]; 
//         //     else return sectionScore[10]; 

//         // }
//         // else {
//         //     double i = Math.toDegrees(Math.atan(Math.abs(y/x)));
//         //     if (x>0 && y>0); 
//         //     else if (x>0 && y<0) i = 180 - i; 
//         //     else if (x<0 && y<0) i = 180 + i; 
//         //     else if (x<0 && y>0) i = 360 - i;
//         //     return sectionScore[(int)i/18]; 
//         // }
//         double angle = Math.atan2(y, x); 
//         if(angle < 0) angle += 360; 
//         return sectionScore[(int)(angle/18)]; 
//     }

//     public static int score(int x, int y, int section) {
//         double distance = Math.sqrt(x*x + y*y); 
//         if (distance <= 5) return 50; 
//         else if (distance <= 19) return 25; 
//         else if (distance <= 50) return section; 
//         else if (distance <= 70) return 3*section; 
//         else if (distance <= 99) return section; 
//         else if (distance <= 120) return 2*section;         
//         else return 0; 
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int n = scanner.nextInt(); 
//         int[][] dart = new int[n + 10][5]; 

//         for (int i=0; i<n; i++) {
//             dart[i][0] = scanner.nextInt(); 
//             dart[i][1] = scanner.nextInt(); 
//         }

//         int answer = 0; 

//         for (int i=0; i<n; i++) {
//             answer += score(dart[i][0], dart[i][1], section(dart[i][0], dart[i][1])); 
//         }

//         System.out.println(answer);
//         scanner.close(); 
//     }
// }

import java.util.Scanner;

public class T2_3 {
    static int[] sectionScore = {20, 1, 18, 4, 13, 6, 10, 15, 2, 17, 3, 19, 7, 16, 8, 11, 14, 9, 12, 5};

    public static int section(int x, int y) {
        double angle = Math.toDegrees(Math.atan2(y, x));
        if (angle < 0) {
            angle += 360; 
        }
        int sectionIndex = (int)(angle / 18);
        return sectionScore[sectionIndex];
    }

    public static int score(int x, int y, int section) {
        double distance = Math.sqrt(x * x + y * y);
        
        // Calculate score based on the distance
        if (distance <= 5) {
            return 50; // Center (bullseye) gets 50 
        }
        else if (distance <= 19) {
            return 25; // Inner circle gets 25 
        }
        else if (distance <= 50) {
            return section; // Single area gets the section score
        }
        else if (distance <= 70) {
            return 3 * section; // Triple area gets 3 times the section score
        }
        else if (distance <= 99) {
            return section; // Single area again
        }
        else if (distance <= 120) {
            return 2 * section; // Double area gets 2 times the section score
        }
        else {
            return 0; // Outside the dartboard (score 0)
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Number of darts
        int n = scanner.nextInt();
        
        int answer = 0;

        // Process each dart throw
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            answer += score(x, y, section(x, y)); // Calculate the score based on section and distance
        }

        // Output the total score
        System.out.println(answer);

        scanner.close();
    }
}
