package Client;

import Handler.ClientHandler;
import Interface.iSnake;
import View.ClientFrame;
import View.ClientTable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements iSnake {


    private static ClientHandler handler;
    private static ClientFrame frame;
    private static ClientTable table;
    private static ClientObservable observable;

    public static void main(String[] args) {


        //crear aqui el panel del login y sacar la ip de ahi
        login();
        frame = new ClientFrame(handler);
        observable = new ClientObservable(frame);
        //table = new ClientTable();

    }


    private static void login(){
        try {
            Socket socket = new Socket(HOST,PORT);
            handler = new ClientHandler(socket,new DataInputStream(socket.getInputStream()), new DataOutputStream(socket.getOutputStream()));
            System.out.println("Logueado en el servidor!");
        } catch (Exception e) {
            System.out.println("Error: No se ha podido logear en el servidor!");
        }
    }







}
