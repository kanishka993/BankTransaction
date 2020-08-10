package threadpooltest2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LoginPr {
	private String unames;
	private String password;
	private DataOutputStream outStream;

    public LoginPr(String uname, String password, DataOutputStream outStream) {

        this.unames = uname;
        this.password = password;
        this.outStream = outStream;
    }

    public User lCheck() throws IOException {
        ArrayList<User> arr = ServerSct.ulist;
        User us = null;
        for (User User : arr) {
            if (User.getUname().equals(unames) && User.getPassword().equals(password)) {
                outStream.writeUTF("Success");
                us = User;
            }

        }

        if (us == null) {
            outStream.writeUTF("Login failed");
        }

        return us;

    }

}
