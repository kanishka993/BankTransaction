package threadpooltest2;

import java.io.*;
import java.net.Socket;

public class ClientSct {

    public static void main(String[] args) throws IOException {


        try (Socket socket = new Socket("localhost", 8668)) {
            try (DataInputStream inStream = new DataInputStream(socket.getInputStream())) {
                try (DataOutputStream outStream = new DataOutputStream(socket.getOutputStream())) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                        String clientMessage = "", serverMessage = "";

                        while (!clientMessage.equals("bye")) {

                            serverMessage = inStream.readUTF();
                            System.out.println(serverMessage);

                            clientMessage = br.readLine();
                            outStream.writeUTF(clientMessage);

                        }

                    }
                }
            }
        }

    }
}
