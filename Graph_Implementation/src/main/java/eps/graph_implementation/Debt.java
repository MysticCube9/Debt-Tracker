
package eps.graph_implementation;

/**
 * a class the implements Node and can create nodes, get and set properties and
 * output the payload as a string
 */
public class Debt implements Node {
    
    private Object payload = ""; // the value the node stores
    private Node debtor = null; // the country to which money is owed
    private String name = "";
    
    /**
     * a constructor to create a new node
     * @param payload - the value the node stores
     * @param debtor - the country to which money is used
     * @param name - the name of the country
     */
    public Debt(Object payload, Node debtor, String name) {
        this.payload = payload;
        this.debtor = debtor;
        this.name = name;
    }
    
    /**
     * a constructor with only the payload set and the next node as null
     * @param payload - the value the node stores
     */
    public Debt(Object payload) {
        this.payload = payload;
        this.debtor = null;
        this.name = "";
    }
    
    /**
     * an empty constructor with all the values null
     */
    public Debt() {
        this.payload = null;
        this.debtor = null;
        this.name = "";
    }

    /**
     * returns the value of the payload of that node
     * @return - the payload (the value the node stores)
     */
    @Override
    public Object getPayload() {
        return payload;
    }

    /**
     * sets the payload to value passed through
     * @param payload - the new value to set the payload to
     * @throws IllegalArgumentException - when payload is null
     */
    @Override
    public void setPayload(Object payload) throws IllegalArgumentException{
        if (payload == null) {
            throw new IllegalArgumentException();
        } else if (!payload.getClass().equals(String.class)){
            throw new IllegalArgumentException();
        }
         else {
            this.payload = payload;
        }  
    }

    /**
     * returns the node that this node is linked to
     * could also be null
     * @return - the next node that this node points to
     */
    @Override
    public Node getDebtor() {
        return debtor;
    }

    /**
     * @param debtor - set the next node to this
     */
    @Override
    public void setDebtor(Node debtor) {
        this.debtor = debtor;
    }
    
    /**
     * returns the name of the country representing the node
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * sets the name of the country to the passed the value
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * override the toString method to print the payload
     * @return - the payload as a string
     */
    @Override
    public String toString() {
        return getPayload().toString();
    }
    
}
