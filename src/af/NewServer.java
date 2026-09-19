package af;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NewServer {
    public NewServer() throws IOException {
        try {
            ServerSocket ss = new ServerSocket(56565);
            System.out.println("Waiting for a client...");

            while (true) {
                Socket clientSocket = ss.accept();
                System.out.println("Connected with a client");
                serve(clientSocket);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        oos.flush(); // Send stream header to prevent handshake deadlock
        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

        int num = (int) ois.readObject();
        int count = 0;

        // Correct digit counting logic
        if (num == 0) {
            count = 1;
        } else {
            num = Math.abs(num); // Ensure negative numbers work correctly
            while (num != 0) {
                count++;
                num /= 10; // Divide by 10 to shift digits right
            }
        }

        oos.writeObject("digit: " + count);
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        NewServer server = new NewServer();
    }
}