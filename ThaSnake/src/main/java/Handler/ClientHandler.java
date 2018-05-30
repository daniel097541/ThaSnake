package Handler;

import Comunication.Packet;
import Enum.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler extends Handler{


    private int id;


    public ClientHandler(Socket socket, DataInputStream input, DataOutputStream output) {
        this.socket = socket;
        this.in = input;
        this.out = output;
        this.start();
    }


    @Override
    public void run() {

        System.out.println("Soy un cliente");
        String line = read();
        while(true){
            if(line == null) continue;
            if(!applyChanges(getPacketFromString(line)))
                break;
            line = read();
        }
    }

    private boolean applyChanges(Packet packetFromString) {

        Header header = packetFromString.getHeader();

        if(header.equals(Header.FIN)){
            close();
            return false;
        }

        if(header.equals(Header.IDC)) {
            id = Integer.parseInt(packetFromString.getArgs().get(0));
            System.out.println("El servidor me ha proporcionado la id de jugador: " + id);
        }

        return true;
    }


    public int getClientId() {
        return id;
    }
}
