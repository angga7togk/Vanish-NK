package com.angga7togk.task;

import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.network.protocol.PlayerListPacket;
import com.angga7togk.Vanish;

public class UpdateTask implements Runnable{

    @Override
    public void run() {
        Server.getInstance().getOnlinePlayers().values().forEach(p -> {
            if(p.spawned){
                if(Vanish.getVanish(p)){
                    p.sendPopupJukebox(Vanish.cfg.getString("hud-msg"));
                    p.setDataFlag(Entity.DATA_FLAGS, Entity.DATA_FLAG_SILENT, true);
                    Server.getInstance().getOnlinePlayers().values().forEach(player -> {
                        if (player.hasPermission("vanish.see") || player.hasPermission("vanish.admin")){
                            player.showPlayer(p);
                        }else{
                            player.hidePlayer(p);
                            PlayerListPacket packet = new PlayerListPacket();
                            packet.type = PlayerListPacket.TYPE_REMOVE;
                            packet.entries = new PlayerListPacket.Entry[]{new PlayerListPacket.Entry(p.getUniqueId())};
                            player.getNetworkSession().sendPacket(packet);
                        }
                    });
                }
            }
        });
    }
}
