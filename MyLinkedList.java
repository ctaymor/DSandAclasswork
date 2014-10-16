// Caroline Taymor
import java.util.Random;
/*
 * Running Time analysis:
 * contains: theta(n)
 * addFirst: theta(1)
 * addLast: theta(n)
 * removeFirst: theta(1)
 * removeLast: theta(1)
 * getFirst: theta(1)
 * getLast: theta(n) (but could have an implementation which is theta(1),
 * see comment above method
 */
/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType extends Comparable <? super AnyType>> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        clear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void clear( )
    {
        beginMarker = new Node<AnyType>( null, null, null );
        endMarker = new Node<AnyType>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<AnyType>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
    }   
    /**
     * Adds an item to the beginning of the list
     * @param x the value to add
     */
    public void addFirst(AnyType x)
    {
      add(0, x);  
    }
    
    /**
     * Add the item at the end of the list
     * @param x the value to add
     */
    public void addLast(AnyType x)
    {
        add(x);
    }
    
    /**
     * Gets the maximum value in the list
     * @return the maximum value
     */
    public AnyType max()
    {
        return (isEmpty()? null : findMax().data);
    }
    
    /**
     * Returns the node which has the maximum value
     * @return the node with the maximum value
     */
    private Node<AnyType> findMax()
    { 
        Node<AnyType> maxNode = beginMarker.next;
        Node<AnyType> counter = beginMarker.next;
        for (int i = 0; i < theSize-1; i++)
        {
            counter = counter.next;
            if (counter.data.compareTo(maxNode.data) > 0)
                maxNode = counter;
        }
        return maxNode;
        
    }
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
    
    /**
     * Returns the first item in the list.
     * @return the first item in the list.
     */
    public AnyType getFirst()
    {
     if(isEmpty())
         return null;
     return get(0);   
    }
    
    /*
     * This could be more efficiently written with
     * return get(endMarker.prev);
     * but I'm not sure that is what you are asking for. That would be 
     * theta(1) instead of the theta(n) of the code below.
     */
    /**
     * Returns the last item in the list.
     * @return last item in list.
     */
    public AnyType getLast()
    {
        if(isEmpty())
            return null;
        return get(size() - 1);
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the first element in the list
     * @return the element which was removed
     */
    public AnyType removeFirst()
    {
        if (isEmpty())
            return null;
        return remove(beginMarker.next);
    }
    
    /**
     * Removes the last element in the list
     * @return the element which was removed
     */
    /* Note: using only public methods, this could be written
     * return remove(size() - 1);
     * but this would be far less efficient, being theta(n)
     * instead of the theta(1) which the method I implemented is.
     */
    public AnyType removeLast()
    {
        if (isEmpty())
            return null;
        return remove(endMarker.prev);
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        
        return p.data;
    }
    /**
     * A method which checks to see if the parameterized value is anywhere in the list
     * @param x the value to search the list for
     * @return true if the list contains x, false otherwise
     */
    public boolean contains(AnyType x)
    {
        Node<AnyType> counter = beginMarker;
        for (int i = 0; i < theSize; i++)
        {
            counter = counter.next;
            if (counter.data.equals(x))
                return true;
        }
        return false;
    }
    

    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );
        
        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );
    
        return new String( sb );
    }
    
    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        
     System.out.println("Test a) an empty list");
     MyLinkedList<Integer> myList = new MyLinkedList<Integer>( );
     System.out.println("myList is " + myList);
     System.out.println("myList contains 3: " + myList.contains(3));
     myList.addFirst(3);
     System.out.println("empty myList after addFirst(3): " + myList);
     myList.clear();
     myList.addLast(5);
     System.out.println("empty myList after addLast(5): " + myList);
     myList.clear();
     myList.removeFirst();
     System.out.println("empty myList after removeFirst():" + myList);
     myList.clear();
     myList.removeLast();
     System.out.println("empty myList after removeLast():" + myList);
     myList.clear();
     System.out.println("first element in the empty list is: " + myList.getFirst());
     System.out.println("last element in the empty list is: " + myList.getLast());
     
     System.out.println("b) Testing [3, 5, 4, 25]");
     myList.clear();
     myList.add(3);
     myList.add(5);
     myList.add(4);
     myList.add(25);
     System.out.println("myList is " + myList);
     System.out.println("myList contains 3: " + myList.contains(3));
     System.out.println("myList contains 7: " + myList.contains(7));
     System.out.println("myList contains 25: " + myList.contains(25));
     myList.addFirst(1);
     System.out.println("After addFirst(3), myList is now " + myList);
     myList.addLast(2);
     System.out.println("After addLast(2), myList is now " + myList);
     myList.removeFirst();
     System.out.println("After removeFirst(), myList is now " + myList);
     myList.removeLast();
     System.out.println("After removeLast(), myList is now " + myList);
     System.out.println("first element in myList is: " + myList.getFirst());
     System.out.println("last element in myList is: " + myList.getLast());

    }
}
