package io.github.willqi.playerlifestylemodifier;

import cn.nukkit.plugin.PluginBase;
import io.github.willqi.playerlifestylemodifier.commands.ModifyPlayerCommand;
import io.github.willqi.playerlifestylemodifier.listeners.ModifyPlayerListener;

import java.util.HashMap;
import java.util.UUID;

public class PlayerLifestylePlugin extends PluginBase {

    public static HashMap<UUID, PlayerLifestyleFormData> forms = new HashMap<UUID, PlayerLifestyleFormData>();

    @Override
    public void onEnable () {
        this.getServer().getCommandMap().register("modify-player", new ModifyPlayerCommand());
        this.getServer().getPluginManager().registerEvents(new ModifyPlayerListener(), this);
    }

}
