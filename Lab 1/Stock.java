public class Stock {
    private String symbol;
    private String name;
    private double price;
    private int quantity;

    // Default constructor
    public Stock() {
        this.symbol = "";
        this.name = "";
        this.price = 0.0;
        this.quantity = 0;
    }

    // Constructor with parameters
    public Stock(String symbol, String name, double price, int quantity) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Method to set the stock price
    public void setPrice(double price) {
        this.price = price;
    }

    // Method to update the quantity of stocks
    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }

    // Method to calculate the total value of the stock
    public double calculateValue() {
        return price * quantity;
    }

    // Method overloading to calculate the total value with tax
    public double calculateValue(double taxRate) {
        double totalValue = price * quantity;
        double taxAmount = totalValue * taxRate;
        return totalValue + taxAmount;
    }

    // Method to display stock information
    public void displayStock() {
        System.out.println("Stock Symbol: " + symbol);
        System.out.println("Stock Name: " + name);
        System.out.println("Stock Price: " + price);
        System.out.println("Stock Quantity: " + quantity);
        System.out.println("Total Value: " + calculateValue());
    }

    public static void main(String[] args) {
        Stock stock1 = new Stock("AAPL", "Apple Inc.", 150.0, 100);
        Stock stock2 = new Stock();
        Stock stock3 = new Stock("GOOGL", "Alphabet Inc.", 2700.0, 50);

        stock2.setPrice(200.0);
        stock2.updateQuantity(75);

        stock1.displayStock();
        stock2.displayStock();
        stock3.displayStock();
    }
}
