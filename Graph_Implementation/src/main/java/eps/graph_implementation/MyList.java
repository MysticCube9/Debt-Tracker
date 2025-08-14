
package eps.graph_implementation;


/**
 * A class that implements functions from a provided LinkedList.
 * This class can create lists, add or remove nodes, and print a list in a 
 * readable format
 * 
 */
public class MyList implements LinkedList {
    
    private Node head = null; // the first node in the list
    private Node tail = null; // the last node in the list
    private int size = 0; // the total amout of nodes in the list
    
    /**
     * a constructor for creating a new linked list
     * @param head - the first node
     * @throws IllegalArgumentException - the node must not point to any node
     */
    public MyList(Node head) throws IllegalArgumentException{
        
        if (head.getDebtor() != null) {
            throw new IllegalArgumentException();
        }
        
        this.head = head;
        this.tail = head;
        this.size = 1;
    }    
   
    /**
     * an empty constructor which has all the values as null or 0
     */
    public MyList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * gets the first node
     * @return - return the first node in the list
     */
    @Override
    public Node getHead() {
        return head;
    }

    /**
     * sets the first node
     * @param head - set the head as the the given parameter
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * gets the last node
     * @return - return the last node in the list
     */
    public Node getTail() {
        return tail;
    }

    /**
     * sets the last node
     * @param tail - set the tail as the given parameter
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * gets the amount of nodes in the linked list
     * @return - return the amount of nodes in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * to update the size of the list when a node is added or deleted
     * @param size - the new size to update it to
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * adds a node above the head making sure the passed node's pointer is null
     * @param newNode - the node that will be the new head
     * @throws IllegalArgumentException - the node must not point to any node
     */
    @Override
    public void addHead(Node newNode) throws IllegalArgumentException {
        
        if (newNode.getDebtor() != null) {
            throw new IllegalArgumentException();
        }
           
        Node originalHead = getHead();
        
        newNode.setDebtor(originalHead);
        
        setHead(newNode);
        
        setSize(getSize()+1); // add 1 to size
        
        // if the head before now becomes tail, set tail variable
        if (getSize() == 2) {
            setTail(originalHead);
        }
        
    }
    
    /**
     * adds the passed node after the tail and adds 1 to size
     * @param newNode - the node that will be added and be the new tail
     * @throws IllegalArgumentException - the node must not point to any node
     */
    @Override
    public void addTail(Node newNode) throws IllegalArgumentException {
        
        if (newNode.getDebtor() != null) {
            throw new IllegalArgumentException();
        }
        
        // if list is empty
        if (getTail() == null) {
            setHead(newNode);
            setTail(newNode);
            setSize(getSize()+1); // add 1 to size
        } else {
            getTail().setDebtor(newNode);
            setTail(newNode);
            setSize(getSize()+1); // add 1 to size

        }
 
    }
    
    /**
     * places a new node after a given node and configures all the pointers
     * @param newNode - the node to be placed in
     * @param oldNode - the node that the newNode will be placed after
     * @throws IllegalArgumentException - the node must not point to any node
     */
    @Override
    public void addAfter(Node newNode, Node oldNode) throws IllegalArgumentException {
        
        if (newNode.getDebtor() != null) {
            throw new IllegalArgumentException();
        }
        
        Node nextNode = getHead();
        boolean found = false;
        
        // find the node to make sure it exists and get the node around it
        while (!found) {   
            
            if (nextNode == oldNode) {
                found = true;
                break;
            }    
            // didnt find the node
            if (nextNode.getDebtor() == null) {
                throw new IllegalArgumentException();
            }
            
            nextNode = nextNode.getDebtor();
        }   
        
        // after the function nextnode 
        // if the node to add after is the last node
        if (oldNode == getTail()) {
            setTail(newNode);
        } else {
            // set newNode pointer to the node after oldNode
            newNode.setDebtor(oldNode.getDebtor());
        }
        
        oldNode.setDebtor(newNode);
        setSize(getSize()+1); // add 1 to size
    }
    
    /**
     * removes the first node and sets the second node as the head
     * @return - the removed node (the old first node)
     * @throws IllegalArgumentException - the list must have a head
     */
    @Override
    public Node removeHead() throws IllegalArgumentException {
        
        if (getHead() == null) {
            throw new IllegalArgumentException();
        }
        
        Node oldHead = getHead();
        
        setHead(oldHead.getDebtor()); // set the head variable to the 2nd node
        
        oldHead.setDebtor(null);
        
        setSize(getSize()-1); // subtract 1 from size
        
        return oldHead;
    }
    
    /**
     * a method that removes the tail of the list and returns the tail it
     * removed
     * @return - the removed tail
     * @throws IllegalArgumentException - if the list is empty
     */
    @Override
    public Node removeTail() throws IllegalArgumentException {
        
        if (getHead() == null) {
            throw new IllegalArgumentException();
        }
        
        Node oldTail = getTail();        
        Node nextNode = getHead();
        boolean found = false; 
        
        // if there is only 1 node
        if (getHead().getDebtor() == null) {
            setTail(null);
            setHead(null);
        } else {
            // naviagate to the tail to be able to access the node before it
            while (!found) {  
                
                if (nextNode.getDebtor() == oldTail) {
                    // next node will be set to the node before the tail
                    found = true;
                    break;
                }   
                
            nextNode = nextNode.getDebtor();            
            }  
            
            nextNode.setDebtor(null);
            setTail(nextNode);
        }  
        
        setSize(getSize()-1); // subtract 1 from the size
        return oldTail;
    }
    
    /**
     * removes a node, in the middle, start or end of the list
     * and connects the nodes around it if necessary as to not lose data
     * @param toDelete - the node to search for and delete
     * @return - the node that was deleted
     * @throws IllegalArgumentException - if the node doesn't exist in the list
     *  or if the list is empty
     */
    @Override
    public Node removeNode(Node toDelete) throws IllegalArgumentException { 
        
        Node nextNode = getHead();        
        boolean found = false;    
        
        // if the node is the head or tail use the already written functions
        if (nextNode == toDelete) {
            return removeHead();
            
        } else if (getTail() == toDelete) {
            return removeTail(); 
            
        } else {           
            // find the node to make sure it exists and access node before it
            while (!found) {
                
                if (nextNode.getDebtor() == toDelete) {
                    //next node will be the node before toDelete
                    found = true;
                    break;
                }
                // the node wasn't found
                if (nextNode.getDebtor() == null) {
                    throw new IllegalArgumentException();
                }
                
                nextNode = nextNode.getDebtor();
            }
            
        toDelete = nextNode.getDebtor();
        // set the pointer of the node before toDelete to the node after
        // toDelete
        nextNode.setDebtor(toDelete.getDebtor());
        
        toDelete.setDebtor(null); // removing the node from the linked list
        
        setSize(getSize()-1); // subtracting one from the size
        return toDelete;
        }
    }
    
    /**
     * removes the duplicate by payload only going once through the list
     * and only using a String to complete the process
     */
    @Override
    public void removeDuplicates() {
    
        String allValues = "";
        
        Node nextNode = getHead();
        
        while(nextNode != null) {
            
            // using this to make sure if you have "bob" and "tom bob", those
            // aren't the same but without the "|" my function would delete it
            String newPayload = "| " + nextNode.getPayload().toString() + "|";
            
            if(allValues.contains(newPayload)) {
                Node originalNode = nextNode; // to not lose connection
                nextNode = nextNode.getDebtor();
                removeNode(originalNode);
            } else {
                allValues = allValues + nextNode + "| ";
                nextNode = nextNode.getDebtor();
            }
                        
        }
        
    }
    
    
    /**
     * a function that prints out all the payloads in a linked list in a clear format
     * @return - returns a printed list in the form of a string with -> in between each payload
     */
    @Override
    public String toString() {
         
        Node nextNode = getHead();
        String printedList = "";
        
        // if the list is empty print null
        if (size == 0) {
            return "null";
        } else {
            // iterates through all the nodes
            while (nextNode != null) {
                printedList = printedList + nextNode.getPayload().toString() + "->";
                nextNode = nextNode.getDebtor();
            }
            return printedList + "null";
        }
    }
    
    
    
    
}
