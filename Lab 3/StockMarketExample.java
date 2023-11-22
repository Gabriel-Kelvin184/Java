// Abstract class representing a generic stock
abstract class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    // Abstract method to calculate dividends
    public abstract double calculateDividend();

    // Concrete method to display stock information
    public void displayStockInfo() {
        System.out.println("Symbol: " + symbol);
        System.out.println("Price: $" + price);
    }
}

// Inheriting from Stock, representing a specific type of stock - CommonStock
class CommonStock extends Stock {
    private int totalShares;

    public CommonStock(String symbol, double price, int totalShares) {
        super(symbol, price);
        this.totalShares = totalShares;
    }

    public int getTotalShares() {
        return totalShares;
    }

    // Overriding the abstract method to calculate dividends for CommonStock
    @Override
    public double calculateDividend() {
        // For simplicity, let's say dividend is 2% of the total value of the stock
        return getPrice() * totalShares * 0.02;
    }
}

// Final class representing a specific type of stock - PreferredStock
final class PreferredStock extends Stock {
    private double fixedDividend;

    public PreferredStock(String symbol, double price, double fixedDividend) {
        super(symbol, price);
        this.fixedDividend = fixedDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }

    // Overriding the abstract method to calculate dividends for PreferredStock
    @Override
    public double calculateDividend() {
        // For simplicity, let's say dividend is the fixed percentage of the stock's par value
        return getFixedDividend() * 100;
    }
}

// Attempting to extend a final class (uncommenting this will result in a compilation error)
/*
class CustomStock extends PreferredStock {
    public CustomStock(String symbol, double price, double fixedDividend) {
        super(symbol, price, fixedDividend);
    }
}
*/

public class StockMarketExample {
    public static void main(String[] args) {
        // Example usage
        CommonStock commonStock = new CommonStock("ABC", 50.0, 1000);
        commonStock.displayStockInfo();
        System.out.println("Dividend: $" + commonStock.calculateDividend());

        PreferredStock preferredStock = new PreferredStock("XYZ", 40.0, 0.05);
        preferredStock.displayStockInfo();
        System.out.println("Dividend: $" + preferredStock.calculateDividend());
    }
}
