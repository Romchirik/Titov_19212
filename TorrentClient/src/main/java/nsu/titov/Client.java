package nsu.titov;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int serverPort = 6666;
        String address = "127.0.0.1";


        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            Handshake myHandshake = Handshake.createHandshake()
                    .setName("BitTorr Protocock")
                    .setReserved(null);
            out.write(myHandshake.getBody());
            byte[] arr = new byte[Handshake.TOTAL_SIZE];
            in.read(arr);
            Handshake response = Handshake.createHandshake(arr);


            if (myHandshake.equals(response)) {
                System.out.println("Accepted!");
            } else {
                System.out.println("Rejected((((");
            }


        } catch (Exception e) {
            System.out.println("Unable to connect to server");
        }
    }
}