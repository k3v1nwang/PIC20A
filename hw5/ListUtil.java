package hw5;
import java.util.*;
import java.util.function.Predicate;


public class ListUtil {

    // merge returns an ArrayList<E> that contains all elements of c1 and c2. You will find the addAll method of List<E> useful.
    public static <E> ArrayList<E> merge(Collection<? extends E> c1, Collection<? extends E> c2) {
        ArrayList<E> merged_list = new ArrayList<E>();
        merged_list.addAll(c1);
        merged_list.addAll(c2);

        return merged_list;
    }
    //select returns an ArrayList<E> that contains all elements of coll for which pred.test(...) evaluates to true.
    public static <E> ArrayList<E> select(Collection<? extends E> coll, Predicate<? super E> pred) {
        ArrayList<E> pos_entries = new ArrayList<E>();
        //if the value evaluates to true, add to the list of positive entries
        for (E s : coll) {
            if (pred.test(s))
                pos_entries.add(s);
        }
        return pos_entries;
    }
}


//this code below also works but was trimmed down to be more readible in the above implementaiton of select.
/*
    //select returns an ArrayList<E> that contains all elements of coll for which pred.test(...) evaluates to true.
    public static <E> ArrayList<E> select(Collection<? extends E> coll, Predicate<? super E> pred){
        ArrayList<E> pos_list = new ArrayList<E>();

        Pred condition = new Pred();
        pos_list.addAll(coll);
        for(int i = 0; i < pos_list.size(); i++){
            int k = find(pos_list,condition);
            if(k>= 0){
                pos_list.remove(k);
            }
        }
        return pos_list;

    }

    //returns the index of the element that does not satisfy the condition pred.test()
    public static <E> int find(ArrayList<E> coll, Predicate<Number> predicate){
        for(int i = 0; i < coll.size(); i++){
            E element = coll.get(i);
            if(!predicate.test((Number) element))
                return i;
        }
        return -1;
    }*/




