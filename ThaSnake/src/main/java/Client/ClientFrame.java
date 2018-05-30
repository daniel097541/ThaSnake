package Client;

import Handler.Handler;

import java.awt.*;

public class ClientFrame extends javax.swing.JFrame{

    private Handler handler;

    public ClientFrame(Handler handler) throws HeadlessException {

        this.handler = handler;
        addKeyListener(new KeyListener(handler));

    }
}
