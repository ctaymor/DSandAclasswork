// Caroline Taymor
import java.util.NoSuchElementException;

/**
 * 
 * @author Caroline Taymor
 * A Queue implemented with LinkedLists, written from scratch
 * (Java exception handling imported).
 * @param <AnyType> type of elements in list.
 */
public class QueueLi<AnyType> {
    // front of the queue, which is begining of the list
    public Node<AnyType> frontMarker;
    // back of the queue which is end of the list
    public Node<AnyType> back;

    // theta(1)
    /**
     * 0 param constructor for QueueLi which creates an empty queue.
     */
    public QueueLi() {
        frontMarker = new Node<AnyType>(null, null);
        back = frontMarker;
    }
    
    // theta(1)
    /**
     * Adds an element to the back of the queue.
     * @param x the element to add to the queue.
     */
    public void enqueue(AnyType x)
    {
     back.next = new Node<AnyType> (x, null);
     back = back.next;
    }
    
    // theta(1)
    /**
     * Removes and returns the front element of the queue.
     * @return the front element of the queue.
     */
    public AnyType dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        Node<AnyType> n = frontMarker.next;
        if (frontMarker.next.next == null)
            back = frontMarker;
        frontMarker.next = frontMarker.next.next;
        return n.data;
    }
    
    // theta(1)
    /**
     * Returns the element in the front of the queue.
     * @return the element at the front of the queue.
     */
    public AnyType getFront()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        return frontMarker.next.data;
    }
    
    // theta(1)
    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return frontMarker == back? true : false;
    }
    
    public class Node<AnyType>
    {
        public AnyType data;
        public Node<AnyType> next;
        public Node(AnyType d, Node<AnyType> n)
        {
            data = d;
            next = n;
        }
    }

    public static void main(String[] args) {
        QueueLi<Integer> q = new QueueLi<Integer>();
        System.out.println("Queue is empty: " + q.isEmpty());
        System.out.println("Enqueueing 3");
        q.enqueue(3);
        System.out.println("Queue is empty: " + q.isEmpty());
        System.out.println("Front of q is " + q.getFront());
        System.out.println("Enqueueing 7");
        q.enqueue(7);
        System.out.println("Front of q is still " + q.getFront());
        System.out.println("Dequeueing " + q.dequeue());
        System.out.println("Front of q is now " + q.getFront());
        System.out.println("Queue is empty: " + q.isEmpty());
        System.out.println("Front of q is still " + q.getFront());
        System.out.println("Dequeueing " + q.dequeue());
        System.out.println("Queue is empty: " + q.isEmpty());
        

    }

}
