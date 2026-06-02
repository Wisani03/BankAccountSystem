public class SavingsAccount extends BankAccount{
    private double interestRate;

    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate){
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate(){
        return interestRate;
    }
    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }

    public void applyInterest(){
        double InterestEarned = getBalance() * (interestRate / 100);
        deposit(InterestEarned);
        System.out.println("Interest Applied.");
    }
}
