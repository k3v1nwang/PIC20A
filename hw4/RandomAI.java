package hw4;
import java.util.Random;
import hw4.CFPlayer;
import hw4.CFGame;

public class RandomAI implements  CFPlayer{
    private int[][] randBoard;
    boolean check = false;
    @Override
    public int nextMove(CFGame g){
       Random rand = new Random();
       int randCol = rand.nextInt(7);
       randBoard = g.getState();

       while(!check){
           for(int y = 0; y<6; y++){
               if(randBoard[randCol][y] == 0){
                   check = true;
                   return randCol;
               }
           }
           randCol = rand.nextInt(7);
       }
       return randCol;
    }
    @Override
    public String getName() {
        return "Random AI";
    }
}
