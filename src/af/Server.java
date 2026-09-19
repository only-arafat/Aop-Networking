package af;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    Server() throws IOException {
        try {
            ServerSocket ss = new ServerSocket(55555);
            System.out.println("Server waiting for client...");

            while(true){
                Socket clientSocket = ss.accept();
                System.out.println("Connected with a Client");
                serve(clientSocket);

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void serve(Socket clientSocket){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println(ois.readObject());
            oos.writeObject("Hlw from Server end");

            clientSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
