import java.util.Scanner; 

public class E1_4 {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        double a = scan.nextDouble();
        double b = scan.nextDouble();
        double c = scan.nextDouble();

        double max = a * b / c; 
        if (a * b + c > max) max = a * b + c;
        if (a * b - c > max) max = a * b - c;
        if (a / b * c > max) max = a / b * c;
        if (a / b + c > max) max = a / b + c;
        if (a / b - c > max) max = a / b - c;
        if (a + b * c > max) max = a + b * c;
        if (a + b / c > max) max = a + b / c;
        if (a + b - c > max) max = a + b - c;
        if (a - b * c > max) max = a - b * c;
        if (a - b / c > max) max = a - b / c;
        if (a - b + c > max) max = a - b + c;
        
        int n = 0; 
        if (a * b / c == max) n += 1;
        if (a * b + c == max) n += 1;
        if (a * b - c == max) n += 1;
        if (a / b * c == max) n += 1;
        if (a / b - c == max) n += 1;
        if (a / b + c == max) n += 1;
        if (a + b * c == max) n += 1;
        if (a + b / c == max) n += 1;
        if (a + b - c == max) n += 1;
        if (a - b * c == max) n += 1;
        if (a - b / c == max) n += 1;
        if (a - b + c == max) n += 1;

        System.out.printf("%.3f", max);
        System.out.println();
        System.out.println(n);

        scan.close();
    }
}