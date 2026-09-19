package simple2;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client(String serverAddress, int serverPort) {
        try {
            Socket socket = new Socket(serverAddress, serverPort); //[cite: 8]
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); //[cite: 8]
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); //[cite: 8]

            // Get number input from user console
            Scanner scanner = new Scanner(System.in);
            System.out.print("Connction Established! Enter an integer number to check for prime: ");
            int number = scanner.nextInt();

            // Send integer to server
            oos.writeObject(number);

            // Print server response
            System.out.println(ois.readObject()); //[cite: 8]

            // Close socket
            socket.close(); //[cite: 8]
        } catch (Exception e) {
            System.out.println(e); //[cite: 8]
        }
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1"; //[cite: 8]
        int serverPort = 44445; //[cite: 8]
        Client client = new Client(serverAddress, serverPort); //[cite: 8]
    }
}
