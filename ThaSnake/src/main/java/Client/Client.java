package Client;

import Handler.ClientHandler;
import Interface.iSnake;
import View.ClientTable;
import View.ClientView;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;

public class Client implements iSnake {


    private static ClientHandler handler;
    private static ClientView view;
    private static ClientTable table;
    private static ClientObservable observable;

    public static void main(String[] args) throws InterruptedException {


        //crear aqui el panel del login y sacar la ip de ahi
        login();
        view = new ClientView(handler);
        observable = new ClientObservable(view);
        //table = new ClientTable();

    }

    public static void paint(int x, int y, String color){
        if(view != null)
            view.paintPoint(x, y, color);
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
