import java.util.Scanner; 

public class E2_4 {

    static char[][] cell = new char[50][50]; 
    static char[][] finalcell = new char[50][50]; 

    public static char type(int i, int j, int row, int clm) {
        if ((i==0) && (j==0)) {
            return 'a'; 
        }
        else if ((i==0) && (j>0) && (j<clm-1)) {
            return 'b'; 
        }
        else if ((i==0) && (j==clm-1)) {
            return 'c'; 
        }
        else if ((j==0) && (i>0) && (i<row-1)) {
            return 'd'; 
        }
        else if ((i>0) && (i<row-1) && (j==clm-1)) {
            return 'f'; 
        }
        else if ((i==row-1) && (j==0)) {
            return 'g'; 
        }
        else if ((i==row-1) && (j>0) && (j<clm-1)) {
            return 'h'; 
        }
        else if ((i==row-1) && (j==clm-1)) {
            return 'i'; 
        }
        else {
            return 'e'; 
        }
    }
    
    public static int isAlive(char status) {
        if (status == 'O') return 1; 
        else return 0; 
    }

    public static int AliveNeighbors(int i, int j, char t){

        if (t == 'a') {
            return (isAlive(cell[0][1]) +
                    isAlive(cell[1][0]) +
                    isAlive(cell[1][1]));
        }

        else if (t == 'b') {
            return (isAlive(cell[0][j-1]) +
                    isAlive(cell[0][j+1]) +
                    isAlive(cell[1][j-1]) +
                    isAlive(cell[1][j]) +
                    isAlive(cell[1][j+1]));
        }
        
        else if (t == 'c') {
            return (isAlive(cell[0][j-1]) +
                    isAlive(cell[1][j-1]) +
                    isAlive(cell[1][j]));
        }

        else if (t == 'd') {
            return (isAlive(cell[i-1][0]) +
                    isAlive(cell[i-1][1]) +
                    isAlive(cell[i][1]) +
                    isAlive(cell[i+1][0]) +
                    isAlive(cell[i+1][1]));            
        }

        else if (t == 'e') {
            return (isAlive(cell[i-1][j-1]) +
                    isAlive(cell[i-1][j]) +
                    isAlive(cell[i-1][j+1]) +
                    isAlive(cell[i][j-1]) +
                    isAlive(cell[i][j+1]) +
                    isAlive(cell[i+1][j-1]) +
                    isAlive(cell[i+1][j]) +
                    isAlive(cell[i+1][j+1]));              
        }

        else if (t == 'f') {
            return (isAlive(cell[i-1][j-1]) +
                    isAlive(cell[i-1][j]) +
                    isAlive(cell[i][j-1]) +
                    isAlive(cell[i+1][j-1]) +
                    isAlive(cell[i+1][j]));               
        }

        else if (t == 'g') {
            return (isAlive(cell[i-1][0]) +
                    isAlive(cell[i-1][1]) +
                    isAlive(cell[i][1]));           
        }

        else if (t == 'h') {
            return (isAlive(cell[i-1][j-1]) +
                    isAlive(cell[i-1][j]) +
                    isAlive(cell[i-1][j+1]) +
                    isAlive(cell[i][j-1]) +
                    isAlive(cell[i][j+1]));
        }

        else {
            return (isAlive(cell[i-1][j-1]) +
                    isAlive(cell[i-1][j]) +
                    isAlive(cell[i][j-1])); 
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 

        int clm = scan.nextInt(); 
        int row = scan.nextInt();
        int g = scan.nextInt(); 
        
        for (int i = 0; i<row; i++){ 
            for (int j = 0; j<clm; j++) {
                cell[i][j] = scan.next().charAt(0);
            }
        }

        for (int a = 0; a<g; a++) {
            for (int i = 0; i<row; i++){ 
                for (int j = 0; j<clm; j++) {
                    if (cell[i][j] == 'O') {
                        if (AliveNeighbors(i, j, type(i, j, row, clm)) < 2) {
                            finalcell[i][j] = 'X'; 
                        }
                        else if ((AliveNeighbors(i, j, type(i, j, row, clm)) == 2) || (AliveNeighbors(i, j, type(i, j, row, clm)) == 3)) {
                            finalcell[i][j] = 'O';
                        }
                        else if (AliveNeighbors(i, j, type(i, j, row, clm)) > 3) {
                            finalcell[i][j] = 'X';
                        }
                    }
                    else {
                        if (AliveNeighbors(i, j, type(i, j, row, clm)) == 3) {
                            finalcell[i][j] = 'O';
                        }
                        else {
                            finalcell[i][j] = 'X';
                        }
                    }
                }
            }
    
            for (int i = 0; i<row; i++){ 
                for (int j = 0; j<clm; j++) {
                    cell[i][j] = finalcell[i][j]; 
                }
            }
        }            

        for (int i = 0; i<row; i++){ 
            for (int j = 0; j<clm; j++) {
                System.out.print(cell[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }        
        
        scan.close(); 
    }
    
}