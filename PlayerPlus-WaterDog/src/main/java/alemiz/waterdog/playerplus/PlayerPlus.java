package alemiz.waterdog.playerplus;

import alemiz.stargate.gate.GateAPI;
import alemiz.stargate.gate.events.CustomPacketEvent;
import alemiz.stargate.gate.packets.StarGatePacket;
import alemiz.waterdog.playerplus.packets.Packets;
import alemiz.waterdog.playerplus.packets.PlayerOnlineList;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.Map;


public class PlayerPlus extends Plugin implements Listener {

    private static PlayerPlus instance;

    @Override
    public void onEnable() {
        instance = this;

        /* Register Packets*/
        init();

        getProxy().getPluginManager().registerListener(this, this);
        getLogger().info("§aEnabling StarGate Addon: §6PlayerPlus");
    }

    public static PlayerPlus getInstance() {
        return instance;
    }

    public void init(){
        GateAPI.RegisterPacket(new PlayerOnlineList());
    }

    @EventHandler
    public void onPacket(CustomPacketEvent event){
        StarGatePacket packet = event.getPacket();

        switch (packet.getID()){
            case Packets.PLAYER_ONLINE_LIST:
                PlayerOnlineList playerOnlineList = (PlayerOnlineList) packet;
                String result = "";

                for (String player : playerOnlineList.players){
                    ProxiedPlayer guest = getProxy().getPlayer(player);

                    if (guest == null || !guest.isConnected()){
                        result += player+"/false"+";";
                    }else {
                        result += player+"/true"+";";
                    }
                }
                result = result.substring(0, result.length()-1);
                GateAPI.setResponse(event.getClient(), playerOnlineList.getUuid(), result);
                break;
        }
    }

}
