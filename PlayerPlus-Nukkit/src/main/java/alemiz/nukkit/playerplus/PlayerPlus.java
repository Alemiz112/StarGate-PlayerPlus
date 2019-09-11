package alemiz.nukkit.playerplus;

import alemiz.nukkit.playerplus.packets.PlayerOnlineList;
import alemiz.sgu.StarGateUniverse;
import cn.nukkit.plugin.PluginBase;

import java.util.List;

public class PlayerPlus extends PluginBase {

    private static PlayerPlus instance;

    @Override
    public void onEnable() {
        instance = this;

        /* Register Packets*/
        init();

        getLogger().info("§aEnabling StarGate Addon: §6PlayerPlus");
    }

    public static PlayerPlus getInstance() {
        return instance;
    }

    public void init(){
        StarGateUniverse.RegisterPacket(new PlayerOnlineList());
    }

    /* You will get in Response list of Players and if are online*/
    public String playerOnlineList(List<String> playerList){
        PlayerOnlineList pk = new PlayerOnlineList();

        pk.players = playerList;

        pk.isEncoded = false;
        return StarGateUniverse.getInstance().putPacket(pk);
    }
}
