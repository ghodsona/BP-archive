import java.util.Scanner;

public class E2_5 {
    public static boolean is_in_meydan(int x_guys,
                                       int y_guys,
                                       int x_negahban,
                                       int y_negahban, 
                                       int r_negahban) {

        double distance = Math.sqrt((x_guys-x_negahban)*(x_guys-x_negahban) +
                                    (y_guys-y_negahban)*(y_guys-y_negahban) ); 

        if (distance > r_negahban) return false; 
        else return true;         
    }

    public static boolean canArrive (int mpx, int mpy, int sx, int sy) {
        while (sx > 0 && sy > 0) {
            if (sx > sy) {
                sx -= sy; 
            }
            else {
                sy -= sx; 
            }

            if ((mpx == sx) && (mpy == sy)) return true;  
        }
        return false; 
    }

    //public static 

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        int mpx, mpy, sx, sy, n; 
        mpx = scan.nextInt();
        mpy = scan.nextInt();
        sx = scan.nextInt();
        sy = scan.nextInt();
        n = scan.nextInt();
        int[][] doshman = new int[n][3];

        for (int i = 0; i<n; i++){
            doshman[i][0] = scan.nextInt(); // x doshman i-th
            doshman[i][1] = scan.nextInt(); // y doshman i-th
            doshman[i][2] = scan.nextInt(); // r doshman i-th
        }


        if ((sx == mpx) && (sy == mpy)) {
            boolean totallyInMaydan = false;
            for (int i = 0; i<n; i++) {
                totallyInMaydan = totallyInMaydan || is_in_meydan(mpx, mpy, doshman[i][0], doshman[i][1], doshman[i][2]);
            }
            if (!totallyInMaydan) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
        else if (canArrive (mpx, mpy, sx, sy)) {
            for (int i = 0; i<n; i++) {
                if ( is_in_meydan(mpx, mpy, doshman[i][0], doshman[i][1], doshman[i][2]) ||
                     is_in_meydan(sx, sy, doshman[i][0], doshman[i][1], doshman[i][2]) ) {
                        System.out.println("NO");
                        break;
                }
            }

            boolean isIn = false; 
            while (sx > 0 && sy > 0) {
                isIn = false; 

                if (sx > sy) {
                    sx -= sy; 
                }
                else {
                    sy -= sx; 
                }

                for (int i = 0; i<n; i++) {
                    if (is_in_meydan(sx, sy, doshman[i][0], doshman[i][1], doshman[i][2])) {
                        isIn = true; 
                        System.out.println("NO");
                        break; 
                    }
                }

                if (isIn) {
                    break;
                }
            }

            if (!isIn) {
                System.out.println("YES");
            }

        }
        else System.out.println("NO");
        
        scan.close(); 
    }
}