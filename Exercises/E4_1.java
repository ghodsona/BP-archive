import java.util.*;

public class E4_1 {
    static int n;
    static long m;

    public static long factoriel(int number) {
        if (number == 0 || number == 1) return 1;
        long answer = 1;
        for (int i = 1; i <= number; i++) {
            answer *= i;
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextLong();

        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            elements.add(scanner.nextInt());
        }

        List<Integer> result = new ArrayList<>();
        m--;

        while (!elements.isEmpty()) {
            long fact = factoriel(elements.size() - 1);
            int index = (int) (m / fact);
            result.add(elements.get(index));
            elements.remove(index);
            m %= fact;
        }

        System.out.println(result);
        scanner.close();
    }
}
