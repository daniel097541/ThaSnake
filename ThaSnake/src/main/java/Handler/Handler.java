package Handler;


import Comunication.Packet;
import Interface.iHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Enum.Header;

public abstract class Handler extends Thread implements iHandler {


    protected Socket socket;

    protected int id;

    protected DataOutputStream out;

    protected DataInputStream in;


    public int getClientId() {
        return id;
    }

    public void setClientId(int id) {
        this.id = id;
    }

    public void sendPacket(Packet packet) {

        try {
            out.writeUTF(packet.getCraftedPacket());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Fallo al enviar el paquete!");
        }

    }


    public Packet getPacketFromString(String packet){

        StringTokenizer tokenizer = new StringTokenizer(packet);
        Header header = Header.valueOf(tokenizer.nextToken(";"));
        List<String> args = new ArrayList<String>();

        while (tokenizer.hasMoreElements()){
            args.add(tokenizer.nextToken(";"));
        }

        return new Packet(header,args);
    }


    public String read(){
        String line = "";
        try {
            line = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public void write(String message){
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void close(){
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }












}
