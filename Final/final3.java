// import java.util.*;

// public class final3 {
//     public static String toAlphabet(String number) {
//         int tmp = Integer.valueOf(number); 
//         String answer = ""; 
//         while (tmp>0) {
//             if (((tmp%26 + 26) % 26) == 0) answer = 'Z' + answer; 
//             else {
//                 answer = (char)('A' - 1 + (tmp%26 + 26) % 26) + answer; 
//             }
//             tmp /= 26; 
//         }
//         return answer; 
//     }

//     public static int toDigit(String str) {
//         int n = str.length();
//         int[] alphabet = new int[n];
//         int answer = 0; 

//         for (int i = 0; i<n; i++) {
//             alphabet[i] = str.charAt(n - 1 - i) - 'A' + 1; 
//             answer += alphabet[i] * (int)Math.pow(26, i);
//         }
        
//         return answer; 
//     }
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in); 
//         String s = scanner.nextLine(); 

//         if (s.charAt(0) == 'R') {
//             String row = ""; 
//             int index_C = 2;  
//             for (int i = 1; i<s.length(); i++) {
//                 if (s.charAt(i) != 'C') {
//                     row = row + s.charAt(i); 
//                 }
//                 else {
//                     index_C = i+1; 
//                     break;
//                 }
//             }
            
//             String clm = s.substring(index_C, s.length()); 

//             System.out.println(row + toAlphabet(clm));
//         }

//         else {
//             String row = ""; 
//             int index_C = 1;  
//             for (int i = 0; i<s.length(); i++) {
//                 if (Character.isDigit(s.charAt(i))) {
//                     row = row + s.charAt(i); 
//                 }
//                 else {
//                     index_C = i; 
//                     break;
//                 }
//             }
            
//             String clm = s.substring(index_C, s.length()); 

//             System.out.println("R" + row + "C" + toDigit(clm));
//         }

//         scanner.close();
//     }
// }

import java.util.*;

public class final3 {
    public static String toAlphabet(String number) {
        int tmp = Integer.parseInt(number);
        StringBuilder answer = new StringBuilder();
        while (tmp > 0) {
            tmp--;
            answer.insert(0, (char) ('A' + (tmp % 26)));
            tmp /= 26;
        }
        return answer.toString();
    }

    public static int toDigit(String str) {
        int answer = 0;
        for (char c : str.toCharArray()) {
            answer = answer * 26 + (c - 'A' + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        if (s.charAt(0) == 'R' && s.contains("C")) {
            int index_C = s.indexOf('C');
            String row = s.substring(1, index_C);
            String clm = s.substring(index_C + 1);
            System.out.println(row + toAlphabet(clm));
        }
        
        else {
            int index_C = 0;
            while (index_C < s.length() && Character.isDigit(s.charAt(index_C))) {
                index_C++;
            }
            String row = s.substring(0, index_C);
            String clm = s.substring(index_C);
            System.out.println("R" + row + "C" + toDigit(clm));
        }

        scanner.close();
    }
}
