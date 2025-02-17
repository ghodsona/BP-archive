import java.util.*;

public class E5_4 {
    static int[][] soduko = new int[9][9]; 
    static int[][][] psble = new int[9][9][9]; 
    static boolean tmp = true;
    static boolean correct = true; 
    static boolean[][] canChange = new boolean[9][9]; 
    static int x_change = 9, y_change = 9; 

    public static int[] env(int row, int clm) {
        int[] ans = new int[2]; 
        ans[0] = (row / 3) * 3;
        ans[1] = (clm / 3) * 3; 
        return ans; 
    }

    public static int count(int[] array) {
        int answer = 0; 
        for (int i = 0; i < array.length; i++) {
            answer += array[i]; 
        }
        return answer;
    }

    public static boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (soduko[i][j] == 0) return false; 
            }
        }
        return true; 
    }

    public static void fill() { 
        tmp = false; 
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (soduko[i][j] != 0) continue;
                if (count(psble[i][j]) == 0) {
                    correct = false; 
                    return; 
                } else if (count(psble[i][j]) == 1) {
                    for (int k = 0; k < 9; k++) {
                        if ((psble[i][j][k] == 1) && (canChange[i][j])) {
                            soduko[i][j] = k + 1; 
                            canChange[i][j] = false; 
                            tmp = true; 
                            return;
                        }
                    }
                }
            }
        }
    }

    public static int[] possibles(int row, int clm) {
        if (soduko[row][clm] != 0) {
            return new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}; 
        }

        int[] answer = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int i = 0; i < 9; i++) {
            if (soduko[row][i] != 0) answer[soduko[row][i] - 1] = 0; 
            if (soduko[i][clm] != 0) answer[soduko[i][clm] - 1] = 0; 
        } 
        int[] dx = {0, 1, 2}; 
        int[] dy = {0, 1, 2}; 
        int x0 = env(row, clm)[0]; 
        int y0 = env(row, clm)[1]; 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = x0 + dx[i]; 
                int y = y0 + dy[j]; 
                if (soduko[x][y] != 0) answer[soduko[x][y] - 1] = 0; 
            }
        }
        return answer;
    }

    public static void output() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(soduko[i][j] + " "); 
            }
            System.out.println();
        }
    }

    public static void debugOutput() {
        System.out.println("Current Sudoku State:");
        output();
        System.out.println("Possible values for each cell:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print("[" + i + "," + j + "]: ");
                System.out.println(Arrays.toString(psble[i][j]));
            }
        }
        System.out.println("---------------");
    }

    public static void maybe() {
        int minOptions = 10; // حداقل تعداد احتمالات
        int chosenRow = -1, chosenCol = -1;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int countOptions = count(psble[i][j]);
                if (countOptions > 1 && countOptions < minOptions) {
                    minOptions = countOptions;
                    chosenRow = i;
                    chosenCol = j;
                }
            }
        }

        if (chosenRow != -1 && chosenCol != -1) {
            for (int k = 0; k < 9; k++) {
                if (psble[chosenRow][chosenCol][k] == 1) {
                    soduko[chosenRow][chosenCol] = k + 1;
                    x_change = chosenRow;
                    y_change = chosenCol;
                    return;
                }
            }
        }
    }

    public static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    public static boolean isValidSudoku() {
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[9];
            boolean[] colCheck = new boolean[9];
            boolean[] boxCheck = new boolean[9];

            for (int j = 0; j < 9; j++) {
                // بررسی ردیف
                if (soduko[i][j] != 0) {
                    if (rowCheck[soduko[i][j] - 1]) return false;
                    rowCheck[soduko[i][j] - 1] = true;
                }
                // بررسی ستون
                if (soduko[j][i] != 0) {
                    if (colCheck[soduko[j][i] - 1]) return false;
                    colCheck[soduko[j][i] - 1] = true;
                }
                // بررسی بلوک ۳×3
                int row = 3 * (i / 3) + j / 3;
                int col = 3 * (i % 3) + j % 3;
                if (soduko[row][col] != 0) {
                    if (boxCheck[soduko[row][col] - 1]) return false;
                    boxCheck[soduko[row][col] - 1] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        for (int i = 0; i < 9; i++) {
            Arrays.fill(canChange[i], true);
        }
        ArrayList<int[][]> versions = new ArrayList<>(); 

        for (int i = 0; i < 9; i++) {
            String tmp = scanner.nextLine(); 
            for (int j = 0; j < 9; j++) {
                char c = tmp.charAt(2 * j);
                if (c != '.') {
                    soduko[i][j] = c - '0';
                    canChange[i][j] = false;
                }
            }
        }
        scanner.close();

        int iterationCount = 0;
        while (!isSolved() && correct) {
            iterationCount++;
            if (iterationCount > 1000) {
                System.out.println("Too many iterations, stopping to prevent infinite loop.");
                break;
            }

            versions.add(deepCopy(soduko));
            boolean progress = false;

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    psble[i][j] = possibles(i, j);
                }
            }

            debugOutput();

            tmp = true;
            while (tmp) {
                fill();
                progress = progress || tmp;
                debugOutput();
            }

            if (!correct) {
                soduko = deepCopy(versions.get(versions.size() - 2));
                canChange[x_change][y_change] = false; 
                correct = true;
                System.out.println("Rolled back to previous version.");
                debugOutput();
            } else if (!progress) {
                maybe();
                debugOutput();
            }
        }

        if (correct) {
            output();
        } else {
            System.out.println("No solution exists.");
        }
    }
}
