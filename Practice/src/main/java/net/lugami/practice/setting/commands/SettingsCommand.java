package net.lugami.practice.setting.commands;

import net.lugami.practice.setting.menu.SettingsMenu;
import net.lugami.qlib.command.Command;

import org.bukkit.entity.Player;

/**
 * /settings, accessible by all users, opens a {@link SettingsMenu}
 */
public final class SettingsCommand {

    @Command(names = {"settings", "preferences", "prefs", "options"}, permission = "")
    public static void settings(Player sender) {
        new SettingsMenu().openMenu(sender);
    }

}