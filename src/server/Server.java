package server;

import util.Mensagem;
import util.Status;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        try{
            new Server(3322);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "ERRO - Erro de entrada ou saída de dados: " + e.getMessage());
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "ERRO - Classe não encontrada: " + e.getMessage());
        }
    }

    public Server(int porta) throws IOException, ClassNotFoundException {
        System.out.println("Aguardando conexão...\n");
        criaServerSocket(porta);
        while(true){
            Socket socket = esperaConexao();
            System.out.println("Cliente conectado.");
            trataConexao(socket);
            System.out.println("Conexão finalizada.\n");
        }
    }

    private void criaServerSocket(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
    }

    public Socket esperaConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }

    public void trataConexao(Socket socket)throws IOException, ClassNotFoundException {
        try {
            ObjectOutputStream objOutput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objInput = new ObjectInputStream(socket.getInputStream());

            Mensagem msg = (Mensagem) objInput.readObject();
            String operacao = msg.getOperacao();
            Mensagem mensagem = null;
            if(operacao.equals("CLIENT")){
                String nome = (String) msg.getParam("nome");
                String sobrenome = (String) msg.getParam("sobrenome");

                mensagem = new Mensagem("SERVER");

                if(nome == null || sobrenome == null){
                    mensagem.setStatus(Status.PARAMERROR);
                }else{
                    mensagem.setStatus(Status.OK);
                    mensagem.setParam("mensagem", "Hello World, " + nome + " " + sobrenome);
                }

            }

            objOutput.writeObject(mensagem);
            objOutput.flush();

            System.out.println("Mensagem enviada para o Cliente: " + msg);

            objOutput.close();
            objInput.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro no servidor: " + e.getMessage());
        }finally{
            fechaSocket(socket);
        }
    }

    private void fechaSocket(Socket socket) throws IOException {
        socket.close();
    }
}
