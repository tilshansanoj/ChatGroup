package Class;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){

        try {
            while(!serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                Scanner scanner = new Scanner(socket.getInputStream());
                String username = scanner.nextLine();
                String role = scanner.nextLine();

                System.out.println( role +" "+ username + " has arrived");
                ClientHandler clientHandler = new ClientHandler(socket, role);

                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        }catch (IOException e) {

        }
    }
    public void closeServerSocket(){
        try {
            if(serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
