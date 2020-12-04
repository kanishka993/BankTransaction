package threadpooltest2;

import java.util.ArrayList;

public class User {

	private String uname;
	private String password;
	private int accunt;

    ArrayList<Account> alist = new ArrayList<Account>();

    public User(String uname, String password) {

        this.uname = uname;
        this.password = password;

    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccunt() {
        return accunt;
    }

    public void setAccunt(int accunt) {
        this.accunt = accunt;
    }

    public void addAccount(Account acc) {

        alist.add(acc);
    }


}
///ct
