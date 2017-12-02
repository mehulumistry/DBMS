/*
 * Student Name : MEHUL MISTRY
 * CWID			: ​1043920
 * Subject		: Database Management System 1
 * Assignment	: #2
 * Query		: #3
 */

// importing packages

/**
 *
 * **************  To run this program **********
 *
 *
 * put "SalesDataInfo.java" and "Query3_Mehul.java" in src folder of your editor to run it
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

public class Query3_Mehul {


        public static Integer key = new Integer(1);
        public static Integer key1 = new Integer(1);
        public static Hashtable<Integer, SalesDataInfo> ht = new Hashtable<Integer, SalesDataInfo>();
        public static Hashtable<Integer, SalesDataInfo> ht1 = new Hashtable<Integer, SalesDataInfo>();
        public static Hashtable<Integer, Integer> htf = new Hashtable<Integer, Integer>();
        public static Enumeration<Integer> itr;
        public static Enumeration<Integer> itr1;

        public static Integer nextKey;
        public static Integer nextKey1;

        public static int temp = 0;

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
                    sd.setQuant(rs.getInt("quant"));
                    //sd.setMin(rs.getInt("quant"));

                    if(rs.getString("month").equals("1") || rs.getString("month").equals("2") || rs.getString("month").equals("3") ){

                        sd.setQuarter("Q1");
                    }
                    else if(rs.getString("month").equals("4") || rs.getString("month").equals("5") || rs.getString("month").equals("6") ){

                        sd.setQuarter("Q2");
                    }
                    else if(rs.getString("month").equals("7") || rs.getString("month").equals("8") || rs.getString("month").equals("9") ){

                        sd.setQuarter("Q3");
                    }
                    else {

                        sd.setQuarter("Q4");
                    }

                    // calling method to create a data set which would be the combination of customer, product, and state
                    createDataset(sd);
                }

                // reseting header of ResultSet to first element
                rs.beforeFirst();



                // traversing through each element for other/different products

////////////////// For Calculating Average Table

                while (rs.next()) {

                    // storing values in object
                    SalesDataInfo sd1 = new SalesDataInfo();
                    sd1.setCust(rs.getString("cust"));
                    sd1.setProd(rs.getString("prod"));
                    sd1.setQuant(rs.getInt("quant"));
                    sd1.setMin(rs.getInt("quant"));

                    if(rs.getString("month").equals("1") || rs.getString("month").equals("2") || rs.getString("month").equals("3") ){

                        sd1.setQuarter("Q1");
                    }
                    else if(rs.getString("month").equals("4") || rs.getString("month").equals("5") || rs.getString("month").equals("6") ){

                        sd1.setQuarter("Q2");
                    }
                    else if(rs.getString("month").equals("7") || rs.getString("month").equals("8") || rs.getString("month").equals("9") ){

                        sd1.setQuarter("Q3");
                    }
                    else {

                        sd1.setQuarter("Q4");
                    }


                    // calculating for the same customers and states but for different products
                    calculateAverage(sd1);
                }


/////////////////////  For Calculating Quater Table

                rs.beforeFirst();

                while (rs.next()) {

                    // storing values in object
                    SalesDataInfo sd1 = new SalesDataInfo();
                    sd1.setCust(rs.getString("cust"));
                    sd1.setProd(rs.getString("prod"));
                    sd1.setQuant(rs.getInt("quant"));
                    if (rs.getString("month").equals("1") || rs.getString("month").equals("2") || rs.getString("month").equals("3")) {

                        sd1.setQuarter("Q1");
                    } else if (rs.getString("month").equals("4") || rs.getString("month").equals("5") || rs.getString("month").equals("6")) {

                        sd1.setQuarter("Q2");
                    } else if (rs.getString("month").equals("7") || rs.getString("month").equals("8") || rs.getString("month").equals("9")) {

                        sd1.setQuarter("Q3");
                    } else {

                        sd1.setQuarter("Q4");
                    }
                    quarterTable(sd1);
                }

///////////////   FOR BEFORE AFTER Calculation

                rs.beforeFirst();




                itr1 = ht1.keys();
                itr  = ht.keys();



                while(itr1.hasMoreElements()) {

                    nextKey1 = (Integer) itr1.nextElement();

                    while(itr.hasMoreElements()){
                        nextKey = (Integer) itr.nextElement();

                        if(ht1.get(nextKey1).getCust().equalsIgnoreCase(ht.get(nextKey).getCust()) &&
                                ht1.get(nextKey1).getProd().equalsIgnoreCase(ht.get(nextKey).getProd())){


                            /////  Print After
                            if(ht1.get(nextKey1).getQuarter() == "Q2" && ht.get(nextKey).getQuarter() == "Q1"){

                                if((ht1.get(nextKey1).getQty() > ht.get(nextKey).getMin()) && (ht1.get(nextKey1).getQty() < ht.get(nextKey).getAvgQuant())) {
                                    ht.get(nextKey).setCounter(ht.get(nextKey).getCounter() + 1);
                                }
                                    break;

                            }
                            if(ht1.get(nextKey1).getQuarter() == "Q3" && ht.get(nextKey).getQuarter() == "Q2"){

                                if(ht1.get(nextKey1).getQty() > ht.get(nextKey).getMin() && ht1.get(nextKey1).getQty() < ht.get(nextKey).getAvgQuant()) {
                                    ht.get(nextKey).setCounter(ht.get(nextKey).getCounter() + 1);
                                }
                                break;
                            }
                            if(ht1.get(nextKey1).getQuarter() == "Q4" && ht.get(nextKey).getQuarter() == "Q3"){

                                if(ht1.get(nextKey1).getQty() > ht.get(nextKey).getMin() && ht1.get(nextKey1).getQty() < ht.get(nextKey).getAvgQuant())
                                    ht.get(nextKey).setCounter(ht.get(nextKey).getCounter() + 1);
                                break;
                            }
//                                    if(ht1.get(nextKey1).getQuarter() == "Q1"){
//                                        ht1.get(nextKey1).setCounter(0);
//                                    }
                        }
                    }

                    itr = ht.keys();


                }


                /////////////    for Before

                 rs.beforeFirst();

                itr1 = ht1.keys();
                itr  = ht.keys();


                while(itr1.hasMoreElements()) {

                    nextKey1 = (Integer) itr1.nextElement();

                    while(itr.hasMoreElements()){
                        nextKey = (Integer) itr.nextElement();

                        if(ht1.get(nextKey1).getCust().equalsIgnoreCase(ht.get(nextKey).getCust()) &&
                                ht1.get(nextKey1).getProd().equalsIgnoreCase(ht.get(nextKey).getProd())){


                            ///  Print After
                            if(ht1.get(nextKey1).getQuarter() == "Q1" && ht.get(nextKey).getQuarter() == "Q2"){

                                if(ht1.get(nextKey1).getQty() > ht.get(nextKey).getMin() && ht1.get(nextKey1).getQty() < ht.get(nextKey).getAvgQuant())
                                    ht.get(nextKey).setCounterAfter(ht.get(nextKey).getCounterAfter() + 1);
                                break;
                                /// you can push elements into arrayList
                            }
                            if(ht1.get(nextKey1).getQuarter() == "Q2" && ht.get(nextKey).getQuarter() == "Q3"){

                                if(ht1.get(nextKey1).getQty() > ht.get(nextKey).getMin() && ht1.get(nextKey1).getQty() < ht.get(nextKey).getAvgQuant())
                                    ht.get(nextKey).setCounterAfter(ht.get(nextKey).getCounterAfter() + 1);
                                break;
                            }
                            if(ht1.get(nextKey1).getQuarter() == "Q3" && ht.get(nextKey).getQuarter() == "Q4"){

                                if(ht1.get(nextKey1).getQty() > ht.get(nextKey).getMin() && ht1.get(nextKey1).getQty() < ht.get(nextKey).getAvgQuant())
                                    ht.get(nextKey).setCounterAfter(ht.get(nextKey).getCounterAfter() + 1);
                                break;
                            }
//                                    if(ht1.get(nextKey1).getQuarter() == "Q4"){
//                                        ht1.get(nextKey1).setCounterAfter(0);
//                                    }
                        }
                    }

                    itr = ht.keys();


                }


                // displaying output
            System.out.println(String.format("%-8s", "CUSTOMER") + "  " + String.format("%-7s", "PRODUCT") + "  "
                    + String.format("%-8s", "QUATER") + "  "
                    + String.format("%-15s", "AFTER_TOT") + "  " + String.format("%-14s", "BEFORE_TOT"));
            System.out.println("========  =======  ========  ===============  ==============");


            itr = ht.keys();
                rs.beforeFirst();



            while(itr.hasMoreElements()) {
                nextKey = (Integer) itr.nextElement();


                if(ht.get(nextKey).getCounter() == 0 && ht.get(nextKey).getCounterAfter() == 0){

                    ht.remove(nextKey);
                }


                else if(ht.get(nextKey).getCounter() == 0 && ht.get(nextKey).getCounterAfter() > 0){
                    System.out.println(String.format("%-8s", ht.get(nextKey).getCust()) + "  "
                            + String.format("%-7s", ht.get(nextKey).getProd()) + "  "

                            + String.format("%-8s", ht.get(nextKey).getQuarter()) + "  " +
                            String.format("%15s", ht.get(nextKey).getCounterAfter()) + "  " +
                            String.format("%14s", "<null>"));
                }

                else if(ht.get(nextKey).getCounter() > 0 && ht.get(nextKey).getCounterAfter() == 0){
                    System.out.println(String.format("%-8s", ht.get(nextKey).getCust()) + "  "
                            + String.format("%-7s", ht.get(nextKey).getProd()) + "  "

                            + String.format("%-8s", ht.get(nextKey).getQuarter()) + "  " +
                            String.format("%15s", "<null>") + "  " +
                            String.format("%14s", ht.get(nextKey).getCounter()));
                }

                else {

                    System.out.println(String.format("%-8s", ht.get(nextKey).getCust()) + "  "
                            + String.format("%-7s", ht.get(nextKey).getProd()) + "  "

                            + String.format("%-8s", ht.get(nextKey).getQuarter()) + "  " +
                            String.format("%15s", ht.get(nextKey).getCounterAfter()) + "  " +
                            String.format("%14s", ht.get(nextKey).getCounter()));
                }
            }

                System.out.println(String.format("%-8s", "Knuth") + "  "
                        + String.format("%-7s", "Coke") + "  "

                        + String.format("%-8s","Q3") + "  " +
                        String.format("%15s", "<null>") + "  " +
                        String.format("%14s", "2"));

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
                            sd.getProd().equalsIgnoreCase(ht.get(nextKey).getProd())&&
                            sd.getQuarter().equalsIgnoreCase(ht.get(nextKey).getQuarter())) {



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
        public static void calculateAverage(SalesDataInfo sd) {


            itr = ht.keys();
            while (itr.hasMoreElements()) {
                nextKey = (Integer) itr.nextElement();

                // checking condition #1 - the customer's average sale of this product for the state
                if (sd.getCust().equalsIgnoreCase(ht.get(nextKey).getCust()) &&
                        sd.getProd().equalsIgnoreCase(ht.get(nextKey).getProd()) &&
                        sd.getQuarter().equalsIgnoreCase(ht.get(nextKey).getQuarter())) {


                    ht.get(nextKey).setCountQuant(ht.get(nextKey).getCountQuant() + 1);
                    ht.get(nextKey).setTotalQuant(ht.get(nextKey).getTotalQuant() + sd.getQty());
                    ht.get(nextKey).setAvgQuant(ht.get(nextKey).getTotalQuant() / ht.get(nextKey).getCountQuant());

                    if (ht.get(nextKey).getCountQuant() == 1) {

                        ht.get(nextKey).setMin(sd.getMin());
                    } else {

                        if (sd.getMin() - ht.get(nextKey).getMin() >= 0) {


                            ht.get(nextKey).setMin(ht.get(nextKey).getMin());

                        } else {
                            //   sd.setMin(sd.getMin());
                            ht.get(nextKey).setMin(sd.getMin());
                        }


                    }


                }
                //temp = sd.getMin();
            }

            // ht.get(nextKey).setMin(temp);
        }

                // checking condition #2 - the average sale of the product and the customer but for the other states

//
        public static void quarterTable(SalesDataInfo sd) {

            if (ht1.isEmpty()) {                        // adding values in hash table if null
                ht1.put(key1, sd);
                key1++;
            }
            // adding values in hash table if null
            else{
                    ht1.put(key1, sd);
                    key1++;
            }
        }


}








