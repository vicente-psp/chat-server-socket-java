package server;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket = null;
    private static final int PORTA = 8000;

    public static void main(String[] args) {
        try {
            new Server(PORTA);
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "error: " + e.getMessage());
        }catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "error: " + e.getMessage());
        }
    }

    public Server(int porta)throws IOException, ClassNotFoundException{
        System.out.println("Aguardando conexão...");
        serverSocket = new ServerSocket(porta);
        while (true){
            Socket socket = serverSocket.accept();
        }
    }

    public void trataConexao(Socket socket) throws IOException, ClassNotFoundException{
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "error: " + e.getMessage());
        }finally {
            socket.close();
        }
    }
}
