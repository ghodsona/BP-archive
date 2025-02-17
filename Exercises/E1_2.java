import java.util.Scanner;

public class E1_2 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); // scanner object 
        int number = scan.nextInt(); // input a number

        if ((number % 2 == 1) || (number % 2 == -1)) {
            System.out.println("kousha moeini");            
        } else {
            System.out.println("mohammad javad gharegozlou");
        }
        
        scan.close();
    }
}
