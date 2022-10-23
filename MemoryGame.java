import java.util.Random;
import java.util.Scanner;

public class MemoryGame {
    public static void main(String[] args) {
        int r1, r2, c1, c2;
        int turn = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("How big of a board (Enter an even number)?");
        MemoryGame myGame = new MemoryGame(sc.nextInt());
        myGame.printBoard();
        do{
            System.out.println("Turn: " + turn);
            System.out.println("Enter first row");
            r1 = sc.nextInt();
            if (!(myGame.validSelection(r1))){
                while(!(myGame.validSelection(r1))){
                    System.out.println("invalid selection");
                    System.out.println("Enter first row");
                    r1 = sc.nextInt();
                }
            }
            System.out.println("Enter first column");
            c1 = sc.nextInt();
            if (!(myGame.validSelection(c1))) {
                while(!(myGame.validSelection(c1))){
                    System.out.println("invalid selection");
                    System.out.println("Enter first column");
                    c1 = sc.nextInt();
                }
            }
            myGame.showPosition(r1, c1);
            myGame.printBoard();
            System.out.println("Enter second row");
            r2 = sc.nextInt();
            if (!(myGame.validSelection(r2))){
                while(!(myGame.validSelection(r2))){
                    System.out.println("invalid selection");
                    System.out.println("Enter second column");
                    r2 = sc.nextInt();
                }
            }
            System.out.println("Enter second column");
            c2 = sc.nextInt();
            if (!(myGame.validSelection(c2)) || (r2 == r1 && c2 == c1)) {
                while(!(myGame.validSelection(c2)) || (r2 == r1 && c2 == c1)){
                    System.out.println("invalid selection");
                    System.out.println("Enter second column");
                    c2 = sc.nextInt();
                }
            }
            myGame.showPosition(r2, c2);
            myGame.printBoard();

            if(!(myGame.match(r1, c1, r2, c2))){
                myGame.hidePosition(r1, c1);
                myGame.hidePosition(r2, c2);
            }
            System.out.print("\n\n\n");
            myGame.printBoard();
            turn++;
        }while(!(myGame.allRevealed()));
        System.out.println("");
        System.out.println("Congratulations you beat the game in " + turn + " tries!!!");
    }

    MemoryGame(int dimension){
        this.size = dimension;
        this.board = new char[dimension][dimension];
        this.booleanBoard = new boolean[dimension][dimension];
        this.fillBoard();
    }
    int size;
    static Random rand = new Random();
    int rand_int1, rand_int2;

    private char[][] board;
    private boolean[][] booleanBoard;

    void fillBoard() {
        int count1,count2 = 0;
        char myChar = 'A';
        for(int i = 0; i<(size*size)/2; i++){
            count1 = 0;
            count2 = 0;
            while(count1 == 0) {
                rand_int1 = rand.nextInt(this.size);
                rand_int2 = rand.nextInt(this.size);
                if (board[rand_int1][rand_int2] == '\u0000') {
                    board[rand_int1][rand_int2] = myChar;
                    count1+=1;
                }
            }
            while(count2 == 0) {
                rand_int1 = rand.nextInt(this.size);
                rand_int2 = rand.nextInt(this.size);
                if (board[rand_int1][rand_int2] == '\u0000') {
                    board[rand_int1][rand_int2] = myChar;
                    count2+=1;
                }
            }
            myChar++;
        }
    }

    void printBoard(){
        System.out.print("  ");
        for(int k = 0; k<size; k++){
            System.out.print(" " + k + " ");
        }
        System.out.println("");
        System.out.print("  --");
        for(int m = 0; m<size; m++){
            System.out.print("--");
        }
        System.out.println("");

        for(int i = 0; i<size; i++){
            System.out.print(i + "|");
            for(int j = 0; j<size; j++){
                if(booleanBoard[i][j]){
                    System.out.print(" " + board[i][j] + " ");
                }else{
                    System.out.print(" + ");
                }
            }
            System.out.println("");
        }
    }

    void showPosition(int row, int col){
        booleanBoard[row][col] = true;
    }

    void hidePosition(int row, int col){
        booleanBoard[row][col] = false;
    }

    boolean allRevealed() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!booleanBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean validSelection(int sel){
        if(sel >= this.size || sel < 0){
            return false;
        }
        return true;
    }

    boolean match(int r1, int c1, int r2, int c2){
        if(board[r1][c1] == board[r2][c2]){
            return true;
        }else{
            return false;
        }
    }

}
