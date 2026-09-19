package tcpsimple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private int clientCount = 0;

    Server() {
        try {
            serverSocket = new ServerSocket(44444); // 0 - 2^16  // default IP: local IP
            System.out.println("Server is waiting ... ");
            while (true) {
                Socket clientSocket = serverSocket.accept(); // waiting for connection here - blocking call - like scanner input
                System.out.println("Server accepts a client ... ");
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        clientCount++;
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
        System.out.println(ois.readObject() + ", " + clientCount); // blocking call again
        oos.writeObject("msg from server: Hello Client, " + clientCount);

        // must close client socket
        clientSocket.close();
    }

    public static void main(String args[]) {

        Server server = new Server();
    }
}

