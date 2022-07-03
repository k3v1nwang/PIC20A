package hw5;

import java.math.BigInteger;
import java.util.*;

public class MetaCollection<E> extends AbstractCollection<E> {
    // Keep references to added collections in this list
    private final ArrayList<Collection<E>> collectionList = new ArrayList<>();

    @SafeVarargs            //this is put here because warnings about unchecked varags used to initialize MetaCollection. Read online that this annotation means that we only care about the elements in array and not the type of array
    public MetaCollection(Collection<E> ... c_arr){
        for(Collection<E> o : c_arr) {
          collectionList.add(o);
        }
    }
       /*for(int i = 0; i< collectionList.size(); i++) {
           System.out.println("collectionList.get(" + i + "):" + collectionList.get(i));
           System.out.println("collectionList.getClass(" + i + "):" + collectionList.get(i).getClass());
       }
                collectionList.get(0):[1, 2, 3]
                collectionList.get(1):[10]
                collectionList.get(2):[1, 2, 3]*/


    public void addCollection(Collection<E> coll) {
        collectionList.add(coll);
    }

    @Override
    public Iterator<E> iterator() {
        return new JoinedIter();
    }

    private class JoinedIter implements Iterator<E> {
        private int itrCounter = 0;
        private int currIndex = 0;
        Iterator<E> it = collectionList.get(currIndex).iterator();

        @Override
        public boolean hasNext(){
            return (itrCounter<size());         //&& collectionList.get(itrCounter) != null
        }

        @Override
        public E next(){
            if(!it.hasNext()){
                currIndex++;
                it = collectionList.get(currIndex).iterator();
            }
            itrCounter++;
            return it.next();
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public int size() {
        int result = 0;
        for(Collection<E> sublist : collectionList){
            result += sublist.size();
        }
        return result;
    }

}
