package threadpooltest2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CliSer extends Thread {

    private Socket serverClient;
    private int clientNo;
    private User User;

    CliSer(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNo = counter;
    }


    DataInputStream inStream;
    DataOutputStream outStream;
    public void run() {
        try {
            inStream = new DataInputStream(serverClient.getInputStream());
            outStream = new DataOutputStream(serverClient.getOutputStream());

            StringBuffer stb = new StringBuffer();

            stb.append("Login=a\n")
                    .append("Inquiry=b\n")
                    .append("Transaction=c");

            outStream.writeUTF(stb.toString());

            String clientMessage = "";

            while (!clientMessage.equals("bye")) {

                clientMessage = inStream.readUTF();

                switch (clientMessage) {

                    case "a": {
                        loginProcess(outStream, inStream);
                        break;
                    }

                    case "b": {

                        bankDetails(outStream, inStream);

                        break;
                    }
                    case "c": {

                        transaction(outStream, inStream);
                        break;
                    }
                    case "d": {
                        outStream.writeUTF("enter another");
                        break;
                    }

                    default: {
                        outStream.writeUTF("invalid input");

                    }
                }

            }


        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client -" + clientNo + " exit!! ");
            try {
               if(inStream!=null) inStream.close();
                if(outStream!=null)  outStream.close();
                if(serverClient!=null) serverClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void transaction(DataOutputStream outStream, DataInputStream inStream) throws IOException {
        outStream.writeUTF("enter transaction details");
        String transfer = inStream.readUTF();
        String frAc = transfer.split(" ")[0];
        String toAc = transfer.split(" ")[1];
        String bal = transfer.split(" ")[2];

        Transaction tr = new Transaction(User, outStream, inStream, Integer.parseInt(frAc), Integer.parseInt(toAc), Float.parseFloat(bal));
        tr.transfer();
    }

    private void bankDetails(DataOutputStream outStream, DataInputStream inStream) throws IOException {
        outStream.writeUTF("account details");
        AccoutDtl acdl = new AccoutDtl(User, outStream, inStream);
        acdl.getDetail();
    }

    private void loginProcess(DataOutputStream outStream, DataInputStream inStream) throws IOException {

        outStream.writeUTF("Enter user name and password");
        String bd = inStream.readUTF();
        String us = bd.split(" ")[0];
        String pw = bd.split(" ")[1];

        LoginPr lgp = new LoginPr(us, pw, outStream);
        User = lgp.lCheck();
    }
}
