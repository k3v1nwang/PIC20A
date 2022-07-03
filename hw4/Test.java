package hw4;
import java.util.Scanner;
import hw4.CFPlayer;
import hw4.RandomAI;
import hw4.KevinAI;
import hw4.ConsoleCF;
//import hw4.GUICF;


public class Test {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int gameMode = reader.nextInt();

        if (gameMode == 1) {
            new GUICF(new KevinAI());
        } else if (gameMode == 2) {
            if (gameMode == 2) {
                CFPlayer ai1 = new KevinAI();
                CFPlayer ai2 = new RandomAI();
                int n = 10000;
                int winCount = 0;
                for (int i = 0; i < n; i++) {
                    ConsoleCF game = new ConsoleCF(ai1, ai2);
                    game.playOut();
                if(game.getWinner() == ai1.getName())
                    winCount++;
                }
                System.out.println(((double) winCount) / n);
                System.out.print(((double) winCount)/((double) n)*100 + "%"); System.out.print(" of the time.");
            } else {
                ConsoleCF game = new ConsoleCF(new KevinAI());
                game.playOut();
                System.out.println(game.getWinner() + " has won.");

            }
//        CFPlayer ai = new RandomAI();
//        ConsoleCF g1 = new ConsoleCF(ai);
//        g1.printBoard();
//        System.out.println(g1.isRedTurn());
//        g1.playOut();
//        g1.getWinner();
//        CFGame g1 = new CFGame();
//        while(!g1.isGameOver()) {
//            Scanner cin = new Scanner(System.in);
//
//            System.out.println("Enter Int: ");
//            int in = cin.nextInt();
//            if(g1.play(in)){
//                g1.placeChip(in);
//            }
//            else
//            {
//                System.out.println("Not valid");
//            }
//
//        }
//        System.out.println(g1.winner());
//        CFPlayer ai1 = new KevinAI();
//        CFPlayer ai2 = new RandomAI();
//        int n = 1000;
//        int winCount = 0;
//        for (int i=0; i<n; i++) {
//            ConsoleCF game = new ConsoleCF(ai1, ai2); game.playOut();
//            if (game.getWinner() == ai1.getName())
//                winCount++; }
//        System.out.print(ai1.getName() + " wins "); System.out.print(((double) winCount)/((double) n)*100 + "%"); System.out.print(" of the time.");

        }
    }
}