package Handler;

import Comunication.Packet;
import Enum.Direction;
import Enum.Header;
import Model.Snake;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player extends Handler{
    
    private Snake snake;

    public Player(Socket socket, int id, String color) {
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
        snake.setColor(color);
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
            changeModel(getPacketFromString(line));
            line = read();
        }
    }

    public Snake getSnake() {
        return snake;
    }
    
    private void changeModel(Packet packet){
        
        switch(packet.getHeader()){
            
            case DIR:
                int id = Integer.parseInt(packet.getArgs().get(0));
                Direction dir = Direction.valueOf(packet.getArgs().get(1));
                Server.Server.getObservable().changeDirection(id, dir);

        }
        
        
    }


    public void die(){
        on = false;
        List<String> args = new ArrayList<String>();
        args.add(snake.getId() + "");
        Packet packet = new Packet(Header.DIE, args);
        sendPacket(packet);
    }
    
    
    
}
