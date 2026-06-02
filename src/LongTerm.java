public class LongTerm extends FixedAmount{
    public LongTerm(String accountNumber, String holderName, double balance){
        super(accountNumber, holderName, balance, 24, 0.085);
    }

    public void applyMaturityInterest(){
        double maturityReward = getBalance() * 0.12;
        deposit(maturityReward);
        System.out.println("Congratulations: Your 24-month term has matured.");
        System.out.println("Maturity interest of R" + maturityReward + " has been added!");
    }
}
