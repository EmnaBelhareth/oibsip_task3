package Task_3;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        ArrayList <Customer> customers = new ArrayList<Customer>();
        try (Scanner scan = new Scanner(System.in)) {
            Account account = new Account(3000.0);
            Account account2 = new Account(2000.0);
            
            Customer customer1 = new Customer ("Emna","12345678","123",account);
            Customer customer2=new Customer("John","87654321","1478",account2);
            customers.add(customer1);
            customers.add(customer2);
            
            Customer customer = null;
            Boolean loggedIn = false ;
            while (!loggedIn) {
                System.out.println("Enter your name:");
                String name=scan.next();
                System.out.println("Enter your Card Number:");
                String cardnum = scan.next();
                System.out.println("Enter your Pin:");
                String pin = scan.next();
            
                for (Customer cust : customers) {
                    if (cust.getcardnum().equals(cardnum) && cust.getpin().equals(pin)) {
                        customer = cust;
                        loggedIn = true;
                        break;
                    }
                }
            
                if (!loggedIn) {
                    System.out.println("Invalid login credentials. Please try again.");
                }
            }
           
            Atm atm = new Atm(customer);
           
            
             while(true)
             {
             
             atm.DisplayMenu();
             System.out.println("Enter your choice:");
             int choice = scan.nextInt();
             atm.Transaction(choice,customer,customers);
             
             }
             
            
        }
        catch(Exception e)
        {
            System.out.println("Something is Wrong!");
        }

        
        
    }

    
}
