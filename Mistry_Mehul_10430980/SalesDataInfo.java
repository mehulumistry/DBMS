
/*

   "SalesDataInfo" class is used to set and get the quantities needed to run Query1


 */


public class SalesDataInfo {

    private String customer = null;
    private String product = null;
    private int quant = 0;
    private String Quarter = null;
    private int beforeAvg = 0;
    private int afterAvg = 0;
    private int min = 0;
    private int count = 0;
    private int countAfter = 0;


    public int countQuant = 0;
    public int sumQuant = 0;
    public int avgQuant = 0;

    public int custCountQuantity = 0;
    public int custsumQuant = 0;
    public int custAvgQuant = 0;

    public int prodCountQunatity = 0;
    public int prodsumQuant = 0;
    public int prodAvgQuant = 0;

    //////  Counter //////

    public void setCounter(int counter ){

        this.count = counter;

    }

    public int getCounter(){
        return count;
    }




    public void setCounterAfter(int counter ){

        this.countAfter = counter;

    }

    public int getCounterAfter(){
        return countAfter;
    }

    /////////// BeforeAvg



    public void setAfter(int avg){
        this.afterAvg = avg;
    }


    public int getAfter(){
       return afterAvg;
    }

    public int getMin(){
        return min;
    }


    ///////// minimum ////////

    public void setMin(int quant){
        this.min = quant;

    }


    ///////////// Quarter


    public void setQuarter(String Quarter){
        if (Quarter.equals("")) {
            this.Quarter = "Null";
        } else {
            this.Quarter = Quarter;
        }
    }

    public String getQuarter(){
        if(Quarter.equals("")){
            return "Null";
        }
        return Quarter;
    }

    ///////////////////////////

    public String getCust(){

        if(customer.equals("")){
            return "Null";
        }
        return customer;
    }

    public String getProd(){

        if(product.equals("")){
            return "NULL";
        }
        return product;
    }

    ///////////////////////

    public void setProd(String product){
        if (product.equals("")) {
            this.product = "Null";
        } else {
            this.product = product;
        }
    }


    public void setCust(String customer){

        if (customer.equals("")) {
            this.customer = "Null";
        } else {
            this.customer = customer;
        }
    }


    //////// setters for Quantity

    public void setQuant(int quant){
        this.quant = quant;
    }

    public void setAvgQuant(int avg){



        this.avgQuant = avg;
    }

    public void setCountQuant(int count){
         this.countQuant = count;
    }

    public void setTotalQuant(int quant){
        this.sumQuant = quant;
    }


    ///////// getters for Quantity

    public int getQty() {
        return quant;
    }

    public int getTotalQuant(){
        return sumQuant;
    }

    public int getAvgQuant(){


        return avgQuant;
    }


    public int getCountQuant(){
        return countQuant;
    }

//////  getters for customers

    public int getCustsumQuant(){
        return custsumQuant;
    }
    public int getCustCountQuantity(){
        return custCountQuantity;
    }
    public int getCustAvgQuant(){
        return custAvgQuant;
    }

///////// setters for Customer

    public int setCustCountQuantity(int count){
        return this.custCountQuantity = count;
    }
    public int setCustsumQuant(int sum){
        return this.custsumQuant = sum;
    }
    public int setCustAvgQuant(int avg){
        return this.custAvgQuant = avg;
    }


 ////// getters for Products

    public int getProdsumQuant(){
        return prodsumQuant;
    }
    public int getProdAvgQuant(){
        return prodAvgQuant;
    }
    public int getProdCountQunatity(){
        return prodCountQunatity;
    }

    ///////////  setters for Products

    public int setProdCountQunatity(int count){
        return this.prodCountQunatity = count;
    }
    public int setSumQuant(int sum) {
        return sumQuant = sum;
    }
    public int setProdsumQuant(int sum){
        return this.prodsumQuant = sum;
    }
    public int setProdAvgQuant(int avg){
        return this.prodAvgQuant = avg;
    }

}
