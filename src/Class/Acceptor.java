package Class;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Acceptor extends Client{

    public Acceptor(Socket socket, String username) {
        super(socket, username);
        this.type = ClientType.ACCEPTOR;
    }
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username for the network: ");
        String username = scanner.nextLine();

        Socket socket = new Socket("localhost", 1234);
        Acceptor acceptor = new Acceptor(socket, username);

        acceptor.listenForMessage();
        acceptor.sendMessage();

    }
}
