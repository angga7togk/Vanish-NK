package com.angga7togk.listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityCombustEvent;
import cn.nukkit.event.entity.EntityDamageEvent;

import cn.nukkit.event.player.*;
import cn.nukkit.event.server.QueryRegenerateEvent;
import com.angga7togk.Vanish;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(Vanish.getVanish(player)){
            if(Vanish.cfg.getBoolean("silent-join/exit")){
                event.setJoinMessage("");
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(Vanish.getVanish(player)){
            if(Vanish.cfg.getBoolean("silent-join/exit")){
                event.setQuitMessage("");
            }
        }
    }

    @EventHandler
    public void onPickUp(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        if(Vanish.getVanish(player)){
            event.setCancelled();
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Player player){
            if(Vanish.getVanish(player)){
                if(Vanish.cfg.getBoolean("disable-damage")){
                    event.setCancelled();
                }
            }
        }
    }

    @EventHandler
    public void onBurn(EntityCombustEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Player player){
            if(Vanish.getVanish(player)){
                if(Vanish.cfg.getBoolean("disable-damage")){
                    event.setCancelled();
                }
            }
        }
    }

    @EventHandler
    public void onHunger(PlayerFoodLevelChangeEvent event){
        Player player = event.getPlayer();
        if(Vanish.getVanish(player)){
            if(Vanish.cfg.getBoolean("disable-hunger")){
                event.setFoodLevel(player.getFoodData().getMaxLevel());
                event.setCancelled();
            }
        }
    }

    @EventHandler
    public void onQuery(QueryRegenerateEvent event){
        Server.getInstance().getOnlinePlayers().values().forEach(player -> {
            if(Vanish.getVanish(player)){
                int online = event.getPlayerCount();
                event.setPlayerCount(online - 1);
            }
        });
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(Vanish.getVanish(player)){
            switch (event.getAction()){
                case RIGHT_CLICK_BLOCK:
                case RIGHT_CLICK_AIR:
                case LEFT_CLICK_BLOCK:
                case LEFT_CLICK_AIR:
                    if (!player.hasPermission("vanish.admin")){
                        event.setCancelled();
                    }
            }
        }
    }
}
