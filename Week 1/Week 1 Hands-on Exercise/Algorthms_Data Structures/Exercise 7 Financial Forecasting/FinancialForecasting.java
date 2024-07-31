public class FinancialForecasting {

    // Method to calculate future value using recursion
    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
        // Base case: if periods is 0, return the present value
        if (periods == 0) {
            return presentValue;
        }
        
        // Recursive case: calculate future value for one less period
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, periods - 1);
    }

    public static void main(String[] args) {
        // Example values
        double presentValue = 1000; // Current investment
        double growthRate = 0.05;    // Growth rate (5%)
        int periods = 10;             // Number of years

        // Calculate future value
        double futureValue = calculateFutureValue(presentValue, growthRate, periods);

        // Print the result
        System.out.printf("The future value after %d years is: %.2f%n", periods, futureValue);
    }
}
