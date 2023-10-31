import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        List<Customer> customers=new ArrayList<>();
        create_account(customers,"Koushik","Koushik2002","qwerty",1000);
        create_account(customers,"Kumar","Kumar1001","ytrewq",1000);
        create_account(customers,"Kishore","Kishore1234","asdfgh",10000);
        int option,amount,inner_option,pin,card_no,flag=0,inner_flag=0,acc_check=0;
        String name,u_name,pass;
        while(flag==0)
        {
            System.out.println("\n1.New Customer 2.Existing Customer 3.Purchase 4.Exit");
            option=sc.nextInt();
            switch(option)
            {
                case 1:
                {
                    System.out.println("Enter Your Name");
                    name=sc.next();
                    System.out.println("Create Username");
                    u_name=sc.next();
                    System.out.println("Set Password");
                    pass=sc.next();
                    System.out.println("Enter amount to deposit");
                    amount=sc.nextInt();
                    create_account(customers,name,u_name,pass,amount);
                    System.out.println("Account created successfully.Please Login to continue");
                    break;
                }
                case 2:
                {
                    System.out.println("----------Login---------");
                    System.out.println("Enter your username");
                    u_name=sc.next();
                    acc_check=0;
                    for(int ind=0;ind<customers.size();ind++)
                    {
                        if(customers.get(ind).u_name.equals(u_name))
                        {
                            acc_check=1;
                            System.out.println("Enter Your password");
                            pass=sc.next();
                            if(customers.get(ind).decrypt_password(customers.get(ind).pass).equals(pass))
                            {
                                while(inner_flag==0)
                                {
                                    System.out.println("\n1.Create Gift Card 2.TopUp 3.Transaction History 4.Block 5.Logout");
                                    inner_option=sc.nextInt();
                                    switch(inner_option)
                                    {
                                        case 1:
                                        {
                                            System.out.println("Input Your PIN number:");
                                            pin=sc.nextInt();
                                            customers.get(ind).create_gift_card(customers.get(ind).name,pin);
                                            break;
                                        }
                                        case 2:
                                        {
                                            System.out.println("Enter Your Card Number");
                                            card_no=sc.nextInt();
                                            if(customers.get(ind).check_if_card_exists(card_no))
                                            {
                                                System.out.println("Enter Your PIN");
                                                pin=sc.nextInt();
                                                if(customers.get(ind).check_if_pin_correct(card_no,pin))
                                                {
                                                    System.out.println("Enter TopUp Amount");
                                                    amount=sc.nextInt();
                                                    if(customers.get(ind).is_balance_available(amount))
                                                    {
                                                        System.out.printf("Your Current Card balance is Rs:%d",customers.get(ind).top_up(card_no,amount));
                                                    }
                                                    else
                                                    {
                                                        System.out.println("Low Balance");
                                                        break;
                                                    }
                                                }
                                                else
                                                {
                                                    System.out.println("Oops!wrong password");
                                                }
                                            }
                                            else
                                            {
                                                System.out.println("Sorry!Card Does not exist.Please create one to continue");
                                            }
                                            break;
                                        }
                                        case 3:
                                        {
                                            System.out.println("Enter Your Card Number");
                                            card_no=sc.nextInt();
                                            if(customers.get(ind).check_if_card_exists(card_no))
                                            {
                                                System.out.println("Enter Your PIN");
                                                pin=sc.nextInt();
                                                if(customers.get(ind).check_if_pin_correct(card_no,pin))
                                                {
                                                    customers.get(ind).get_transaction_history(card_no);
                                                }
                                                else
                                                {
                                                    System.out.println("Oops!wrong password");
                                                }
                                            }
                                            else
                                            {
                                                System.out.println("Sorry!Card Does not exist.Please create one to continue");
                                                break;
                                            }
                                            break;
                                        }
                                        case 4:
                                        {
                                            System.out.println("Enter your card number");
                                            card_no=sc.nextInt();
                                            if(customers.get(ind).check_if_card_exists(card_no))
                                            {
                                                amount=customers.get(ind).block_card(card_no);
                                                System.out.printf("Your card has been Blocked and your existing amount of Rs.%d has been added to your Bank Account",amount);
                                            }
                                            else
                                            {
                                                System.out.println("Card does not exist");
                                            }
                                            break;
                                        }
                                        case 5:
                                        {
                                            inner_flag=1;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("Incorrect Password");
                                break;
                            }
                        }
                    }
                    if(acc_check==0)
                    {
                        System.out.println("Account does not exist.Please create one to continue");
                        break;
                    }
                    break;
                }
                case 3:
                {
                    System.out.println("Enter your username");
                    u_name=sc.next();
                    acc_check=0;
                    for(int ind=0;ind<customers.size();ind++)
                    {
                        if(customers.get(ind).u_name.equals(u_name))
                        {
                            acc_check=1;
                            System.out.println("Enter Your password");
                            pass=sc.next();
                            if(customers.get(ind).decrypt_password(customers.get(ind).pass).equals(pass))
                            {
                                System.out.println("Enter Your Card Number");
                                card_no=sc.nextInt();
                                if(customers.get(ind).check_if_card_exists(card_no))
                                {
                                    System.out.println("Enter Your PIN");
                                    pin=sc.nextInt();
                                    if(customers.get(ind).check_if_pin_correct(card_no,pin))
                                    {
                                        System.out.println("Enter purchase amount");
                                        amount=sc.nextInt();
                                        if(customers.get(ind).check_transaction_possible(card_no,amount))
                                        {
                                            int c_balance=customers.get(ind).purchase(card_no,amount);
                                            int a_balance=customers.get(ind).get_balance();
                                            System.out.printf("Purchase of Rs.%d was done successfully.Your card balance is %d and Your current account balance is Rs.%d including %d reward points",amount,c_balance,a_balance,(int)(amount/10));
                                            break;
                                        }
                                        else
                                        {
                                            System.out.println("Insufficient card balance.TopUp to continue");
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(acc_check==0)
                    {
                        System.out.println("Account does not exist.Please create one to continue");
                        break;
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("Thanks For Using our Banking service");
                    flag=1;
                }
            }
        }
    }

    public static void create_account(List<Customer> customers,String name,String u_name,String pass,int amount)
    {
        Customer object=new Customer(name,u_name,amount);
        customers.add(object);
        object.encrypt_passsword(pass);
    }
}
