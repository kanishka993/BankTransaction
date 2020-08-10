package threadpooltest2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AccoutDtl {

	private User u;
	private DataOutputStream outStream;
	private DataInputStream dis;

    public AccoutDtl(User u, DataOutputStream outStream, DataInputStream dis) {

        this.u = u;
        this.outStream = outStream;
        this.dis = dis;
    }


    public User getDetail() throws IOException {
        for (Account acc : u.alist) {
            outStream.writeUTF(acc.getAccNo() + " " + acc.getBalance() + " ");
            dis.readUTF();
        }
        return u;
    }
}
