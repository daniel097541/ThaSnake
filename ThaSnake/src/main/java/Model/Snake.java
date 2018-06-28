/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author DiegoPC
 */
public class Snake {
    
    private Integer id;

    private ArrayList<BodyPart> snake;

    private String color;


    private Integer points;
    
    private int x , y;
    protected int size = 5; 
    
    private boolean right = true, left = false, up = false, down = false;
    
    public Snake(){
        Random r = new Random();
        this.x = r.nextInt(20);
        this.y = r.nextInt(20);
        this.snake = new ArrayList();
        this.points = 0;
    }
    
    public Snake(Integer id){
        this.snake = new ArrayList();
        this.id = id;
        this.points = 0;
    }
    
    public void addToSnake(int x, int y){
        BodyPart b = new BodyPart(x, y);
        snake.add(b);
    }


    public List<String> getSnakeCrafted(){
        List<String> args = new ArrayList<String>();
        args.add(id + "");
        args.add(color);

        args.add(snake.get(snake.size()-1).getX() + "");
        args.add(snake.get(snake.size()-1).getY() + "");

        args.add(snake.get(0).getX() + "");
        args.add(snake.get(0).getY() + "");

        /*
        args.add(this.x + "");
        args.add(this.y + "");
        if(down) {
            args.add(x + "");
            args.add(y + size + "");
        }
        if (up){
            args.add(x + "");
            args.add(y - size + "");
        }
        if(left){
            args.add(x + size + "");
            args.add(y + "");
        }
        if (right){
            args.add(x - size + "");
            args.add(y + "");
        }
        */


        return args;
    }



    
    public void addSize(){
        size++;
    }
    
    public void addPoints(){
        points += 50;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<BodyPart> getSnake() {
        return snake;
    }

    public void setSnake(ArrayList<BodyPart> snake) {
        this.snake = snake;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
