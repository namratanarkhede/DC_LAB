import java.io.*;
import java.net.*;

class Client {
    public static void main(String[] args) {
        try (Socket sock = new Socket("127.0.0.1", 3000);
             BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pwrite = new PrintWriter(sock.getOutputStream(), true);
             BufferedReader receiveRead = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

            System.out.println("Client ready, type and press Enter key");

            String receiveMessage, sendMessage, temp;
            while (true) {
                try {
                    System.out.println("\nEnter operation to perform (add, sub, mul, div)....");
                    temp = keyRead.readLine();
                    sendMessage = temp.toLowerCase();
                    pwrite.println(sendMessage);

                    System.out.println("Enter first parameter: ");
                    sendMessage = keyRead.readLine();
                    pwrite.println(sendMessage);

                    System.out.println("Enter second parameter: ");
                    sendMessage = keyRead.readLine();
                    pwrite.println(sendMessage);

                    if ((receiveMessage = receiveRead.readLine()) != null) {
                        System.out.println(receiveMessage);
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while communicating with the server: " + e.getMessage());
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to connect to server: " + e.getMessage());
        }
    }
}
