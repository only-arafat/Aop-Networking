package tcpsimple;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public Client(String serverAddress, int serverPort) {
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            //String name = "hlw server";
            oos.writeObject("msg from client: hlw server! this is client");

            System.out.println(ois.readObject());

            // must close socket here
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        Client client = new Client(serverAddress, serverPort);
    }
}

