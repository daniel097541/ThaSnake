package Client;

import Handler.ClientHandler;
import Interface.iSnake;
import View.ClientFrame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client implements iSnake {


    private static ClientHandler handler;
    private static ClientFrame frame;

    public static void main(String[] args) {


        //crear aqui el panel del login y sacar la ip de ahi
        login();
        frame = new ClientFrame(handler);


    }


    private static void login(){
        try {
            Socket socket = new Socket(HOST,PORT);
            handler = new ClientHandler(socket,new DataInputStream(socket.getInputStream()), new DataOutputStream(socket.getOutputStream()));
        } catch (Exception e) {
            System.out.println("Error: No se ha podido logear en el servidor!");
        }
    }







}
