package Class;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Proposer extends Client{

    private int proposalId;
    private int proposalValue;



    public Proposer(Socket socket, String username,ClientType type, int proposalId, int proposalValue) {
        super(socket, username);
        this.proposalId = proposalId;
        this.proposalValue = proposalValue;
        this.type = type;

    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 1234);
        Scanner scanner1 = new Scanner(socket.getInputStream());


        System.out.println("Enter your username for the network: ");
        String username = scanner.nextLine();

        PrintStream printStream = new PrintStream(socket.getOutputStream());
        printStream.println(username);

        System.out.println("Enter your role for the network: ");
        String role = scanner.nextLine();
        printStream.println(role);

//        System.out.println();
//
//        System.out.println("Enter your type for the network: ");
//        ClientType type = ClientType.valueOf(scanner.nextLine());
//        System.out.println();



        Proposer proposer = new Proposer(socket, username, ClientType.PROPOSER, 1, 44);


        proposer.listenForMessage();
        proposer.sendMessage();

    }
}
