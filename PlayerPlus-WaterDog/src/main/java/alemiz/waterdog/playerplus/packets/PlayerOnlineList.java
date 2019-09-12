package alemiz.waterdog.playerplus.packets;


import alemiz.stargate.gate.packets.StarGatePacket;
import alemiz.stargate.untils.gateprotocol.Convertor;

import java.util.ArrayList;
import java.util.List;

public class PlayerOnlineList extends StarGatePacket {

    /* This Packet is created to send Response with list of player data ONLINE|OFFLINE*/
    public PlayerOnlineList(){
        super("PLAYER_ONLINE_LIST", Packets.PLAYER_ONLINE_LIST);
    }

    public List<String> players = new ArrayList<String>();

    @Override
    public void decode() {
        isEncoded = false;

        String[] data = Convertor.getPacketStringData(encoded);

        for (int i = 1; i+1 < data.length; i++){
            /* Add Player to list*/
            players.add(data[i]);
        }
    }

    @Override
    public void encode() {
        Convertor convertor = new Convertor(getID());

        for (String player : players){
            convertor.putString(player);
        }

        this.encoded = convertor.getPacketString();
        isEncoded = true;
    }

    @Override
    public StarGatePacket copy() throws CloneNotSupportedException {
        return null;
    }
}
