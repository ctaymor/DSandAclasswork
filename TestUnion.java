// Caroline Taymor
// Running time analysis: theta(m + n) = theta (n)

import java.util.*;
public class TestUnion
{
    public static <AnyType extends Comparable<AnyType>> List<AnyType>
        union (List<AnyType> list1, List<AnyType> list2)
    {
        Iterator<AnyType> itr1 = list1.iterator();
        Iterator<AnyType> itr2 = list2.iterator();
        //TODO: now union method is enforcing LinkedList not Array list, is this a problem??
        List<AnyType> results = new LinkedList<AnyType>();
        AnyType el1 = null;
        AnyType el2 = null;
        if (itr1.hasNext())
            el1 = itr1.next();
        if (itr2.hasNext())
            el2 = itr2.next();

        while (el1 != null && el2 != null) {
            if (el1.compareTo(el2) > 0 ) {
                results.add(el2);
                if (itr2.hasNext())
                    el2 = itr2.next();
                else
                    el2 = null;
            } else if (el1.compareTo(el2) < 0) {
                results.add(el1);
                if (itr1.hasNext())
                    el1 = itr1.next();
                else el1 = null;
            } else {
                results.add(el1);
                
                if (itr1.hasNext())
                    el1 = itr1.next();
                else
                    el1 = null;
                if (itr2.hasNext())
                    el2 = itr2.next();
                else
                    el2 = null;
            }
        }

        while (el1 != null) {
            results.add(el1);
            if (itr1.hasNext())
                el1 = itr1.next();
            else
                break;
        }
        while (el2 != null) {
            results.add(el2);
            if (itr2.hasNext())
                el2 = itr2.next();
            else
                break;
        }
        return results;
    }
    // sample testing
    // you need to provide additional testing samples including String objects
    public static void main(String[] args)
    {
        
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        for (int i = 0; i < 20; i++)
            list1.add(i);
        System.out.println("List 1: " + list1);
        LinkedList<Integer> list2 = new LinkedList<Integer>();
        for (int i = -6; i < list1.size() + 10; i = i + 3)
            list2.add(i);
        System.out.println("List 2: " + list2);
        List<Integer> resultList = union(list1, list2);
        System.out.println("Union is: " + resultList);
        resultList = union(list2, list1);
        System.out.println("Union is: " + resultList);
        
        LinkedList<String> list3 = new LinkedList<String>();
        for (char i = 'a'; i < 'e'; i++)
            list3.add(i + " ");
        System.out.println("List 3: " + list3);
        LinkedList<String> list4 = new LinkedList<String>();
        for (char i = 'b'; i < 'w'; i = (char) (i+2))
            list4.add(i+" ");
        System.out.println("List 4: " + list4);
        List<String> resultList2 = union(list3, list4);
        System.out.println("Union is: " + resultList2);
        resultList2 = union(list4, list3);
        System.out.println("Union is: " + resultList2);
    }
}