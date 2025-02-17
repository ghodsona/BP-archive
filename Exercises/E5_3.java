import java.util.*;

public class E5_3 {
    public static int[] toArray(int n) {
        String s = "" + n; 
        int c = s.length();
        int[] digits = new int[c]; 
        for (int i = 1; i<=c; i++) {
            digits[c - i] = n % 10; 
            n/=10; 
        }
        return digits; 
    }

    public static int sumCube(int[] digits) {
        int sum = 0; 
        for (int i = 0; i<digits.length; i++) {
            sum += digits[i]*digits[i]; 
        }
        return sum; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt(); 
        ArrayList<Integer> actions = new ArrayList<>(); 
        int num = n; 
        while (!actions.contains(num)) {
            actions.add(num);
            num = sumCube(toArray(num)); 
            if (num == 1) {
                System.out.println("Happy");
                return; 
            }
        }
        actions.add(num);
        ArrayList<Integer> answer = new ArrayList<>(actions.subList(actions.indexOf(num), actions.size()));

        for (int i : answer) System.out.print(i + " ");

        scanner.close();
    }
}
