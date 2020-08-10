package threadpooltest2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerSct {

    static ArrayList<User> ulist = new ArrayList<User>();

    public static void main(String[] args){


        User us1 = new User("kani", "abc");
        User us2 = new User("sasi", "qwe");


        us1.addAccount(new Account(1111, 1212, 500));
        us2.addAccount(new Account(2222, 3333, 1000));


        ulist.add(us1);
        ulist.add(us2);


        try {
            ServerSocket server = new ServerSocket(8668);
            int counter = 0;
            System.out.println("Server Started ....");
            while (true) {
                counter++;
                Socket serverClient = server.accept();

                CliSer sct = new CliSer(serverClient, counter);


                CustomThreadPool ctp = new CustomThreadPool(3);
                ctp.execute(sct);


            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
