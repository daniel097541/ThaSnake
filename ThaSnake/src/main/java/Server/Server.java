package Server;

import Interface.iSnake;
import Handler.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server implements iSnake {

    private static List<Player> players;


    public static void main(String[] arg) {

        System.out.println("Soy el servidor!");

        players = new ArrayList<Player>();
        try {

            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = null;

            int clientId = 1;

            while(true){
                socket = serverSocket.accept();
                players.add(new Player(socket,clientId));
                clientId ++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
