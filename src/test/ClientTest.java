package test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 33333);
            System.out.println("Connected to server!");

            // Initialize stream pipelines
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Concurrent Read Thread (Inline Lambda)
            new Thread(() -> {
                try {
                    while (true) {
                        Object message = ois.readObject();
                        System.out.println("\nServer: " + message);
                        System.out.print("Client: ");
                    }
                } catch (Exception e) {
                    System.out.println("\nServer disconnected.");
                }
            }).start();

            // Write Loop (Main Thread)
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Client: ");
                String message = scanner.nextLine();
                oos.writeObject(message);
                oos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
