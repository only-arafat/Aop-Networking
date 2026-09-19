package af;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class NewClient {
    public NewClient(String serverAdd, int serverPort){
        try {
            Socket s = new Socket(serverAdd, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            oos.writeObject(num);
            System.out.println(ois.readObject());

            s.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException {
        String serverAdd = "127.0.0.1";
        int serverPort = 56565;

        NewClient client = new NewClient(serverAdd, serverPort);
    }
}
