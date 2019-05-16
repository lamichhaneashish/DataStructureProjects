/** Starter code for AVL Tree
 * @author: Ashish Lamichhane
 *
 */
package axl173530;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {
    static class Entry<T> extends BinarySearchTree.Entry<T> {
        int height;
        Entry(T x, Entry<T> left, Entry<T> right){
            super (x, left, right);
            height = 0;
        }
    }

    // To do for Problem 1
    AVLTree() {
        super();
    }

    // To do for Problem 1
    @Override
    public boolean add(T x) {
        return add(new Entry<>(x,null,null));
    }

    /**
     * Helper method to add the entry to the AVL tree.
     * After each addition updates the height of the nodes and
     * checks if the balance factor is violated and performs the respective rotation to restore the balance.
     * @param entry
     * @return if add is successful or not.
     */
    private boolean add(Entry<T> entry) {
        Entry <T> e;
        int balanceFactor;
        if (super.add(entry)) {
            if (entry == root) {
                return true;
            } else {
                while (ancestors.peek() != null) {
                        e = (Entry <T>) ancestors.pop();
                        updateHeight(e);
                        balanceFactor = getBalanceFactor(e);
                        if (balanceFactor > 1) {
                            if (height((Entry <T>) e.left) > height((Entry <T>) e.right)){
                                rightRotate(e);
                                break;
                            }else{
                                e.left = leftRotate((Entry <T>) e.left);
                                rightRotate(e);
                                break;
                        }
                        } else if (balanceFactor < -1) {
                            if (height((Entry)e.right) > height((Entry)e.left)){
                                leftRotate(e);
                                break;
                            }else{
                                e.right = rightRotate((Entry <T>) e.right);
                                leftRotate(e);
                                break;
                        }
                    }
                    }
                return true;
                    }
                }
        return false;
        }


    /**
     * Performs the left rotation on the current entry.
     * @param entry
     * @return new root of the sub-tree.
     */
    private Entry leftRotate(Entry <T> entry) {
        Entry parent = (Entry) entry.right;
        entry.right = parent.left;
        parent.left = entry;
        updateHeight(entry);
        updateHeight(parent);
        if (entry == root){
            root = parent;
        }
            return parent;
        }

    /**
     * Performs the right rotation on the current entry.
     * @param entry
     * @return new root of the sub-tree.
     */

    private Entry rightRotate(Entry<T> entry){
        Entry parent = (Entry) entry.left;
        entry.left = parent.right;
        parent.right = entry;
        if (entry == root){
            root = parent;
        }
        updateHeight(entry);
        updateHeight(parent);
        return parent;
    }




    /**
     * Returns the balance factor. if the entry is null then its zero else its the difference of left sub-tree's height and right sub-tree's height.
     * @param entry
     * @return
     */
    private int getBalanceFactor(AVLTree.Entry<T> entry){
        if (entry == null){
            return  0;
        }else{
            return (height((Entry <T>) entry.left)) - (height((Entry)entry.right));
        }
    }

    /**
     * Updates the new height of the entry after the addition.
     *
     */
    private void updateHeight(Entry<T> entry){
        entry.height = 1 + Math.max(height((Entry) entry.left), height((Entry)entry.right) );
    }

    /**
     * Returns the height of the current node. if the entry is null then returns -1 else returns the current height of the tree.
     * @param entry
     * @return : height of the tree.
     */
    private int height(Entry<T> entry){
        return entry == null ? -1 : entry.height;
    }


    // To do for Problem 2
    @Override
    public T remove(T x) {
        return super.remove(x);
    }

    /** To do for Problem 3
     * Check if the AVL tree is valid.  Need to check BST condition, height balance condition.
     * Nulls and duplicate elements are not allowed.
     * Do not assume that the height stored at every node is correct. That has to be verified too
     */
    public boolean verify() {
        return false;
    }


}
