package View;

import Comunication.Packet;
import Enum.Direction;
import Enum.Header;
import Handler.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyListener extends KeyAdapter{

    private Handler handler;

    KeyListener(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e){
        applyChanges(e.getKeyCode(),true);
    }



    private void applyChanges(int keyCode, boolean pressed){

        Header heade = Header.DIR;
        List<String> args = new ArrayList<String>();

        args.add(handler.getClientId() + "");
        switch (keyCode) {
            case KeyEvent.VK_UP:
                args.add(Direction.UP.toString());
                break;
            case KeyEvent.VK_DOWN:
                args.add(Direction.DOWN.toString());
                break;
            case KeyEvent.VK_LEFT:
                args.add(Direction.LEFT.toString());
                break;
            case KeyEvent.VK_RIGHT:
                args.add(Direction.RIGHT.toString());
                break;
        }
        handler.sendPacket(new Packet(heade,args));
    }


}
