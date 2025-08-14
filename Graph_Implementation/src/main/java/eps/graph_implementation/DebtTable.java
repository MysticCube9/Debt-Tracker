
package eps.graph_implementation;
import java.util.*;

/**
 * This class implements a weighted graph using a nested Hash Map inside
 * another Hash Map. This implementation focuses on making it easier to
 * keep track of debt between countries.
 * 
 */
public class DebtTable {


    HashMap<String, HashMap<String,Double>> map = new HashMap<String, HashMap<String,Double>>();
    
    /**
     * empty constructor to define an empty debtTable
     */
    public DebtTable() {
        
        this.map = new HashMap<String, HashMap<String,Double>>();
        
    }

    /**
     * Finds if two values are equal with a percision of 0.1
     * 
     * @param a - value one to compare
     * @param b - value two to compare
     * @return - if the values are equal or not
     */
    public boolean equalTo(double a, double b) {

        if (Math.abs(a - b) < 0.1) {
            return true;
        }
        
        return false;

    }
    
    /**
     * This function adds the country but doesn't add any information about
     * this country into the other countries' hash maps
     * @param country - the country to add to the graph
     */
    public void addCountry(String country) {
        
        // if the country doesn't already exist in the map we can add it
        if(!map.containsKey(country)) {
            
            // add it to the outer map and create an inner map for the country
            map.put(country, new HashMap<String, Double>());
        
        // if the country already exists we cannot add it again    
        } else {
            System.out.println("This country already exists in the system");

        }   
        
    }
    
    /**
     * This function deletes the country from the outer hash map and also 
     * deletes all debt entries in other countries' inner hash maps
     * 
     * @param toDeleteCountry - the country that needs to be deleted from the
     * system
     */
    public void deleteCountry(String toDeleteCountry) {
        
        // if the country doesn't exist in the hash map then we can't delete it
        if (!map.containsKey(toDeleteCountry)) {
            System.out.println("Please enter the name of a countryt that"
                    + " exists.");
            return;
        }

        // deleting the country from the outer hash map
        map.remove(toDeleteCountry);
        
        // loops through the inner hash maps of all the countries
        for (HashMap <String,Double> inner : map.values()) {
            
            // if the inner map has an entry in it's hash map of the country
            // we want to delete it is deleted
            if (inner.containsKey(toDeleteCountry)) {
                inner.remove(toDeleteCountry);
            }
            
        }
        
    }
    
    /**
     * This function adds debt to a country's inner hash map that stores how much
     * it owes other countries. Also it has logic to only have one entry for a
     * relationship between two countries. Meaning, if country A owes
     * country B, and now country B uses addDebt, the country who owes more
     * money will have the net debt of their relationship in their hash map.
     * 
     * @param owedFrom - the country that needs to pay another
     * @param owedTo - the country that the money is owed to
     * @param amount - the amount that is owed (has to be greater than 0)
     */
    public void addDebt(String owedFrom, String owedTo, Double amount) {

        // IN THESE COMMENTS I USE 'WE' & 'US', THAT REFERS TO owedFrom
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        // Finds the inner HashMap inside the outer HashMap, of the country that
        // is owedFrom in this function, containing the debts to other countries
        // from a single country
        HashMap<String, Double> owedFromInnerMap = map.get(owedFrom);
        
        
        // null/compliancy checks
        if (owedFromInnerMap == null) {
            System.out.println("We did not find the country the debt is"
                    + "originating from. Please add that country first");
            return;
        } 
        if (!map.containsKey(owedTo)) {
            System.out.println("We did not find the country the money is"
                    + "owed to. Please add that country first");
            return;
        }
        if (amount <= 0) {
            System.out.println("The amount must be positive.");
            return;
        }
        
        // Finds the inner HashMap inside the outer HashMap, of the country that
        // is owedTo in this function, containing the debts to other countries
        // from a single country
        HashMap<String, Double> owedToInnerMap = map.get(owedTo);
        
        // if the country we owe money to already owes money to us
        if (owedToInnerMap.containsKey(owedFrom)) {
            
            // the amount the country we owe money to owes us
            double owedAmount = owedToInnerMap.get(owedFrom);
            
            // subtracting the amount we owe and how much the other country owes
            // us
            double newAmount = amount - owedAmount;

            //newAmount = Math.round(newAmount * 100) / 100;
            
            // if the amount we owe is larger than the amount the other country
            // owed us, add a new key but with the newAmount to our hashmap
            if (newAmount > 0) {
                
                // add the new key and value with the newAmount
                owedFromInnerMap.put(owedTo, newAmount);
                
                // remove the amount the other country owes us because now we
                // owe that country money
                owedToInnerMap.remove(owedFrom);
             
            // if the money we owe each other cancels out, remove the key and
            // value pair from the other country's hash map
            } else if (newAmount == 0) {
                
                // removes the debt from the other country and doesn't add any
                // debt to our hash map
                owedToInnerMap.remove(owedFrom);
            
            // if the amount we owe is less than the amount the other country
            // owed us, then put the new amount in the other country's hashmap
            } else {
                
                // takes the absolute value and adds debt to the owedTo inner
                // map. needs to be absolut value because if the code hits this 
                // case newAmount is negative.
                owedToInnerMap.replace(owedFrom, Math.abs(newAmount));
                
            }
            
        // if we already owe the country money then we add how much we already
        // owed and the new amount and make that the new value
        } else if (owedFromInnerMap.containsKey(owedTo)) {
            
            // the amount we already owe
            double exsistingDebt = owedFromInnerMap.get(owedTo);
            
            // replaces the old value with the new value
            owedFromInnerMap.replace(owedTo, exsistingDebt + amount);
            
        // if there is no previous relation between the two countries
        } else {
            
            // adds a new debt to the country that owes the money
            owedFromInnerMap.put(owedTo, amount);
            
        }
 
    }
    
    /**
     * Returns the total of all debts from a country.
     * 
     * @param country - the country we are finding the debt of
     * @return - the total amount owed to other countries
     */
    public double totalDebtForCountry(String country) {
        
        if (map.get(country) == null) {
            System.out.println("\nThis country doesn't exist");
            return -1.0;
        }

        // the inner hash map of the country
        HashMap<String, Double> innerMap = map.get(country);

        // the total of all debts
        double total = 0.0;
        
        // checks each value in the inner hash map
        for (double amount : innerMap.values()) {
            
            // adds the value to the total each time
            total += amount;
            
        }
        
        // returns the total debt owed with one decimal
        return total;
        
    }

    public double totalOwedToCountry(String owedTo) {

        if (map.get(owedTo) == null) {
            System.out.println("\nThis country doesn't exist");
            return -1.0;
        }

        double total = 0.0;

        // loops through the inner hash maps of all the countries
        for (HashMap <String,Double> inner : map.values()) {

            // if the inner map has an entry in it's hash map of the country
            // passed into this function, find how much a country is in debt
            // to owedTo
            if (inner.containsKey(owedTo)) {
                
                // add the amount owed to a total
                total += inner.get(owedTo);

            }

        }

        // returns the total owedTo a country owed with one decimal
        return total;

    }

    /**
     * 
     * @param country
     * @return
     */
    public double findNetBalance(String country) {

        if (map.get(country) == null) {
            System.out.println("\nThis country doesn't exist");
            return -1.0;
        }

        return totalOwedToCountry(country) - totalDebtForCountry(country);

    }

    /**
     * prints out who the debt is owed to and how much
     * @param country - the country of whose debts you are trying to find
     */
    public void printAllDebts(String country) {

        if (map.get(country) == null) {
            System.out.println("\nThis country doesn't exist");
            return;
        }

        // the inner hash map of the country
        HashMap<String, Double> innerMap = map.get(country);
        
        // adds a header to make it easier to read in the console
        System.out.println("\nDebts owed from " + country);

        // checks each value in the inner hash map
        for (String owedTo : innerMap.keySet()) {
            
            System.out.format("%s - %.2f\n", owedTo, innerMap.get(owedTo));
            
        }

    }


    /**
     * This function finds every middle man in the debt table. A middle man is
     * when country A owes country B 5 and country B owes country C 5, then it
     * returns a list with country A at index 0, country B and index 1 and
     * country C and index 2. In this situation country A could pay country C
     * directly giving the middle man.
     * 
     * Very inneficient, if I had more time than I would optimize it
     * 
     * @return - an arraylist of arrays that contain the information of the
     *           middle men
     */
    public ArrayList<String[]> findMiddleMan(){


        ArrayList<String[]> answerList = new ArrayList<>();

        // loops through every country's inner hash map
        for(String innerKey : map.keySet()) {

            HashMap<String,Double> inner = map.get(innerKey);

            // loops thourgh all the keys inside the inner hash map
            for(String debtKey : inner.keySet()) {

                // the amount owed so that it can be equal
                double debtAmount = inner.get(debtKey);
                
                // the hash map of who the first country owes money to
                HashMap<String,Double> middleManMap = map.get(debtKey);

                // have to iterate through the keys to know what country it is
                for(String middleKey : middleManMap.keySet()) {

                    // if this hash map has a value the same as the original
                    // hashmap
                    if(equalTo(middleManMap.get(middleKey), debtAmount)) {

                        // make a new array to add to the arraylist
                        String[] answerArray = new String[3];

                        // the country who could skip a middle man
                        answerArray[0] = innerKey;

                        // the middle man
                        answerArray[1] = debtKey;

                        // the country who is getting paid
                        answerArray[2] = middleKey;

                        // adds it to the arraylist
                        answerList.add(answerArray);                        

                    }

                }

            }

        }

        // returns the arraylist
        return answerList;

    }


 
}
