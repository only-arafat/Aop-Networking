package tcpobject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import util.NetworkUtil;

public class Server {

    private ServerSocket serverSocket;
    int clientCount = 0;

    Server() {
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept(); // a new client socket is formed everytime a new conn comes, has remote IP and remote port, cannot be same for remote ip and
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThread(networkUtil);
        new WriteThread(networkUtil, "Server");
    }

    public static void main(String args[]) {

        Server server = new Server();
    }
}
