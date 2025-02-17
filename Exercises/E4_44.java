import java.util.Scanner;

public class E4_44 {
    static int n; 
    static int k; 
    static int[][] graph;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        graph = new int[n][n]; 
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt(); 
            }
        }
        int s = scanner.nextInt();
        int t = scanner.nextInt(); 

        int[][][] pathBetweenCount = new int[n][n][k + 1];

        for (int i = 0; i < n; i++) {
            for (int ii = 0; ii < n; ii++) {
                pathBetweenCount[i][ii][1] = graph[i][ii];
            }
        }

        for (int l = 2; l <= k; l++) {
            for (int i = 0; i < n; i++) {
                for (int iii = 0; iii < n; iii++) {
                    for (int ii = 0; ii < n; ii++) {
                        if (graph[i][ii] == 1) {
                            pathBetweenCount[i][iii][l] += pathBetweenCount[ii][iii][l - 1];
                        }
                    }
                }
            }
        }
        
        
        System.out.println(pathBetweenCount[s][t][k]);
        scanner.close();
    }
}
