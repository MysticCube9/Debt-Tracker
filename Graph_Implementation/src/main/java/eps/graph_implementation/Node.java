
package eps.graph_implementation;

/**
 * Interface for a linked list to be implemented to create a linked list.
 * 
 */
public interface Node {

    /**
     * Method for replacing what the node points to for its "next".
     * Substitutes the next to the passed in Node.
     * Does nothing with the original next node that is dropped
     * 
     * @param debtor new node to set to this node's next
     */
    public void setDebtor(Node debtor);

    /**
     * Simple accessor: returns the next node
     * 
     * @return next node
     */
    public Node getDebtor();

    /**
     * Method for retrieving the payload of the node.
     * Allows the payload to be an arbitrary object - check instanceof and cast
     * to get it in the type you are looking for.
     * 
     * @param payload the payload
     * @throws IllegalArgumentException throws if payload is null or expected object type
     */
    public void setPayload(Object payload) throws IllegalArgumentException;


    /**
     * Method for accessing the payload of the node.
     * All payloads should automatically be cast to match Object.
     * Caller of this method should check the object type with instanceof and cast
     * as appropriate.
     * 
     * @return payload (data) of node
     */
    public Object getPayload();

    /**
     * Payload description of this node. If payload is null return "null," else the
     * string value of the payload (data).
     * Note this is an existing method in the Object class
     * should be overriden
     * 
     * @return payload description
     */
    public String toString();

}
