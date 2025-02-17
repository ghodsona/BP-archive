import java.util.Scanner;

public class midterm4 {
    static int[][] distanceIfromJ; 
    static int[][] mokhtasat; 
    static int[] maxDistanceFrom;
    static int n; 

    public static int distance (int i, int j) {
        int dx = mokhtasat[i][0] - mokhtasat[j][0]; 
        int dy = mokhtasat[i][1] - mokhtasat[j][1]; 
        return (dx*dx + dy*dy);
    }

    public static void distanceMaker() {
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                distanceIfromJ[i][j] = distance(i, j); 
                distanceIfromJ[j][i] = distanceIfromJ[i][j]; 
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        n = scanner.nextInt(); 
        distanceIfromJ = new int[n][n]; 
        mokhtasat = new int[n][2]; 
        maxDistanceFrom = new int[n]; 

        for (int k = 0; k<n; k++) {
            mokhtasat[k][0] = scanner.nextInt(); 
            mokhtasat[k][1] = scanner.nextInt(); 
        }

        distanceMaker();

        for (int i = 0; i<n; i++) {
            int max = 0 ;
            for (int j = 0; j<n; j++) {
                if (distanceIfromJ[i][j] > max) {
                    max = distanceIfromJ[i][j]; 
                }
            }
            maxDistanceFrom[i] = max; 
        }

        int min = 99999999 ;
        for (int j = 0; j<n; j++) {
            if (maxDistanceFrom[j] < min) {
                min = maxDistanceFrom[j]; 
            }
        }
        
        System.out.println(min);
        scanner.close();
        
    }
}