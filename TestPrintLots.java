/* Caroline Taymor
 * Running Time: theta of n
 * */
import java.util.*;
/**
 * A class for the function and tests of  printLots on two lists.
 * For j in list p, printLots prints the jth element of list l.
 * @author Caroline Taymor
 *
 */
public class TestPrintLots {

    /**
     * For j in list p, printLots prints the jth element of list l.
     * @param l the list of elements which should be printed
     * @param p the list of positions in l to print
     */
    private static <AnyType> void printLots(List<AnyType> l, List<Integer> p) {
        Iterator<AnyType> itrl = l.iterator();
        Iterator<Integer> itrp = p.iterator();
        int counter = 0;
        StringBuilder sb = new StringBuilder("Vals in list1 in pos of list2: [");
        Integer pos;
        // sum from i = 0 to n of 1
        while (itrp.hasNext())
        {
            //constant
            pos = itrp.next();
            if (pos < 0)
                continue;
            
            while ((counter < pos) && itrl.hasNext())
            {
                counter++;
                itrl.next();
            }
            if (!itrl.hasNext()) {
                break;
            }
            // then counter == pos
            sb.append(itrl.next()).append(", ");
            counter++;
            
        }
        sb.append("]");
        System.out.println(sb);
        
    }

    // Sample testing
    // you need to provide additional testing samples including String objects
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        for (int i = 0; i < 20; i++)
            list1.add(i*10);
        System.out.println("List of values: " + list1);
        
        LinkedList<Integer> list2 = new LinkedList<Integer>();
        for (int i = -6; i < list1.size() + 10; i = i + 3)
            list2.add(i);
        System.out.println("List of positions: " + list2);
        
        printLots(list1, list2);
        printLots(list2, list1);
        
        LinkedList<String> list3 = new LinkedList<String>();
        for (char i = 'a'; i < 'l'; i++)
            list3.add(i + " ");
        System.out.println("List of values: " + list3);
        
        LinkedList<Integer> list4 = new LinkedList<Integer>();
        for (int i = -6; i < list3.size() + 10; i = i + 3)
            list4.add(i);
        System.out.println("List of positions: " + list4);
        
        printLots(list3, list4);

    }

}
