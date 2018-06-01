package Comunication;

import Enum.Header;

import java.util.List;

public class MegaPacket {

    private Header header;

    private List<Packet> args;


    public MegaPacket(Header header, List<Packet> args) {
        this.header = header;
        this.args = args;
    }


    public String getCraftedPacket(){
        String crafted = header.toString() + ":";
        int index = 0;
        for(Packet packet : args){
            crafted = crafted.concat(packet.getCraftedPacket());
            if(index < args.size()-1){
                crafted = crafted.concat(":");
            }
        }
        return crafted;
    }


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<Packet> getArgs() {
        return args;
    }

    public void setArgs(List<Packet> args) {
        this.args = args;
    }
}
