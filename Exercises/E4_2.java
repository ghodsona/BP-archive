import java.util.*;

public class E4_2 {
    static int n; 
    static String[] string; 
    static boolean[] isPrinted; 
    static boolean[][] is_in_a_group; 

    public static boolean isInString(char a, String str) {
        for (int i = 0; i<str.length(); i++) {
            if (str.charAt(i) == a) return true; 
        }
        return false; 
    }

    public static boolean isInAGroup(String s_1, String s_2) {
        if (s_1.length() != s_2.length()) return false; 

        boolean temp = true;
        for (int i = 0; i<s_1.length(); i++) {
            temp = temp && isInString(s_1.charAt(i), s_2); 
            if (!temp) return temp; 
        }
        for (int i = 0; i<s_2.length(); i++) {
            temp = temp && isInString(s_2.charAt(i), s_1); 
            if (!temp) return temp; 
        }
        return temp; 
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        n = scanner.nextInt(); 
        string = new String[n]; 
        is_in_a_group = new boolean[n][n]; 
        isPrinted = new boolean[n];

        for (int i = 0; i<n; i++) {
            string[i] = scanner.next(); 
        }

        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                is_in_a_group[i][j] = isInAGroup(string[i], string[j]); 
            }
        }

        for (int i = 0; i<n; i++) {
            if (isPrinted[i]) continue; 
            List<String> group = new ArrayList<>(); 
            for (int j = 0; j<n; j++) {
                if (is_in_a_group[i][j]) {
                    isPrinted[j] = true;
                    group.add(string[j]); 
                }
            }
            System.out.println(group);
        }




        scanner.close();
    }
}
