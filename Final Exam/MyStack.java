package finalexam;

import java.util.*;

class MyStack<E> {
    private ArrayList<E>  stack;
    private int size;

    //constructor
    public MyStack(){
       stack = new ArrayList<>();
       size = 0;
    }

    public void push_back(E e){
       stack.add(size,e);
       size++;
    }

    public E pop_back(){
        //throws EmptyStackException
        if(size == 0){ throw new EmptyStackException(); }
        else {
             size--;
             E last_ind_elem = stack.get(size);
             stack.remove(last_ind_elem);
             return last_ind_elem;
        }
    }

    public E peek(){
        if(size == 0){ return null; }
        else {
            int pos = size-1;
            return stack.get(pos);
        }
    }

}