import java.net.*;
import java.io.*;

public class IPCClient {
    public static void main(String[] args) {
        try {
            // Connect to the server on localhost at port 1200
            Socket s = new Socket("localhost", 1200);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\n\t************* CLIENT PROCESS STARTED *************");
            System.out.println("\n********* PLEASE ENTER THE VALUES OF Number 1 AND Number 2 TO PASS THEM TO SERVER PROCESS ********\n");
            
            // Read the first number from the user
            System.out.print("Enter Number 1: ");
            int a = Integer.parseInt(br.readLine());
            System.out.println("Number 1 ====> " + a);
            dos.writeInt(a);

            // Read the second number from the user
            System.out.print("Enter Number 2: ");
            int b = Integer.parseInt(br.readLine());
            System.out.println("Number 2 ====> " + b);
            dos.writeInt(b);

            // Receive the result from the server
            int result = dis.readInt();
            System.out.println("\n..SERVER..\n"); // Corrected the misplaced print statement
            System.out.println("THE ADDITION OF " + a + " AND " + b + " IS " + result);

            // Close the socket connection
            s.close();
        } catch (Exception e) {
            System.out.println("Exception is " + e);
        }
    }
}
