package net.lugami.practice.match.replay;

import org.bukkit.entity.Player;

public interface ReplayableAction {

    public void replay(Player replayFor);
}
