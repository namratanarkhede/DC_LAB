import java.io.*;
import java.net.*;

public class ClientTwo {
    public static void main(String args[]) throws IOException {
        Socket s = new Socket("localhost", 7000);
        PrintStream out = new PrintStream(s.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        String str;
        
        while(true) {
            System.out.println("Waiting for token");
            str = in.readLine();
            if(str.equalsIgnoreCase("Token")) {
                System.out.println("Do you want to send some data");
                System.out.println("Enter Yes or no");
                str = br.readLine();
                if(str.equalsIgnoreCase("Yes")) {
                    System.out.println("Enter the data");
                    str = br.readLine();
                    out.println(str);
                }
            }
        }
    }
}
