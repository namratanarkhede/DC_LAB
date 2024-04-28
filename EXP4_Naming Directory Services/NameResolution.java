import java.net.*;
import java.io.*;

public class NameResolution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the website URL (like google.com) to resolve its IP:");

        // Reading the domain name from user input
        String name = br.readLine();
        
        try {
            // Resolving the IP address of the entered domain name
            InetAddress ip = InetAddress.getByName(name);
            System.out.println("\nIP Address: " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            // This exception is thrown if the host could not be resolved
            System.out.println("\nNo such host is present. Please try again.");
        }
    }
}
