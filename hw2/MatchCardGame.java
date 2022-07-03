package hw2;
import java.util.Random;

public class MatchCardGame{
    //game size helper functions and variables 
    public final int n;
    private int gameSize;
    private int getSize(){ return gameSize;}

    //allowable letters to be assigned to cards
    private final String letters  = "abcdefghijklmnopqrstuvwxyz";
    
    private String board = "";                  //string representation of board
    private String getBoard(){return board;}
    
    //game data 
    private char[] Cards;                   //array of card face letters 
    private boolean[] canFlip;              //array that records what cards can be flipped
    private int[] cardPos;                  //array of card indexes 
    private char[] shuffled;

    //variables used to track progress of each turn 
    private char prevFlip;
    private char currFlip;
    private int prevFlipPos;
    private int currFlipPos;

    private boolean flipMade; 
    private int turnFlipCount;
    private void resetFlipCount(){
        turnFlipCount =0;
    }

    //the total number of successful flips made 
    private int gameFlipCount;

    public MatchCardGame(int n){
        //number of playing cards (assumed to be multiple of 4)
        this.n=n;
        gameFlipCount = 0;          //initialize the gameFlipCount to 0 when constructed 
        gameSize = n;

        //initialize gamedata arrays 
        Cards = new char[gameSize]; 
        canFlip = new boolean[gameSize];
        cardPos = new int[gameSize];
        

        int alpha = 0;          //starts at the first letter of the alphabet 
        //assign card values 
        for (int k = 0; k < gameSize; ){            //loop until all cards have a value
            int counter = 0;
            while(counter < 4){                        //each letter is assinged 4 times
                Cards[k] = letters.charAt(alpha);
                board += Cards[k];                      
                canFlip[k] = true;                  //each card assinged can be flipped
                cardPos[k] = k;                     //the position is the index of assignment 
                k++;
                counter++;
            }
            alpha++;             //increment to the next letter of alphabet 
        }
        
        //after each card has a value, shuffle the position     
        // shuffleCards();    
    }

    //helper function to show either the card index or card face value
    private String createCard (int index){
        int i = index;
        String card;
        if (canFlip[i] == true){       //show the card index if the card has not been flipped
            card = "[" + i + "] ";
        }
        else{                           //if the card has been flipped, show card face
            card = "{" + Cards[i] + "} ";
        }
        return card;                    //return the card representation 
    }

    // returns an int between the specified bounds max is exclusive 
    private int getRandNum(int min, int max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }

    public void shuffleCards(){
        //shuffles cards using Fisher-Yates shuffle. 
        //array of shuffled cards
        shuffled = new char[gameSize];
        String source = getBoard();
        
        //copy the board values into the shuffled array first
        for(int k = 0; k < source.length(); k++){
            shuffled[k] = source.charAt(k);
        }
        
        int range = gameSize - 1;
        String tempString = "";
        int[] usedValues = new int[gameSize];
        
        boolean valid = true;

        while(tempString.length()!= source.length()){
            //generate random num
            int r = getRandNum(0, range);
            //check to see if the number has already been generated
            for(int k = 0; k < gameSize; k++){
                if(usedValues[k] == r){
                    valid = false;
                }
            }
            if(valid){
                tempString += source.charAt(r);     //add to tempstring the char at index r
                usedValues[r] = r;                  //add r to used values so we don't use it again 
            }

        }

        //with the shuffled array complete, update the game array to correspond to shuffled deck
        for (int k = 0; k < gameSize; k++){
            shuffled[k] =  tempString.charAt(k);
        }

    }

    public String boardToString(){
        int flips = getFlips();
        String display = "Total Flips: " + flips + '\n';
        
        //create grid so cards are presented nicely 
        int x = getSize();
        int rows = 4;
        int col = x/rows;
        int pos = 0;
       
        for (int k = 0; k < rows; k++){         
            for (int j = 0; j < col; j++){
                display += createCard(pos);         //createCard() takes in the index and checks if it is faceup or down
                pos++;
            }
            display += '\n';
        }

        // //other arrays (hide these after)
        // for(int k = 0; k< x; k++){
        //     System.out.print("(" + cardPos[k] + ")" + canFlip[k] + "{" +Cards[k] + "} ");
        // }
        // System.out.print('\n'+ "Shuffled cards: "+'\n');
        // int spos = 0;
        // for (int k = 0; k < rows; k++){         
        //     for (int j = 0; j < col; j++){
        //         System.out.print( "{" +shuffled[spos] + "} ");
        //         spos++;
        //     }
        //     System.out.print('\n');
        // }
        // String cardsPicked = "1st: " + prevFlip + '\n' + "2nd: " + currFlip;
        // display += cardsPicked;
        return display;
    } 

    public boolean flip(int i){
        //make turnFlipCount reset to 0 if the game just started
        if(gameFlipCount== 0){          
            turnFlipCount = 0;
        }
        

        flipMade = false;         //picking a valid card turns flipMade true
        //loop if flip has not been made and turnflipCount is not >2          
        while(!flipMade ){      //&& turnFlipCount <2 
            if (i >= getSize() || i < 0){          //return false if the card selected is out of index
                // System.out.print("Cannot flip out-of-index card!" + '\n');
                // break;
                return false;
            }
            else if (canFlip[i] == false){  //return false if card already face up
                // System.out.print("Card is already face up!"+ '\n');
                // break;
                return false;
            }
            //if the card is not already faceup or is within index, proceed
            else{
                gameFlipCount++;            //iterate flip counter of the game (score)
                canFlip[i] = false;          //change flip status of the card at index to false
                if(turnFlipCount == 1){        //assign currFlip to the card flipped if there already is a prevFlip   
                    currFlip = Cards[i];
                    currFlipPos = cardPos[i];
                    turnFlipCount++;            //incrementing past 2 will break out of the loop
                }
                else if(turnFlipCount == 0){        //if it is the first flip of the turn, assign prevFlip to the card that was flipped
                    prevFlip = Cards[i];             //call the first flip prevFlip
                    prevFlipPos = cardPos[i];
                    turnFlipCount++;
                }
                flipMade = true;            
            }
        }
        return flipMade;
    }

    public boolean wasMatch(){
        //return true if prev pair is a math and returns false otherwise
        boolean match = false;
        
               //after an even number of flips, check that the two cards picked are the same 
            char c1 = prevFlip;
            char c2 = currFlip;
            int pos1 = prevFlipPos;
            int pos2 = currFlipPos;
            if(c1 == c2){
                match = true;
                canFlip[pos1] = false;
                canFlip[pos2] = false;
            }
            
            resetFlipCount();           //helper function that resets turnFlipCount back to 0
        
        return match;
    }

    public char prevoiusFlipIdentity(){
        char id = '\0';
        if(turnFlipCount == 1){         //if 1 flip has been made, return the first flip
            id = prevFlip;
        }
        if(turnFlipCount ==2){           //if 2 flips have been made, return currFlip
            id = currFlip;
        }
        return id;              //return the card that was last flipped
    }

    public void flipMismatch(){
        int prev = prevFlipPos;
        int curr = currFlipPos;
        //the cards that were flipped can be flipped again 
        canFlip[prev] = true;
        canFlip[curr] = true;
    }

    public boolean gameOver(){
        boolean check = false; 
        for (int k = 0; k < getSize(); k++){
            if(canFlip[k] == true){              //return false if any cards can still be flipped    
                return false;
            }
        }
        //if the loop completes then the check has passed for all cards and the game is over
        check = true;
        return check;
    }

    public int getFlips(){
        return gameFlipCount;
    }

}