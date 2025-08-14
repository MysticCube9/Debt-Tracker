
package eps.graph_implementation;

import java.util.ArrayList;

/**
 * This class tests all the functions created in DebtTable and displays
 * the functionality.
 * 
 */
public class TestMain {
    
    public static void main(String[] args) {

        DebtTable debtTable = new DebtTable();
    
        // adds all the countries
        System.out.println("Adding Countries...");

        debtTable.addCountry("United States");
        debtTable.addCountry("Japan");
        debtTable.addCountry("Germany");
        debtTable.addCountry("United Kingdom");
        debtTable.addCountry("Belgium");

        // should print out that the country is already in the system
        System.out.println("\nAttempting to add the United States AGAIN");
        debtTable.addCountry("United States");


        // adding debt for the United States
        debtTable.addDebt("United States", "Japan", 1129.6);
        debtTable.addDebt("United States", "Germany", 101.6);
        debtTable.addDebt("United States", "United Kingdom", 743.9);
        debtTable.addDebt("United States", "Belgium", 324.3);


        // adding debt for Japan
        debtTable.addDebt("Japan", "Germany", 150.2);
        debtTable.addDebt("Japan", "United Kingdom", 200.1);
        debtTable.addDebt("Japan", "Belgium", 149.9);

        // adding debt for Germany
        debtTable.addDebt("Germany", "United Kingdom", 147.2);
        debtTable.addDebt("Germany", "Belgium", 132.1);

        // adding debt for United Kingdom
        debtTable.addDebt("United Kingdom", "Belgium", 99.2);


        //------------------------------------------------------------------------------------
        // functional tests


        // checking if the amount calculated is correct for the United States
        if (debtTable.equalTo(debtTable.totalDebtForCountry("United States"), 2299.4)) {

            System.out.format("\nAmount owed by the United States: %.2f", debtTable.totalDebtForCountry("United States"));
            System.out.println("\nCorrect!");

        } else {
            System.out.println("\nIncorrect Value, Fail.");
        }


        // checking if the amount calculated is correct for Japan
        if (debtTable.equalTo(debtTable.totalDebtForCountry("Japan"), 500.2)) {

            System.out.format("\nAmount owed by Japan %.2f", debtTable.totalDebtForCountry("Japan"));
            System.out.println("\nCorrect!");

        } else {
            System.out.println("\nIncorrect Value, Fail.");
        }


        // checking if the amount calculated is correct for Belgium
        if (debtTable.equalTo(debtTable.totalDebtForCountry("Belgium"), 0.0)) {

            System.out.format("\nAmount owed by Belgium %.2f", debtTable.totalDebtForCountry("Belgium"));
            System.out.println("\nCorrect!");

        } else {
            System.out.println("\nIncorrect Value, Fail.");
        }

        System.out.println("----------------");

        // originally japan owed germany, but when germany owed japan more
        // than japan originally owed, germany owes money and japan doesn't

        System.out.println("\nBEFORE addDebt");

        debtTable.printAllDebts("Japan");

        System.out.println("\nAFTER 180m addDebt from Germany");

        debtTable.addDebt("Germany", "Japan", 180.3);
        debtTable.printAllDebts("Germany");

        // japan will not have germany in it's hash map anymore
        debtTable.printAllDebts("Japan");

        System.out.println("----------------");

        System.out.println("Japan Deleted From System");


        // deletes japan from the outer hashmap
        // goes into every country and deletes japan as an entry
        debtTable.deleteCountry("Japan");

        // japan won't show up because it was deleted
        debtTable.printAllDebts("Germany");

        // will not work because japan is deleted
        debtTable.printAllDebts("Japan");

        // this will calculate all the money that is owed to a country
        System.out.format("\nThe UK is owed %.2f million dollars", debtTable.totalOwedToCountry("United Kingdom"));

        System.out.format("\nThe UK owes %.2f million dollars to other countries", debtTable.totalDebtForCountry("United Kingdom"));

        System.out.format("\nThe UK's net balance is %.2f million dollars\n\n", debtTable.findNetBalance("United Kingdom"));


        // setting it up to test the middle man feature
        debtTable.addDebt("Belgium", "Germany", 30.5);

        // to be able to see the middle man relationship
        debtTable.printAllDebts("United States");
        debtTable.printAllDebts("Germany");


        System.out.println();

        // finds any middle man relationship in the entire debtTable
        ArrayList<String[]> middleMan2DArrayList = debtTable.findMiddleMan();

        System.out.println("The second name is the middle man. The first is " +
         "the one who would pay the third.");

        // these two for loops print out a 2d arraylist
        for (String[] row: middleMan2DArrayList) {

            for(String value : row) {

                System.out.print(value + ", ");

            }
            // adds a line in between the rows of the 2d array
            System.out.println();
        }



    }
    
    
}
