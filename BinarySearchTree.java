//Caroline Taymor
//CS124: Hw 9 problem 1 and 2
// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }
    
    /**
     * Return the number of nodes in the tree;
     * @return the number of nodes 
     */
    public int nodes()
    {
     if (isEmpty())
         return 0;
     return nodes(root);
    }
    
    /**
     * Return the number of leaves in the tree;
     * @return the number of leaves
     */
    public int leaves()
    {
     if (isEmpty())
         return 0;
     return leaves(root);
    }
    
    /**
     * Return the number of full nodes in the tree;
     * @return the number of full nodes
     */
    public int fullNodes()
    {
     if (isEmpty())
         return 0;
     return fullNodes(root);
    }
    
    
    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Prints the tree in pre-order
     */
    public void printTreePreOrder()
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTreePreOrder( root );
    }
    
    /**
     * Prints the tree in post-order
     */
    public void printTreePostOrder()
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTreePostOrder( root );
    }
    /**
     * Prints all the elements in the tree whose value is x with 
     * k1 <= x <= k2.
     * @param k1 the first element to print between
     * @param k2 the second element to print between
     */
    public void printBetween(AnyType k1, AnyType k2)
    {
        printBetween(root, k1, k2);
    }
    
    /**
     * Find an element of the tree
     */
    public AnyType find(AnyType e)
    {
        BinaryNode<AnyType> walker;
        walker = root;
        while ((walker != null) && (!walker.element.equals(e))){
            if (walker.element.compareTo(e)>0)
                walker = walker.left;
            else
                walker = walker.right;
        }
        if (walker == null)
            return null;
        return e;
    }
    
//    /**
//     * Internal method to insert into a subtree.
//     * @param x the item to insert.
//     * @param t the node that roots the subtree.
//     * @return the new root of the subtree.
//     */
//    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
//    {
//        if( t == null )
//            return new BinaryNode<AnyType>( x, null, null );
//        
//        int compareResult = x.compareTo( t.element );
//            
//        if( compareResult < 0 )
//            t.left = insert( x, t.left );
//        else if( compareResult > 0 )
//            t.right = insert( x, t.right );
//        else
//            ;  // Duplicate; do nothing
//        return t;
//    }
    
    // Note: This preserves the public method the same whether or not the insert
    // is recursive, so that the API is not changed, although it is not strictly
    // needed to pass in the root.
    /**
     * 
     * @param x
     * @param t
     * @return
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t)
    {
        BinaryNode<AnyType> walker = t;
        if (t == null)
            t = new BinaryNode<AnyType>(x);
        while ((walker != null))
        {
            if (walker.element.compareTo(x)>0) {
                if (walker.left != null) {
                    walker = walker.left;
                } else {
                    walker.left = new BinaryNode<AnyType>(x);
                    break;
                }
            } else if (walker.element.compareTo(x)<0) {
                if (walker.right != null) {
                    walker = walker.right;
                } else {
                    walker.right = new BinaryNode<AnyType>(x);
                    break;
                }
            } else {
                break;
            } 
        }
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in preorder.
     */
    private void printTreePreOrder( BinaryNode<AnyType> t ) {
        if ( t!= null ) {
            System.out.println( t.element );
            printTreePreOrder( t.left );
            printTreePreOrder( t.right );
        }
    }
    
    /**
     * Internal method to print a subtree in postorder.
     */
    private void printTreePostOrder( BinaryNode<AnyType> t )
    {
        if ( t != null )
        {
            printTreePostOrder( t.left );
            printTreePostOrder( t.right );
            System.out.println( t.element );
        }
    }
    
    /**
     * Internal method to print the elements in the tree whose value 
     * is x with k1 <= x <= k2.
     * @param t the node that roots the subtree
     * @param k1 the first element to print between
     * @param k2 the second element to print between
     */
    private void printBetween(BinaryNode<AnyType> t, AnyType k1, AnyType k2)
    {
        if (t != null) {
            if (t.element.compareTo(k1)<0) {
                printBetween(t.left, k1, k2);
                printBetween()
            }
        }
    }
    
    /**
     * Internal method to count nodes in the tree.
     * @param t the node that roots the subtree.
     * @return the number of nodes in the subtree.
     */
    private int nodes( BinaryNode<AnyType> t )
    {
        if (t != null)
            return nodes(t.left) + nodes(t.right) + 1;
        return 0;
    }
    
    /**
     * Internal method to count leaves in the tree.
     * @param t the node that roots the subtree.
     * @return the number of leaves in the subtree.
     */
    private int leaves( BinaryNode<AnyType> t )
    {
        if (t != null) {
            if ((t.left != null) && (t.right != null))
                return leaves(t.left) + leaves(t.right);
            else if (t.left != null)
                return leaves(t.left);
            else if (t.right != null)
                return leaves(t.right);
            else
                return 1;
        }
        return 0;
    }
    
    /**
     * Internal method to count nodes in the tree.
     * @param t the node that roots the subtree.
     * @return the number of nodes in the subtree.
     */
    private int fullNodes( BinaryNode<AnyType> t )
    {
        if (t != null) {
            if ((t.left != null) && (t.right != null))
                return 1 + fullNodes(t.left) + fullNodes(t.right);
            else if (t.left != null)
                return fullNodes(t.left);
            else if (t.right != null)
                return fullNodes(t.right);
        }
        return 0;
    }
    
    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<Integer>( );
        t.insert(20);
        t.insert(2);
        t.insert(77);
        t.insert(23);
        t.insert(92);
        t.insert(44);
        t.insert(29);
        t.insert(5);
        t.insert(17);
        t.insert(3);
        System.out.println("Caroline Taymor: CS124 HW 9");
        System.out.println("Problem 1:");
        System.out.println("Tree in order with non-recursive insert is:");
        t.printTree();
        System.out.println("Problem 2a:");
        System.out.println("The number of nodes in the tree is: " + t.nodes());
        System.out.println("Runtime of nodes() is theta n.");
        System.out.println("Problem 2b:");
        System.out.println("The number of leaves in the tree is: " + t.leaves());
        System.out.println("Runtime of leaves() is theta n.");
        System.out.println("Problem 2c:");
        System.out.println("The number of full nodes in the tree is: " + t.fullNodes());
        System.out.println("Runtime of fullNodes() is theta n.");
        System.out.println("Problem 3/4.37:");
        System.out.println("The elements of the tree between 3 an 35 are: " + t.printBetween(3, 35));
        System.out.println("The elements of the tree between 3 an 35 are: " + t.printBetween(-4, -2));
        System.out.println("The elements of the tree between 3 an 35 are: " + t.printBetween(104, 1023));
        System.out.println("The elements of the tree between 3 an 35 are: " + t.printBetween(77, 92));
        System.out.println("The elements of the tree between 3 an 35 are: " + t.printBetween(23, 105));
        System.out.println("The elements of the tree between 3 an 35 are: " + t.printBetween(-4, 7));


    }
}
