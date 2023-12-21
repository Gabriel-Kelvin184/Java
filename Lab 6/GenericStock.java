import java.util.ArrayList;
import java.util.List;

// Generics interface for a financial instrument
interface FinancialInstrument {
    String getSymbol();
    double getPrice(); // Added this method to the interface
}


// Generics class representing a stock
class Stock implements FinancialInstrument {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

// Generics class representing a bond
class Bond implements FinancialInstrument {
    private String symbol;
    private double faceValue;

    public Bond(String symbol, double faceValue) {
        this.symbol = symbol;
        this.faceValue = faceValue;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getPrice() {
        return faceValue; // Assuming face value is the price for a bond
    }
}


// Generics class for a portfolio that can hold different types of financial instruments
class Portfolio<T extends FinancialInstrument> {
    private List<T> holdings;

    public Portfolio() {
        this.holdings = new ArrayList<>();
    }

    // Method to add a financial instrument to the portfolio
    public void addInstrument(T instrument) {
        holdings.add(instrument);
    }

    // Method to calculate the total value of the portfolio
    public double calculatePortfolioValue() {
        double totalValue = 0.0;
        for (T instrument : holdings) {
            // Assuming there's a method getPrice() common to both stocks and bonds
            totalValue += instrument.getPrice();
        }
        return totalValue;
    }
}

public class GenericStock {
    public static void main(String[] args) {
        // Create a stock
        Stock googleStock = new Stock("GOOGL", 2500.0);

        // Create a bond
        Bond governmentBond = new Bond("US123", 1000.0);

        // Create a portfolio for stocks
        Portfolio<Stock> stockPortfolio = new Portfolio<>();
        stockPortfolio.addInstrument(googleStock);

        // Create a portfolio for bonds
        Portfolio<Bond> bondPortfolio = new Portfolio<>();
        bondPortfolio.addInstrument(governmentBond);

        // Calculate total value of the portfolios
        double stockPortfolioValue = stockPortfolio.calculatePortfolioValue();
        double bondPortfolioValue = bondPortfolio.calculatePortfolioValue();

        System.out.println("Stock Portfolio Value: $" + stockPortfolioValue);
        System.out.println("Bond Portfolio Value: $" + bondPortfolioValue);
    }
}
