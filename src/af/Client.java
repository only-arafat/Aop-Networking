package af;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    Client(String serverAddress, int serverPort) throws IOException {
        try {
            Socket s = new Socket(serverAddress, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            oos.writeObject("Hlw by client");
            System.out.println(ois.readObject());

            s.close();


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 55555;
        try {
            Client client = new Client(serverAddress, serverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

