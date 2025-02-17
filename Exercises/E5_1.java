import java.util.*;

public class E5_1 {
    public static boolean isPossiblle(int[] alphabet) {
        int oddCount = 0; 
        for (int count : alphabet) {
            if (count % 2 != 0) oddCount++;
        }
        return oddCount <= 1;
    }

    public static void toPermitations(int[] alphabet) {
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] /= 2; 
        }
    }

    public static String middle(int[] alphabet) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] % 2 != 0) {
                return Character.toString((char) (i + 'a')); 
            }
        }
        return ""; 
    }

    public static String toString(int[] alphabet) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < alphabet.length; i++) {
            for (int j = 0; j < alphabet[i]; j++) {
                ans.append((char) (i + 'a')); 
            }
        }
        return ans.toString();
    }

    public static void generatePermutations(String half, boolean[] used, StringBuilder current, Set<String> results) {
        if (current.length() == half.length()) {
            results.add(current.toString());
            return;
        }

        for (int i = 0; i < half.length(); i++) {
            if (used[i] || (i > 0 && half.charAt(i) == half.charAt(i - 1) && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            current.append(half.charAt(i));
            generatePermutations(half, used, current, results);
            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }

    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        String s = scanner.nextLine();

        int[] alphabet = new int[26]; 
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++; 
        }

        if (!isPossiblle(alphabet)) {
            System.out.println("GG");
            return;
        }

        String mdl = "";
        if (s.length() % 2 != 0) {
            mdl = middle(alphabet); 
        }
        toPermitations(alphabet);

        String half = toString(alphabet);
        Set<String> permutations = new TreeSet<>();
        generatePermutations(half, new boolean[half.length()], new StringBuilder(), permutations);

        for (String perm : permutations) {
            System.out.println(perm + mdl + reverse(perm));
        }

        scanner.close();
    }
}
