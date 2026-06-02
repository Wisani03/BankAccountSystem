public class MediumTerm extends FixedAmount{
    public MediumTerm(String accountNumber, String holderName, double balance){
        super(accountNumber, holderName, balance, 12, 5.0);
    }

    public void checkLoyaltyReward(){
        if(getBalance() >= 20000.0){
            deposit(500);
            System.out.println("Loyalty reward approved! R500.0 added to your account.");
        }else{
            System.out.println("Account does not qualify for loyalty reward yet. Keep saving!");
        }
    }
}
