/**
 * The StockDataManager class manages stock data using an AVL tree.
 */
public class StockDataManager {
    private AVLTree avlTree;

    /**
     * Constructs a new StockDataManager with an empty AVL tree.
     */
    public StockDataManager() {
        avlTree = new AVLTree();
    }

    /**
     * Adds a new stock or updates an existing stock in the AVL tree.
     *
     * @param symbol    The stock symbol.
     * @param price     The stock price.
     * @param volume    The stock volume.
     * @param marketCap The stock market capitalization.
     */
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        Stock existingStock = avlTree.search(symbol);
        if (existingStock != null) {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } else {
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
    }

    /**
     * Removes a stock from the AVL tree.
     *
     * @param symbol The stock symbol to be removed.
     */
    public void removeStock(String symbol) {
        avlTree.delete(symbol);
    }

    /**
     * Searches for a stock in the AVL tree.
     *
     * @param symbol The stock symbol to search for.
     * @return The Stock object if found, otherwise null.
     */
    public Stock searchStock(String symbol) {
        return avlTree.search(symbol);
    }

    /**
     * Updates the details of a stock, including its symbol.
     *
     * @param oldSymbol   The current symbol of the stock.
     * @param newSymbol   The new symbol of the stock.
     * @param newPrice    The new price of the stock.
     * @param newVolume   The new volume of the stock.
     * @param newMarketCap The new market capitalization of the stock.
     */
    public void updateStock(String oldSymbol, String newSymbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = avlTree.search(oldSymbol);
        if (stock != null) {
            avlTree.delete(oldSymbol); // Remove old stock
            stock.setSymbol(newSymbol); // Update symbol
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
            avlTree.insert(stock); // Insert updated stock with new symbol
        }
    }

    /**
     * Prints the in-order traversal of the AVL tree.
     */
    public void printInOrder() {
        avlTree.inOrderTraversal();
    }

    /**
     * Prints the pre-order traversal of the AVL tree.
     */
    public void printPreOrder() {
        avlTree.preOrderTraversal();
    }

    /**
     * Prints the post-order traversal of the AVL tree.
     */
    public void printPostOrder() {
        avlTree.postOrderTraversal();
    }

    /**
     * The main method for testing the StockDataManager class.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        StockDataManager manager = new StockDataManager();
        manager.addOrUpdateStock("AAPL", 150.0, 1000000, 2500000000L);
        manager.addOrUpdateStock("GOOGL", 2800.0, 500000, 1500000000L);
        System.out.println(manager.searchStock("AAPL"));
        manager.updateStock("AAPL", "APPL", 155.0, 1100000, 2600000000L);
        System.out.println(manager.searchStock("APPL"));
        manager.removeStock("GOOGL");
        System.out.println(manager.searchStock("GOOGL"));

        // Perform a simple performance analysis
        performPerformanceAnalysis(manager, 1000);
    }

    /**
     * Performs a simple performance analysis on the StockDataManager.
     *
     * @param manager The StockDataManager instance to perform operations on.
     * @param size    The number of operations to perform for the analysis.
     */
    private static void performPerformanceAnalysis(StockDataManager manager, int size) {
        long startTime, endTime;

        // Measure time for ADD operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
        }
        endTime = System.nanoTime();
        System.out.println("Average ADD time: " + (endTime - startTime) / size + " ns");

        // Measure time for SEARCH operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.searchStock("SYM" + i);
        }
        endTime = System.nanoTime();
        System.out.println("Average SEARCH time: " + (endTime - startTime) / size + " ns");

        // Measure time for REMOVE operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.removeStock("SYM" + i);
        }
        endTime = System.nanoTime();
        System.out.println("Average REMOVE time: " + (endTime - startTime) / size + " ns");

        // Print traversals
        System.out.println("In-order traversal:");
        manager.printInOrder();
        System.out.println("Pre-order traversal:");
        manager.printPreOrder();
        System.out.println("Post-order traversal:");
        manager.printPostOrder();
    }
}
