package hw4;
import java.util.Scanner;
import java.util.Random;

public class ConsoleCF extends CFGame {

    public CFPlayer p1;
    public CFPlayer p2;

    public boolean p1Red;
    private boolean p1Win = false;
    private boolean p2Win = false;
    private boolean drawGame = false;

    Random rand = new Random();
    Scanner cin = new Scanner(System.in);

    ConsoleCF(){
        super();
    }

    public CFPlayer getPlayer1() {
        return p1;
    }

    public CFPlayer getPlayer2(){
        return p2;
    }

    boolean getWhoFirst(){
        return p1Red = rand.nextBoolean();
    }

    //inner class
    private class HumanPlayer implements CFPlayer {
        @Override
        public int nextMove(CFGame g) {
            int[][] board = g.getState();
            g.printBoard();
            boolean check = false;
            while (!check) {  //if there is vacancy
                System.out.println("Enter Col:  ");
                inputCol = cin.nextInt();
                for (int i = 0; i < 6; i++) {
                    board = g.getState();
                    if (g.play(inputCol)) {
                        placeChip(inputCol);
                        check = true;
                    }
                    else {
                        System.out.println("Invalid input");
                        inputCol = cin.nextInt();
                    }
                }
            }
            return inputCol;


        }

        @Override
        public String getName() {
            return "Human Player";
        }
    }

    //sets up console game for player vs AI
    public ConsoleCF(CFPlayer ai) {
        p1 = this.new HumanPlayer();
        p2 = ai;

        p1Red = rand.nextBoolean();     //random assign who p1 is either human or ai
    }

    //set up console game for 2 AIs
    public ConsoleCF(CFPlayer ai1, CFPlayer ai2) {
        p1 = ai1;
        p2 = ai2;
        p1Red = rand.nextBoolean();
    }

    public void playOut() {     //swaa player order and play until gameOver
        while (!this.isGameOver()) {
            if (p1Red) {
                this.play(p1.nextMove(this));
                placeChip(p1.nextMove(this));
                if (this.isGameOver()) {
                    p1Win = true;
                    break;
                }
                this.play(p2.nextMove(this));
                placeChip(p2.nextMove(this));
                if (this.isGameOver()) {
                    p2Win = true;
                    break;
                }
            } else {
                this.play(p2.nextMove(this));
                placeChip(p2.nextMove(this));
                if (this.isGameOver()) {
                    p2Win = true;
                    break;
                }
                this.play(p1.nextMove(this));
                placeChip(p1.nextMove(this));
                if (this.isGameOver()) {
                    p1Win = true;
                    break;
                }
            }
        }
//        printBoard();
        if (p1Win)
            System.out.println(p1.getName() + " wins!");
        else if (p2Win)
            System.out.println(p2.getName() + " wins!");
        else
            System.out.println("Game Over! Draw");
        getWinner();
    }

    public String getWinner() {
        String winner = "";
        if (p1Win)
            winner = p1.getName();
        else if (p2Win)
            winner = p2.getName();
        else
            winner = "draw";
        return winner;
    }

}
