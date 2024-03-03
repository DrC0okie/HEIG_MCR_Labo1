package mcr;

/**
 * Entry point of the clock application.
 * Initializes the application based on CLI arguments specifying the number of clocks to manage.
 * @author Samuel Roland, Timothée Van Hove
 */
public class App {

    private static final int MIN = 1;
    private static final int MAX = 9;

    public static void main(String[] args) {
        // Check if there is at least one argument provided
        if (args.length > 0) {
            try {
                // Parse the first argument to an integer
                int value = Integer.parseInt(args[0]);

                // Check if the value is in the accepted range
                if (value >= MIN && value <= MAX)
                    new ControlPanel(value);
                else
                    System.out.printf("Error: The provided value must be between %d and %d.%n", MIN, MAX);

            } catch (NumberFormatException e) {
                // Handle the case where the argument is not an integer
                System.out.println("Error: The provided argument is not a valid integer.");
            }
        } else {
            System.out.printf("Error: No argument provided. Please provide an integer value " +
                    "between %d and %d.%n", MIN, MAX);
        }
    }
}
