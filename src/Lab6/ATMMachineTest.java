package Lab6;

import java.util.*;

public class ATMMachineTest {
    public static void main(String[] args) {
        Map< String , Client> clients =new HashMap<>();
        Map<String ,String >pins = new HashMap<>();

        Client client = new Client("1", "sajeenthiran", "sri lankan tamil", "software developer","no 210/B/4 , samupakara mawatha piliyandala",21,"male","1234" ,new ArrayList<>(),"LKR");
        client.openNewBankAccount(new CurrentaAcount("currentAccount1","LKR","piliyandala",0.00) );
        client.openNewBankAccount(new CurrentaAcount("currentAccount0","LKR","piliyandala",0.00) );

        clients.put( client.getClientId(), client);

        pins.put(client.getPIN("999"),client.getClientId());

        Scanner scanner =new Scanner(System.in);
        System.out.println("Welcome !");
        System.out.println("Enter the PIN");
        String pin = scanner.nextLine();

        Client currentClient= clients.get(pins.get(pin));


        int index=0;
        for ( BankAccount bankAccount :currentClient.getBankAccounts()){

            System.out.println(index+ bankAccount.getAccountNumber());
            index++;

        }

        String selectedAccount=  scanner.nextLine();
         BankAccount currentBankAccount= currentClient.getBankAccounts().get( new Integer(selectedAccount));





        System.out.println(" Main Menu \n" +
                             "1. View Balance \n"+
                            " 2. Deposit money \n "+
                            " 3. Exit ");


        int selection = scanner.nextInt();

        if(selection== 0){
            System.out.println("balance "+ currentBankAccount.getBalance());
            BalanceInquiry balanceInquiry=new BalanceInquiry("1",currentBankAccount.getAccountNumber(),new Date(),Status.SUCCESS, currentBankAccount.getBalance());

        }else if( selection== 1) {
            System.out.println(" How much to withdraw");
            int amount =scanner.nextInt();
            currentBankAccount.setBalance(currentBankAccount.getBalance()-amount);
            currentBankAccount.addtransaction(new Withdrawal("1",currentBankAccount.getAccountNumber(),new Date(),Status.SUCCESS,amount));
        }else{
            System.out.println(" How much to deposit");
            int amount =scanner.nextInt();
            currentBankAccount.setBalance(currentBankAccount.getBalance()+amount);
            currentBankAccount.addtransaction(new Withdrawal("1",currentBankAccount.getAccountNumber(),new Date(),Status.SUCCESS,amount));


        }
    }

}


abstract class Transaction {
    private String id;
    private String bankAccountId;
    private Date date;
    private Status status;

    public Transaction(String id, String bankAccountId, Date date, Status status) {
        this.id = id;
        this.bankAccountId = bankAccountId;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

class Withdrawal extends  Transaction {
    private  double withdrawalAmount;

    public double getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(double transactionAmount) {
        this.withdrawalAmount = transactionAmount;
    }

    public Withdrawal(String id, String bankAccountId, Date date, Status status, double transactionAmount) {
        super(id, bankAccountId, date, status);
        setWithdrawalAmount(transactionAmount);
    }



}
class Deposit extends Transaction {
    private  double depositAmount;

    public Deposit(String id, String bankAccountId, Date date, Status status,double depositAmount) {
        super(id, bankAccountId, date, status);
        setDepositAmount(depositAmount);
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }
}

class BalanceInquiry extends  Transaction{
    private double  balanceInquired;

    public BalanceInquiry(String id, String bankAccountId, Date date, Status status, double balanceInquired) {
        super(id, bankAccountId, date, status);
        this.balanceInquired = balanceInquired;
    }

    public double getBalanceInquired() {
        return balanceInquired;
    }

    public void setBalanceInquired(double balanceInquired) {
        this.balanceInquired = balanceInquired;
    }
}

enum Status{
    SUCCESS,
    FAILURE,
    CANCELLATION

}

class Loan {
    private double amount ;
    private double interest;
    private int duration ; // in days

    private String  paymentMethod;

    public Loan(double amount, double interest, int duration, String paymentMethod) {
        this.amount = amount;
        this.interest = interest;
        this.duration = duration;
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

abstract class BankAccount {
    private String accountNumber;
    private String currencyINOperation;
    private String branch;
    private double balance ;

    private Loan loan;

    private List<Transaction> transactions;

    public void addtransaction(Transaction transaction){
        this.transactions.add(transaction);

    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BankAccount(String accountNumber, String currencyINOperation, String branch, double balance) {
        this.accountNumber = accountNumber;
        this.currencyINOperation = currencyINOperation;
        this.branch = branch;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrencyINOperation() {
        return currencyINOperation;
    }

    public void setCurrencyINOperation(String currencyINOperation) {
        this.currencyINOperation = currencyINOperation;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}

class CurrentaAcount extends BankAccount{
    public CurrentaAcount(String accountNumber, String currencyINOperation, String branch, double balance) {
        super(accountNumber, currencyINOperation, branch, balance);
    }


}
class SavingsAccount extends BankAccount {



    private double interestRate;


    public SavingsAccount(String accountNumber, String currencyINOperation, String branch, double balance,double interestRate) {
        super(accountNumber, currencyINOperation, branch, balance);
        this.setInterestRate(interestRate);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double payInterest(){
        this.setBalance(this.getBalance()+this.getBalance()*this.interestRate);
        return this.getBalance();
    }
}





class Client {
    private String clientId;
    private String name ;
    private String nationality;
    private String occupation ;
    private String address;
    private int age;
    private String gender ;
    private String PIN ;


    private List<BankAccount> bankAccounts=new ArrayList<>();
    private String currencyInOperation;

    public Client(String clientId, String name, String nationality, String occupation, String address, int  age, String gender, String PIN, List<BankAccount> bankAccounts, String currencyInOperation) {
        this.clientId = clientId;
        this.name = name;
        this.nationality = nationality;
        this.occupation = occupation;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.PIN = PIN;
        this.bankAccounts = bankAccounts;
        this.currencyInOperation = currencyInOperation;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClient(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int  getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPIN(String accessPassword) {
        if(accessPassword=="999"){
            return PIN;
        }
        else{
            System.out.println("access not granted");

        }
        return  null;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public String getCurrencyInOperation() {
        return currencyInOperation;
    }

    public void setCurrencyInOperation(String currencyInOperation) {
        this.currencyInOperation = currencyInOperation;
    }

    public void openNewBankAccount(BankAccount bankAccount){
        if( bankAccount.getCurrencyINOperation().equals(this.currencyInOperation)){
            bankAccounts.add(bankAccount);
        }
        else{
            System.out.println("currency in operation mismatched");
        }
    }
}