package q9;
import java.util.*;

public class Test {
    public static void main(String [] args) {
        ArrayList<String> str_arr = new ArrayList <>();
        //add Strings into ArrayList
        str_arr.add("mmjmjbmmm");
        str_arr.add("kgkbjjbbgbbjb");
        str_arr.add("bjggjggkjmkmj");
        str_arr.add("bbmmjmb");
        //start writing code here ...
        //order : k=a , m=b , g=c , j=d , b=e
        System.out.println("Original Order: ");
        for(String i : str_arr)
            System.out.println(i);
        //dict_order adds to the arraylist the changed values after Order() is called
        ArrayList<String> dict_order = new ArrayList<>();
        for(int k = 0; k < str_arr.size(); k++){
            dict_order.add(k, Order(str_arr.get(k)));
        }
        //order the list in alphabetical order
        Collections.sort(dict_order);       //Collections.sort sorts the human language equivalent on the passed in list

        ArrayList<String> last_step = new ArrayList<>();
        for(int k = 0; k < dict_order.size(); k++){
            last_step.add(k, OrderBack(dict_order.get(k)));
        }
        //sets the original list back to the now sorted version
        for(int k = 0; k < str_arr.size(); k++){
            str_arr.set(k, last_step.get(k));
        }
        System.out.println("Here is the re-order: ");
        for(String i : str_arr){ System.out.println(i); }
    }

    public static String Order(String word){
        String result = "";
        String toChange = word;
        for(int i = 0; i < word.length(); i++){
            char dictPos = toChange.charAt(i);
            switch(dictPos){
                case 'k':
                    result+= "a";
                    break;
                case 'm':
                    result+= "b";
                    break;
                case 'g':
                    result+= "c";
                    break;
                case 'j':
                    result+= "d";
                    break;
                case 'b':
                    result+= "e";
                    break;
            }
        }
        return result;
    }

    public static String OrderBack(String word) {
        String result = "";
        String toChange = word;
        for (int i = 0; i < word.length(); i++) {
            char dictPos = toChange.charAt(i);
            switch (dictPos) {
                case 'a':
                    result += "k";
                    break;
                case 'b':
                    result += "m";
                    break;
                case 'c':
                    result += "g";
                    break;
                case 'd':
                    result += "j";
                    break;
                case 'e':
                    result += "b";
                    break;
            }
        }
        return result;
    }

}

