package Client;

import Handler.ClientHandler;
import Interface.iSnake;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client implements iSnake {


    private static ClientHandler clientHandler;
    private static ClientFrame frame;

    public static void main(String[] args) {


        //crear aqui el panel del login y sacar la ip de ahi
        login();


    }


    private static void login(){
        try {
            Socket socket = new Socket(HOST,PORT);
            clientHandler = new ClientHandler(socket,new DataInputStream(socket.getInputStream()), new DataOutputStream(socket.getOutputStream()));
            frame = new ClientFrame(clientHandler);
        } catch (Exception e) {
            System.out.println("Error: No se ha podido logear en el servidor!");
        }
    }





}
