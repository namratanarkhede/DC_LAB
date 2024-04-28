import java.io.*;
import java.net.*;

class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket sersock = new ServerSocket(3000);
        System.out.println("Server ready for connections");

        Socket sock = sersock.accept();
        System.out.println("Connection established");

        // Reader to read from socket's input stream
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        // Writer to write to socket's output stream
        PrintWriter pwrite = new PrintWriter(sock.getOutputStream(), true);

        String fun;
        int a, b, c;

        while (true) {
            fun = receiveRead.readLine(); // Read the operation command
            if (fun == null) {
                System.out.println("Client has closed the connection");
                break; // Exit the loop if client closes the connection
            }
            
            System.out.println("Operation: " + fun);

            a = Integer.parseInt(receiveRead.readLine()); // First number
            System.out.println("Parameter 1: " + a);
            b = Integer.parseInt(receiveRead.readLine()); // Second number
            System.out.println("Parameter 2: " + b);

            // Perform operations based on the command
            switch (fun.toLowerCase()) {
                case "add":
                    c = a + b;
                    System.out.println("Addition = " + c);
                    pwrite.println("Addition = " + c);
                    break;
                case "sub":
                    c = a - b;
                    System.out.println("Subtraction = " + c);
                    pwrite.println("Subtraction = " + c);
                    break;
                case "mul":
                    c = a * b;
                    System.out.println("Multiplication = " + c);
                    pwrite.println("Multiplication = " + c);
                    break;
                case "div":
                    if (b != 0) {
                        c = a / b;
                        System.out.println("Division = " + c);
                        pwrite.println("Division = " + c);
                    } else {
                        System.out.println("Division by zero error");
                        pwrite.println("Error: Division by zero");
                    }
                    break;
                default:
                    System.out.println("Invalid operation");
                    pwrite.println("Error: Invalid operation");
            }
        }

        sock.close();
        sersock.close();
    }
}
