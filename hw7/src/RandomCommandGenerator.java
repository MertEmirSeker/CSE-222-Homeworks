import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * The RandomCommandGenerator class generates random stock commands for testing the StockDataManager.
 */
public class RandomCommandGenerator {

    private static final int NUM_COMMANDS = 1000;
    private static final int NUM_ADDS = 1000;
    private static final String FILE_NAME = "operations.txt";

    /**
     * The entry point of the command generator application. Generates random ADD, SEARCH, REMOVE, and UPDATE commands and writes them to a file.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // First generate 1000 ADD commands
        for (int i = 0; i < NUM_ADDS; i++) {
            String symbol = "SYM" + i;
            double price = 10 + (1000 - 10) * random.nextDouble();
            long volume = 1 + (1000000 - 1) * Math.abs(random.nextLong());
            long marketCap = 1 + (1000000000 - 1) * Math.abs(random.nextLong());
            sb.append(String.format("ADD %s %.2f %d %d%n", symbol, price, volume, marketCap));
        }

        // Generate remaining commands
        for (int i = 0; i < NUM_COMMANDS; i++) {
            int commandType = random.nextInt(3);
            int index = random.nextInt(NUM_ADDS);

            switch (commandType) {
                case 0: // SEARCH
                    sb.append(String.format("SEARCH SYM%d%n", index));
                    break;
                case 1: // REMOVE
                    sb.append(String.format("REMOVE SYM%d%n", index));
                    break;
                case 2: // UPDATE
                    String newSymbol = "SYM" + index;
                    double newPrice = 10 + (1000 - 10) * random.nextDouble();
                    long newVolume = 1 + (1000000 - 1) * Math.abs(random.nextLong());
                    long newMarketCap = 1 + (1000000000 - 1) * Math.abs(random.nextLong());
                    sb.append(String.format("UPDATE SYM%d %s %.2f %d %d%n", index, newSymbol, newPrice, newVolume, newMarketCap));
                    break;
            }
        }

        // Write commands to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Random commands have been generated and written to " + FILE_NAME);
    }
}
