import java.net.InetAddress;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Client client = new Client("192.168.1.19", 1235);
        client.createConnection();
        client.run();


    }
}
