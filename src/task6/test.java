package task6;
public class test{
    public static void main(String[] args){
        Bank bank=Bank.getBank();
        try {
            bank.register("w123", "neo", "2748", "213214", 1);
        } catch (RegisterException e) {
            e.printStackTrace();
        }
        try {
            bank.register("w123", "jast", "2748", "213214", 2);
        } catch (RegisterException e) {
            e.printStackTrace();
        }
        try {
            bank.register("w123", "a", "2748", "213214", 3);
        } catch (RegisterException e1) {
            e1.printStackTrace();
        }
        try {
            bank.register("w123", "b", "2748", "213214", 4);
        } catch (RegisterException e) {
            e.printStackTrace();
        }
        bank.deposit(100002, 200);
        bank.requestLoan(100004, 200);
        bank.setCeiling(100002, 100);
        /*System.out.println(b.getLoan());*/
        System.out.println(bank.allAccounts());
        System.out.println(bank.getAllLoan());
        System.out.println(bank.getAllCeiling());
    }
}