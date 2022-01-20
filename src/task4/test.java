package task4;
public class test{
    public static void main(String[] args){
        Bank bank=Bank.getBank();
        bank.register("w123", "neo", "2748", "213214", 1);
        bank.register("w123", "jast", "2748", "213214", 2);
        bank.register("w123", "a", "2748", "213214", 3);
        bank.register("w123", "b", "2748", "213214", 4);
        System.out.println(bank.login(100001,"w123").getName());
        bank.deposit(100002, 200);
        bank.requestLoan(100004, 200);
        bank.setCeiling(100004, 100);
        System.out.println();
        System.out.println(bank.login(100002,"w123").getBalance());
        System.out.println(bank.getAllCeiling());
        System.out.println(bank.allAccounts());
        System.out.println(bank.getAllLoan());
    }
}