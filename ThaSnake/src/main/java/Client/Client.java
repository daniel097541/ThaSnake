package Client;

import Handler.ClientHandler;
import Interface.iSnake;
import View.ClientTable;
import View.ClientView;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
                login();
                System.out.println();
                view = new ClientView(handler);
                observable = new ClientObservable(view);
                table = new ClientTable();
                break;
            }
            JOptionPane.showMessageDialog(null,"Puerto incorrecto, introduzca uno nuevo");
        }
    }

    public static void paint(int x, int y, String color){
        if(view != null)
            view.paintPoint(x, y, color);
    }
    
    public static void addRow(Object[] row){
        ((DefaultTableModel)table.getjTable1().getModel()).addRow(row);
    }
    
    public static void modifyRow(Integer i,Object[] row,Integer points){
        table.getjTable1().getModel().setValueAt(points,i,1);
    }
   
    public static void modifyTable(Integer id,Integer points){
        Object row[] = {id,points};
        boolean found = false;
        for(int i=0;i<table.getjTable1().getRowCount()&&!found;i++){
            Integer valor = (Integer) table.getjTable1().getValueAt(i,0);
            if(valor == id){
                modifyRow(i,row,points);
                found = true;
                break;
            }
        }
        if(!found){
            addRow(row);
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

}
