package Server;

import Comunication.MegaPacket;
import Comunication.Packet;
import Enum.Direction;
import Enum.Header;
import Handler.Player;
import Model.Apple;
import Model.BodyPart;
import Model.Game;
import Model.Snake;

import java.util.ArrayList;
import java.util.List;

public class ServerObservable extends java.util.Observable{


    private Game game;


    ServerObservable(Game game) {
        this.game = game;
    }



    // CONVIERTE TODO EL ESTADO DEL JUEGO EN UN STRING
    private String craftStatus(){
        List<Packet> packets = new ArrayList<>();
        for(Snake snake : game.getSnakePlayers()){
            packets.add(new Packet(Header.MOV, snake.getSnakeCrafted()));
        }


        for(Snake bot : game.getSnakeBots()){
            packets.add(new Packet(Header.MOV, bot.getSnakeCrafted()));
        }

        for(Apple apple : game.getApples()){
            packets.add(new Packet(Header.APPLE, apple.getAppleCrafted()));
        }

        return new MegaPacket(Header.STATUS,packets).getCraftedPacket();
    }



    void broadcastStatus(){

        //tradutor traduce a un string el estado de cada serpiente en el juego
        String status = craftStatus();

        // se envia el estatus a cada cliente del servidor como una difusion par que cada cliente pinte
        // el estado del juego en su correspondiente vista
        for(Player player: Server.getPlayers().values()){
            Server.sendMessageToPlayer(player.getClientId(),status);
        }



    }

    void broadcastPts(){
        List<Packet> packets = new ArrayList<>();
        for(Snake snakP : game.getSnakePlayers()){
            List<String> args = new ArrayList<>();
            args.add(snakP.getId() + "");
            args.add(snakP.getPoints() + "");
            Packet packet = new Packet(Header.PTS,args);
            packets.add(packet);
        }

        MegaPacket megaPacket = new MegaPacket(Header.MEGAPTS,packets);

        for(Snake snake : game.getSnakePlayers()){
            Server.sendMessageToPlayer(snake.getId(),megaPacket.getCraftedPacket());
        }
    }


    void broadcastRemoveDead(int id){

        Snake dead = game.getSnakePlayers().get(id-1);
        List<String> args = new ArrayList<>();
        for(BodyPart b : dead.getSnake()){
            args.add(b.getX() + "");
            args.add(b.getY() + "");
        }

        Packet packet = new Packet(Header.REMOVE_DEAD,args);

        for(Snake snake : game.getSnakePlayers()){
            if(snake.getId() != id){
                Server.sendMessageToPlayer(snake.getId(),packet.getCraftedPacket());
            }
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
    
        
    public void setNumberBots(int numBots){
        for(int i = 0; i<numBots; i++){
            Snake bot = new Snake();
            bot.setColor("RED");
            bot.setId(game.getSnakePlayers().size() + i);
            game.getSnakeBots().add(bot);
        }
    }



}
