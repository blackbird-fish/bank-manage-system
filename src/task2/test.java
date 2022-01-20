package task2;
import java.util.Scanner;
public class test {
     public static void main(String[] args){
      Bank bank=new Bank();
      Scanner scn=new Scanner(System.in);
      System.out.println("id:");
      String id=scn.nextLine();
      System.out.println("password:");
      String password=scn.nextLine();
      System.out.println("name");
      String name=scn.nextLine();
      System.out.println("personld");
      String personld=scn.nextLine();
      System.out.println("email");
      String email=scn.nextLine();
      long id0=Long.parseLong(id);
      System.out.println("accounttype");
      int type=scn.nextInt();
      bank.register(id0, password, name, personld, email,type);
      System.out.println(bank);
      System.out.println("login id:");
      String id1=scn.nextLine();
      System.out.println("login password:");
      String password1=scn.nextLine();
      long id2=Long.parseLong(id1);
      bank.login(id2, password1);
      scn.close();
    }
}
