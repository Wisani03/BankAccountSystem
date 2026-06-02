public class CurrentAccount extends BankAccount{
    public double overdraftLimit; // type of acc that allows you to get negative balance (overdraft)

    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit, String pinCode){
        super(accountNumber, holderName, balance, pinCode);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit(){
        return overdraftLimit;
    }
    public void setOverdraftLimit(double overdraftLimit){
        this.overdraftLimit = overdraftLimit;
    }

    //we are changing how the withdrawal method works
    public void withdraw(double amount){
        double totalAvailFunds = getBalance() + overdraftLimit;

        if (amount > 0 && amount <= totalAvailFunds){
            setBalance(getBalance() - amount);
            System.out.println("Successfully withdrew: R" + amount);
            if(getBalance() < 0){
                System.out.println("Warning: You are now using your overdraft!\nCurrent Balance: R" + getBalance());
            }
        }else{
            System.out.println("Transaction declined! Exceeds your overdraft limit of R" + overdraftLimit);
        }
    }
}
