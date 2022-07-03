package midterm;
import java.lang.Math;
 
public  class DiceRoll {

    public static int roll(){ 
        int count = 0;
        boolean check = false;

        while(!check){
            int dice1 = (int) (Math.random() * 6) +1;   
            int dice2 = (int) (Math.random() * 6) + 1; 

            int sum = dice1 + dice2;
            count++;
            if(sum == 7){
                check= true;
            }
        }
        return count;
    }

    public static double MonteCarlo(int N) {
        int trials = N;
        int total =0;

        for(int k = 0; k < trials; k++){
            total += roll();
        }
        double average = (double)total/(double) trials;
        return average;
    }

    public static void main(String[] args){
        int N = 1000000;
        System.out.println(MonteCarlo(N));
    }
}



//  public static void main(String[] args) {
//     int nyear;
//     int millenium = 3000;
//     int years = 0;
//     System.out.println("Please enter the current year: ");
//     Scanner cin = new Scanner(System.in);
//     nyear = cin.nextInt();

//     while (nyear != millenium)
//     {
//         nyear++;
//         years++;
//     }

//     System.out.println("Another " +
//     years + " years to the millenium."); 

// }