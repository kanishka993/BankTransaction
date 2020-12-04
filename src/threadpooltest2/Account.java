package threadpooltest2;

public class Account {

    private int cvv;
    private int accNo;
    private float balance;

    public Account(int cvv, int accNo, float balance) {

        this.cvv = cvv;
        this.accNo = accNo;
        this.balance = balance;
    }


    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getCvv() {
        return cvv;
    }
//kk
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

}
