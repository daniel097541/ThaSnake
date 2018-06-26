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

    private void applyRemoveDead(String line){
        Packet p = getPacketFromString(line);
        for(int i = 0; i <= p.getArgs().size() - 1 ; i++){
            int x = Integer.parseInt(p.getArgs().get(i));
            int y = Integer.parseInt(p.getArgs().get(i+1));
            Client.Client.getObservable().paint(x,y,"WHITE");
            i++;
        }
    }

    @Override
    public void run() {

        System.out.println("Soy un cliente");
        String line = read();


        while(on){

            //si no escribe nada el server sigue comprobando
            if(line == null) continue;

            //orden de borrar los bloques de un jugador muerto
            if(line.startsWith(Header.REMOVE_DEAD.toString())){
                applyRemoveDead(line);
                line = read();
                continue;
            }

            //ejecutado al morir o al acabarse la partida
            if(line.startsWith(Header.DIE.toString()) || line.startsWith(Header.FIN.toString())){
                close();
                on = false;
                break;
            }

            // paquete de puntos para actualizar los marcadores de todos los jugadores
            if(line.startsWith(Header.MEGAPTS.toString())){
                applyPtsChanges(getMegaPacketFromString(line));
                line = read();
                continue;
            }

            // paquete que aplica el estado del tablero en la vista
            if(line.startsWith("STATUS")) {
                applyViewChanges(getMegaPacketFromString(line));
                line = read();
                continue;
            }

            // paquete de login con la id asignada al cliente
            loginId(getPacketFromString(line));
            line = read();
        }
    }


    private void applyViewChanges(MegaPacket megaPacket){

        for(Packet packet : megaPacket.getArgs()){

            if(packet.getHeader().equals(Header.APPLE)){
                Client.Client.getObservable().paint(Integer.parseInt(packet.getArgs().get(0)),Integer.parseInt(packet.getArgs().get(1)),"RED");
                return;
            }
            int xPaint = Integer.parseInt(packet.getArgs().get(2));
            int yPaint = Integer.parseInt(packet.getArgs().get(3));


            int xRemove = Integer.parseInt(packet.getArgs().get(4));
            int yRemove = Integer.parseInt(packet.getArgs().get(5));
            
            
            Client.Client.getObservable().paint(xPaint,yPaint,packet.getArgs().get(1));
            Client.Client.getObservable().paint(xRemove, yRemove, "WHITE");
        }
        
    }

    private void applyPtsChanges(MegaPacket megaPacket){

        for(Packet packet : megaPacket.getArgs()){
            int id = Integer.parseInt(packet.getArgs().get(0));
            int pts = Integer.parseInt(packet.getArgs().get(1));
            Client.Client.getObservable().modifyTable(id,pts);
        }
    }


    private void loginId(Packet packetFromString) {
        Header header = packetFromString.getHeader();
        if(header.equals(Header.IDC)) {
            id = Integer.parseInt(packetFromString.getArgs().get(0));
            System.out.println("El servidor me ha proporcionado la id de jugador: " + id);
        }
    }


    public int getClientId() {
        return id;
    }


}
