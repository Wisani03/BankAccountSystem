public class FixedAmount extends BankAccount {
    private int lockInMonths;
    protected double penaltyPercentage;


    public FixedAmount(String accountNumber, String holderName, double balance, int lockInMonths, double penaltyPercentage, String pinCode) {
        super(accountNumber, holderName, balance, pinCode);
        this.lockInMonths = lockInMonths;
        this.penaltyPercentage = penaltyPercentage;
    }


    public int getLockInMonths() { return lockInMonths; }
    public void setLockInMonths(int lockInMonths) { this.lockInMonths = lockInMonths; }

    public double getPenaltyPercentage() { return penaltyPercentage; }
    public void setPenaltyPercentage(double penaltyPercentage) { this.penaltyPercentage = penaltyPercentage; }

    // Overriding withdraw to apply the penalty calculation automatically
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }


        double penaltyFee = amount * (this.penaltyPercentage / 100);
        double totalDeduction = amount + penaltyFee;

        if (totalDeduction <= getBalance()) {
            setBalance(getBalance() - totalDeduction);
            System.out.println("Early withdrawal executed!");
            System.out.println("Amount Requested: R" + amount);
            System.out.println("Penalty Fee Applied (" + penaltyPercentage + "%): R" + penaltyFee);
            System.out.println("Total Deducted from Balance: R" + totalDeduction);
        } else {
            System.out.println("Transaction declined! Insufficient funds to cover the amount plus the penalty fee.");
        }
    }
}