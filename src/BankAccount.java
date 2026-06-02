public abstract class BankAccount implements IBankActions{
    private String accountNumber;
    private String holderName;
    private double balance;
    private String pinCode;

    public BankAccount(){
        this.accountNumber = "";
        this.holderName = "";
        this.balance = 0.0;
        this.pinCode = "";
    }

    public BankAccount(String accountName, String holderName, double balance, String pinCode){
        this.accountNumber = accountName;
        this.holderName = holderName;
        this.balance = balance;
        this.pinCode = pinCode;
    }

    public boolean verifyPin(String inputPin){
        return this.pinCode.equals(inputPin);
    }

    public String getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(String accountName){
        this.accountNumber = accountName;
    }

    public String getHolderName(){
        return holderName;
    }
    public void setHolderName(String holderName){
        this.holderName = holderName;
    }
    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    public void deposit(double amount){
        if (amount > 0){
            this.balance += amount;
            System.out.println("Deposited: R" + amount);
        }
    }

    public void withdraw(double amount){
        if (amount > 0 && amount <= this.balance){
            this.balance -= amount;
            System.out.println("Withdrew: R" + amount);
        }else{
            System.out.println("Insufficient funds!");
        }
    }
    @Override
    public String toString(){
        return "Acc No: " +  accountNumber + ", Owner: " + holderName + ", balance: " + balance;
    }
}
