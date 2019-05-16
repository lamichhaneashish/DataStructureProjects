package axl173530;
import java.util.*;

/**
 * @author: Ashish Lamichhane
 * @version: 1.0
 * @Date: 11/9/18
 * @projectname: Project
 */

public class MDS {

    /**
     * Item class that holds the object with 3 attributes : id, price and description.
     */
    static class Item {
        private int id;
        private int price;
        private HashSet<Integer> description; /** only unique description are kept **/

        Item(int id, int price, HashSet<Integer> description){
            this.id = id;
            this.price = price;
            this.description = description;
        }

    }

    HashMap<Integer,Item> table ; /** table to store data where key is id and value is item itself. **/
    HashMap<Integer,HashSet<Integer>> tree; /** tree to store items by their description and value is list of ids **/

    /**
     * Creates a new table ( HashMap) that stores the id and item itself.
     * Creates a new tree (TreeMap) that stores description and corresponding ids.
     */
    public MDS() {
        table = new HashMap <>();
        tree = new HashMap <>();
    }

    /**
     * Description is stored in the HashSet.
     * This method inserts new item with id, price and description. If the id already exists then it replaces price and
     * description, given that description is not null.
     * Checks if the table contains the key. Then grabs the item from the table if true. If description is not empty then for each description check
     * if the tree contains multiple ids or just one, if later then remove it from the tree. If not, then remove the specific id.
     * Again goes through the loop to add new description back to the tree. Change the description and price of previously grabbed (old item) and put it
     * back to the table.
     * If the id is never seen before then again checks if the description is not empty and update the TreeSet of ids accordingly as many ids can have same
     * description.
     * @param id : id of the item.
     * @param price : price of the item.
     * @param list : description of the item.
     * @return 1 if the new item is added, 0 if the item's price and description are only updated.
     */
    public int insert(int id, int price, List<Integer> list) {
        HashSet<Integer> description = new HashSet <>(list); /** keeping a copy of description of id **/
        if (table.containsKey(id)){
            Item o_item = table.get(id);
            if (!description.isEmpty()){
                for(Integer e : o_item.description){
                    if (tree.get(e).size() == 1){ /** size = 1 means that it only contains the id that is being searched for **/
                        tree.remove(e);
                    }else{
                        tree.get(e).remove(id);
                    }
                }
                for (Integer e: description){
                    desAdd(e,id);
                }
                o_item.description = description;       /** updating description after updating tree **/
            }
            o_item.price = price;       /** updating price **/
            table.replace(id,o_item);   /** putting updated price and description back to the table **/
        }else{
            if (!description.isEmpty()){
                for (Integer e : description){
                    desAdd(e,id);
                }
            }
            Item n_item = new Item(id,price,description);
            table.put(id,n_item);
            return 1;
        }
        return 0;
    }

    /**
     * Helper method to add description to the tree.
     * If the tree already contains the description then only update the TreeSet of id else create new TreeSet of ids and then add the id to the set
     * before adding to the tree.
     * @param e : description of the item
     * @param id : id of the item.
     */
    void desAdd(int e, int id){
        if (tree.containsKey(e)){
            tree.get(e).add(id);
        }else{
            HashSet<Integer> temp = new HashSet <>();
            temp.add(id);
            tree.put(e,temp);
        }
    }

    /**
     * Checks if the table contains the id, if true returns the price.
     * @param id : id of the item.
     * @return : price on success, 0 on failure.
     */
    public int find(int id) {
        if (table.containsKey(id)){
            return table.get(id).price;
        }
        return 0;
    }

    /**
     * Deletes the item with the given id.
     * If table contains the id then goes through the list of description first adding the description of the item removed and then removing
     * it from the tree. If the description only matches with one id then remove it completely from the tree. But, if false then only remove
     * id associated with that description. Finally, remove id from the table.
     * @param id : id of the item to be deleted.
     * @return : sum of the descriptions that are deleted on success, 0 on failure.
     */
    public int delete(int id) {
        int sum = 0;
        if (table.containsKey(id)){
            Item o_item = table.get(id);
            for (Integer e : o_item.description){
                sum += e;
                if (tree.get(e).size() == 1){
                    tree.remove(e);
                }else{
                    tree.get(e).remove(id);
                }
            }
            table.remove(id);
            return sum;
        }
        return 0;
    }

    /**
     * If tree contains the entered description then grab the TreeSet of ids. Iterate over the set and
     * check price for each id, if the price is smaller than minPrice then update the price.
     * @param n : description to be matched.
     * @return : minimum price in of the item that matches the description on success, 0 on failure.
     */
    public int findMinPrice(int n) {
        HashSet<Integer> ids;
        Integer min = Integer.MAX_VALUE; /** minPrice is set to be max value so that first price discovered will be min **/
        Integer price;
        if (tree.containsKey(n)){
            ids = tree.get(n);
            for (Integer id : ids){     /** iterate over the ids, grab their prices and compare with minPrice **/
                price = table.get(id).price;
                if (price < min){
                    min = price;
                }
            }
            return min;
        }
        return 0;
    }

    /**
     * If the tree contains the description then grab all the ids that matches with the description.
     * Iterate over prices of all ids and check with maximum price. Update the max value accordingly.
     * @param n : description to be matched.
     * @return : maximum price of the item that matches the description on success, 0 on failure.
     */
    public int findMaxPrice(int n) {
        HashSet<Integer> ids ;
        Integer max = 0;    /** max is set to be zero so that first price that matches the description will be max**/
        Integer price ;
        if(tree.containsKey(n)){
            ids = tree.get(n);
            for(Integer id : ids){
                price = table.get(id).price;
                if (price > max){
                    max = price;
                }
            }
            return max;
        }
        return 0;
    }

    /**
     * If teh tree contains the description that is being searched for grab all the ids that matches. Then for each id,
     * iterate by checking if the id falls between low and high. Increment the count of item on success.
     * @param n : description of the item to be matched.
     * @param low : low price range.
     * @param high : high price range.
     * @return : number of items that matches that exact description on success, 0 on failure.
     */
    public int findPriceRange(int n, int low, int high) {
        HashSet<Integer> ids;
        Integer price;
        int count = 0;
        if (tree.containsKey(n)){
            ids = tree.get(n);
            for (Integer id : ids){
                price = table.get(id).price;
                if (price <= high && price >= low){
                    count++;
                }
            }
            return count;
        }
        return 0;
    }

    /**
     * Copy given list to a TreeSet.
     * If table contains the given id then grab the description of the id matched.
     * Go through the description that is provided and check if it contains the description of id. If true sum the "to be removed" description
     * If the description only matches with one id (the one being searched) then remove both description and id from the tree
     * else remove only the id that matches. Finally, remove description from the table.
     * @param id : id of item whose description is to be removed.
     * @param list : list of descriptions that should be checked against the description of item for removal.
     * @return : sum of all description that are actually removed on success, 0 on failure.
     */
    public int removeNames(int id, List<Integer> list) {
        int sum =0;
        HashSet<Integer> description = new HashSet <>(list);
        HashSet<Integer> des;
        if (table.containsKey(id)){
            des = table.get(id).description;
            for (Integer e : description){
                if (des.contains(e)){
                    sum += e;
                    if (tree.get(e).size() == 1){
                        tree.remove(e);
                    }else{
                        tree.get(e).remove(id);
                    }
                   table.get(id).description.remove(e);
                }
            }
            return sum;
        }
        return 0;
    }



}
