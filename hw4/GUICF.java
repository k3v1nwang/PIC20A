package hw4;
import hw4.ConsoleCF;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import java.util.Arrays;

import static java.lang.String.valueOf;

public class GUICF extends ConsoleCF{
    private GameBoard this_board;
    private int gameMode;

    //gui objects
    private JButton[] jButtons = new JButton[7];
    private JFrame jframe = new JFrame("Connect Four");
    private JPanel jpanel = new JPanel();
    private Container pane;

    //ai game button to start
    private JButton playButton = new JButton("Play");           //didn't work

    //gameplay
    private int inputColumn;
    //who won
    boolean p1Win = true;
    boolean p2Win = false;
    boolean drawGame = false;

    //random
    Random rand = new Random();

    public GUICF(CFPlayer ai) {
        super();
        //sets up and starts a human vs AI game where red is randomly decided
        this_board = new GameBoard();
        gameMode = 1;

        //initialize players
        p1 = new RandomAI();
        p2 = new KevinAI();
        if(getWhoFirst() == p1Red){
            p1 = new KevinAI();
            p2 = new RandomAI();
        }
        else{
            p1 = new RandomAI();
            p2 = new KevinAI();
        }
        //button pane and layout
        JPanel buttonPanel = new JPanel();
        LayoutManager buttonGrid = new GridLayout(7,7);
        buttonPanel.setLayout(buttonGrid);

        for(int i = 0; i <7; i++) {
            jButtons[i] = new JButton("\u2193"); //unicode for downward arrow
            buttonPanel.add(jButtons[i]); //add buttons to panel
        }
        pane.add(buttonPanel,BorderLayout.NORTH);
        pane.add(jpanel,BorderLayout.SOUTH);

        playOut();
        getWinner();

    }
    public GUICF(CFPlayer ai1, CFPlayer ai2) {
        //sets up and starts a human vs AI game where red is randomly decided
        super();
        this_board = new GameBoard();
        gameMode = 2;
        //set up play button to begin game
       getWhoFirst();
        if(getWhoFirst() == p1Red){
            p1 = new KevinAI();
            p2 = new RandomAI();
        }
        else{
            p1 = new RandomAI();
            p2 = new KevinAI();
        }
        playOut();
        getWinner();

    }

    private boolean playGUI(int c) {
        boolean comp = false;
        if(play(c)){
            int color;
            color = isRedTurn()? -1:1;
            placeChip(c);
            int [][] board = getState();
            for(int x = 0; x < w; x ++){
                for(int y = 0; y < h; y++){
                    board = getState();
                    comp = true;
                    break;
                }
            }
        }
        return comp;
    }

    private class GameBoard extends JPanel {
        private GameBoard() {
            // initialize empty board
            JFrame frame = new JFrame ("Connect Four");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);

            frame.setSize(640,640);
            Container pane = frame.getContentPane();

            JPanel panel = new JPanel();
            pane.add(panel);
            LayoutManager grid = new GridLayout(6, 7);
            panel.setLayout(grid);
            int[][] chips = new int[w][h];
            for(int x = 0; x < w; x++ ){
                for(int y = 0; y < h; y++){
                    chips[x][y] = 0;
                }
            }

            for (int i = 0; i < w*h; i++) {
                JPanel square = new JPanel();
                square.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(square);
            }

            int turn = 0;
            Scanner s = new Scanner(System.in);
            while (turn < w*h) {
                for(int x = 0; x < w; x++){
                    for(int y =0; y < h; y++){
                        chips[x][y] = p(x,y);
                    }
                }
            }
            pane.add(jpanel, BorderLayout.CENTER);
        }

    }
    //attempt was made to create lamba expression thru p(x,y) binOp function in CFGame.java
    private void paint(int x, int y, int color) {
        jpanel.removeAll();

        for ( x=0; x< w; x++) {
            for (y = 0; y < h; y++) {
                int [][]board= getState();
                paint(x,y, Integer.parseInt(valueOf(p(x,y))));
            }
        }

    }

    public void MP(MouseEvent e){
        int player;
        if(isRedTurn()){
             player = 1;
        }
        else{
             player = -1;
        }
        int x = e.getX()/100;
        int y = nextSpace(x);
        int[][] board = getState();
        if(y>= 0){
            board[x][y] = player;
        }

    }


    //ran out of time //

//    public int MP (MouseListener l){
//
//    }
//    public static void main(String[] args) {
//
//    }

}
