import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Main class for running the stock management application and performing performance analysis on AVL tree operations.
 */
public class Main {
    /**
     * The entry point of the application. Reads commands from the input file and processes them.
     * 
     * @param args The command-line arguments. The first argument should be the input file path.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Perform a simple performance analysis
        int size = 1000;
        AddPerformanceAnalysis(manager, size);
        SearchPerformanceAnalysis(manager, size);
        RemovePerformanceAnalysis(manager, size);
        UpdatePerformanceAnalysis(manager, size);
    }

    /**
     * Processes a single command line and executes the corresponding operation on the StockDataManager.
     * 
     * @param line    The command line to be processed.
     * @param manager The StockDataManager instance to perform operations on.
     */
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch (command) {
            case "ADD":
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                break;
            case "REMOVE":
                manager.removeStock(tokens[1]);
                break;
            case "SEARCH":
                Stock stock = manager.searchStock(tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                manager.updateStock(tokens[1], tokens[2], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    /**
     * Performs performance analysis for the ADD operation on the StockDataManager.
     * 
     * @param manager The StockDataManager instance to perform operations on.
     * @param size    The number of operations to perform for the analysis.
     */
    private static void AddPerformanceAnalysis(StockDataManager manager, int size) {
        ArrayList<Integer> xAxis = new ArrayList<>();
        ArrayList<Long> yAxis = new ArrayList<>();
        long totalTime = 0;

        for (int i = 0; i < size; i++) {
            long operationStart = System.nanoTime();
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            long operationTime = System.nanoTime() - operationStart;
            totalTime += operationTime;
            if ((i + 1) % 100 == 0) {
                xAxis.add(i + 1);
                yAxis.add(totalTime);
            }
        }

        long averageTime = totalTime / size;
        System.out.println("Average ADD time: " + averageTime + " ns");

        SwingUtilities.invokeLater(() -> {
            GUIVisualization frame = new GUIVisualization("scatter", xAxis, yAxis, "Add Operation Performance");
            frame.setVisible(true);
        });
    }

    /**
     * Performs performance analysis for the SEARCH operation on the StockDataManager.
     * 
     * @param manager The StockDataManager instance to perform operations on.
     * @param size    The number of operations to perform for the analysis.
     */
    private static void SearchPerformanceAnalysis(StockDataManager manager, int size) {
        ArrayList<Integer> xAxis = new ArrayList<>();
        ArrayList<Long> yAxis = new ArrayList<>();
        long totalTime = 0;

        for (int i = 0; i < size; i++) {
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
        }

        for (int i = 0; i < size; i++) {
            long operationStart = System.nanoTime();
            manager.searchStock("SYM" + i);
            long operationTime = System.nanoTime() - operationStart;
            totalTime += operationTime;

            if ((i + 1) % 100 == 0) {
                xAxis.add(i + 1);
                yAxis.add(totalTime);
            }
        }

        long averageTime = totalTime / size;
        System.out.println("Average SEARCH time: " + averageTime + " ns");

        SwingUtilities.invokeLater(() -> {
            GUIVisualization frame = new GUIVisualization("scatter", xAxis, yAxis, "Search Operation Performance");
            frame.setVisible(true);
        });
    }

    /**
     * Performs performance analysis for the REMOVE operation on the StockDataManager.
     * 
     * @param manager The StockDataManager instance to perform operations on.
     * @param size    The number of operations to perform for the analysis.
     */
    private static void RemovePerformanceAnalysis(StockDataManager manager, int size) {
        ArrayList<Integer> xAxis = new ArrayList<>();
        ArrayList<Long> yAxis = new ArrayList<>();
        long totalTime = 0;

        for (int i = 0; i < size; i++) {
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
        }

        for (int i = 0; i < size; i++) {
            long operationStart = System.nanoTime();
            manager.removeStock("SYM" + i);
            long operationTime = System.nanoTime() - operationStart;
            totalTime += operationTime;
            if ((i + 1) % 100 == 0) {
                xAxis.add(i + 1);
                yAxis.add(totalTime);
            }
        }

        long averageTime = totalTime / size;
        System.out.println("Average REMOVE time: " + averageTime + " ns");

        SwingUtilities.invokeLater(() -> {
            GUIVisualization frame = new GUIVisualization("scatter", xAxis, yAxis, "Remove Operation Performance");
            frame.setVisible(true);
        });
    }

    /**
     * Performs performance analysis for the UPDATE operation on the StockDataManager.
     * 
     * @param manager The StockDataManager instance to perform operations on.
     * @param size    The number of operations to perform for the analysis.
     */
    private static void UpdatePerformanceAnalysis(StockDataManager manager, int size) {
        ArrayList<Integer> xAxis = new ArrayList<>();
        ArrayList<Long> yAxis = new ArrayList<>();
        long totalTime = 0;

        for (int i = 0; i < size; i++) {
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
        }

        for (int i = 0; i < size; i++) {
            long operationStart = System.nanoTime();
            manager.updateStock("SYM" + i, "SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            long operationTime = System.nanoTime() - operationStart;
            totalTime += operationTime;

            if ((i + 1) % 100 == 0) {
                xAxis.add(i + 1);
                yAxis.add(totalTime);
            }
        }

        long averageTime = totalTime / size;
        System.out.println("Average UPDATE time: " + averageTime + " ns");

        SwingUtilities.invokeLater(() -> {
            GUIVisualization frame = new GUIVisualization("scatter", xAxis, yAxis, "Update Operation Performance");
            frame.setVisible(true);
        });
    }
}
