package com.angga7togk.language;

import com.angga7togk.Vanish;

import java.util.HashMap;
import java.util.Map;

public class Language {
    private String lang;
    public Language(){
        this.lang = Vanish.cfg.getString("language");
        if(!lang.equalsIgnoreCase("id") || !lang.equalsIgnoreCase("en")){
            this.lang = "en";
        }
    }

    public Map<String, String> getLanguage(){
        return baseLang().get(this.lang);
    }

    private Map<String, Map<String, String>> baseLang() {
        Map<String, Map<String, String>> lang = new HashMap<>();

        Map<String, String> id = new HashMap<>();
        id.put("string-empty", "§cString tidak boleh kosong!");
        id.put("player-notfound", "§cPlayer tidak ditemukan!");
        id.put("vanish-on", "§aVanish diaktifkan.");
        id.put("vanish-off", "§cVanish dimatikan.");
        id.put("vanish-on-others", "§aVanish diaktifkan oleh: {player}");
        id.put("vanish-off-others", "§cVanish dimatikan oleh: {player}");
        id.put("console", "§cGunakan perintah ini dalam permainan!");
        id.put("player-vanish", "§e{player} Dalam Mode Vanish.");

        Map<String, String> en = new HashMap<>();
        en.put("string-empty", "§cString cannot be empty!");
        en.put("player-notfound", "§cPlayer not found!");
        en.put("vanish-on", "§aVanish is now turned on.");
        en.put("vanish-off", "§cVanish is now turned off.");
        en.put("vanish-on-others", "§aVanish is turned on by: {player}");
        en.put("vanish-off-others", "§cVanish is turned off by: {player}");
        en.put("console", "§cUse this command in the game!");
        en.put("player-vanish", "§e{player} On Vanish Mode.");

        lang.put("id", id);
        lang.put("en", en);

        return lang;
    }

}
