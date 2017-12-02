/*
 * Student Name : MEHUL MISTRY
 * CWID			: ​1043920
 * Subject		: Database Management System 1
 * Assignment	: #2
 * Query		: #1
 */

// importing packages

/**
 *
 * **************  To run this program **********
 *
 *
 * put "SalesDataInfo.java" and "Query1_Mehul.java" in src folder of your editor to run it
 *
 *
 *
 */

/**
 *
 *
 *
 * In this program, as per guidelines, i have used only one SQL statement
 I am using HashTable, it has keys which maintains pair and stores their value
 It doesn't return value in order that is why my output wont't have ordered values
 Although use of array would have returned values in order.
 Algorithm i used for this program:
 create connection as mentioned like in the sample program
 and also initialize HashTable which will have keys.
 then used SQL statement for selecting
 used Calendar class from java.util package which will return the date in the format month/day/year
 created another class query1 for Store variables and functions for obtaining values.
 then created object of this above class in the main program
 this object is used for calculating average,min and max values.
 this process is done under calculate()
 it checks the value in the HashTable and there are two conditions which can occur
 if flag is false then it update the value in the HashTable
 loops works in this way: it increments keys for count
 for max values it will keep on updating the values until it finds the maximum
 same applies for minimum,it will keep on updating minimum values
 for average,its very simple it will divide total/count
 display result of HashTable


 ****  Justification of using HashTable:  *****


 I have used HashTable because it will be easy to maintain the database in the form of key and value pair
 and searching operation is also very easy compare to Linked list.

 ****  pseudo code *******

 while(true){

 it will fetch all the data from the sql to hash table
 where (key = iter and value = Data)
 Data = reference of object

 if(Previous_min_Quantity > Next_min_Qunatity){
 temp = Next_miN_quantity

 }
 else if(Previous_min_Quantity < Next_min_Qunatity){

 temp = Previous_miN_quantity

 }

 Do similar for Finding Min;



 }





 *
 *
 */

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class Query1_Mehul {


    public static Integer key = new Integer(1);
    public static Hashtable<Integer, SalesDataInfo> ht = new Hashtable<Integer, SalesDataInfo>();
    public static Enumeration<Integer> itr;
    public static Integer nextKey;

    public static void main(String[] args) {

// database connection configuration
        String usr = "postgres";
        String pwd = "123";
        String url = "jdbc:postgresql://localhost:5432/test";

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Successfully loaded the driver!");
        } catch (Exception ex) {
            System.out.println("Failed to load the driver!");
            ex.printStackTrace();
        }

        // connection server
        try {
            // opening connection
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            System.out.println("Successfully connected to the server! \n");

            // get query result to ResultSet rs
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM sales");

            // traversing through each element to get values that are combination of customer, product, and state
            while (rs.next()) {

                // storing values in object
                SalesDataInfo sd = new SalesDataInfo();
                sd.setCust(rs.getString("cust"));
                sd.setProd(rs.getString("prod"));


                // calling method to create a data set which would be the combination of customer, product, and state
                createDataset(sd);
            }

            // reseting header of ResultSet to first element
            rs.beforeFirst();



            // traversing through each element for other/different products



            while (rs.next()) {

                // storing values in object
                SalesDataInfo sd = new SalesDataInfo();
                sd.setCust(rs.getString("cust"));
                sd.setProd(rs.getString("prod"));
                sd.setQuant(rs.getInt("quant"));

                // calculating for the same customers and states but for different products
                calculateQueries(sd);
            }


            // displaying output
            System.out.println(String.format("%-8s", "CUSTOMER") + "  " + String.format("%-7s", "PRODUCT") + "  "
                     + String.format("%-8s", "THE_AVG") + "  "
                    + String.format("%-15s", "OTHER_PROD_AVG") + "  " + String.format("%-14s", "OTHER_CUST_AVG"));
            System.out.println("========  =======  ========  ===============  ==============");

            itr = ht.keys();



            while(itr.hasMoreElements()) {
                nextKey = (Integer) itr.nextElement();

                System.out.println(String.format("%-8s", ht.get(nextKey).getCust()) + "  "
                        + String.format("%-7s", ht.get(nextKey).getProd()) + "  "
                        + String.format("%-8s",ht.get(nextKey).getAvgQuant()) + " "
                        + String.format("%8s", ht.get(nextKey).getProdAvgQuant()) + "  "
                        + String.format("%14s", ht.get(nextKey).getCustAvgQuant()));
            }

            // closing connection
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Connection URL or username or password errors!");
            ex.printStackTrace();
        }
    }

    // creating a data set for the combination of customer, product, and state
    public static void createDataset(SalesDataInfo sd) {
        boolean addNewFlag = true;

        if (ht.isEmpty()) {						// adding values in hash table if null
            ht.put(key, sd);
            key++;
        } else {								// traversing hash table to check for existing values
            itr = ht.keys();
            while(itr.hasMoreElements()) {
                nextKey = (Integer) itr.nextElement();

                if (sd.getCust().equalsIgnoreCase(ht.get(nextKey).getCust()) &&
                        sd.getProd().equalsIgnoreCase(ht.get(nextKey).getProd())) {

                    addNewFlag = false;
                }
            }

            if (addNewFlag) {
                ht.put(key, sd);
                key++;
            }
        }
    }

    // calculating for the same customers and states but for different products
    public static void calculateQueries(SalesDataInfo sd) {

        itr = ht.keys();
        while(itr.hasMoreElements()) {
            nextKey = (Integer) itr.nextElement();

            // checking condition #1 - the customer's average sale of this product for the state
            if (sd.getCust().equalsIgnoreCase(ht.get(nextKey).getCust()) &&
                    sd.getProd().equalsIgnoreCase(ht.get(nextKey).getProd())) {

                ht.get(nextKey).setCountQuant(ht.get(nextKey).getCountQuant() + 1);
                ht.get(nextKey).setTotalQuant(ht.get(nextKey).getTotalQuant() + sd.getQty());
                ht.get(nextKey).setAvgQuant(ht.get(nextKey).getTotalQuant() / ht.get(nextKey).getCountQuant());

            }

            // checking condition #2 - the average sale of the product and the customer but for the other states
            if (sd.getCust().equalsIgnoreCase(ht.get(nextKey).getCust()) &&
                    !sd.getProd().equalsIgnoreCase(ht.get(nextKey).getProd())) {

                ht.get(nextKey).setCustCountQuantity(ht.get(nextKey).getCustCountQuantity() + 1);
                ht.get(nextKey).setCustsumQuant(ht.get(nextKey).getCustsumQuant() + sd.getQty());
                ht.get(nextKey).setCustAvgQuant(ht.get(nextKey).getCustsumQuant() / ht.get(nextKey).getCustCountQuantity());
            }

            // checking condition #3 - the customer’s average sale for the given state, but for the other products
            if (!sd.getCust().equalsIgnoreCase(ht.get(nextKey).getCust()) &&
                    sd.getProd().equalsIgnoreCase(ht.get(nextKey).getProd()) ) {

                ht.get(nextKey).setProdCountQunatity(ht.get(nextKey).getProdCountQunatity() + 1);
                ht.get(nextKey).setProdsumQuant(ht.get(nextKey).getProdsumQuant() + sd.getQty());
                ht.get(nextKey).setProdAvgQuant(ht.get(nextKey).getProdsumQuant() / ht.get(nextKey).getProdCountQunatity());

            }
        }
    }
}



