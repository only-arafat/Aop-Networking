package test;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(33333)) {
            System.out.println("Server listening on port 33333...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Initialize stream pipelines (Output stream first to avoid deadlock)
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Concurrent Read Thread (Inline Lambda)
            new Thread(() -> {
                try {
                    while (true) {
                        Object message = ois.readObject();
                        System.out.println("\nClient: " + message);
                        System.out.print("Server: ");
                    }
                } catch (Exception e) {
                    System.out.println("\nClient disconnected.");
                }
            }).start();

            // Write Loop (Main Thread)
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Server: ");
                String message = scanner.nextLine();
                oos.writeObject(message);
                oos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
