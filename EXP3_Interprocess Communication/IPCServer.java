import java.net.*;
import java.io.*;

public class IPCServer {
    public static void main(String args[]) {
        System.out.println("\n**** INTERPROCESS COMMUNICATION ****\n");
        System.out.println("*** SERVER PROCESS STARTED ***\n");
        System.out.println("* SERVER IS READY AND WAITING TO RECEIVE DATA FROM CLIENT PROCESS ON PORT " + 1200);

        try {
            // Creating a server socket listening on port 1200
            ServerSocket ss = new ServerSocket(1200);
            
            // Accepts a connection from a client
            Socket clientSocket = ss.accept();
            System.out.println("* Client is connected with IP address " + clientSocket.getInetAddress() + " and port Number " + clientSocket.getPort());
            
            // Stream to send data to the client
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            
            // Stream to receive data from the client
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());

            // Read two integers from the client
            int a = dis.readInt();
            System.out.println("\nSERVER RECEIVED");
            System.out.println("Number 1 ====> " + a);
            int b = dis.readInt();
            System.out.println("Number 2 ====> " + b);

            // Perform addition
            int c = a + b;

            // Send the result back to the client
            dos.writeInt(c);
            System.out.println("\nSERVER PROCESS HAS EXECUTED REQUESTED PROCESS AND SENT RESULT " + c + " TO THE CLIENT");

            // Close client socket
            clientSocket.close();
            System.out.println("\nSERVER PROCESS EXITING...");

            // Close server socket
            ss.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
