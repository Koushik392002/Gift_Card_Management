import java.util.*;
public class Gift_Card {
    int card_no;
    int pin;
    int balance;
    String owner_name;
    List<String> transactions;

    public Gift_Card(String name,int pin)
    {
        transactions=new ArrayList<>();
        this.owner_name=name;
        this.pin=pin;
        this.balance=100;
        this.card_no=(int)Math.floor(Math.random()*(9999-1000+1)+1000);
        this.sample(this.card_no);
    }

    public void sample(int card_no)
    {
        System.out.println("Your Card Number Is "+card_no);
    }

    public int add_balance(int amount)
    {
        balance+=amount;
        String transaction="TopUp Rs:"+String.valueOf(balance);
        transactions.add(transaction);
        return balance;
    }

    public void print_transaction_history()
    {
        for(int ind=0;ind<transactions.size();ind++)
        {
            System.out.println(transactions.get(ind));
        }
    }

}
