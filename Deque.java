import java.util.NoSuchElementException;
import java.util.Iterator;
    
public class Deque<Item> implements Iterable<Item> {
    
    private Node first, last;
    private int N;
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    
    public Deque() {                           // construct an empty deque
        first = null;
        last = null;
        N = 0;
    }
   
   public boolean isEmpty() {                 // is the deque empty?
       return N == 0;
   }
   
   public int size(){                        // return the number of items on the deque
       return N;
   }
   public void addFirst(Item item) {          // add the item to the front
       if (item == null) throw new NullPointerException();
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       first.next = oldfirst;
       if (isEmpty()) {
           last = first;
           first.next = null;
       }
       else {
           oldfirst.previous = first;
       }
       N++;
      
   }
   public void addLast(Item item) {          // add the item to the end
       if (item == null) throw new NullPointerException();
       if (isEmpty()) {
           first = last;
           last.previous = null;
       }
       else {
            Node oldlast = last;
            Node last = new Node();
            last.item = item;
            oldlast.next = last;
            last.previous = oldlast;

       }
       N++;
   }
   public Item removeFirst() {                // remove and return the item from the front
       if(isEmpty()) { throw new NoSuchElementException(); }
       Item item = first.item;
       first = first.next;
       first.previous = null;
       N--;
       return item;
   }
   public Item removeLast() {                 // remove and return the item from the end
       if(isEmpty()) throw new NoSuchElementException();
       Item item = last.item;
       last = last.previous;
       last.next = null;
       N--;
       return item;
   }
   
   public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
       return new ListIterator();
   }
   
   private class ListIterator implements Iterator<Item> {
       
       private Node current = first;
       public boolean hasNext() { return current != null; }
       public void remove() { throw new UnsupportedOperationException();  }

       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
       }
           
   }
   public static void main(String[] args){   // unit testing
     
 }
   
}