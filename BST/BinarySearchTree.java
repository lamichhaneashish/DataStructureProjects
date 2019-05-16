package axl173530;

/**
 * @author: Ashish Lamichhane
 * @version: 1.0
 * @Date: 10/06/18
 * @projectname: IA4_Question 1 and 2.
 */

import java.util.*;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry <T> left, right;

        public Entry(T x, Entry <T> left, Entry <T> right) {
            this.element = x;
            this.left = left;
            this.right = right;
        }
    }

    Entry <T> root;
    int size;
    Stack <Entry <T>> ancestors;          // stack to store the ancestors of "x" when x is being searched for.

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public static void main(String[] args) {
        BinarySearchTree <Integer> t = new BinarySearchTree <>();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int x = in.nextInt();
            if (x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if (x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for (int i = 0; i < t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            }
        }

    }

    /**
     * Finds if the entry that contains the element is in the tree and see if the element is the same.
     *
     * @param x : to see if x is in the BST.
     * @return true if the element is in the tree else false.
     */
    public boolean contains(T x) {
        Entry entry = find(x);
        return entry != null && entry.element == x;
    }

    /**
     * Find the element in the list and also maintains the stack of ancestors.
     *
     * @param x : element to be searched in the list.
     * @return : returns the entry where x is found or where the search ended.
     */
    private Entry find(T x) {
        ancestors = new Stack <>();
        ancestors.push(null);
        return find(root, x);
    }


    /**
     * Helper method for find.
     * If the value of x is smaller then value of entry t's element then search for the item in the left sub-tree.
     * Search for the x until there is no further element left in the left subtree and break from the while loop if null is reached.
     * if the value of x is greater than the value of entry t's element then search for the item in the right sub-tree.
     * Search until the there are no further element in the right sub tree and break from the while loop if null is reached.
     * At the end of the while loop, return the entry t.
     * Entry t is the entry where the x was found or where the search ended and x was not found.
     *
     * @param t : Entry to be compared with.
     * @param x : element to be searched.
     * @return : entry where x is found or where the search ended.
     */
// Helper method for find.
    private Entry find(Entry t, T x) {
        if (t == null || t.element == x) {
            return t;
        }
        while (true) {
            if (x.compareTo((T) t.element) < 0) {
                if (t.left == null) {   // no left subtree.
                    break;
                } else {
                    ancestors.push(t);  // adding visited nodes to stack of ancestors for x.
                    t = t.left;
                }
            } else if (x.compareTo((T) t.element) == 0) {
                break;
            } else {
                if (t.right == null) {  // no right sub-tree.
                    break;
                } else {
                    ancestors.push(t);
                    t = t.right;
                }
            }
        }
        return t;
    }

    /**
     * Search for the x in the tree. if x is found then return element of entry where the x was founded. If find returns the entry where the search ended but
     * x was not found then return the null value.
     *
     * @param x : element to be extracted from the tree.
     * @return : returns the element of entry t if x is found otherwise null.
     */
    public T get(T x) {
        Entry entry = find(x);
        return x.compareTo((T) entry.element) == 0 ? (T) entry.element : null;
    }

    /**
     * Adds element to the binary search tree.
     * If root is null then add element to the root of the tree.
     * else find the item to be added in the binary search tree.
     * if the item is found then duplicate elements can not be added to the tree.
     * if x is smaller than the element in entry returned by find then add new element to the left
     * if x is greater than the element in entry returned by find then add new element to the right
     *
     * @param x : element to be added to the tree.
     * @return : true if the add is successful else false.
     */
    public boolean add(T x) {
        if (root == null) {
            root = new Entry <>(x, null, null);
            size = 1;
            return true;
        } else {
            // LI : BST is not empty.
            Entry <T> entry = find(x);
            if (x.compareTo(entry.element) == 0) {
                System.out.println("Duplicate value rejected !!!");
                return false;
            } else if (x.compareTo(entry.element) < 0) {          // add new element to the left.
                entry.left = new Entry(x, null, null);
            } else {
                entry.right = new Entry(x, null, null);    // add new element to the right.
            }
            size++;
            return true;
        }
    }

    /**
     * Remove the element from the list. To remove the element from the list first the entry where the element is at.
     * If the entry is not found then return null.
     * If the entry is found then check the number of children of that entry.
     * If there is just one children then bypass that entry.
     * If there are two children then find the successor of the element and bypass the successor.
     *
     * @param x : element to be removed.
     * @return
     */
    public T remove(T x) {
        T result;
        Entry <T> entry = find(x);
        if (entry == null || entry.element != x) {
            return null;
        }
        result = entry.element;
        if (entry == root && entry.left == null & entry.right ==null ){
            root.element = null;
            size --;
        }else if (entry.left == null || entry.right == null) {  // Entry has only one child.
            bypass(entry);
            size--;
        } else {                                           // Entry has 2 children. Bypass the successor.
            ancestors.push(entry);
            Entry <T> minRight;
            minRight = find(entry.right, x);
            entry.element = minRight.element;
            bypass(minRight);
            size--;
        }
        return result;
    }


    /**
     * Helper method to bypass the element.
     *
     * @param t
     */
    private void bypass(Entry <T> t) {
        Entry <T> parent = ancestors.peek();         // parent of of successor.
        Entry <T> child = t.left == null ? t.right : t.left;  // if successor doesn't have left child then child is right entry else it is left entry.
        if (parent.left == t) {                   // if successor was its parent's left child then now child becomes the parent's left child.
            parent.left = child;
        } else {
            parent.right = child;               // if successor was parent's right child then now child becomes the parent's right child.
        }
    }


    /**
     * calls the helper method to find the minimum element of the tree.
     * @return : minimum element in the tree.
     */
    public T min() {
        return min(root);
    }

    /**
     * Helper method to find the minimum element in the tree.
     * Goto the leftmost element in the left sub-tree and return the element.
     * @param lEntry  : element to be checked for it is the left most ?
     * @return : element of the leftmost entry in the tree.
     */
    private T min(Entry<T> lEntry){
        if (lEntry.left == null){
            return lEntry.element;
        }else{
            return min(lEntry.left);            // recursive call to find the min.
        }
    }

    /**
     * calls the helper method to find the maximum element of the tree.
     * @return : maximum element in the tree.
     */
    public T max() {
        return max(root);
    }

    /**
     * Helper method to find the maximum element in the tree.
     * Goto the rightmost element in the right subtree and return the element.
     * @param rEntry : element to be checked for if it is the rightmost ?
     * @return : element of the rightmost entry in the tree.
     */
    private T max(Entry<T> rEntry){
        if (rEntry.right == null){
            return rEntry.element;
        }else{
            return max(rEntry.right);          // recursive call to find the max.
        }
    }

    /**
     * Converts the BST to an array. Uses the helper method : extractValues.
     * @return array with the elements of the tree traversed in-order.
     */

    public Comparable[] toArray() {
        Comparable[] arr = new Comparable[this.size];
        extractValues(this.root,arr,0);
        return arr;
    }

    /**
     * Helper method to extract values from the bst and put the values in array by doing in-order traversal.
     * A stack is created to store the elements in the BST and elements are visited in the level-order.
     * current is the entry field.
     * first if the root is not null push the root to the stack and
     * keep pushing element to the list unless the leftmost element is found and make the element current on every left move.
     * pop the current entry and add its value to the stack.
     * assign the value of current's right to current and repeat the process until the stack is emptied.
     * @param entry : contains the value of root.
     * @param arr   : array to hold the element of visited entry.
     * @param index : starting index of the arr - 0.
     */
    private void extractValues(Entry<T> entry, Comparable[] arr,int index){
        Stack<Entry<T>> tovisit = new Stack <>() ;
        Entry<T> current = entry;
        if (arr.length == 0){           // If there are no elements in the tree.
            return;
        }
        while (current != null || tovisit.size() >0){               // if end of stack is not reached or current is not null.
            while (current != null){
                tovisit.push(current);
                current = current.left;
            }
            current = tovisit.pop();
            arr[index++] = current.element;                         // assign current to be array element.
            current = current.right;
        }
    }



// Start of Optional problem 2


    /** Optional problem 2: Iterate elements in sorted order of keys
     Solve this problem without creating an array using in-order traversal (toArray()).
     */

    /**
     * Iterator for the BST. The iterator traverses the tree in In-Order.
     * Check the BSTIterator class for details.
     * @return : the instance of BSTIterator class.
     */
    public Iterator<T> iterator() {
        return new BSTIterator(this.root);
    }

    /**
     * Creates iterator for the BST that traverses the tree in In-order.
     */

    public class BSTIterator implements Iterator<T>{

        private Stack<Entry<T>> ancestors;          // stack to store ancestors of the current node.

        /**
         * Constructor for BSTIterator.
         * if the tree has root then push every left entries from the root to the ancestors stack.
         * @param root : root of the bst.
         */
        public BSTIterator(Entry<T> root){
            ancestors = new Stack<>();
            while (root !=null){
                ancestors.push(root);
                root = root.left;
            }
        }

        /**
         * if the ancestors stack is not empty then it has next element, else return null.
         * @return : if ancestors stack is not empty.
         */

        @Override
        public boolean hasNext() {
            return !ancestors.isEmpty();
        }

        /**
         * Returns the next element in the binary search tree. ( traversal is in-order).
         * if the top of the ancestor's stack (current) has right sub-tree then pop that entry and go to its leftmost entry if it exists.
         * if the left entry doesn't exists then return the value of its right entry else return the value of its leftmost entry.
         * @return
         */

        @Override
        public T next() {
            Entry<T> current = ancestors.pop();
            T value = current.element;
            if (current.right != null){         // if the current has right entry assign current to its right entry.
                current = current.right;
                value = current.element;
                while (current != null){        // go to the leftmost entry of the current entry if it exists.
                    ancestors.push(current);
                    current = current.left;
                    value = current.element;
                }
            }
            return value;                        // return either current element's value or if it only has right entry try to find the leftmost entry from the right entry
            // if found then return that leftmost entry's element else just return the right entry's element.
        }
    }

    // End of BSTIterator class.


    /**
     * Find the floor of the element x. Floor is the largest key in the tree that is no bigger than x. Return null if there is no such key.
     * find the element x in the tree. find returns the entry where the element was found or the entry where search ended. ancestors has the path taken by
     * find method. if the element of the founded entry is bigger than x then keep popping ancestor from the stack of ancestors.
     * Check if the popped ancestor's element is smaller than x and return if it is smaller than x else keep popping until the end of the stack is reached.
     * if no such element is found in the stack of ancestors then return null.
     * if entry returned by find has the element bigger than x or equals to x then just return that entry's element.
     * @param x : element whose floor is to be found.
     * @return : the floor of parameter x.
     */
    public T floor(T x) {
        Entry <T> tEntry = find(x);
        if (x.compareTo(tEntry.element) < 0){
            Entry<T> ancestor = ancestors.pop() ;
            while (ancestor != null){
                if (ancestor.element.compareTo(x) < 0){
                    return ancestor.element;
                }
                ancestor = ancestors.pop();
            }
            return null;
        }else{
            return  tEntry.element;
        }
    }

    /**
     * Find the ceiling of the element x. Ceiling is the smallest key that is no smaller than x. Return null if there is no such key.
     * Find the element x in the tree. find returns the entry where the x was found or where the search ended. ancestors has the path taken by find method.
     * if the element of the founded entry is smaller than x then keep popping elements from the ancestors stack.
     * if the popped entry has the element bigger than x then return that value else keep popping and checking until the null (root's parent) entry is reached.
     * if such value is not found then return the null. else if the element of the founded entry is bigger or equal to x then return the element of that entry.
     * @param x : element whose ceiling is to be found.
     * @return  : the ceiling of parameter x.
     */
    public T ceiling(T x) {
        Entry<T> tEntry = find(x);
        if (x.compareTo(tEntry.element) > 0){
            Entry<T> parent = ancestors.pop();
            while (parent != null){
                if (parent.element.compareTo(x) > 0){
                    return parent.element;
                }
                parent = ancestors.pop();
            }
            return null;
        }else{
            return tEntry.element;
        }
    }

    /**
     * Return the maximum element in the left subtree of the entry that contained x or null if such thing doesn't exist.
     * if x is not in the tree then floor is returned.
     * find the element in the tree. if the element is found then goto its left entry and traverse right every time until the rightmost entry is not
     * found. if such entry is found then return its element else return null.
     * if the element is not found in the tree then return floor.
     * @param x : element whose predecessor is to be found.
     * @return : return either null, floor or predecessor of the element x.
     */

    // Optional problem 2.  Find predecessor of x.  If x is not in the tree, return floor(x).  Return null if there is no such key.
    public T predecessor(T x) {
        Entry<T> tEntry = find(x);
        if (tEntry!= null && tEntry.element.compareTo(x) == 0){
            if (tEntry.left != null){
                Entry<T> maxLeft = tEntry.left.right;
                while (maxLeft != null){
                    maxLeft = maxLeft.right;
                }
                return maxLeft.element;
            }
            return null;
        }else{
            return floor(x);
        }
    }


    /**
     * Return the minimum element in the right subtree of the entry that contained x or null if such thing doesn't exist.
     * If x is not in the tree then ceiling of x is returned.
     * find the element in the tree. if the element is found then goto its right entry and traverse left every time until the leftmost entry is not
     * found. if such entry is found then return its element else return null.
     * if the element is not found in the tree then return ceiling.
     * @param x
     * @return
     */
    // Optional problem 2.  Find successor of x.  If x is not in the tree, return ceiling(x).  Return null if there is no such key.
    public T successor(T x) {
        Entry<T> tEntry = find(x);
        if (tEntry != null && tEntry.element.compareTo(x) == 0){
            if (tEntry.right != null){
                Entry<T> minRight = tEntry.right.left;
                while (minRight != null){
                    minRight = minRight.left;
                }
                return minRight.element;
            }
            return null;
        }else{
            return ceiling(x);
        }
    }

    // End of problem 2

    // Given ..
    public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if(node != null) {
            printTree(node.left);
            System.out.print(" " + node.element);
            printTree(node.right);
        }
    }




}