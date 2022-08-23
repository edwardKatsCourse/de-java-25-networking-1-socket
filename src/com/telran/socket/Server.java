package com.telran.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(8080);) {

            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                     DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());) {


                    String message = dataInputStream.readUTF();
                    System.out.println(message);
                    dataOutputStream.writeUTF("Echo: " + message);


                    if (message.equals("exit")) {
                        break;
                    }
                }
            }


        }
    }
}

class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("exit");
        dos.flush();

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        System.out.println(dis.readUTF());
        dos.close();
        dis.close();
    }
}
