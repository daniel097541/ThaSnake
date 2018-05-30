package Interface;

import Comunication.Packet;

public interface iHandler {

    String read();
    void close();
    Packet getPacketFromString(String message);
    void sendPacket(Packet packet);

}
