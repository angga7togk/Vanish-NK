package com.angga7togk;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import com.angga7togk.command.Commands;
import com.angga7togk.language.Language;
import com.angga7togk.listener.Listeners;
import com.angga7togk.task.UpdateTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vanish extends PluginBase {

    public static List<Player> vanish = new ArrayList<>();
    public static String prefix;
    public static Config cfg;

    @Override
    public void onEnable() {
        this.saveResource("config.yml");
        cfg = new Config(this.getDataFolder() + "/config.yml", Config.YAML);
        prefix = cfg.getString("prefix");
        this.getServer().getPluginManager().registerEvents(new Listeners(), this);
        this.getServer().getScheduler().scheduleRepeatingTask(this, new UpdateTask(), 20, true);
        this.getServer().getCommandMap().register("vanish", new Commands());
    }

    public static boolean getVanish(Player player){
        return vanish.contains(player);
    }

    public static void setVanish(Player player, boolean bool){
        if(bool){
            if(!vanish.contains(player)){
                vanish.add(player);
                Server.getInstance().getOnlinePlayers().values().forEach(p -> {
                    Map<String, String> lang = new Language().getLanguage();
                    if (p.hasPermission("vanish.see") || p.hasPermission("vanish.admin")) {
                        p.sendMessage(Vanish.prefix + lang.get("player-vanish")
                                .replace("{player}", player.getName()));
                    }
                });
            }
        }else {
            vanish.remove(player);
        }
    }
}