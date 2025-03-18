import java.util.Scanner;

public class SudokuGame {

    public static void DisplayGrid(int[][] layout) {
        for (int j = 0; j < layout.length; j++) {
            for (int i = 0; i < layout.length; i++) {
                System.out.print(" - -");
            }
            System.out.println();
            for (int i = 0; i < layout.length + 1; i++) {
                System.out.print("|");
                if (i < layout.length) {
                    if (layout[j][i] == 0) {
                        System.out.print("   ");
                    } else {
                        System.out.print(String.format("%3d", layout[j][i]));
                    }
                }
            }
            System.out.println();
        }
        for (int i = 0; i < layout.length; i++) {
            System.out.print(" - -");
        }
        System.out.println();
    }

    public static int[][] getLayout(int size) {
        int[][] s = new int[size][size];
        int num = 1;
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                s[a][b] = num++;
                if (num > size) {
                    num = 1;
                }
            }
            num = (num + 1) % (size + 1);
            if (num == 0) num = 1;
        }
        return s;
    }

    public static int[][] createPuzzle(int size) {
        int[][] s = getLayout(size);
        int target = (s.length * s.length) / 3;
        int cnt = 0;
        int pos1 = 0, pos2 = 0;
        for (int i = 0; i < target; i++) {
            pos1 = i / size;
            pos2 = i % size;
            s[pos1][pos2] = 0;
        }
        return s;
    }

    public static boolean checkSolution(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            boolean[] seen = new boolean[puzzle.length + 1];
            for (int j = 0; j < puzzle[i].length; j++) {
                int value = puzzle[i][j];
                if (value != 0) {
                    if (value < 1 || value > puzzle.length || seen[value]) {
                        return false;
                    }
                    seen[value] = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void playSudoku(int[][] puzzle) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            DisplayGrid(puzzle);
            boolean emptyFound = false;
            for (int i = 0; i < puzzle.length; i++) {
                for (int j = 0; j < puzzle[i].length; j++) {
                    if (puzzle[i][j] == 0) {
                        emptyFound = true;
                        System.out.println("Enter number (1 to " + puzzle.length + ") for position [" + (i + 1) + "][" + (j + 1) + "]: ");
                        int num = sc.nextInt();
                        if (num >= 1 && num <= puzzle.length) {
                            puzzle[i][j] = num;
                        } else {
                            System.out.println("Invalid input. Try again.");
                        }
                        break;
                    }
                }
                if (emptyFound) break;
            }
            if (!emptyFound) {
                System.out.println("Puzzle completed!");
                DisplayGrid(puzzle);
                if (checkSolution(puzzle)) {
                    System.out.println("Congratulations! The solution is correct.");
                } else {
                    System.out.println("Oops! The solution is incorrect.");
                }
                break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        int[][] puzzle = createPuzzle(5);
        playSudoku(puzzle);
    }
}
