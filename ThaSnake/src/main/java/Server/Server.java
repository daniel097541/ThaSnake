package Server;

import Handler.Player;
import Interface.iSnake;
import Model.Game;
import View.ServerPanel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;


public class Server extends Thread implements iSnake {

    private static HashMap<Integer,Player> players;
    private static Game game;
    private static ServerObservable observable;
    private static boolean on;
    private static BotController botController;
    private static ServerPanel serverPanel;

    private static Random random = new Random();

    public static void main(String[] arg) {



        System.out.println("Soy el servidor!");
        serverPanel = new ServerPanel();
        serverPanel.setVisible(true);
        on = false;

        while(!on){
            System.out.println("");
        }
        
        players = new HashMap<>();
        game = new Game();//Creamos el juego sin ningun jugador
        observable = new ServerObservable(game);       
        botController = new BotController();

        try {

            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket;

            int clientId = 1;

            while(on){
                socket = serverSocket.accept();
                Player p = new Player(socket,clientId);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    static void sendMessageToPlayer(int id, String message){
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

    static HashMap<Integer, Player> getPlayers() {
        return players;
    }

    public static Game getGame() {
        return game;
    }

    public static void sendOutAlert(int id){
        observable.sendOutAlert(id);
    }

    public static boolean isOn() {
        return on;
    }

    public static void setOn(boolean on) {
        Server.on = on;
    }
    
    public static void turnOff(){
        observable.sendTurnOff();
    }
    

    public static void crash(int id){
        getObservable().crash(id);
    }
    
    
}
