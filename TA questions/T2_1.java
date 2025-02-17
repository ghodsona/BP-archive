// import java.util.Scanner; 

// public class T2_1 {
//     static int N = (int)Math.pow(2, 20);
//     static String[] reshte = new String[N];
//     static long[] number = new long[N];
//     static int[] M_reshte = new int[N]; 
//     static int[] m_reshte = new int[N]; 
//     static int[] hash_reshte = new int[N]; 
//     static int[] c_reshte = new int[N]; 
//     static boolean[] maskMax = new boolean[N];   
//     static boolean[] maskMin = new boolean[N];    

//     public static boolean isFinish(String x) { 
//         String comparing = ""; 
//         for (int i=0; i<x.length(); i++) {
//             if (x.charAt(i) == '#') continue;
//             else if ((x.charAt(i) == 'M') || (x.charAt(i) == 'm')) return false;
//             else comparing = comparing + x.charAt(i); 
//         }
//         if (comparing.compareTo("finish") == 0) return true; 
//         else return false; 
//     }

//     public static long numberOf(String x) {
//         String comparing = ""; 
//         for (int i=0; i<x.length(); i++) {
//             if ((x.charAt(i) == 'M') || (x.charAt(i) == 'm') || (x.charAt(i) == '#')) continue; 
//             else comparing = comparing + x.charAt(i); 
//         }
        
//         if (comparing.isEmpty()) return 0;
//         return Long.parseLong(comparing);
//     }

//     public static int M(String x) {
//         int count = 0;
//         for (int i=0; i<x.length(); i++) {
//             if (x.charAt(i) == 'M') count++;
//         }
//         return count; 
//     }

//     public static int m(String x) {
//         int count = 0;
//         for (int i=0; i<x.length(); i++) {
//             if (x.charAt(i) == 'm') count++;
//         }
//         return count; 
//     }

//     public static int hash(String x) {
//         int count = 0;
//         for (int i=0; i<x.length(); i++) {
//             if (x.charAt(i) == '#') count++;
//         }
//         return count; 
//     }

//     public static long whatsMax(long[] number, boolean[] maskMax, int j) {
//         long Max = -999999999999999999L;
//         for (int k=0; k<j; k++) {
//             if (maskMax[k]) {
//                 if (number[k] > Max) Max = number[k]; 
//             }
//         }
//         return Max; 
//     }

//     public static long whatsMin(long[] number, boolean[] maskMin, int j) {
//         long Min = 999999999999999999L;
//         for (int k=0; k<j; k++) {
//             if (maskMin[k]) {
//                 if (number[k] < Min) Min = number[k]; 
//             }
//         }
//         return Min; 
//     }

//     public static int howManyMaxMin(long[] number, boolean[] mask, int j, long MaxOrMin) {
//         int counter = 0; 
//         for (int k=0; k<j; k++) {
//             if (mask[k]) {
//                 if (number[k] == MaxOrMin) counter++;
//             }
//         }
//         return counter;
//     }   

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in); 
        
//         int tedadMaxGroup = 0; 
//         int tedadMinGroup = 0;     
//         int j = 0;
//         String x;

//         while (true) {
//             x = scanner.next();
//             if (!isFinish(x)) {
//                 if (!x.isEmpty()) {
//                     reshte[j] = x;
//                     j++;
//                 }
//             }
//             else {
//                 break;
//             }
//         }

        
//         // j = tedad reshteha
//         for (int i=0; i<j; i++) {
//             number[i] = numberOf(reshte[i]);
//             M_reshte[i] = M(reshte[i]);
//             m_reshte[i] = m(reshte[i]);
//             hash_reshte[i] = hash(reshte[i]);
//             c_reshte[i] = M_reshte[i]*M_reshte[i] - 5*m_reshte[i] - 2*hash_reshte[i]; 

//             if (c_reshte[i] > 0) {
//                 maskMax[i] = true;
//                 maskMin[i] = false; 
//                 tedadMaxGroup++; 
//             }
//             else if (c_reshte[i] < 0) {
//                 maskMax[i] = false;
//                 maskMin[i] = true; 
//                 tedadMinGroup++;
//             }
//             else {
//                 maskMax[i] = true;
//                 maskMin[i] = true; 
//                 tedadMaxGroup++;
//                 tedadMinGroup++; 
//             }
//         }

//         if (tedadMinGroup == 0) {
//             System.out.println("\"Minimum Input Not Found\"");
//         }
//         else {
//             System.out.println(whatsMin(number, maskMin, j));
//             System.out.println(howManyMaxMin(number, maskMin, j, whatsMin(number, maskMin, j)));
//         }

//         if (tedadMaxGroup == 0) {
//             System.out.println("\"Maximum Input Not Found\"");
//         }
//         else {
//             System.out.println(whatsMax(number, maskMax, j));
//             System.out.println(howManyMaxMin(number, maskMax, j, whatsMax(number, maskMax, j)));
//         }

//         scanner.close();
//     }
// }

import java.util.Scanner;

public class T2_1 {
    static int N = (int)Math.pow(2, 20);
    static String[] reshte = new String[N];
    static long[] number = new long[N];
    static int[] M_reshte = new int[N]; 
    static int[] m_reshte = new int[N]; 
    static int[] hash_reshte = new int[N]; 
    static int[] c_reshte = new int[N]; 
    static boolean[] maskMax = new boolean[N];   
    static boolean[] maskMin = new boolean[N];    

    public static boolean isFinish(String x) { 
        String comparing = x.replaceAll("#", ""); // Remove all # characters
        return comparing.equals("finish"); // Directly compare with "finish"
    }

    public static long numberOf(String x) {
        String cleanedInput = x.replaceAll("[#Mm]", ""); // Remove all M, m, and #
        if (cleanedInput.isEmpty()) return 0;
        return Long.parseLong(cleanedInput); // Convert remaining string to long
    }

    public static int M(String x) {
        int count = 0;
        for (int i=0; i<x.length(); i++) {
            if (x.charAt(i) == 'M') count++;
        }
        return count; 
    }

    public static int m(String x) {
        int count = 0;
        for (int i=0; i<x.length(); i++) {
            if (x.charAt(i) == 'm') count++;
        }
        return count; 
    }

    public static int hash(String x) {
        int count = 0;
        for (int i=0; i<x.length(); i++) {
            if (x.charAt(i) == '#') count++;
        }
        return count; 
    }

    public static long whatsMax(long[] number, boolean[] maskMax, int j) {
        long Max = Long.MIN_VALUE;
        for (int k=0; k<j; k++) {
            if (maskMax[k]) {
                if (number[k] > Max) Max = number[k]; 
            }
        }
        return Max; 
    }

    public static long whatsMin(long[] number, boolean[] maskMin, int j) {
        long Min = Long.MAX_VALUE;
        for (int k=0; k<j; k++) {
            if (maskMin[k]) {
                if (number[k] < Min) Min = number[k]; 
            }
        }
        return Min; 
    }

    public static int howManyMaxMin(long[] number, boolean[] mask, int j, long MaxOrMin) {
        int counter = 0; 
        for (int k=0; k<j; k++) {
            if (mask[k]) {
                if (number[k] == MaxOrMin) counter++;
            }
        }
        return counter;
    }   

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        
        int tedadMaxGroup = 0; 
        int tedadMinGroup = 0;     
        int j = 0;
        String x;

        while (true) {
            x = scanner.next();
            if (!isFinish(x)) {
                if (!x.isEmpty()) {
                    reshte[j] = x;
                    j++;
                }
            }
            else {
                break;
            }
        }

        // Process each input string
        for (int i=0; i<j; i++) {
            number[i] = numberOf(reshte[i]);
            M_reshte[i] = M(reshte[i]);
            m_reshte[i] = m(reshte[i]);
            hash_reshte[i] = hash(reshte[i]);
            c_reshte[i] = M_reshte[i] * M_reshte[i] - 5 * m_reshte[i] - 2 * hash_reshte[i]; 

            if (c_reshte[i] > 0) {
                maskMax[i] = true;
                maskMin[i] = false; 
                tedadMaxGroup++; 
            }
            else if (c_reshte[i] < 0) {
                maskMax[i] = false;
                maskMin[i] = true; 
                tedadMinGroup++;
            }
            else {
                maskMax[i] = true;
                maskMin[i] = true; 
                tedadMaxGroup++;
                tedadMinGroup++; 
            }
        }

        // Output results for min group
        if (tedadMinGroup == 0) {
            System.out.println("Minimum Input Not Found");
        }
        else {
            long minVal = whatsMin(number, maskMin, j);
            System.out.println(minVal);
            System.out.println(howManyMaxMin(number, maskMin, j, minVal));
        }

        // Output results for max group
        if (tedadMaxGroup == 0) {
            System.out.println("Maximum Input Not Found");
        }
        else {
            long maxVal = whatsMax(number, maskMax, j);
            System.out.println(maxVal);
            System.out.println(howManyMaxMin(number, maskMax, j, maxVal));
        }

        scanner.close();
    }
}