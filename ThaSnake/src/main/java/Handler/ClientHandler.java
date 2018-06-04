package Handler;

import Comunication.MegaPacket;
import Comunication.Packet;
import Enum.Header;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler extends Handler{



    public ClientHandler(Socket socket, DataInputStream input, DataOutputStream output) {
        this.socket = socket;
        this.in = input;
        this.out = output;
        on = true;
        this.start();
    }


    @Override
    public void run() {

        System.out.println("Soy un cliente");
        String line = read();
        while(on){
            if(line == null) continue;

            if(line.startsWith(Header.REMOVE_DEAD.toString())){

                Packet p = getPacketFromString(line);
                for(int i = 0; i <= p.getArgs().size() - 1 ; i++){
                    int x = Integer.parseInt(p.getArgs().get(i));
                    int y = Integer.parseInt(p.getArgs().get(i+1));
                    Client.Client.paint(x,y,"WHITE");
                    i++;
                }
                continue;
            }

            if(line.startsWith(Header.DIE.toString())){
                close();
                on = false;
                break;
            }

            if(line.startsWith(Header.MEGAPTS.toString())){
                applyPtsChanges(getMegaPacketFromString(line));
            }

            if(!line.startsWith("STATUS")) {
                if (!applyChanges(getPacketFromString(line)))
                    break;
            }

            else if(line.startsWith("STATUS"))applyViewChanges(getMegaPacketFromString(line));

            line = read();
        }
        on = false;
    }


    private void applyViewChanges(MegaPacket megaPacket){

        for(Packet packet : megaPacket.getArgs()){

            if(packet.getHeader().equals(Header.APPLE)){
                Client.Client.paint(Integer.parseInt(packet.getArgs().get(0)),Integer.parseInt(packet.getArgs().get(1)),"RED");
                return;
            }
            int xPaint = Integer.parseInt(packet.getArgs().get(1));
            int yPaint = Integer.parseInt(packet.getArgs().get(2));


            int xRemove = Integer.parseInt(packet.getArgs().get(3));
            int yRemove = Integer.parseInt(packet.getArgs().get(4));
            
            
            Client.Client.paint(xPaint,yPaint,"RED");
            Client.Client.paint(xRemove, yRemove, "WHITE");
        }
        
    }

    private void applyPtsChanges(MegaPacket megaPacket){

        for(Packet packet : megaPacket.getArgs()){

            int id = Integer.parseInt(packet.getArgs().get(0));
            int pts = Integer.parseInt(packet.getArgs().get(1));
            // aplica los cambios en el table

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
