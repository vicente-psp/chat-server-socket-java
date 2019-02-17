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
        System.out.println("Aguardando cliente...");
        serverSocket = new ServerSocket(porta);
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado!");
            trataConexao(socket);
        }
    }

    public void trataConexao(Socket socket) throws IOException, ClassNotFoundException{
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            Object objeto = objectInputStream.readObject();
            System.out.println(objeto);


        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Erro de IOException no servidor. Error: " + e.getMessage());
        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Erro de ClassNotFoundException no servidor. Error: " + e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro de Exception no servidor. Error: " + e.getMessage());
        }finally {
            socket.close();
        }
    }
}
