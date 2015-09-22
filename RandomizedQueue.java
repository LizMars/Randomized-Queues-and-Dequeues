import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
   private int N = 0;
   private Item[] array;
    
   public RandomizedQueue() {                 // construct an empty randomized queue
        array = (Item[]) new Object[1];    
    }
   public boolean isEmpty() {                 // is the queue empty?
       return N == 0;
   }
   
   public int size() {                        // return the number of items on the queue
       return N;
   }
   private void resize(int capacity) {
       Item[] copy = (Item[]) new Object[capacity];
       for (int i = 0; i < N; i++) {
           copy [i] = array[i];
       }
       array = copy;
   }
   
   public void enqueue(Item item) {          // add the item
       if (item == null) throw new NullPointerException();
       
       if (N == array.length) resize(2*array.length);
       array[N++] = item; 
   }
   public Item dequeue() {                   // remove and return a random item
      if (N == 0) throw new NullPointerException();
      
      int random = StdRandom.uniform(N);
      Item item = array[random];
      
      if (random != N-1) array[random] = array [N - 1];
      array[N -1] = null;
      N--;
      
      if (N > 0 && N == array.length/4) resize(array.length/2);
      return item;
   }
   public Item sample() {                    // return (but do not remove) a random item
      if (N == 0) throw new NullPointerException();
      int random = StdRandom.uniform(N);
      return array[random];
   }
   
   public Iterator<Item> iterator() {        // return an independent iterator over items in random order
       return new RandomIterator();
   }
   
   private class RandomIterator implements Iterator<Item> {
       private Item[] copy = (Item[]) new Object[array.length];
       private int i;
       public RandomIterator() {
           i = N - 1;
           copy = array;
           
       }
           
       public boolean hasNext() { return i >= 0; }
       
       public void remove() { throw new UnsupportedOperationException();  }

       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           int random = StdRandom.uniform(N);
           Item item = copy[random];
           if (random != i) copy[random] = copy[i];
           copy[i] = null;
           i--;
           return item;
       }
           
   }
   public static void main(String[] args) {  // unit testing
   }
}