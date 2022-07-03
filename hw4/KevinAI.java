package hw4;
import java.util.Random;

public class KevinAI extends CFGame implements CFPlayer{
    private int[][] kevinBoard;
    @Override
    public int nextMove(CFGame g) {
        CFPlayer op = new RandomAI();
//        int checkMove = op.nextMove(g);
//        int tryCol;
        int[][] board = g.getState();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++)
                if (board[x][y] == 0 && g.play(x) == g.isGameOver()) {
                    return x;           //return x if the game will end by opponent's next move
                }
        }
//        return op.nextMove(g);
        Random rand = new Random();
        int randCol = rand.nextInt(7);
        boolean check = false;
        while (!check) {
            kevinBoard = g.getState();
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 5; y++)
                    if (kevinBoard[x][y] == 0) {        //otherwise return empty slot
                        return x;
                    }

            }
            if (kevinBoard[randCol][0] == 0) {          //if all else fails return a random col that is empty
                check = true;
                return randCol;
            }
            randCol = rand.nextInt(7);
        }

        return randCol;
    }
    @Override
    public String getName() {
        return "Kevin's AI";
    }
}
