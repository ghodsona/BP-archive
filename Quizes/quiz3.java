import java.util.*;

public class quiz3 {
    public static String remove(String s, int a) {
        String answer = ""; 
        for (int i = 0; i<a; i++) {
            answer = answer + s.charAt(i);
        }
        for (int i = a+1; i<s.length(); i++) {
            answer = answer + s.charAt(i);
        }
        return answer; 
    }

    public static boolean first(String s, String ss) {
        for (int i = 0; i<s.length(); i++) {
            if (ss.equals(remove(s, i))) return true; 
        }
        for (int i = 0; i<ss.length(); i++) {
            if (s.equals(remove(ss, i))) return true; 
        }
        return false; 
    }

    public static boolean sec(String s, String ss) {
        if (s.length() != ss.length()) return false; 

        int diffrentCount = 0;

        for (int i = 0; i<s.length(); i++) {
            if (s.charAt(i) != ss.charAt(i)) diffrentCount++; 
        }
        if (diffrentCount == 1) return true; 
        return false; 
    }

    public static boolean thr(String s, String ss) {
        if ((ss.toLowerCase()).equals(s.toLowerCase())) return true; 
        return false; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt(); 
        int k = scanner.nextInt(); 
        
        scanner.nextLine();
                
        String[] str = new String[n];
        int[] answer = new int[k];
        for (int i = 0; i<n; i++) {
            str[i] = scanner.nextLine(); 
        }

        for (int i = 0; i<k; i++) {
            String tmp = scanner.nextLine(); 
            for (int j = 0; j<n; j++) {
                if (first(str[j], tmp) || thr(str[j], tmp) || sec(str[j], tmp)) answer[i]++; 
            }
        }


        for (int i = 0; i<k; i++) {
            System.out.println(answer[i]);
            answer[i]++; 
        }
        scanner.close(); 

    }
}
