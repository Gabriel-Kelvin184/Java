import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Stock {
    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

public class StockMarket {
    public static void main(String[] args) {
        // Creating a list of stocks
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock("AAPL", "Apple Inc.", 150.0));
        stockList.add(new Stock("GOOGL", "Alphabet Inc.", 2800.0));
        stockList.add(new Stock("MSFT", "Microsoft Corporation", 300.0));

        // Displaying the list of stocks
        System.out.println("Stock List:");
        for (Stock stock : stockList) {
            System.out.println(stock);
        }

        // Creating a map of stocks with symbol as the key
        Map<String, Stock> stockMap = new HashMap<>();
        for (Stock stock : stockList) {
            stockMap.put(stock.getSymbol(), stock);
        }

        // Retrieving a stock using its symbol
        String symbolToRetrieve = "AAPL";
        Stock retrievedStock = stockMap.get(symbolToRetrieve);
        if (retrievedStock != null) {
            System.out.println("\nRetrieved Stock with Symbol " + symbolToRetrieve + ": " + retrievedStock);
        } else {
            System.out.println("\nStock with Symbol " + symbolToRetrieve + " not found.");
        }
    }
}
