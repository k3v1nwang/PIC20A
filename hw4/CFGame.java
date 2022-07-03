package hw4;

public class CFGame {
    //state[i][j]= 0 means the i,j slot is empty
    //state[i][j]= 1 means the i,j slot has red
    //state[i][j]=-1 means the i,j slot has black
    private final int[][] state;
    private boolean isRedTurn;

    static final int w = 7;         //width (col)
    static final int h = 6;         //height (row)

    private boolean over;
    private int winID;
    int inputCol;

    public CFGame(){
        //state = int[col][row]    [x][y]   [0][0] upper left
        state = new int[w][h];
        for (int i=0; i<w; i++)
            for (int j=0; j<h; j++)
                state[i][j] = 0;
        isRedTurn = true; //red goes first
        over = false;
    }

    //copy constructor
//    public CFGame(CFGame g, int[][] boardState){
//        int[][] copy = new int[w][h];
//        for(int i = 0; i < h; i++){
//            for(int j = 0; j< w; j++){
//               copy = boardState.clone();
//            }
//        }
//        isRedTurn = true;
//        over = false;
//    }

    public int[][] getState() {
        int[][] ret_arr = new int[w][h];
        for (int i=0; i<w; i++)
            for (int j=0; j<h; j++)
                ret_arr[i][j] = state[i][j];
        return ret_arr;
    }

    public void printBoard(){
        int[][]board = getState();
        for(int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.print('\n');
        }
    }
    public boolean isRedTurn() {
        return isRedTurn;
    }

    public boolean play(int column) {
        //the game is indexed 1-7 but the board is 0-6 for columns
        //must be within index and the top row must be empty
        return (0<= column && column <= w && state[column][0]==0);
    }

    public void placeChip (int c){
       try {
           if (play(c)) {
               for (int i = h - 1; i >= 0; i--) {
                   if (state[c][i] == 0)
                       if (isRedTurn()) {
                           state[c][i] = 1;        //red
//                           System.out.println("Red Chip placed");
                           isRedTurn = false;
                           break;
                       } else {
                           state[c][i] = -1;       //black
//                           System.out.println("black Chip placed");
                           isRedTurn = true;
                           break;
                       }

               }
//               System.out.println("The chip was placed in col :" + c);
//               printBoard();
           }
//           else {
//               System.out.println("no");
//           }
    }
       catch(ArrayIndexOutOfBoundsException e){
           System.out.println("invalid");
       }

    }
    //return the point if it is inbounds 0 otherwise
    public int p(int x, int y){
        int board[][] = getState();
        return (x<0|| y< 0|| y>h-1|| x>w-1)? 0: board[x][y];
    }

//    finds the next vacant space
    int nextSpace (int x){
        for(int y = w-1; y >=0; y--) {
            if (state[x-1][y] == 0) return y;
        }
        return -1;
    }

    public boolean isDraw(){
        for(int i = 0; i < h; i++){
            for(int j = 0; j< w; j++){
                if(state[j][i] == 0)
                    return false;        //returns false upon finding vacancy
            }
        }
        return true;

    }

    public boolean isGameOver() {

       //horizontal win
        for(int y= 0; y < h-1; y++){
            for(int x = 0; x < w-1; x++){
                if(p(x,y)!= 0 && p(x,y) == p(x+1, y) && p(x,y) == p(x+2, y) && p(x,y) == p(x+3, y)) {
                    winID = p(x,y);
                    over = true;
                    return true;
                }
            }
        }

        //vertical win, return value of player with 4 in a row
        for(int y = 0; y < h-1; y++){
            for(int x = 0; x < w-1; x++) {
                if (p(x, y) != 0 && p(x, y) == p(x, y + 1) && p(x, y) == p(x, y + 2) && p(x, y) == p(x, y + 3)) {
                    winID = p(x, y);
                    over = true;
                    return true;
                }
            }

        }

        //diagonal win, positive direction and negative direction d, will switch from -1 to +1
        for(int y = h-1; y >=0; y--){
            for(int x = w-1; x >=0; x--){
                for(int d = -1; d <= 1; d+=2){
                    if(p(x,y) != 0 && p(x, y) == p(x+1*d, y+1)&& p(x, y) == p(x+2*d, y+2)&& p(x, y) == p(x+3*d, y+3)) {
                        winID = p(x,y);
                        over = true;
                        return true;
                    }
                }
            }
        }

        //check for all spots filled
        for(int i = 0; i < h; i++){
            for(int j = 0; j< w; j++){
                if(state[j][i] == 0)
                    return false;        //returns false upon finding vacancy
            }
        }
        over = true;
       return true;
    }

    boolean isOver(){
        return over;
    }
//    private void finishTurn() {
//        if (isDraw()){
//            over = true;
//        }
//        else if (isGameOver())
//            over = true;
//        else
//            isRedTurn = isRedTurn? false:true;
//    }

    public int winner() {
        //return 1 redWin -1 BlackWin 0 draw

        if(isGameOver()){
            if(winID == 1){
                System.out.println("The winner is: RED");
            }
            else if(winID == -1)
                System.out.println("the winner is: Black");
            else
                System.out.println("draw");
        }

        //return 0 if no wins found and the game is a draw
        return winID;
    }
}