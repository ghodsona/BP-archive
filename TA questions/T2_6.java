import java.util.Scanner; 

public class T2_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i=0; i<n; i++) {
            array[i] = scanner.nextInt(); 
        }

        int count = 0; 
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                if (array[i] > array[j]) count++; 
            }
        }
        System.out.println(count);

        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                if (array[i] > array[j]) {
                    System.out.println(i + " " + j);
                } 
            }
        }
        scanner.close();
    }
}
