package Client;


import View.ClientFrame;

import java.util.Observer;

public class ClientObservable implements Observer{


    private ClientFrame view;


    ClientObservable(ClientFrame view) {
        this.view = view;
    }

    public void paintCoord(int x, int y, int color){
        // pintar en la vista
    }

    public void unPaintCoord(int x, int y, int color){
        // borrar en la vista
    }

















    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
