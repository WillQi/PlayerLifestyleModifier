package io.github.willqi.playerlifestylemodifier.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.utils.TextFormat;
import io.github.willqi.playerlifestylemodifier.PlayerLifestyleFormData;
import io.github.willqi.playerlifestylemodifier.PlayerLifestylePlugin;

import java.util.UUID;

public class ModifyPlayerListener implements Listener {

    @EventHandler
    public void onPlayerFormRespondedEvent (PlayerFormRespondedEvent event) {

        UUID playerUUID = event.getPlayer().getUniqueId();
        if (PlayerLifestylePlugin.forms.containsKey(playerUUID)) {
            PlayerLifestyleFormData data = PlayerLifestylePlugin.forms.get(playerUUID);
            PlayerLifestylePlugin.forms.remove(playerUUID);
            FormResponseCustom response = (FormResponseCustom) event.getResponse();
            if (response != null && event.getFormID() == data.getID()) {
                Player target = event.getPlayer().getServer().getPlayer(data.getPlayerName());
                if (target != null) {
                    target.setHealth(response.getSliderResponse(0));
                    target.getFoodData().setLevel((int) (response.getSliderResponse(1) + 0.5));
                    event.getPlayer().sendMessage(TextFormat.GREEN + "Modified!");
                } else {
                    event.getPlayer().sendMessage(TextFormat.RED + "Sorry! That player is no longer online!");
                }
            }



        }

    }


}
