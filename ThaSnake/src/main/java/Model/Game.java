/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import Server.Server;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author DiegoPC
 */
public class Game extends Thread{
    
    private boolean running = true;
    
    private ArrayList<Snake> snakePlayers;
    private ArrayList<Snake> snakeBots;
    
    private Apple a;
    private ArrayList<Apple> apples;
    
    private Random r;
    
    private int ticks;
    
    public Game(){
        this.snakePlayers = new ArrayList();
        this.snakeBots = new ArrayList();
        this.apples = new ArrayList();
        this.r = new Random();
        start();
    }
    
    private void tick(){
        if(snakePlayers.isEmpty()){
            //No hacemos nada
        } else{
            //Por cada serpiente registrado, comprobamos si esta vac�a, si es as� la creamos
            for(Snake snakeP: snakePlayers){
                if(snakeP.getSnake().isEmpty()) {
                    snakeP.addToSnake(snakeP.getX(), snakeP.getY());
                }
            }
            
            //Si no hay ninguna manzana, la creamos
            if(apples.isEmpty()){      
                int x = r.nextInt(59);
                int y = r.nextInt(59);
                this.a = new Apple(x,y);
                apples.add(a);
            }
            
            //Iteramos entre todas las manzanas para ver que jugador la coge
            for(int i = 0;i<apples.size();i++){
                for(Snake snakeP: snakePlayers){
                    //Si la X e Y de ambos coinciden: a�adimos puntos al jugador, aumentamos su tama�o y quitamos esa manzana
                    if(snakeP.getX() == apples.get(i).getX() && snakeP.getY() == apples.get(i).getY()){
                        snakeP.addSize();
                        snakeP.addPoints();
                        if(!apples.isEmpty()){
                            apples.remove(i);
                            i--;
                            break;
                        }
                    }
                }
            }
            
            //Sumamos ticks al juego que serviran para comprobar la direccion
            ticks++;

            //Si los ticks son mayores que 150000 modificamos la serpiente
            if(ticks>70000){
                for(Snake snakeP: snakePlayers){
                    //Cambiamos la direccion de la serpiente
                    if(snakeP.isRight()) snakeP.setX(snakeP.getX()+1);
                    if(snakeP.isLeft()) snakeP.setX(snakeP.getX()-1);                  
                    if(snakeP.isUp()) snakeP.setY(snakeP.getY()-1);
                    if(snakeP.isDown()) snakeP.setY(snakeP.getY()+1);
                    
                    //A�adimos a la serpiente un nuevo punto 
                    snakeP.addToSnake(snakeP.getX(),snakeP.getY());

                    //Eliminamos de la serpiente el ultimo punto
                    if(snakeP.getSnake().size() > snakeP.getSize()){
                        snakeP.getSnake().remove(0);
                    }

                }
                //Ponemos ticks a 0 para que se reinice
                ticks = 0;

                Server.broadCastGameStatus();
                try {
                    sleep(500);
                } catch (InterruptedException e) {

                }
            }
        }
    }
    
    @Override
    public void run(){
        while(running){
            tick();
        }
    }
    
    public Snake getSnakeById(int id){
        for(Snake snake : snakePlayers)
            if(snake.getId() == id)
                return snake;
        return null;
    }


    public void kill(int id){
        snakePlayers.remove(getSnakeById(id));
    }

    public ArrayList<Snake> getSnakePlayers() {
        return snakePlayers;
    }

    public void setSnakePlayers(ArrayList<Snake> snakePlayers) {
        this.snakePlayers = snakePlayers;
    }

    public ArrayList<Snake> getSnakeBots() {
        return snakeBots;
    }

    public void setSnakeBots(ArrayList<Snake> snakeBots) {
        this.snakeBots = snakeBots;
    }

    public ArrayList<Apple> getApples() {
        return apples;
    }

    public void setApples(ArrayList<Apple> apples) {
        this.apples = apples;
    }

}
