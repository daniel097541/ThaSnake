/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DiegoPC
 */
public class Apple {
    
    private int x,y;
    
    public Apple(int x,int y){
        this.x = x;
        this.y = y;
    }
    
    public void tick(){
        
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

    public List<String> getAppleCrafted(){
        List<String> crafted = new ArrayList<String>();

        crafted.add(x + "");
        crafted.add(y + "");

        return crafted;
    }
    
}
