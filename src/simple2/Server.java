package simple2;


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
            serverSocket = new ServerSocket(44445); //[cite: 7]
            System.out.println("Server is waiting ... ");
            while (true) {
                Socket clientSocket = serverSocket.accept(); //[cite: 7]
                System.out.println("Server accepts a client ... ");
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e); //[cite: 7]
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        clientCount++;
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream()); //[cite: 7]
        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream()); //[cite: 7]

        // Read integer sent from the client
        Object receivedObj = ois.readObject();
        if (receivedObj instanceof Integer) {
            int number = (Integer) receivedObj;
            boolean isPrime = checkPrime(number);

            String result = "Server response: Number " + number + " is " + (isPrime ? "PRIME" : "NOT PRIME");
            oos.writeObject(result);
        } else {
            oos.writeObject("Server response: Invalid input type received.");
        }

        // Close client socket
        clientSocket.close(); //[cite: 7]
    }

    // Helper method to determine if a number is prime
    private boolean checkPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String args[]) {
        Server server = new Server(); //[cite: 7]
    }
}