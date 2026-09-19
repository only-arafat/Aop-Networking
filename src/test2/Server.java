package test2;


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
            serverSocket = new ServerSocket(44444);

            System.out.println("Server is waiting ... ");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Server accepts a client ... ");

                serve(clientSocket);
            }

        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        clientCount++;

        ObjectOutputStream oos =
                new ObjectOutputStream(clientSocket.getOutputStream());

        ObjectInputStream ois =
                new ObjectInputStream(clientSocket.getInputStream());

        int number = (int) ois.readObject();

        if(number == 1){
            oos.writeObject("Not prime");
        }
        else if(number == 2 || number == 3 || number == 5 || number == 7){
            oos.writeObject(" prime");
        }
        else if(number % 2==0 || number % 3==0 || number % 5==0 || number % 7==0 ){
            oos.writeObject("Not prime");
        }
        else{
            oos.writeObject(" prime");
        }

        clientSocket.close();
    }

    public static void main(String args[]) {

        Server server = new Server();
    }
}

