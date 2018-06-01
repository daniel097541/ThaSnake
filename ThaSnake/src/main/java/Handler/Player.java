package Handler;

import Comunication.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import Enum.Header;
import Model.Snake;

public class Player extends Handler{
    
    private Snake snake;

    public Player(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
        this.on = true;
        try {
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new DataInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        sendInitialMessage();
        System.out.println("Se ha registrado en el servidor el cliente con el id: " + id);
        this.snake = new Snake(id);
        this.start();

    }


    private void sendInitialMessage(){
        Header header = Header.IDC;
        List<String> args = new ArrayList<String>();
        args.add(id + "");
        sendPacket(new Packet(header,args));
    }


    @Override
    public void run() {

        String line = read();
        while(on){
            if(line == null) continue;
            System.out.println(line);
            line = read();
        }


    }

    public Snake getSnake() {
        return snake;
    }
    
    
}
