// File: stock/Stock.java
package stock;

public class Stock implements StockMarket {
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

    // Implementing the interface method
    @Override
    public double getStockIndex() {
        return 5000.0; // Dummy value, replace with real implementation
    }

    // Method to calculate the total value of the stock
    public double calculateValue() {
        return price * quantity;
    }

    // Other methods...

    // Method to display stock information
    public void displayStock() {
        System.out.println("Stock Symbol: " + symbol);
        System.out.println("Stock Name: " + name);
        System.out.println("Stock Price: " + price);
        System.out.println("Stock Quantity: " + quantity);
        System.out.println("Total Value: " + calculateValue());
    }
}
