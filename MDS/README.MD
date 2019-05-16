## MDS (Multi Directional Search) using HashSet and HashMap

It allows to search a list using different criteria. In this implementation, a list can be searched using price and ID. 

Item class has three attributes : 
	a. id : integer to store item id.
	b. price : integer to store price.
	c. description : HashSet to store unique description.

MDS class has 7 methods and 1 helper method.

HashMap is used to store id as key and item itself as value.
HashMap is used to store description as key and TreeSet of ids associated
with that particular description as value. The reason for choosing description as key is that a single description can be found in many items (many ids). And for some functions we are looking for a single description that matches with the input or only the partial descriptions that are matched (like in the remove name method).


a. insert(int id, int price, List<Integer> list)
     Description is stored in the HashSet.
     This method inserts new item with id, price and description. If the id already exists then it replaces price and
     description, given that description is not null.
     Checks if the table contains the key. Then grabs the item from the table if true. If description is not empty then for each description check if the tree contains multiple ids.   or just one, if later then remove it from the tree. If not, then remove the specific id.
     Again goes through the loop to add new description back to the tree. Change the description and price of previously grabbed (old item) and put it back to the table.
     If the id is never seen before then again checks if the description is not empty and update the HashSet of ids accordingly as many ids can have same description.
     @param id : id of the item.
     @param price : price of the item.
     @param list : description of the item.
     @return 1 if the new item is added, 0 if the item's price and description are only updated.
     
b.  find(int id)
     Checks if the table contains the id, if true returns the price.
     @param id : id of the item.
     @return : price on success, 0 on failure.

c.	delete(int id)
     Deletes the item with the given id.
     If table contains the id then goes through the list of description first adding the description of the item removed and then removing
     it from the tree. If the description only matches with one id then remove it completely from the tree. But, if false then only remove
     id associated with that description. Finally, remove id from the table.
     @param id : id of the item to be deleted.
     @return : sum of the descriptions that are deleted on success, 0 on failure.

d.	findMinPrice(int n)
     If tree contains the entered description then grab the HashSet of ids. Iterate over the set and check price for each id, if the price is smaller than minPrice then update the price.
     @param n : description to be matched.
     @return : minimum price in of the item that matches the description on success, 0 on failure.

5.	findMaxPrice(int n)
     If the tree contains the description then grab all the ids that matches with the description. Iterate over prices of all ids and check with maximum price. Update the max value accordingly.
     @param n : description to be matched.
     @return : maximum price of the item that matches the description on success, 0 on failure.

6.	findPriceRange(int n, int low, int high)
     If teh tree contains the description that is being searched for grab all the ids that matches. Then for each id, iterate by checking if the id falls between low and high. Increment the count of item on success.
     @param n : description of the item to be matched.
     @param low : low price range.
     @param high : high price range.
     @return : number of items that matches that exact description on success, 0 on failure.

7.	removeNames(int id, List<Integer> list)	
	Copy given list to a HashSet.
    If table contains the given id then grab the description of the id matched.
    Go through the description that is provided and check if it contains the description of id. If true sum the "to be removed" description
    If the description only matches with one id (the one being searched) then remove both description and id from the tree
    else remove only the id that matches. Finally, remove description from the table.
    @param id : id of item whose description is to be removed.
    @param list : list of descriptions that should be checked against the description of item for removal.
    @return : sum of all description that are actually removed on success, 0 on failure.
    
Helper Method : desAdd(int e, int id)
     Helper method to add description to the tree.
     If the tree already contains the description then only update the HashSet of id else create new HashSet of ids and then add the id to the set
     before adding to the tree.
     @param e : description of the item
     @param id : id of the item.
        

