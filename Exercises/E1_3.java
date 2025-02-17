import java.util.Scanner; 

public class E1_3 {
    public static void main (String[] args) {  
        
        Scanner scan = new Scanner(System.in); 
        int a = scan.nextInt(); 
        int b = scan.nextInt(); 
        int c = scan.nextInt(); 

        if (a+b>c && b+c>a && a+c>b) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
        
        scan.close();
    }
}