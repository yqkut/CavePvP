package net.frozenorb.foxtrot.team.commands.team;

import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import net.frozenorb.foxtrot.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeamRemoveRaidablePointsCommand {
    @Command(names={ "team removeraidable", "t removeraidable", "f removeraidable", "faction removeraidable", "fac removeraidable" }, permission="foxtrot.removeraidable")
    public static void teamRemovePoints(Player sender, @Parameter(name = "team") final Team team, @Parameter(name = "amount") int amount) {
        if (team == null) {
            sender.sendMessage(ChatColor.GRAY + "That team doesn't exist!");
            return;
        }

        team.setFactionsMadeRaidable(team.getFactionsMadeRaidable() - amount);
        sender.sendMessage(ChatColor.translate("&eYou have successfully &cremoved &b" + amount + "&e raidable points from &a" + team.getName() + "&e."));
    }
}