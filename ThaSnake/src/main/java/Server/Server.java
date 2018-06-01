package Server;

import Interface.iSnake;
import Handler.Player;
import Model.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Server extends Thread implements iSnake {

    private static HashMap<Integer,Player> players;
    private static Game game;

    public static void main(String[] arg) {

        System.out.println("Soy el servidor!");

        players = new HashMap<Integer, Player>();
        game = new Game();                                  //Creamos el juego sin ningún jugador
        
        try {

            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = null;

            int clientId = 1;

            while(true){
                socket = serverSocket.accept();
                Player p = new Player(socket,clientId);
                players.put(clientId,p);
                game.getSnakePlayers().add(p.getSnake());           //Añadimos la nueva serpiente al modelo
                clientId ++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void removePlayerFromServer(int id){
        players.remove(id);
    }

}
