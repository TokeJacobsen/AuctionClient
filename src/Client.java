import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by den udvalgte on 19-09-2017.
 */
public class Client
{

    private String clientName;
    private InetAddress host;
    private int PORT = 1235;
    Scanner userEntry, input;
    PrintWriter output;


    public Client()
    {
        System.out.println("Welcome to the auction! Please type your name:");
        userEntry = new Scanner(System.in);
        clientName = userEntry.nextLine();

        try
        {
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();

    }

    private void accessServer()
    {
        Socket link = null;

        try
        {
            link = new Socket(host,PORT);
            System.out.println("Thank you, " + clientName + "\nYou are now connected to the auction.");

            input = new Scanner(link.getInputStream());

            output = new PrintWriter(link.getOutputStream(),true);

            output.println(clientName);

            String fromServer = input.nextLine();
            System.out.println(fromServer);

            String toServer = userEntry.nextLine();
            System.out.println(toServer);
            output.println(toServer);



        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }

        finally
        {
            try
            {
                System.out.println(
                        "\n* Closing connection... *");
                link.close();					//Step 4.
            }
            catch(IOException ioEx)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
