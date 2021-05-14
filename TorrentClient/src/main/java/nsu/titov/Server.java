package nsu.titov;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] ar) {
        int port = 6666;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            byte[] arr = new byte[Handshake.TOTAL_SIZE];
            Handshake myHandshakeInfo = Handshake.createHandshake()
                    .setName("BitTorrent Protocol")
                    .setReserved(null);


            out.write(myHandshakeInfo.getBody());
            if (in.read(arr) != Handshake.TOTAL_SIZE) {
                System.out.println("foooooooooo!");
            }
            Handshake response = Handshake.createHandshake(arr);
            if (myHandshakeInfo.equals(response)) {
                System.out.println("Accepted :)");
            } else {
                System.out.println("Rejected :(");
            }

        } catch (Exception e) {
            System.out.println("Some troubles with client");
        }
    }
}