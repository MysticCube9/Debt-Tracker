/*
 * Change the package name to match the package you import it into.
 */
package eps.graph_implementation;

/**
 * Interface for a linked list to be implemented to create a linked list.
 */
public interface LinkedList {

    /**
     * Should return the head of the list, simple accessor
     * 
     * @return head of list, returns null if no head present
     */
    Node getHead();

    // Addition methods: adds to the head, tail, or after a specified node
    // Should check that the new node is not pointing to anything in next before
    // adding it.

    /**
     * Add item to head of the list
     * 
     * @param newNode the new node to add
     * @throws IllegalArgumentException newNode must be a single node, throws
     *                                  exception if newNode is a list of multiple
     *                                  nodes
     */
    public void addHead(Node newNode) throws IllegalArgumentException;

    /**
     * Add item to the tail of the list
     * 
     * @param newNode the new node to add
     * @throws IllegalArgumentException newNode must be a single node, throws
     *                                  exception if newNode is a list of multiple
     *                                  nodes
     */
    public void addTail(Node newNode) throws IllegalArgumentException;

    /**
     * inserts a new node in the list
     * 
     * @param newNode the new node to insert
     * @param oldNode the node to insert the new node after
     * @throws IllegalArgumentException throws exception if oldNode doesn't exist in
     *                                  list. newNode must be a single node, throws
     *                                  exception if newNode is a list of multiple
     *                                  nodes
     */
    public void addAfter(Node newNode, Node oldNode) throws IllegalArgumentException;

    // Removal methods: removes the head (updating it), the tail, or a specified
    // node.

    /**
     * Removes head from the list
     * 
     * @return removed node in case you need to further process it. Ignore it if not
     *         needed.
     * @throws IllegalArgumentException thrown if list is empty
     */
    public Node removeHead() throws IllegalArgumentException;

    /**
     * Removes tail from the list
     * 
     * @return removed node in case you need to further process it. Ignore it if not
     *         needed.
     * @throws IllegalArgumentException thrown if list is empty
     */
    public Node removeTail() throws IllegalArgumentException;

    /**
     * Removes specified node from the list
     * 
     * @param toDelete node to remove
     * @return removed node in case you need to further process it. Ignore it if not
     *         needed.
     * @throws IllegalArgumentException thrown if node to delete doesn't exist in
     *                                  the list.
     */
    public Node removeNode(Node toDelete) throws IllegalArgumentException;
   
     /**
     * removes the duplicate by payload only going once through the list
     * and only using a String to complete the process
     * 
     * will not remove "tom bob" when searching for "bob"
     */
    public void removeDuplicates();
    
    /**
     * Override the Object toString method to print the linked list in a nice
     * format:
     * example: yellow->red->blue->green->null
     * where "yellow" is the payload for the head and "green" is the payload for
     * the tail. Should always point to null at the end.
     * 
     * @return description of list
     */    
    @Override
    public String toString();
}