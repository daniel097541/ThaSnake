package Server;

import Comunication.MegaPacket;
import Comunication.Packet;
import Enum.Direction;
import Enum.Header;
import Model.Apple;
import Model.Game;
import Model.Snake;

import java.util.ArrayList;
import java.util.List;

public class ServerObservable extends java.util.Observable{


    private Game game;


    public ServerObservable(Game game) {
        this.game = game;
    }



    // CONVIERTE TODO EL ESTADO DEL JUEGO EN UN STRING
    public String craftStatus(){
        List<Packet> packets = new ArrayList<Packet>();
        for(Snake snake : game.getSnakePlayers()){
            packets.add(new Packet(Header.MOV, snake.getSnakeCrafted()));
        }

        for(Apple apple : game.getApples()){
            packets.add(new Packet(Header.APPLE, apple.getAppleCrafted()));
        }

        return new MegaPacket(Header.STATUS,packets).getCraftedPacket();
    }



    public void broadcastStatus(){

        //tradutor traduce a un string el estado de cada serpiente en el juego
        String status = craftStatus();

        // se envia el estatus a cada cliente del servidor como una difusion par que cada cliente pinte
        // el estado del juego en su correspondiente vista
        for(Snake snake : game.getSnakePlayers()){
            Server.sendMessageToPlayer(snake.getId(),status);
        }



    }

    public void broadcastPts(){
        List<Packet> packets = new ArrayList<Packet>();
        for(Snake snakP : game.getSnakePlayers()){
            List<String> args = new ArrayList<String>();
            args.add(snakP.getId() + "");
            args.add(snakP.getPoints() + "");
            Packet packet = new Packet(Header.PTS,args);
            packets.add(packet);
        }

        MegaPacket megaPacket = new MegaPacket(Header.PTS,packets);

        for(Snake snake : game.getSnakePlayers()){
            Server.sendMessageToPlayer(snake.getId(),megaPacket.getCraftedPacket());
        }
    }
    
    public void changeDirection(int id, Direction direction){
        switch(direction){
           case RIGHT:
               game.getSnakeById(id).setDown(false);
               game.getSnakeById(id).setUp(false);
               game.getSnakeById(id).setRight(true);
               break;
               
           case LEFT:
               game.getSnakeById(id).setDown(false);
               game.getSnakeById(id).setUp(false);
               game.getSnakeById(id).setLeft(true);
               break;
           case UP:
               game.getSnakeById(id).setRight(false);
               game.getSnakeById(id).setLeft(false);
               game.getSnakeById(id).setUp(true);
               break;
           case DOWN:
               game.getSnakeById(id).setRight(false);
               game.getSnakeById(id).setLeft(false);
               game.getSnakeById(id).setDown(true);
               
       }
        
        
        
    }



}
