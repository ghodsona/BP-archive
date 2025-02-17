import java.util.*;

public class E5_22 {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        String[] matrix = {"1"};
        for (int i = 0; i < n; i++) {
            matrix = matrix_next(matrix);
        }

        for (String row : matrix) {
            System.out.println(row);
        }
    }
}
