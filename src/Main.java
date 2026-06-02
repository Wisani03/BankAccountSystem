import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankGUI myWindow = new BankGUI();
                myWindow.setVisible(true);
            }
        });

        System.out.println("=========================================");
        System.out.println("   BANKING SYSTEM DEVELOPMENT    ");
        System.out.println("=========================================\n");


        // 1. TESTING THE SAVINGS ACCOUNT
        // ------------------------------------------------------------
        System.out.println("=== 1. Savings Account ===");
        SavingsAccount savings = new SavingsAccount("SAV-4412", "Wisani Mabunda", 5000.0, 6.5);
        System.out.println("Initial: " + savings.toString());

        // Apply interest (6.5% of R5000 = R325)
        savings.applyInterest();
        System.out.println("Updated: " + savings.toString() + "\n");



        // 2. TESTING THE CURRENT (CHEQUE) ACCOUNT WITH OVERDRAFT
        // ------------------------------------------------------------
        System.out.println("=== 2. Current Account ===");
        // Starts with R1000 balance, but has a R3000 overdraft limit
        CurrentAccount cheque = new CurrentAccount("CHQ-5502", "Wisani Mabunda", 1000.0, 3000.0);
        System.out.println("Initial: " + cheque.toString());

        // Attempting to withdraw R2500....this triggers overdraft
        System.out.println("\nAction: Withdrawing R2500...");
        cheque.withdraw(2500.0);
        System.out.println("Updated: " + cheque.toString() + "\n");



        // 3. TESTING THE SHORT TERM FIXED ACCOUNT
        // ------------------------------------------------------------
        System.out.println("=== 3. Short-Term Investment ===");
        ShortTerm shortTermAcc = new ShortTerm("FIX-9901", "Tresina Mabunda", 10000.0);
        System.out.println("Initial: " + shortTermAcc.toString());

        // Add the flat R150 cash sign-up bonus
        shortTermAcc.addShortTermBonus();

        // Execute early withdrawal (R2000 withdrawal + 2% penalty of R40 = R2040 deduction)
        System.out.println("\nAction: Early withdrawal of R2000...");
        shortTermAcc.withdraw(2000.0);
        System.out.println("Updated: " + shortTermAcc.toString() + "\n");



        // 4. TESTING THE MEDIUM TERM FIXED ACCOUNT
        // ------------------------------------------------------------
        System.out.println("=== 4. Medium-Term Investment ===");
        MediumTerm medTermAcc = new MediumTerm("FIX-1205", "Wisani Mabunda", 25000.0);
        System.out.println("Initial: " + medTermAcc.toString());


        medTermAcc.checkLoyaltyReward();
        System.out.println("Updated: " + medTermAcc.toString() + "\n");



        // 5. TESTING THE LONG TERM FIXED ACCOUNT
        // ------------------------------------------------------------
        System.out.println("=== 5. Long-Term Investment ===");
        LongTerm longTermAcc = new LongTerm("FIX-7704", "Tresina Mabunda", 50000.0);
        System.out.println("Initial: " + longTermAcc.toString());

        // Apply 12% maturity interest reward at the end of the 24 months
        longTermAcc.applyMaturityInterest();
        System.out.println("Updated: " + longTermAcc.toString() + "\n");

        System.out.println("=========================================");
        System.out.println("       GOODBYE, ENJOY YOUR DAY!       ");
        System.out.println("=========================================");

    }
}