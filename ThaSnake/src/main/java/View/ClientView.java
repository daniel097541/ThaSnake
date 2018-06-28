/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Handler.Handler;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Daniel
 */
public class ClientView extends javax.swing.JFrame {

    /**
     * Creates new form ClientView
     * 
     *
     */
    
    private JPanel[][] snakePanel;
    
    public ClientView(Handler handler) {
        initComponents();
        
        addKeyListener(new KeyListener(handler));
        setFocusable(true);
        setResizable(false);
        setLocationRelativeTo(null);
        initGame(100, 100);
        setVisible(true);
    }
    
    public void paintPoint(int x, int y, String color){
        Color c = Color.BLACK;

        if(color.equalsIgnoreCase("RED"))
            c = Color.RED;
        if(color.equalsIgnoreCase("BLUE"))
            c = Color.BLUE;
        if(color.equalsIgnoreCase("black"))
            c = Color.black;
        if(color.equalsIgnoreCase("cyan"))
            c = Color.CYAN;
        if(color.equalsIgnoreCase("green"))
            c = Color.GREEN;
        if(color.equalsIgnoreCase("GRAY"))
            c = Color.MAGENTA;

        //si no es blanco pinta el color que sea
        if(!color.equalsIgnoreCase("WHITE"))
            snakePanel[y][x].setBackground(c);
        //por el contrario pinya en blanco que borrara el punto final
        else snakePanel[y][x].setBackground(Color.WHITE);

        scene.add(snakePanel[x][y]);
        repaint();
    }
    
    private void initGame(int x, int y){
        
        snakePanel = new JPanel[y][y];
        scene.setLayout(new GridLayout(x, y, 1, 1));
        
        for(int i = 0; i<x; i++){
            for(int j = 0; j<y; j++){
                snakePanel[i][j] = new JPanel();
                snakePanel[i][j].setBackground(Color.WHITE);
                scene.add(snakePanel[i][j]);
            }
            
        }
        
        scene.setVisible(true);
        this.setBounds(this.getX(), this.getY() + 10, this.getWidth(), this.getHeight() + 10);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scene = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout sceneLayout = new javax.swing.GroupLayout(scene);
        scene.setLayout(sceneLayout);
        sceneLayout.setHorizontalGroup(
            sceneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        sceneLayout.setVerticalGroup(
            sceneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel scene;
    // End of variables declaration//GEN-END:variables
}
