import java.util.*;

public class E5_44 {
    static int[][] soduko = new int[9][9];
    static ArrayList<int[]> empties = new ArrayList<>();
    static int tmp = 0;

    public static boolean is_valid(int i, int j) {
        int num = soduko[i][j];

        for (int col = 0; col < 9; col++) {
            if (col != j && soduko[i][col] == num) {
                return false;
            }
        }

        for (int row = 0; row < 9; row++) {
            if (row != i && soduko[row][j] == num) {
                return false;
            }
        }
    
        int xEnv = (i / 3) * 3;
        int yEnv = (j / 3) * 3;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if ((xEnv + row != i || yEnv + col != j) &&
                    soduko[xEnv + row][yEnv + col] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void solve() {
        if (tmp == empties.size()) {
            return;
        }

        int[] current = empties.get(tmp);
        int i = current[0];
        int j = current[1];

        soduko[i][j]++;
        while (soduko[i][j] <= 9 && !is_valid(i, j)) {
            soduko[i][j]++;
        }

        if (soduko[i][j] > 9) {
            soduko[i][j] = 0;
            tmp--;
            solve();
        }
        else {
            tmp++;
            solve();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            String tmp = scanner.nextLine();
            for (int j = 0; j < 9; j++) {
                char c = tmp.charAt(2 * j);
                if (c == '.') {
                    soduko[i][j] = 0;
                    empties.add(new int[]{i, j});
                } else {
                    soduko[i][j] = c - '0';
                }
            }
        }

        solve();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(soduko[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
