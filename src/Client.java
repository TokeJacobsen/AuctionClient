import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
    private String clientName;
    private InetAddress host;
    private int PORT = 1235;
    Scanner userEntry, input;
    PrintWriter output;

    private Socket link;


    public Client(String host, int PORT)
    {
        getClientName();

        try
        {
            this.host = InetAddress.getByName(host);
            this.PORT = PORT;
        }
        catch(UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
    }

    private void getClientName(){
        System.out.println("Welcome to the auction! Please type your name:");
        userEntry = new Scanner(System.in);
        clientName = userEntry.nextLine();
    }

    public void createConnection()
    {
        try
        {
            link = new Socket(host, PORT);
            input = new Scanner(link.getInputStream());

            output = new PrintWriter(link.getOutputStream(), true);
            System.out.println("Thank you, " + clientName + "\nYou are now connected to the auction.");
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }

    public void closeConnection(){

            try
            {
                System.out.println("\n* Closing connection... *");
                link.close();					//Step 4.
            }
            catch(IOException ioEx)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }

    }

    public void run(){
        try{
            output.println(clientName);
            while(true)
            {
                String fromServer = input.nextLine();
                System.out.println(fromServer);

                while(!userEntry.hasNextInt() || userEntry.nextInt() < 0){
                    System.out.println("Please type a valid, positive integer");
                }

                String toServer = userEntry.nextLine();

                output.println(toServer);

                if(fromServer.equals("nu lukker vi lortet")) {
                    closeConnection();
                    break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
