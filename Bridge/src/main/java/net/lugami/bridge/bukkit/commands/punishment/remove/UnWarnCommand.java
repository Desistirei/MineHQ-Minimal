package net.lugami.bridge.bukkit.commands.punishment.remove;

import net.lugami.qlib.command.Command;
import net.lugami.qlib.command.Flag;
import net.lugami.qlib.command.Param;
import net.lugami.bridge.BridgeGlobal;
import net.lugami.bridge.bukkit.BukkitAPI;
import net.lugami.bridge.global.packet.PacketHandler;
import net.lugami.bridge.global.packet.types.PunishmentPacket;
import net.lugami.bridge.global.profile.Profile;
import net.lugami.bridge.global.punishment.Punishment;
import net.lugami.bridge.global.punishment.PunishmentType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class UnWarnCommand {

    @Command(names = {"unwarn"}, permission = "bridge.unwarn", description = "Remove a player's warn", async = true)
    public static void unbanCmd(CommandSender s, @Flag(value = { "a", "announce" }, description = "Announce this unwarn to the server") boolean silent, @Param(name = "target") Profile target, @Param(name = "reason", wildcard = true) String reason) {
        Profile pf = BukkitAPI.getProfile(s);

        if(target.getActivePunishments(PunishmentType.WARN).isEmpty()) {
            s.sendMessage(ChatColor.RED + target.getUsername() + " does not have an active warning.");
            return;
        }
        Punishment punishment = (Punishment) target.getActivePunishments(PunishmentType.WARN).toArray()[0];

        if (!BukkitAPI.canOverride(pf, punishment.getExecutor())) {
            s.sendMessage(ChatColor.RED + "You cannot undo this punishment.");
            return;
        }

        punishment.pardon(pf, BridgeGlobal.getSystemName(), reason, !silent);
        target.getPunishments().add(punishment);
        target.saveProfile();
        PacketHandler.sendToAll(new PunishmentPacket(punishment));
    }

}

