import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Learner extends Client{

    public Learner(Socket socket, String username) {
        super(socket, username);
        this.type = ClientType.LEARNER;
    }
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username for the network: ");
        String username = scanner.nextLine();

        Socket socket = new Socket("localhost", 1234);
        Learner learner = new Learner(socket, username);

        learner.listenForMessage();
        learner.sendMessage();

    }
}
