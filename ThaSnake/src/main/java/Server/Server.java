package Server;

import Interface.iSnake;
import Handler.Player;
import Model.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Server extends Thread implements iSnake {

    private static HashMap<Integer,Player> players;
    private static Game game;
    private static ServerObservable observable;
    private static boolean on;

    public static void main(String[] arg) {

        System.out.println("Soy el servidor!");
        on = true;
        players = new HashMap<Integer, Player>();
        game = new Game();                                  //Creamos el juego sin ning?n jugador
        observable = new ServerObservable(game);

        try {

            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = null;

            int clientId = 1;

            while(on){
                socket = serverSocket.accept();
                Player p = new Player(socket,clientId);
                players.put(clientId,p);
                game.getSnakePlayers().add(p.getSnake());           //A?adimos la nueva serpiente al modelo
                clientId ++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void endGame(){
        on = false;
    }


    //METODO PARA MANDAR LAS PUNTUACIONES A TODOS
    public static void broadcastPoints(){
        observable.broadcastPts();
    }

    public static void broadCastGameStatus(){
        observable.broadcastStatus();
    }

    public static void removePlayerFromServer(int id){
        game.kill(id);
        players.remove(id);

    }

    public static void sendMessageToPlayer(int id, String message){
        players.get(id).write(message);
    }

    public static ServerObservable getObservable() {
        return observable;
    }


}
