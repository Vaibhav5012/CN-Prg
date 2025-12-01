<----------------------ServerSide------------------------>

import java.util.*;
import java.net.*;
import java.io.*;

public class udpserver {
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        Scanner scan = new Scanner(System.in);

        int ServerPort = 999;
        System.out.println("Server ready \n Waiting for connection\n");
        try {
            aSocket = new DatagramSocket(ServerPort);
            byte[] buffer = new byte[1000];
            byte[] buff = new byte[1000];
            DatagramPacket data1 = new DatagramPacket(buff, buff.length);
            aSocket.receive(data1);

            byte[] msg = new byte[1000];
            msg = data1.getData();
            System.out.println(new String(msg, 0, data1.getLength()));

            System.out.println("\nEnter message to be sent:");
            String str = scan.nextLine();
            buffer = str.getBytes();
            DatagramPacket data = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 998);
            aSocket.send(data);
        } catch (SocketException e) {
            System.out.println("Socket " + e.getMessage());
        } catch (IOException o) {
            System.out.println("IO :" + o.getMessage());
        } finally {
            System.out.println("\nMessage sent\nconnection terminated\n");
            if (aSocket != null)
                aSocket.close();
            scan.close();
        }
    }
}


<--------------------------ClientSide:---------------------------->

import java.net.*;
import java.io.*;

public class udpclient {
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        int clientPort = 998;
        try {
            aSocket = new DatagramSocket(clientPort);
            byte[] buf = new byte[1000];
            byte[] buf1 = new byte[1000];
            DatagramPacket data = new DatagramPacket(buf, buf.length);

            String conf = "connected to client";
            buf1 = conf.getBytes();
            DatagramPacket data1 = new DatagramPacket(buf1, buf1.length, InetAddress.getLocalHost(), 999);
            aSocket.send(data1);

            System.out.println("connected to server");
            aSocket.receive(data);
            byte[] msg = new byte[1000];
            msg = data.getData();

            System.out.println("\n message:");
            System.out.println(new String(msg, 0, data.getLength()));
        } catch (SocketException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}
