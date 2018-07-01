package Client;

import Comunication.Packet;
import Enum.Header;
import Handler.ClientHandler;
import Interface.iSnake;
import View.ClientTable;
import View.ClientView;
import View.ColorChooser;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements iSnake {


    private static ClientHandler handler;
    private static ClientView view;
    private static ClientTable table;
    private static ClientObservable observable;
    private static String ip;
    private static String port;
    private static String color;
    private static ColorChooser cc;

    public static void main(String[] args) throws InterruptedException {

        start();

    }
    
    public static void start(){

        

        cc = new ColorChooser();
        cc.setVisible(true);

        while(color == null){
            System.out.println(" ");
        }

        cc.setVisible(false);
        cc.dispose();
        
        //ip
        boolean correctIp = false;
        while(!correctIp){
            ip = JOptionPane.showInputDialog("Introduzca la IP del servidor: ");
            if(ip.equals(HOST)){
                correctIp = true;
                break;
            }
            JOptionPane.showMessageDialog(null, "IP incorrecta, introduzca una nueva");
        }

        //Para el numero de bots
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
    
    private static void setNumberBots(){
        if(handler.getClientId()==1){
            String number = JOptionPane.showInputDialog("Introduce el numero de bots con los que deseas jugar");
            //CREAR PAQUETE Y ENVIARLO
            Header heade = Header.BOTS;
            List<String> args = new ArrayList<String>();
            args.add(number);
            handler.sendPacket(new Packet(heade,args));
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

    public static String getColor() {
        return color;
    }

    public static void setColor(String color) {
        Client.color = color;
    }
    
    
}
