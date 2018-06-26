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
import java.util.Random;


public class Server extends Thread implements iSnake {

    private static HashMap<Integer,Player> players;
    private static Game game;
    private static ServerObservable observable;
    private static boolean on;

    private static Random random = new Random();
    private static List<String> colors;

    public static void main(String[] arg) {

        colors = new ArrayList<>();
        colors.add("RED");
        colors.add("BLUE");
        colors.add("BLACK");
        colors.add("CYAN");
        colors.add("green");

        System.out.println("Soy el servidor!");
        on = true;
        players = new HashMap<>();
        game = new Game();//Creamos el juego sin ningun jugador
        observable = new ServerObservable(game);

        try {

            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = null;

            int clientId = 1;

            while(on){
                int i = random.nextInt(colors.size()-1);
                socket = serverSocket.accept();

                Player p = new Player(socket,clientId, colors.get(i));
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

    public static void sendError(){

    }


    public static void sendMessageToPlayer(int id, String message){
        players.get(id).write(message);
    }

    public static ServerObservable getObservable() {
        return observable;
    }


    public static void killSnake(Integer id) {
        players.remove(id);
    }

    public static void broadcastRemoveDead(int id){
        observable.broadcastRemoveDead(id);
    }

    public static HashMap<Integer, Player> getPlayers() {
        return players;
    }
}
