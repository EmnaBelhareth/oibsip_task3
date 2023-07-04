package Task_3;



public class Customer {

    private String name;
    private String cardnum;
    private String pin;
    private Account account;

   
    public Customer (String name,String cardnum,String pin,Account account) {
        this.name=name;
        this.cardnum=cardnum;
        this.pin=pin;
        this.account=account;
    };

    Account getaccountCust(){
        return account;
    }

    String getNameCust() {
        return name;
    }

    String getcardnum() {
        return cardnum;
    }

    String getpin() {
        return pin;
    }
    void setcardnum(String cardnum) {
       this.cardnum=cardnum;
    }

    void setpin(String pin) {
        this.pin=pin;
    }
    


}
