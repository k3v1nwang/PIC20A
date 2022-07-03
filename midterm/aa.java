package midterm;
import java.util.Scanner;
public class aa {

    public static void main(String[] args) {
        int nyear;
        int millenium = 3000;
        int years = 0;
        System.out.println("Please enter the current year: ");
        Scanner cin = new Scanner(System.in);
        nyear = cin.nextInt();
    
        while (nyear != millenium)
        {
            nyear++;
            years++;
        }
    
        System.out.println("Another " +
        years + " years to the millenium."); 
    
    }
}