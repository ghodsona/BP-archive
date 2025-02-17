import java.util.*;

public class E4_5 {
    public static boolean is_positive(String number) {
        return number.charAt(0) != '-';
    }

    public static String appendZeroBefore(String number, int n) {
        while (number.length() < n) {
            number = "0" + number;
        }
        return number;
    }

    public static int[] toArray(String number) {
        int[] digits = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            digits[i] = number.charAt(i) - '0';
        }
        return digits;
    }

    public static String removeLeadingZeros(String str) {
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0') {
            i++;
        }
        return i == str.length() ? "0" : str.substring(i);
    }

    public static int[] maxxx(int[] a, int[] b) {
        if (a.length != b.length) {
            return a.length > b.length ? a : b;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return a[i] > b[i] ? a : b;
            }
        }
        return a;
    }

    public static int[] minnn(int[] a, int[] b) {
        return Arrays.equals(maxxx(a, b), a) ? b : a;
    }

    public static String to_string(int[] a) {
        StringBuilder answer = new StringBuilder();
        for (int digit : a) {
            answer.append(digit);
        }
        return answer.toString();
    }

    public static String minus(int[] a, int[] b) {
        boolean negate = false;
        if (!Arrays.equals(maxxx(a, b), a)) {
            int[] tmp = a;
            a = b;
            b = tmp;
            negate = true;
        }

        b = toArray(appendZeroBefore(to_string(b), a.length));
        int[] answer = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (a[a.length - 1 - i] < b[a.length - 1 - i]) {
                a[a.length - 1 - i] += 10;
                int j = i + 1;
                while (j < a.length && a[a.length - 1 - j] == 0) {
                    a[a.length - 1 - j] = 9;
                    j++;
                }
                a[a.length - 1 - j]--;
            }
            answer[a.length - 1 - i] = a[a.length - 1 - i] - b[a.length - 1 - i];
        }
        String result = removeLeadingZeros(to_string(answer));
        if (negate) return "-" + result;
        else return result;
    }

    public static String sum(int[] a, int[] b) {
        int[] s1 = maxxx(a, b);
        int[] s2 = minnn(a, b);
        s2 = toArray(appendZeroBefore(to_string(s2), s1.length));

        int[] answer = new int[s1.length + 1];
        int carry = 0;

        for (int i = 0; i < s1.length; i++) {
            int sum = s1[s1.length - 1 - i] + s2[s2.length - 1 - i] + carry;
            answer[answer.length - 1 - i] = sum % 10;
            carry = sum / 10;
        }

        if (carry > 0) {
            answer[0] = carry;
        }

        return removeLeadingZeros(to_string(answer));
    }

    public static String realSum(String s_1, String s_2) {        
        s_1 = removeLeadingZeros(s_1);
        s_2 = removeLeadingZeros(s_2);

        if (is_positive(s_1) && is_positive(s_2)) {
            return (sum(toArray(s_1), toArray(s_2)));
        }
        else if (!is_positive(s_1) && !is_positive(s_2)) {
            s_1 = s_1.substring(1);
            s_2 = s_2.substring(1);
            return ("-" + sum(toArray(s_1), toArray(s_2)));
        }
        else if (!is_positive(s_1)) {
            s_1 = s_1.substring(1);
            return (minus(toArray(s_2), toArray(s_1)));
        }
        else {
            s_2 = s_2.substring(1);
            return (minus(toArray(s_1), toArray(s_2)));
        }
    }

    public static String mltp(String s, int a) {
        int[] number = toArray(s); 
        int[] answer = new int[s.length() + 1]; 
        int carry = 0; 

        for (int i = 0; i < number.length; i++) {
            int tmp = number[number.length - 1 - i] * a + carry;
            answer[answer.length - 1 - i] = tmp % 10; 
            carry = tmp / 10;
        }

        answer[0] = carry;
        return removeLeadingZeros(to_string(answer)); 
    }

    public static String multiply(int[] a, int[] b) {
        int[] s1 = maxxx(a, b);
        int[] s2 = minnn(a, b);
        
        String[] results = new String[s2.length]; 

        for (int i = 0; i<s2.length; i++) {
            results[i] = mltp(to_string(s1), s2[s2.length - 1 - i]); 
            for (int j = 0; j<i; j++) {
                results[i] += "0"; 
            }
        }
        
        String answer = "0";
        for (int i = 0; i<s2.length; i++) {
            answer = sum(toArray(results[i]), toArray(answer)); 
        }

        return answer; 
    }

    public static String devide(int[] a, int[] b) {
        if (Arrays.equals(maxxx(a, b), b)) return "0"; 

        int[] answer = new int[a.length];
        for (int i = 0; i<a.length; i++) {
            for (int j = 1; j<10; j++) {
                answer[i] = j; 
                String tmp = multiply(answer, b);
                if (Arrays.equals(toArray(tmp), a)) break; 
                if (Arrays.equals(maxxx(toArray(tmp), a), toArray(tmp))) {
                    answer[i] = j - 1; 
                    break;
                }
            }
        }
        return removeLeadingZeros(to_string(answer)); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s_1 = scanner.next();
        String s_2 = scanner.next();
        char c = scanner.next().charAt(0);

        if (c == '*') {
            if (is_positive(s_1) && is_positive(s_2)) {
                System.out.println(multiply(toArray(s_1), toArray(s_2)));
            }
            else if (!is_positive(s_1) && !is_positive(s_2)) {
                s_1 = s_1.substring(1);
                s_2 = s_2.substring(1);
                System.out.println(multiply(toArray(s_1), toArray(s_2)));
            }
            else if (is_positive(s_1) && !is_positive(s_2)) {
                s_2 = s_2.substring(1);
                System.out.print("-");
                System.out.println(multiply(toArray(s_1), toArray(s_2)));
            }
            else if (!is_positive(s_1) && is_positive(s_2)){
                s_1 = s_1.substring(1);
                System.out.print("-");
                System.out.println(multiply(toArray(s_1), toArray(s_2)));
            }
        }

        if (c == '/') {
            if (is_positive(s_1) && is_positive(s_2)) {
                System.out.println(devide(toArray(s_1), toArray(s_2)));
            }
            else if (!is_positive(s_1) && !is_positive(s_2)) {
                s_1 = s_1.substring(1);
                s_2 = s_2.substring(1);
                System.out.println(devide(toArray(s_1), toArray(s_2)));
            }
            else if (is_positive(s_1) && !is_positive(s_2)) {
                s_2 = s_2.substring(1);
                System.out.print("-");
                System.out.println(devide(toArray(s_1), toArray(s_2)));
            }
            else if (!is_positive(s_1) && is_positive(s_2)){
                s_1 = s_1.substring(1);
                System.out.print("-");
                System.out.println(devide(toArray(s_1), toArray(s_2)));
            }
        }

        scanner.close();
    }
}
