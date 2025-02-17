import java.util.*;

public class E5_2 {
    public static String zeros(int k) {
        char[] zeroArray = new char[k];
        Arrays.fill(zeroArray, '0');
        return new String(zeroArray);
    }

    public static String[] matrix_next(String[] matrix_last) {
        int n = matrix_last.length;
        String[] matrix = new String[2 * n];
        String zeros = zeros(n);

        for (int i = 0; i < n; i++) {
            matrix[i] = matrix_last[i] + matrix_last[i];
            matrix[i + n] = matrix_last[i] + zeros; 
        }

        return matrix;
    }

    public static String toString(String[] matrix) {
        StringBuilder ans = new StringBuilder();
        for (String row : matrix) {
            ans.append(row);
        }
        return ans.toString();
    }

    public static String[] toMatrix(String s) {
        int n = (int) Math.sqrt(s.length());
        String[] matrix = new String[n];
        for (int i = 0; i < n; i++) {
            matrix[i] = s.substring(i * n, (i + 1) * n);
        }
        return matrix;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] matrixes = new String[n + 1];
        matrixes[0] = "1";

        for (int i = 1; i < n + 1; i++) {
            matrixes[i] = toString(matrix_next(toMatrix(matrixes[i - 1])));
        }

        String result = matrixes[n];
        int size = (int) Math.sqrt(result.length());
        for (int i = 0; i < result.length(); i++) {
            System.out.print(result.charAt(i));
            if ((i + 1) % size == 0) {
                System.out.println();
            }
        }

        scanner.close();
    }
}




