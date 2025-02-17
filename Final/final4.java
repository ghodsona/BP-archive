import java.util.*;

public class final4 {
    static int n;
    static int m;
    static int[][] earth;
    static boolean[][] visited;

    public static int ways(int rs, int cs) {
        if (rs < 0 || cs < 0 || rs >= n || cs >= m || earth[rs][cs] == 1 || visited[rs][cs]) {
            return 0;
        }
        if (rs == n - 1 && cs == m - 1) {
            return 1;
        }

        visited[rs][cs] = true;

        int totalWays = ways(rs, cs + 1) + ways(rs + 1, cs) + ways(rs, cs - 1) + ways(rs - 1, cs);

        visited[rs][cs] = false; 

        return totalWays;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        earth = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                earth[i][j] = scanner.nextInt();
            }
        }

        System.out.println(ways(0, 0));

        scanner.close();
    }
}

