import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

class StockMarketSimulator {

    private static double stockPrice = 100.0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Create two threads for different tasks
        executor.submit(new StockPriceUpdater());
        executor.submit(new UserInterfaceUpdater());

        // Shutdown the executor when tasks are complete
        executor.shutdown();
    }

    // Thread to update stock prices
    static class StockPriceUpdater implements Runnable {
        @Override
        public void run() {
            try {
                int updatesCount = 0;
                while (updatesCount < 5) { // Limit the updates to 5 for demonstration purposes
                    Thread.sleep(2000);
                    stockPrice = stockPrice + (Math.random() - 0.5) * 2;
                    System.out.println("Updated Stock Price: " + stockPrice);
                    updatesCount++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Thread to update user interface with stock information
    static class UserInterfaceUpdater implements Runnable {
        @Override
        public void run() {
            try {
                int displayCount = 0;
                while (displayCount < 5) { // Limit the display updates to 5 for demonstration purposes
                    Thread.sleep(1000);
                    System.out.println("Displaying Stock Information: " + stockPrice);
                    displayCount++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
