package net.badlion.arenalobby.commands;

import net.badlion.arenalobby.ArenaLobby;
import net.badlion.arenalobby.Group;
import net.badlion.arenalobby.GroupStateMachine;
import net.badlion.gberry.managers.UserDataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleFlightCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can fly dumbass.");
			return true;
		}

		Player player = (Player) sender;
		if (!player.hasPermission("badlion.staff") && !player.hasPermission("badlion.donatorplus")) {
			player.sendFormattedMessage("{0}You do not have permission.", ChatColor.WHITE );
			return true;
		}

		Group group = ArenaLobby.getInstance().getPlayerGroup(player);
		if (GroupStateMachine.kitCreationState.contains(group)) {
			player.sendFormattedMessage("{0}You can not fly while in the kit editing area.", ChatColor.RED);
			return true;
		}

		/* Dont need this since you cant morph on arena lobbies
		// Can't fly when morphed
		if (CosmeticsManager.getCosmeticsSettings(player.getUniqueId()).getActiveMorph() != null) {
			player.sendMessage(ChatColor.RED + "You can't fly when morphed!");
			return true;
		}*/

		//if (false) { // FUCK UR MATH SMELLY
		//	Location focusLocation = new Location(player.getWorld(), 0.5, 0, -5.5);
//
//			player.sendMessage(1.9 * (90 + (180 * Math.atan2(player.getLocation().getZ() - focusLocation.getZ(), player.getLocation().getX() - focusLocation.getX()) / Math.PI)) + "");
//			return true;
//		}

		if (args.length == 0) {
			player.setAllowFlight(!player.getAllowFlight());
			UserDataManager.getUserData(player).setLobbyFlight(player.getAllowFlight());
			player.sendFormattedMessage("{0}You have toggled flight {1}", ChatColor.GREEN, (player.getAllowFlight() ? "on." : "off."));
		} else {
			if (args[0].equalsIgnoreCase("on")) {
				if (player.getAllowFlight()) {
					player.sendFormattedMessage("{0}You have already enabled flight.", ChatColor.RED);
				} else {
					player.setAllowFlight(true);
					UserDataManager.getUserData(player).setLobbyFlight(true);
					player.sendFormattedMessage("{0}You have toggled flight on.", ChatColor.GREEN);
				}
			} else if (args[0].equalsIgnoreCase("off")) {
				if (!player.getAllowFlight()) {
					player.sendFormattedMessage("{0}You have already disabled flight.", ChatColor.RED);
				} else {
					player.setAllowFlight(false);
					UserDataManager.getUserData(player).setLobbyFlight(false);
					player.sendFormattedMessage("{0}You have toggled flight off.", ChatColor.GREEN);
				}
			}
		}
		return true;
	}

}
