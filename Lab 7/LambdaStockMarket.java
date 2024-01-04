import java.util.ArrayList;
import java.util.List;

// Define a Stock class
class Stock {
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

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}

// Define a functional interface for stock market operations
@FunctionalInterface
interface StockMarketOperation {
    void perform(Stock stock);
}

// Define a StockMarket class to execute operations on a list of stocks
class StockMarket {
    private List<Stock> stockList = new ArrayList<>();

    public void addStock(Stock stock) {
        stockList.add(stock);
    }

    public void executeOperation(StockMarketOperation operation) {
        for (Stock stock : stockList) {
            operation.perform(stock);
        }
    }
}

public class LambdaStockMarket {
    public static void main(String[] args) {
        // Create some stock instances
        Stock appleStock = new Stock("AAPL", 150.0);
        Stock googleStock = new Stock("GOOGL", 2500.0);
        Stock microsoftStock = new Stock("MSFT", 300.0);

        // Create a StockMarket instance and add stocks
        StockMarket stockMarket = new StockMarket();
        stockMarket.addStock(appleStock);
        stockMarket.addStock(googleStock);
        stockMarket.addStock(microsoftStock);

        // Use lambda expressions to define buy and sell operations
        StockMarketOperation buyOperation = stock -> System.out.println("Buying " + stock);
        StockMarketOperation sellOperation = stock -> System.out.println("Selling " + stock);

        // Execute buy and sell operations using the lambda expressions
        System.out.println("Executing Buy Operation:");
        stockMarket.executeOperation(buyOperation);

        System.out.println("\nExecuting Sell Operation:");
        stockMarket.executeOperation(sellOperation);
    }
}
