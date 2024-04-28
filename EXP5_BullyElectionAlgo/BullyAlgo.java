import java.io.*;

class BullyAlgo {
    int coord; // To hold the coordinator's process number
    int[] proc; // To hold the state of each process (0=crashed, 1=active)
    
    public void election(int n) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nThe Coordinator Has Crashed!");
        int flag = 1;
        
        while (flag == 1) {
            int crash = 0;
            for (int i = 0; i < n; i++) {
                if (proc[i] == 0) {
                    crash++;
                }
            }
            
            if (crash == n) {
                System.out.println("\n*** All Processes Are Crashed ***");
                break;
            } else {
                System.out.println("\nEnter The Initiator");
                int init = Integer.parseInt(br.readLine());
                
                if ((init < 1) || (init > n) || (proc[init - 1] == 0)) {
                    System.out.println("\nInvalid Initiator");
                    continue;
                }
                
                for (int i = init - 1; i < n; i++) {
                    System.out.println("Process " + (i + 1) + " Called For Election");
                }
                System.out.println("");
                
                for (int i = init - 1; i < n; i++) {
                    if (proc[i] == 0) {
                        System.out.println("Process " + (i + 1) + " Is Dead");
                    } else {
                        System.out.println("Process " + (i + 1) + " Is In");
                    }
                }
                
                for (int i = n - 1; i >= 0; i--) {
                    if (proc[i] == 1) {
                        coord = i + 1;
                        System.out.println("\n*** New Coordinator Is " + coord + " ***");
                        flag = 0;
                        break;
                    }
                }
            }
        }
    }

    public void Bully() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter The Number of Processes:");
        int n = Integer.parseInt(br.readLine());
        proc = new int[n];
        
        for (int i = 0; i < n; i++) {
            proc[i] = 1; // Assume all processes are up initially
        }
        
        coord = n; // Last process is the coordinator initially
        int ch;
        
        do {
            System.out.println("\n\t1. Crash A Process");
            System.out.println("\t2. Recover A Process");
            System.out.println("\t3. Display New Coordinator");
            System.out.println("\t4. Exit");
            ch = Integer.parseInt(br.readLine());
            
            switch (ch) {
                case 1:
                    System.out.println("\nEnter A Process To Crash");
                    int cp = Integer.parseInt(br.readLine());
                    if ((cp > n) || (cp < 1)) {
                        System.out.println("Invalid Process! Enter A Valid Process");
                    } else if (proc[cp - 1] == 1 && coord != cp) {
                        proc[cp - 1] = 0;
                        System.out.println("\nProcess " + cp + " Has Been Crashed");
                    } else if (proc[cp - 1] == 1 && coord == cp) {
                        proc[cp - 1] = 0;
                        election(n);
                    } else {
                        System.out.println("\nProcess " + cp + " Is Already Crashed");
                    }
                    break;
                case 2:
                    System.out.println("\nCrashed Processes Are:");
                    for (int i = 0; i < n; i++) {
                        if (proc[i] == 0) {
                            System.out.println(i + 1);
                        }
                    }
                    System.out.println("Enter The Process You Want To Recover");
                    int rp = Integer.parseInt(br.readLine());
                    if ((rp < 1) || (rp > n)) {
                        System.out.println("\nInvalid Process. Enter A Valid ID");
                    } else if (proc[rp - 1] == 0) {
                        proc[rp - 1] = 1;
                        System.out.println("\nProcess " + rp + " Has Recovered");
                        if (rp > coord) {
                            coord = rp;
                            System.out.println("\nProcess " + rp + " Is The New Coordinator");
                        }
                    } else {
                        System.out.println("\nProcess " + rp + " Is Not A Crashed Process");
                    }
                    break;
                case 3:
                    System.out.println("\nCurrent Coordinator Is " + coord);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid Entry!");
                    break;
            }
        } while (ch != 4);
    }

    public static void main(String[] args) throws IOException {
        BullyAlgo ob = new BullyAlgo();
        ob.Bully();
    }
}
