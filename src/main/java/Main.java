import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // ex1();
        // ex2();
        // ex3();
        // ex4();
        // ex5();
        // ex6();
    }

    private static void ex1() {
        System.out.println("Todo...");
        var p1 = new Person();
        var p2 = new Person("Jon", "Smith");
        var p3 = new Person("Mary", "Jane", (byte) 12, 123456789);
        
        System.out.println(p1.speak());
        System.out.println(p2.speak());
        System.out.println(p3.speak());
        
        System.out.println(p1.toString());
        System.out.println(p2);
        System.out.println(p3);
        
        System.out.println("Person count: " + Person.getPersonCount());        
    }

    private static void ex2() {
        System.out.println("Todo...");
    }

    private static void ex3() {
        System.out.println("Todo...");
        var n1 = new AwesomeNumber(4);
        var n2 = new AwesomeNumber(2);
        var result1 = AwesomeNumber.add(n1, n2);
        var result2 = AwesomeNumber.subtract(n1, n2);
        System.out.println(result1);
        System.out.println(result2);        
    }
    private static void ex4(){
        var calculator = new Calculator();
        var result1 = calculator.add(1, 2);
        var result2 = calculator.subtract(4, 2);
        var result3 = calculator.divide(10, 5);
        var result4 = calculator.multiply(2, 2);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(calculator.getHistory());
    }
    private static void ex5() {
        System.out.println("Todo...");
        var shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(2.00F, 4, "Socks"));
        shoppingCart.addItem(new Item(10.00F, 2, "Shirts"));
        shoppingCart.calculateTotal();
        var invoice = shoppingCart.shipOrder("Jon Smith", "123 Green Street", "Austin", "TX", 78737);
        System.out.println(invoice);
    }

    private static void ex6() {
        System.out.println("Todo....");
    }
}

class Person{
    public static int count = 0;
    private String firstName;
    private String lastName;
    private byte age;
    private long ssn;
    public String speak;

    public Person(){
        speak = "I dont have a name" ;
        count++;
    }
    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        speak = "My name is " + this.firstName + this.lastName;
        count++;
    }
    public Person(String firstName, String lastName, byte age, long ssn){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.ssn = ssn;
        speak = "My name is " + this.firstName + this.lastName + ", I am "+this.age+" years old";
        count++;
    }

    public String speak(){
        return speak;
    }

    public static int getPersonCount(){
        return count;
    }

    public String toString(){
        if (this.firstName == null && this.lastName == null){
            return "N/A";
        } else {
       return "Firstname: " + this.firstName + " Lastname: " + this.lastName + " age: " + this.age;
        }
    }
}

class AwesomeNumber{
     int num;

    public AwesomeNumber(int num){
        this.num = num;
    }

    public static int add(AwesomeNumber x, AwesomeNumber y){
        return x.getNum() + y.getNum();
    }
    public int getNum(){
        return this.num;
    }
    public static int subtract(AwesomeNumber x, AwesomeNumber y){
        return x.getNum() - y.getNum();
    }

    
}
class Item{
    private float price;
    private int quantity;
    private String itemName;

    public Item(float price, int quantity, String itemName) {
        this.price = price;
        this.quantity = quantity;
        this.itemName = itemName;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }
}
class ShoppingCart {
   private ArrayList<Item> shoppingCart;
    private float total = 0;
    private double shippingCost = 0;
    private double salesTax;

    public ShoppingCart() {
        this.shoppingCart = new ArrayList<>();
    }

    public void addItem(Item i) {
        this.shoppingCart.add(i);
    }

    public void calculateTotal(){
        for(Item items: shoppingCart){
            total += (items.getPrice()*items.getQuantity());
        }
        calculateShiping();
        calculateSalesTax();
        total += (this.salesTax + this.shippingCost);
    }

    public void calculateShiping(){
        if(this.total < 10)
            this.shippingCost = 10;
    }

    public void calculateSalesTax(){
            this.salesTax = 0.1 * this.total;
    }
    public String getTotal(){
        return "$" + this.total;
    }

    public ArrayList<Item> getShoppingCart() {
        return shoppingCart;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public Invoice shipOrder(String name, String add, String city, String state, int zip ){
        return new Invoice(name, add, city, state, zip, this);
    }
}

class Invoice{
    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private int zipcode;
    private ShoppingCart shoppingCart;



    public Invoice(String name, String address, String city, String state, int zipcode, ShoppingCart shoppingCart) {
        this.name = name;
        this.streetAddress = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        String header = "Ship to:\n  " +
                        this.name + "\n  " +
                        this.streetAddress + "\n  " +
                        this.city + ", " + this.state + " " + this.zipcode + "\n\n" +
                        "Items\n" +
                        "-----\n" ;

        String itemList = "";
        for (Item i: shoppingCart.getShoppingCart()){
            itemList += i.getItemName() + " $" + i.getPrice() + " (" + i.getQuantity() + ")  $" + (i.getPrice()*i.getQuantity()) + "\n";
        }

        String shipping = "\nShipping: ";
        if(shoppingCart.getShippingCost() == 0)
            shipping += "Free\n\n";
        else
            shipping += shoppingCart.getShippingCost();

        String totalCost =  "Total Cost\n" +
                            "----------\n" +
                            shoppingCart.getTotal();

        return header + itemList + shipping + totalCost;
    }
}

interface Calculatable {

    Integer add(Integer number1, Integer number2);
    Integer subtract(Integer number1, Integer number2);
    Integer multiply(Integer number1, Integer number2);
    Integer divide(Integer number1, Integer number2);

    String getHistory();
}
class Calculator implements Calculatable{

    static ArrayList<String> history = new ArrayList<>();

    public Integer add(Integer number1, Integer number2) {
        int result = number1 + number2;
        history.add(number1 + " + " + number2 + " = " + result);
        return result;

    }

    public Integer subtract(Integer number1, Integer number2) {
        int result = number1 - number2;
        history.add(number1 + " - " + number2 + " = " + result);
        return result;
    }

    public Integer multiply(Integer number1, Integer number2) {
        int result = number1 * number2;
        history.add(number1 + " * " + number2 + " = " + result);
        return result;
    }

    public Integer divide(Integer number1, Integer number2) {
        int result = number1 / number2;
        history.add(number1 + " / " + number2 + " = " + result);
        return result;
    }

    public String getHistory() {
        return history.toString();
    }
}