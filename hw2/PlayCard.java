package hw2;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class PlayCard {
  
  public static int playRandom(MatchCardGame g){
    int score = 0;
 
    int max = g.n;        //the maxium int that can be selected by the AI
    int r, r2 = 0;
    boolean flip1 = false;
    boolean flip2 = false;
    Random rand = new Random();
    Random rand2 = new Random();
  
    boolean over = g.gameOver();    //checks to see if the game is over
    //have the AI keep playing as long as game is not over
    while(!over){ 
      over = g.gameOver();
      //generate random integers to try to flip
      r = rand.nextInt(max);
      r2 = rand2.nextInt(max);
      
      //loop for the first flip in a turn 
      while(flip1 == false){
        flip1 = g.flip(r);        //flip1 turns true if the randomly generated card was successfully flipped
        if(!flip1)                //if the flip was unsuccessful, generate another number
          r = rand.nextInt(max);
      }
      //loop for the second flip 
      while(flip2 == false){
        flip2 = g.flip(r2);
        if(!flip2)
          r2 = rand2.nextInt(max);
      }
      //after 2 successful flips, check to see if the cards matched
      boolean match = g.wasMatch();
      if(!match){
        g.flipMismatch();       //if they didn't match, call flipMisMatch()
      }
      flip1 = flip2 = false;    //reset flip1 & 2 to be flipped agian 
      if(g.gameOver()){               //if the game is over, return the total flips made 
        score = g.getFlips();
        return score;
      }
    }
    return score;                     //return the total flips 
  }

  // private static ArrayList<Character> memory;


  public static int playGood(MatchCardGame g){
    int size = g.n;
    int score = 0; 
    
    //random play varaibles
    int r = 0; 
    int r2 = 0;
    
    //smart pick for pairs 
    int p1 = 0;
    int p2 = 0;

    //matching pair for odd flip
    int goodChoice;
    boolean flip1 = false;
    boolean flip2 = false;
    Random rand = new Random();
    //AI has the ability to store values into memory 
    // ArrayList<Character> memory = new ArraryList<>();
    
    char [] memory = new char[size];
    int[] knownCards = new int[size];
    for(int k : knownCards){
      knownCards[k] = -k;
    }
    int loopTracker = 0;
    int turn = 0;
    boolean over = g.gameOver();
    boolean unknown = false;
    boolean evenTurn = ((turn % 2) == 0);
    do{
      //random play
      unknown = flip1;
      while(!flip1 ){  //&& evenTurn
        if(turn == 0){
          r = 0;
          unknown = true;
        }
        while(!unknown){
          r = rand.nextInt(size);
          for(int x : knownCards){
            if(knownCards[x] == r){
              unknown = false;
              if(!unknown)
                r = rand.nextInt(size);
            }
          }
            unknown = true;
       }

        flip1 = g.flip(r);
        if(flip1){                
          char c = g.prevoiusFlipIdentity();      //get the char value of the flipped card
          memory[r] = c;                    //add the char into memory 
          knownCards[r] = r;
          turn++;
        }
        else if(flip1 == false && unknown == true)
          r = rand.nextInt(size);
      }

      unknown = flip2;
      evenTurn = ((turn % 2) == 0);
      while(!flip2 ){  //&& !evenTurn
          while(!unknown){
            r2 = rand.nextInt(size);
            for(int x : knownCards){
              if(knownCards[x] == r2){
                unknown = false;
                if(!unknown)
                  r2 = rand.nextInt(size);
              }
            }
              unknown = true;
          }

          flip2 = g.flip(r2);
          if(flip2){
            char d = g.prevoiusFlipIdentity();
            memory[r2] = d;
            knownCards[r2] = r2;
            turn++;
          }
          else if(flip2 == false && unknown == true)
            r2 = rand.nextInt(size);
          else
            flip1 = false;  
      }

      boolean match = g.wasMatch();
      if(!match){                 //if it wasn't a match, flip the cards over 
        g.flipMismatch();
      }
      else{                     //otherwise, remove the stored cards in memory 
        memory[r] = '\0';
        memory[r2] = '\0';
      }

      flip1 = flip2 = false;        //reset flips 

      //onto the second iteration 
      //even turns, see if there are any cards in memory, otherwise, choose new unkown caards
      boolean pairFound = false;
      boolean matchFound = false;
      evenTurn = ((turn % 2) == 0);
      if(evenTurn){
        // char t = memory;
          for(int i = 0; i < size;  i++){
            if(knownCards[i] != 0 ){ //memory[i]!= '\0'
              int a = i;
              char target = memory[a];
              for(int q = 1; q < size; q++){
                if(memory[q] == target && memory[q]!= '\0'){
                  p1 = r =a;
                  p2 = r2 = q;
                  pairFound= true;
                }
              }
            }
          }
          if(pairFound){
            g.flip(p1);
            g.flip(p2);
            match = g.wasMatch();
            if(!match){
              g.flipMismatch();
            }
            else{
              flip1 = false;
            }
         }
          flip1 = false;
      }
      //odd turn
      else{
        char target = g.prevoiusFlipIdentity();
        for(int i = 0; i<size; i++){
          char c = memory[i];
          if(i != r && target == c){
            p2 = r2 = i;
            matchFound = true;
          }
        }
        if(matchFound&& g.flip(p2)== true)    //&& g.flip(p2)== true
        {
          g.flip(p2);
          match = g.wasMatch();
          if(!match){
            g.flipMismatch();
            turn++;
          }
          else{
            flip2 = false;
          }
        }
        flip1= false;
      }

      if(g.gameOver()){
        score = g.getFlips();
        return score;
      }
      else{
        evenTurn = (g.getFlips() %2 == 0);
        if(evenTurn == true){
          flip1 = false;
        }
         if(evenTurn == false){
          flip2 = false;
        }
      }
      
    }while(!over);
    return score;
}

public static double randomMC(int N){
  double average = 0;
  int times = N;
  int size = 32;
  int[] score = new int[times];
  double[] total = new double[times];
  MatchCardGame g = new MatchCardGame(size);
  for(int k = 0; k < times; k++){
    score[k] =playRandom(g);
    total[k] = Double.valueOf(score[k]);
  }
  Double d = 0.0;
  for(int k = 0; k< score.length; k++){
    d += Double.valueOf(total[k]);
    average += d;
  }
  
  return average/N;
}

public static double goodMC(int N){
  double average = 0;
  int times = N;
  int size = 32;
  int[] score = new int[times];
  double[] total = new double[times];
  MatchCardGame g = new MatchCardGame(size);
  for(int k = 0; k < times; k++){
    score[k] =playGood(g);
    total[k] = Double.valueOf(score[k]);
  }
  Double d = 0.0;
  for(int k = 0; k< score.length; k++){
    d += Double.valueOf(total[k]);
    average += d;
  }
  
  return average/N;
}
  public static void main(String[] args) {
    //set up reader to take inputs
    // java.util.Scanner reader = new java.util.Scanner (System.in);
    
    int n = 12; //game size
    // MatchCardGame g1 = new MatchCardGame(n);
    // //g1.shuffleCards();
    
    // while(!g1.gameOver()) {
    //   //print board status
    //   System.out.println(g1.boardToString());
      
    //   //ask for a card to flip until we get a valid one
    //   System.out.println("Which card to play?");
    //   while(!g1.flip(reader.nextInt())) {}
      
    //   //print board status
    //   System.out.println(g1.boardToString());
      
    //   //ask for a card to flip until we get a valid one
    //   while(!g1.flip(reader.nextInt())) {}
      
    //   //say whether the 2 cards were a match
    //   if(g1.wasMatch()) {
    //     System.out.println("Was a match!");
    //   } else {
    //     //print board to show mismatched cards
    //     System.out.println(g1.boardToString());		
    //     System.out.println("Was not a match.");
    //     //flip back the mismatched cards
    //     g1.flipMismatch();
    //   }
    // }
    
    // Report the score
    // System.out.println("The game took " + g1.getFlips() + " flips.");
    
    //Using the AIs
    int count;
    MatchCardGame g2 = new MatchCardGame(n);
    //g2.shuffleCards();
    count = playRandom(g2);
    System.out.println("The bad AI took " + count + " flips.");
    MatchCardGame g3 = new MatchCardGame(n);
    //g3.shuffleCards();
    count = playGood(g3);
    System.out.println("The good AI took " + count + " flips.");
    
    // Using MCs
    int N = 1000;
    System.out.println("The bad AI took " + randomMC(N) + " flips on average.");
    System.out.println("The good AI took " + goodMC(N) + " flips on average.");
  }
}