import java.util.*;
public class Customer {
    String name="";
    String u_name="";
    String pass="";
    int amount_balance;
    List<Gift_Card> giftcards;

    public Customer(String name,String u_name,int amount)
    {
        giftcards=new ArrayList<>();
        this.name=name;
        this.u_name=u_name;
        this.amount_balance=amount;
    }

    public void encrypt_passsword(String pass)
    {
        String enc_pass="";
        for(int ind=0;ind<pass.length();ind++)
        {
            int temp=pass.charAt(ind);
            temp++;
            if(temp==123 || temp==91)
            {
                temp-=26;
            }
            if(temp==58)
            {
                temp-=10;
            }
            enc_pass+=(char)temp;
        }
        this.pass=enc_pass;
    }

    public String decrypt_password(String pass)
    {
        String dec_pass="";
        for(int ind=0;ind<pass.length();ind++)
        {
            int temp=pass.charAt(ind);
            temp--;
            if(temp==96 || temp==64)
            {
                temp+=26;
            }
            if(temp==47)
            {
                temp+=10;
            }
            dec_pass+=(char)temp;
        }
        return dec_pass;
    }

    public void create_gift_card(String name,int pin)
    {
        Gift_Card card=new Gift_Card(name,pin);
        giftcards.add(card);
    }

    public boolean check_if_card_exists(int card_no)
    {
        for(int ind=0;ind<giftcards.size();ind++)
        {
            if(giftcards.get(ind).card_no==card_no)
            {
                return true;
            }
        }
        return false;
    }

    public boolean check_if_pin_correct(int card_no,int pin)
    {
        for(int ind=0;ind<giftcards.size();ind++)
        {
            if(giftcards.get(ind).card_no==card_no && giftcards.get(ind).pin==pin)
            {
                return true;
            }
        }
        return false;
    }

    public boolean is_balance_available(int amount)
    {
        if(amount<=amount_balance)
        {
            return true;
        }
        return false;
    }

    public int top_up(int card_no,int amount)
    {
        int curr=0;
        for(int ind=0;ind<giftcards.size();ind++)
        {
            if(giftcards.get(ind).card_no==card_no)
            {
                curr=giftcards.get(ind).add_balance(amount);
                break;
            }
        }
        return curr;
    }

    public void get_transaction_history(int card_no)
    {
        for(int ind=0;ind<giftcards.size();ind++)
        {
            if(giftcards.get(ind).card_no==card_no)
            {
                giftcards.get(ind).print_transaction_history();
                break;
            }
        }
    }

    public int block_card(int card_no)
    {
        int bal=0;
        for(int ind=0;ind<giftcards.size();ind++)
        {
            if(giftcards.get(ind).card_no==card_no)
            {
                bal=giftcards.get(ind).balance;
                giftcards.remove(ind);
                break;
            }
        }
        return bal;
    }

    public int purchase(int card_no,int amount)
    {
        String transaction="";
        int bal=0;
        for(int ind=0;ind<giftcards.size();ind++)
        {
            if(giftcards.get(ind).card_no==card_no)
            {
                giftcards.get(ind).balance-=amount;
                transaction+="Purchase Rs:"+String.valueOf(amount);
                giftcards.get(ind).transactions.add(transaction);
                bal=giftcards.get(ind).balance;
                amount_balance+=bal;
                amount_balance+=(int)(amount/10);
                break;
            }
        }
        return bal;
    }

    public int get_balance()
    {
        return amount_balance;
    }

    public boolean check_transaction_possible(int card_no,int amount)
    {
        for(int ind=0;ind<giftcards.size();ind++)
        {
            if(giftcards.get(ind).card_no==card_no && giftcards.get(ind).balance>=amount)
            {
                return true;
            }
        }
        return false;
    }

}

