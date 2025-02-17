import java.util.Scanner; 

public class T3_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt();
        int q = scanner.nextInt(); 
        int trueCount = 0; 
        int[][] question = new int[q][3]; 
        boolean[] isPossible = new boolean[n]; 
        int location; 

        for (int i=0; i<q; i++) {
            question[i][0] = scanner.nextInt();
            question[i][1] = scanner.nextInt();
            question[i][2] = scanner.nextInt();

            if (question[i][2] == 0) {
                for (int j=question[i][0]-1; j<question[i][1]; j++) {
                    isPossible[j] = false; 
                }
            }
            else {
                for (int j=question[i][0]-1; j<question[i][1]; j++) {
                    isPossible[j] = true; 
                }
            }
        }


        for (int i=0; i<n; i++) {
            if (isPossible[i]) {
                trueCount++; 
                if (trueCount > 0) break;
                System.out.println("YES");
                System.out.println(i+1);
                return; 
            }
        }
        System.out.println("NO");
        


        scanner.close();
    }
}
