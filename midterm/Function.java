package midterm;
import java.lang.Math;
import java.util.Scanner;

public class Function{
    
    public static float fn(float x){
        double result = 0;

        double a =  Math.pow(x, 4);     //x^4
        double b = Math.sqrt((Math.cos(x)) + ((8.0/3.0) * x));       //sqrt(cos(x) + 8/3(x))
        double c = Math.pow(x, 2) + 1;                   //x^2 + 1

        result = (a - b)/c;
        return (float)result;
    }
    
    public static void main (String[] args){
        float x = 0;
        boolean check = false;
        while(!check){
            System.out.println("Enter a positive value: ");
            Scanner cin = new Scanner(System.in);
            x = cin.nextFloat();
            if (x > 0){
                check = true;
            }
        }
        System.out.println("f(" + x + ") = " + fn(x));
    }
}

