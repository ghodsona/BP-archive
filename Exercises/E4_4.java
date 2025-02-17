import java.util.Scanner;

public class E4_4 {
    static int n; 
    static int k; 
    static int[][] graph;

    public static void input(Scanner scanner) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt(); 
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        int answer = 0; 
        graph = new int[n][n]; 

        input(scanner);
        int s = scanner.nextInt();
        int t = scanner.nextInt(); 

        for (int i = 0; i < Math.pow(2, n); i++) {
            if (Integer.bitCount(i) != k - 2) continue;
            
            int[] real_path = new int[k - 2];
            int idx = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    real_path[idx++] = j;
                    if (idx == k - 2) break;
                }
            }

            boolean is_realy_path = true;

            if (graph[s][real_path[0]] != 1) continue;

            for (int j = 0; j < k - 3; j++) {
                if (graph[real_path[j]][real_path[j + 1]] != 1) {
                    is_realy_path = false;
                    break;
                }
            }

            if (is_realy_path && graph[real_path[k - 3]][t] != 1) {
                is_realy_path = false;
            }

            if (is_realy_path) {
                answer++;
            }
        }

        System.out.println(answer);
        scanner.close();
    }
}
