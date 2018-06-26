package Client;

import Handler.ClientHandler;
import Interface.iSnake;
import View.ClientTable;
import View.ClientView;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client implements iSnake {


    private static ClientHandler handler;
    private static ClientView view;
    private static ClientTable table;
    private static ClientObservable observable;
    private static String ip;
    private static String port;

    public static void main(String[] args) throws InterruptedException {


        //crear aqui el panel del login y sacar la ip de ahi
        
        start();


    }
    
    public static void start(){
        boolean correctIp = false;
        while(!correctIp){
            ip = JOptionPane.showInputDialog("Introduzca la IP del servidor: ");
            if(ip.equals(HOST)){
                correctIp = true;
                break;
            }
            JOptionPane.showMessageDialog(null, "IP incorrecta, introduzca una nueva");
        }
        boolean correctPort = false;
        while(!correctPort){
            port = JOptionPane.showInputDialog("Introduzca el puerto al que se quiere conectar: ");
            int p = Integer.parseInt(port);
            if(p == PORT){
                correctPort = true;
                observable = new ClientObservable();
                login();
                System.out.println();
                view = new ClientView(handler);
                table = new ClientTable();
                break;
            }
            JOptionPane.showMessageDialog(null,"Puerto incorrecto, introduzca uno nuevo");
        }
    }


    private static void login(){
        try {
            Socket socket = new Socket(ip,PORT);
            handler = new ClientHandler(socket,new DataInputStream(socket.getInputStream()), new DataOutputStream(socket.getOutputStream()));
            System.out.println("Logueado en el servidor!");
        } catch (Exception e) {
            System.out.println("Error: No se ha podido logear en el servidor!");
        }
    }


    public static ClientView getView() {
        return view;
    }

    public static ClientObservable getObservable() {
        return observable;
    }

    public static ClientHandler getHandler() {
        return handler;
    }

    public static ClientTable getTable() {
        return table;
    }
}
