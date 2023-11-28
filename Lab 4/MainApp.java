// File: MainApp.java
import stock.Stock;

public class MainApp {
    public static void main(String[] args) {
        Stock stock = new Stock("AAPL", "Apple Inc.", 150.0, 100);

        stock.displayStock();

        // Using the interface method
        double stockIndex = stock.getStockIndex();
        System.out.println("Current Stock Index: " + stockIndex);
    }
}
