package com.angga7togk.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.angga7togk.Vanish;
import com.angga7togk.language.Language;

import java.util.Map;

public class Commands extends Command {

    public Commands(){
        super("vanish", "vanish mode", "/vanish", new String[]{"v"});
        this.setPermission("vanish.command");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(testPermission(sender)){
            Map<String, String> lang = new Language().getLanguage();
            if(args.length < 1){
                if(sender instanceof Player player){
                    if(Vanish.getVanish(player)){
                        Vanish.setVanish(player, false);
                        player.sendMessage(Vanish.prefix + lang
                                .get("vanish-off"));
                    }else{
                        Vanish.setVanish(player, true);
                        player.sendMessage(Vanish.prefix + lang
                                .get("vanish-on"));
                    }
                }else{
                    sender.sendMessage(Vanish.prefix + lang.get("console"));
                }
            }else{
                if(sender.hasPermission("vanish.command.others") || sender.hasPermission("vanish.admin")){
                    String target = args[0];
                    if(target.isBlank()){
                        sender.sendMessage(Vanish.prefix + lang.get("string-empty"));
                        return false;
                    }

                    Player player = Server.getInstance().getPlayer(target);
                    if(player == null){
                        sender.sendMessage(Vanish.prefix + lang.get("player-notfound"));
                        return false;
                    }

                    if(Vanish.getVanish(player)){
                        Vanish.setVanish(player, false);
                        player.sendMessage(Vanish.prefix + lang
                                .get("vanish-off"));
                        sender.sendMessage(Vanish.prefix + lang.get("vanish-off-others")
                                .replace("{player}", player.getName()));
                    }else{
                        Vanish.setVanish(player, true);
                        player.sendMessage(Vanish.prefix + lang
                                .get("vanish-on"));
                        sender.sendMessage(Vanish.prefix + lang.get("vanish-on-others")
                                .replace("{player}", player.getName()));
                    }
                }
            }
        }
        return true;
    }
}
