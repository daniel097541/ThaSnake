/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Model.BodyPart;
import Model.Game;
import Model.Snake;
import Server.Server;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DiegoPC
 */
public class BotController extends Thread{
    
    private Game game;
    private Thread thread;
    private Boolean running = false;
    
    public BotController(){
        this.game = Server.getGame();
        start();
    }
    
    public void botMovement() throws InterruptedException{
        if(game.getApples().isEmpty()) return;
        
        
        //AQUI HACE EL MOVIMIENTO EVITANDO A LOS JUGADORES


        
        // AQUI HACE EL MOVIMIENTO DEPENDIENDO DE DONDE SALGA LA MANZANA
        for(Snake snake: game.getSnakeBots()){

            if(snake.getX() == game.getApples().get(0).getX()){
               if((snake.getY()<game.getApples().get(0).getY())){
                    snake.setRight(false);
                    snake.setLeft(false);
                    snake.setDown(true);
                }
                if((snake.getY()>game.getApples().get(0).getY())){
                    snake.setRight(false);
                    snake.setLeft(false);
                    snake.setUp(true);
                }
            }

            if(snake.getX() > game.getApples().get(0).getX()){
                snake.setUp(false);
                snake.setDown(false);
                snake.setLeft(true);
            }
            if(snake.getX() < game.getApples().get(0).getX()){
                snake.setUp(false);
                snake.setDown(false);
                snake.setRight(true);
            }
            sleep(10);
        }
    }

    @Override
    public void start(){    
        running = true;
        thread = new Thread(this,"Hilo del bot");             //CREAMOS HILO DEL JUEGO DE LA CLASE SCREEN
        thread.start();
    }
    
    @Override
    public void run(){
        while(true){
            try {
                botMovement();
            } catch (InterruptedException ex) {
                Logger.getLogger(BotController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(BotController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
