import java.util.*;

public class final1 {
    static int n;
    static String[] numbers;
    static int[][] counts;
    static int[] ans;

    public static int[] counter(String number) {
        int[] digitCount = new int[10];
        for (char c : number.toCharArray()) {
            digitCount[c - '0']++;
        }
        return digitCount;
    }

    public static int maxTekrar(int[] digitCount) {
        int maxxx = -1;
        for (int i = 0; i < digitCount.length; i++) {
            if (digitCount[i] > maxxx) maxxx = digitCount[i];
        }
        return maxxx;
    }

    public static int compareNumbers(String num1, String num2) {
        if (num1.length() != num2.length()) {
            return Integer.compare(num1.length(), num2.length());
        }
        return num1.compareTo(num2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        numbers = new String[n];
        ans = new int[n];
        counts = new int[n][10];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.next();
        }

        int maximum_tekrar = 0;
        for (int i = 0; i < n; i++) {
            ans[i] = maxTekrar(counter(numbers[i]));
            if (ans[i] > maximum_tekrar) maximum_tekrar = ans[i];
        }

        String min = null;
        for (int i = 0; i < n; i++) {
            if (ans[i] == maximum_tekrar) {
                if (min == null || compareNumbers(numbers[i], min) < 0) {
                    min = numbers[i];
                }
            }
        }

        System.out.println(min);

        scanner.close();
    }
}
// import java.util.*;

// public class final5 {
//     static int[] numbers; 

//     public static int value(int start, int end) { 
//         int min = Integer.MAX_VALUE;
//         int max = Integer.MIN_VALUE;
//         for (int i = start; i<end; i++) {
//             if (numbers[i] < min) min = numbers[i]; 
//             if (numbers[i] > max) max = numbers[i]; 
//         }
//         return max - min; 
//     }

//     public static int maxPoint(int start, int end) {
//         int n = end - start; 

//         if (n==2) {
//             return 0; 
//         }

//         // int[] left = new int[n/2]; 
//         // int[] right = new int[n/2]; 
//         // for (int i = 0; i<n/2; i++) {
//         //     left[i] = numbers[i]; 
//         //     right[i] = numbers[i+n/2]; 
//         // }

//         return Math.max(value(start, (start+end)/2) + maxPoint((start+end)/2+1, end), value((start+end)/2+1, end) + maxPoint(start, (start+end)/2));
//     }
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in); 
//         int n = scanner.nextInt();
//         numbers = new int[n]; 

//         for (int i = 0; i<n; i++) {
//             numbers[i] = scanner.nextInt(); 
//         }

//         System.out.println(maxPoint(0, numbers.length-1));

//         scanner.close();
//     }
// }

// import java.util.*;

// public class final1 {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int n = scanner.nextInt();
//         String[] numbers = new String[n];
//         for (int i = 0; i < n; i++) {
//             numbers[i] = scanner.next();
//         }
//         scanner.close();

//         String result = numbers[0];
//         int maxRepeat = -1;

//         for (String number : numbers) {
//             int[] digitCount = new int[10];
//             for (char c : number.toCharArray()) {
//                 digitCount[c - '0']++;
//             }

//             int maxDigitRepeat = Arrays.stream(digitCount).max().getAsInt();
//             if (maxDigitRepeat > maxRepeat || 
//                 (maxDigitRepeat == maxRepeat && compareNumbers(number, result) < 0)) {
//                 result = number;
//                 maxRepeat = maxDigitRepeat;
//             }
//         }

//         System.out.println(result);
//     }

//     public static int compareNumbers(String num1, String num2) {
//         if (num1.length() != num2.length()) {
//             return Integer.compare(num1.length(), num2.length());
//         }
//         return num1.compareTo(num2);
//     }
// }
