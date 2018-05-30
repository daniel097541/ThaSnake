package Comunication;

import Enum.Header;

import java.util.List;

public class Packet {

    private Header header;

    private List<String> args;


    public Packet(Header header, List<String> args) {
        this.header = header;
        this.args = args;
    }




    public String getCraftedPacket(){
        String crafted = header.toString() + ";";

        for(String string: args){
            crafted = crafted.concat(string) + ";";
        }
        return crafted;
    }




    public Header getHeader() {
        return header;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
