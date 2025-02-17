import java.util.Scanner;

public class E3_55 {
    static int m, n;
    static int[][] wall;
    static boolean[][] visited;

    static int countBricks(int[][] w) {
        int c = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (w[i][j] == 1) c++;
            }
        }
        return c;
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx>=0 && nx<m && ny>=0 && ny<n) {
                if (!visited[nx][ny] && wall[nx][ny]==1) {
                    dfs(nx, ny);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        wall = new int[m][n];

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                wall[i][j] = sc.nextInt();
            }
        }

        int h = sc.nextInt();
        int[] result = new int[h];

        for (int attackIndex = 0; attackIndex < h; attackIndex++) {
            int hx = sc.nextInt();
            int hy = sc.nextInt();

            int oldCount = countBricks(wall);
            wall[hx][hy] = 0;
            visited = new boolean[m][n];

            for (int j=0; j<n; j++) {
                if (wall[0][j]==1 && !visited[0][j]) {
                    dfs(0, j);
                }
            }

            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if (!visited[i][j] && wall[i][j] == 1) {
                        wall[i][j] = 0;
                    }
                }
            }

            int newCount = countBricks(wall);
            result[attackIndex] = oldCount - newCount - 1;
        }

        System.out.print("[");
        for (int i=0; i<h; i++) {
            System.out.print(result[i]);
            if (i < h-1) System.out.print(", ");
        }
        System.out.print("]");

        sc.close();
    }
}
