package Task_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Task_3.Transaction.Type;

/**
 * Atm
 */
public class Atm {
    private Customer customer;
    

    public Atm(Customer customer) {
        this.customer = customer;
       
    }

    public void DisplayMenu() {
        System.out.println("\t**************Welcome, " + customer.getNameCust() + "!*************");
        System.out.println("\t*\t1. Transactions History\t\t*");
        System.out.println("\t*\t2. Withdraw\t\t\t*");
        System.out.println("\t*\t3. Deposit\t\t\t*");
        System.out.println("\t*\t4. Transfer\t\t\t*");
        System.out.println("\t*\t5. Check Balance\t\t*");
        System.out.println("\t*\t6. Quit\t\t\t\t*");
        System.out.println("\t*****************************************");
    }

    public void Transaction(int choice,Customer customer,ArrayList<Customer> customers) throws IOException {
       
        switch (choice) {
            case 1:
                History();
                break;
            case 2:
            TransactionOpt(Type.WITHDRAWAL);
                break;
            case 3:
            TransactionOpt(Type.DEPOSIT);
                break;
            case 4 :
           Transfer(customer,customers);
            break;
            case 5 :
            updateBalance(customer);
            checkBalance();
          
            break;
            case 6:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void checkBalance() {
        System.out.println("Account Balance: $" + customer.getaccountCust().getBalance());
    }

     private void TransactionOpt(Type type) throws IOException
     {
      String filename = "History_Transaction" +customer.getcardnum()+ ".txt";
      FileWriter file = new FileWriter(filename,true);
       Scanner scanner = new Scanner(System.in);
      if(type==Type.WITHDRAWAL)
      { 
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        boolean success = customer.getaccountCust().withdraw(amount);
        if (success) {
            System.out.println("Amount withdrawn successfully.");
            file.write("You Have withdrew:" + amount+"\n"); // Write in the file
            checkBalance();
            
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
           
        }
         

      }
      else{
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        customer.getaccountCust().deposit(amount);
        System.out.println("Amount deposited successfully.");
        file.write("You Have deposited:" + amount +"\n");
        checkBalance();

      }
      file.write("Your Balance: $" + customer.getaccountCust().getBalance() +"\n");
      file.close();
     }
    public void History() throws IOException{
        String filename = "History_Transaction" +customer.getcardnum()+ ".txt";
        File file = new File(filename);
        Scanner scan = new Scanner (file);
         if (file.length() == 0) {
            System.out.println("No transaction yet!!");
         }
         else{
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            System.out.println(line);
        }
    }
        scan.close();

       


    }
    public double ExtractBalanceFromFileRec(String filename,Customer customer) throws FileNotFoundException //Extract balance from the file OF CUSTOMER
    { 
        double balance = customer.getaccountCust().getBalance() ;
        Scanner scan = new Scanner(new File(filename));
        String lastline ="";
        while(scan.hasNextLine())
        {
            lastline=scan.nextLine();
        }
        if(lastline.startsWith("Your Balance:")){
            String balancestring=lastline.substring(lastline.indexOf("$")+1);
            balance = Double.parseDouble(balancestring);
        }
        return balance;
    }
    public void Transfer(Customer sender,ArrayList<Customer> customers) throws IOException{
       String filename = "History_Transaction" +sender.getcardnum()+ ".txt";
       FileWriter file = new FileWriter(filename,true);
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the card number that you want to transfer to : \n");
        String cardnum = scan.next();
        System.out.print("Enter the amount to transfer: $");
        double amount = scan.nextDouble();
        Customer recipient = null;
    for (Customer customer : customers) {
        if (customer.getcardnum().equals(cardnum)) {
            recipient = customer;
           
            break;
        }
        
    }
    if (recipient == null) {
        System.out.println("Transfer failed. Invalid recipient.");
        file.close();
        return; // Exit the method if recipient is null
    }
    boolean success = sender.getaccountCust().withdraw(amount);
    if (success) {
        String filename2 = "History_Transaction" +recipient.getcardnum()+".txt";
        FileWriter file2 = new FileWriter(filename2,true);
        recipient.getaccountCust().deposit(amount);
        System.out.println("Transfer successful.");
        System.out.println("Amount transferred: $" + amount);
        System.out.println("Your Balance: $" + sender.getaccountCust().getBalance());
        file.write("You Have Transferred: $" + amount+" to "+recipient.getNameCust()+"\n"); 
        file.write("Your Balance: $" + sender.getaccountCust().getBalance()+"\n"); 
        file2.write("You Have Received: $"+amount+" from "+sender.getNameCust()+"\n");
        file2.write("Your Balance: $"+recipient.getaccountCust().getBalance()+"\n");
        file2.close();
    } else {
        System.out.println("Transfer failed. Invalid recipient or insufficient balance.");
    }
    

file.close();
    }
    public void updateBalance(Customer customer) throws IOException{
        String filename2 = "History_Transaction"+customer.getcardnum()+".txt";
        FileWriter file2 = new FileWriter(filename2,true);
        customer.getaccountCust().setBalance(ExtractBalanceFromFileRec(filename2,customer));
        file2.close();


    }
}
    
