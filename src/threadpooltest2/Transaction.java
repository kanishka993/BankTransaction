package threadpooltest2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Transaction {

	private User u;
	private DataOutputStream outStream;
	private DataInputStream dis;

	private float amount;
	private int toAcNo;
	private int fromANo;


    public Transaction(User u, DataOutputStream outStream, DataInputStream dis, int fromANo, int toAcNo, float amount) {

        this.u = u;
        this.outStream = outStream;
        this.dis = dis;
        this.toAcNo = toAcNo;
        this.fromANo = fromANo;
        this.amount = amount;
    }


    public void transfer() throws IOException {
        ArrayList<Account> alist = u.alist;
        Account accc = null;
        Account tranferaccc = null;
        User transferuser = null;
        for (Account acc : u.alist) {
            if (fromANo == acc.getAccNo()) {
                accc = acc;
                break;

            }
        }
        for (User u : ServerSct.ulist) {
            for (Account acc : u.alist) {
                if (toAcNo == acc.getAccNo()) {
                    tranferaccc = acc;
                    transferuser = u;
                    break;
                }

            }
        }
        if (accc != null && tranferaccc != null) {
            if (accc.getBalance() < amount) {
                outStream.writeUTF("not sufficent amount");

            } else {
                alist.add(u.alist.indexOf(accc), accc);
                u.alist = alist;
                ServerSct.ulist.set(ServerSct.ulist.indexOf(u), u);

                accc.setBalance(accc.getBalance() - amount);
                tranferaccc.setBalance(tranferaccc.getBalance() + amount);
                u.setAccunt(fromANo);
                transferuser.setAccunt(toAcNo);
                ServerSct.ulist.set(ServerSct.ulist.indexOf(transferuser), transferuser);

                ServerSct.ulist.set(ServerSct.ulist.indexOf(u), u);

                outStream.writeUTF("transaction success");

                //dis.readUTF();


            }
        } else if (accc == null) {
            outStream.writeUTF("not registerd one");

        } else if (tranferaccc == null) {
            outStream.writeUTF("enter valid number");

        }
    }


    public void transactio() throws IOException {
        Account accc = null;

        //User u = null;
        ArrayList<Account> alist = u.alist;
        ArrayList<User> arr = ServerSct.ulist;
        User u1 = u;
        for (Account acc : alist) {
            for (User u : arr) {
                if ((acc.getAccNo() == toAcNo) && (acc.getBalance() > amount)) {

                    acc.setBalance(acc.getBalance() - amount);

                    accc = acc;
                    u = u1;

                    alist.add(u.alist.indexOf(acc), acc);
                    u1.alist = alist;
                    ServerSct.ulist.set(ServerSct.ulist.indexOf(u), u1);
                    outStream.writeUTF("Transaction done");

                    break;
                } else {

                    outStream.writeUTF("not sufficent balance ");
                    dis.readUTF();

                    break;
                }
            }
        }
        outStream.writeUTF(accc.getAccNo() + " " + accc.getBalance() + " ");
        dis.readUTF();


    }

    public void transacti() throws IOException {
        Account accc = null;
        ArrayList<Account> alist = u.alist;
        User u1 = u;
        for (Account acc : alist) {
            if ((acc.getBalance() > amount)) {
                acc.setBalance(acc.getBalance() - amount);
                accc = acc;

                alist.add(u.alist.indexOf(acc), acc);
                u1.alist = alist;
                ServerSct.ulist.set(ServerSct.ulist.indexOf(u), u1);
                outStream.writeUTF("Transaction done");

                break;
            } else {

                outStream.writeUTF("not sufficent balance ");
                dis.readUTF();

                break;
            }

        }
        outStream.writeUTF(accc.getAccNo() + " " + accc.getBalance() + " ");
        dis.readUTF();


    }
}
