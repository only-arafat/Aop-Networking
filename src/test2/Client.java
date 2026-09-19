package test2;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client(String serverAddress, int serverPort) {
        try {
            Socket socket = new Socket(serverAddress, serverPort);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter a number: ");
            int number = sc.nextInt();

            oos.writeObject(number);

            System.out.println(ois.readObject());

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


