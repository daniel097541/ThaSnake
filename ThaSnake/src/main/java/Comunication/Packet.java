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

        int index = 0;
        for(String string: args){
            if(index == args.size()-1){
                crafted = crafted.concat(string);
            }
            else
                crafted = crafted.concat(string) + ";";
            index ++;
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
