package io.github.willqi.playerlifestylemodifier.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementSlider;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.utils.TextFormat;
import io.github.willqi.playerlifestylemodifier.PlayerLifestyleFormData;
import io.github.willqi.playerlifestylemodifier.PlayerLifestylePlugin;

import java.util.ArrayList;
import java.util.List;

public class ModifyPlayerCommand extends Command {

    private String permission = "io.github.willqi.playerlifestylemodifier.command.modifyplayer";

    public ModifyPlayerCommand () {
        super("modify-player", "Modify the health and hunger of a player!", "/modify-player <player>");
        this.setPermission(this.permission);
        CommandParameter[] paramTypes = new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, false)
        };
        this.getCommandParameters().put("default", paramTypes);
        this.setPermissionMessage(TextFormat.RED + "You do not have permission to execute this command!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String label, String[] args) {

        if (!commandSender.hasPermission(this.permission)) {
            commandSender.sendMessage(this.getPermissionMessage());
            return true;
        }

        if (!commandSender.isPlayer()) {
            commandSender.sendMessage(TextFormat.RED + "Sorry, this command can only be run in-game!");
            return true;
        }

        if (args.length < 1) {
            commandSender.sendMessage(TextFormat.RED + this.getUsage());
            return true;
        }

        Player target = commandSender.getServer().getPlayer(args[0]);
        if (target == null) {
            commandSender.sendMessage(TextFormat.RED + "That player could not be found!");
            return true;
        }

        Player sender = commandSender.getServer().getPlayer(commandSender.getName());

        List<Element> elements = new ArrayList<Element>();

        elements.add(
            new ElementSlider("Health", 0, target.getMaxHealth(), 1, (int)target.getHealth())
        );
        elements.add(
            new ElementSlider("Hunger", 0, target.getFoodData().getMaxLevel(), 1, target.getFoodData().getLevel())
        );

        FormWindowCustom window = new FormWindowCustom("Modify Player - \"" + target.getName() + "\"", elements);

        int formId = sender.showFormWindow(window);

        PlayerLifestylePlugin.forms.put(sender.getUniqueId(), new PlayerLifestyleFormData(formId, target.getName()));



        return true;
    }
}
