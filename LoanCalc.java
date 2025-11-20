public class LoanCalc {
    
    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 
    
    public static void main(String[] args) {        
        // Gets the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
        System.out.println(); // שורה ריקה

        // Computes the periodical payment using brute force search
        System.out.print("Periodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
        System.out.println(); // שורה ריקה

        // Computes the periodical payment using bisection search
        System.out.print("Periodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    private static double endBalance(double loan, double rate, int n, double payment) { 
        double balance = loan;
        for (int i = 0; i < n; i++) {
            balance = (balance - payment) * (1 + rate / 100);
        }
        return balance;
    }
    
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double g = loan / n; 
        while (endBalance(loan, rate, n, g) > 0) {
            g += epsilon;
            iterationCounter++;
        }
        return g;
    }
    
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;
        double L = loan / n;
        double H = loan;
        while (endBalance(loan, rate, n, H) > 0) { H *= 2; }
        double g = (L + H) / 2;
        while ((H - L) > epsilon) {
            if (endBalance(loan, rate, n, g) > 0) { L = g; } else { H = g; }
            g = (L + H) / 2;
            iterationCounter++;
        }
        return g;
    }
}