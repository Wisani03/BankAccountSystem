public class ShortTerm extends FixedAmount{
    public ShortTerm(String accountNumber, String holderName, double balance){
        super(accountNumber, holderName, balance, 3, 2.0);
    }
    public void addShortTermBonus(){
        double bonus = 150.0;
        deposit(bonus);
        System.out.println("Short term sign-up bonus of R150 added!");
    }
}
