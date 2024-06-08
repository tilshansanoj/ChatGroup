package Class;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientHandler implements Runnable {

    public enum ClientType {PROPOSER, ACCEPTOR, LEARNER}
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private String  clientType;


    public ClientHandler(Socket socket, String clientType) {
        try {

            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);




            broadcastMessage("Server: A " + clientUsername + " entered the chat!");
            broadcastMessage("SERVER: " + clientType + " entered the chat!");

        } catch (IOException e) {
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    @Override
    public void run() {
    String messageFromClient;

    while(socket.isConnected()) {
        try {
            messageFromClient = bufferedReader.readLine();

            broadcastMessage(messageFromClient);
        }catch (IOException e){
            closeEverything(socket, bufferedWriter, bufferedReader);
            break;
        }
    }


    }

    public void broadcastMessage(String messageToSend){
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if(!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedWriter,bufferedReader);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("Server: " + clientUsername + " left the chat!");
    }

    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
        removeClientHandler();
        try {
            if(bufferedReader != null) {
                bufferedReader.close();
            }if(bufferedWriter != null) {
                bufferedWriter.close();
            }if(socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
