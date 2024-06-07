import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Proposer extends Client{

    private int proposalId;


    public Proposer(Socket socket, String username, int proposalId) {
        super(socket, username);
        this.proposalId = proposalId;
        this.type = ClientType.PROPOSER;

    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username for the network: ");
        String username = scanner.nextLine();

        System.out.println("Enter your Proposal ID for the network: ");
        int proposalId = scanner.nextInt();

        Socket socket = new Socket("localhost", 1234);
        Proposer proposer = new Proposer(socket,username,proposalId);

        System.out.println("Enter your proposal Value for the network: ");

        proposer.listenForMessage();
        proposer.sendMessage();

    }
}
