package me.bobcatsss.trampolines.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bobcatsss.trampolines.Core;
import me.bobcatsss.trampolines.utils.Messages;
import me.bobcatsss.trampolines.utils.Trampoline;
import me.bobcatsss.trampolines.utils.Utils;

public class CreateTrampolineCommand implements CommandExecutor {
	
	private Core plugin;
	public CreateTrampolineCommand(Core pl) {
		this.plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if(!player.hasPermission("trampolines.use")) {
			player.sendMessage(Messages.NO_PERMISSION.getValue());
			return true;
		}
		if(args.length >= 2) {
			if(args[0].equalsIgnoreCase("create")) {
				if(!player.hasPermission("trampolines.create")) {
					player.sendMessage(Messages.NO_PERMISSION.getValue());
					return true;
				}
				Trampoline t = new Trampoline(args[1].toLowerCase());
				plugin.getCreating().put(player, t);
				player.sendMessage(Messages.CREATING_TRAMPOLINE.getValue());
				return true;
			}
			if(args[0].equalsIgnoreCase("remove")) {
				if(!player.hasPermission("trampolines.remove")) {
					player.sendMessage(Messages.NO_PERMISSION.getValue());
					return true;
				}
				if(!plugin.getTrampolines().containsKey(args[1].toLowerCase())) {
					player.sendMessage(Messages.INVALID_TRAMPOLINE.getValue("{trampoline_id}", args[1]));
					return true;
				}
				plugin.removeTrampoline(args[1].toLowerCase());
				player.sendMessage(Messages.TRAMPOLINE_REMOVED.getValue("{trampoline_id}", args[1].toLowerCase()));
				return true;
			}
			player.sendMessage(Messages.INVALID_COMMAND.getValue());
			return true;
		}
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("cancel")) {
				if(plugin.getCreating().containsKey(player)) {
					plugin.getCreating().remove(player);
					player.sendMessage(Messages.CANCELLED_CREATION.getValue());
					return true;
				}
				player.sendMessage(Messages.NO_CREATING.getValue());
				return true;
			}
			if(args[0].equalsIgnoreCase("list")) {
				int count = 1;
				player.sendMessage(Utils.c("&7&m--------------------&f[&3Trampolines&f]&7&m--------------------"));
				for(String key : plugin.getTrampolines().keySet()) {
					player.sendMessage(Utils.c("&a" + count + "&f) &a" + key));
					count++;
				}
				player.sendMessage(Utils.c("&7&m---------------------------------------------------"));
				return true;
			}
			if(args[0].equalsIgnoreCase("help")) {
			player.sendMessage(Utils.c("&7&m--------------------&f[&3Trampolines&f]&7&m--------------------"));
			player.sendMessage(Utils.c("&f/trampoline create &8<&7name&8> &f| &7Start creating your trampoline."));
			player.sendMessage(Utils.c("&f/trampoline remove &8<&7name&8> &f| &7Remove a trampoline with the given name."));
			player.sendMessage(Utils.c("&f/trampoline cancel | &7Cancels the creation of a trampoline."));
			player.sendMessage(Utils.c("&f/trampoline list | &7Lists all of the trampolines."));
			player.sendMessage(Utils.c("&f/trampoline help | &7Shows this."));
			player.sendMessage(Utils.c("&7&m---------------------------------------------------"));
			return true;
			}
		}
		player.sendMessage(Messages.INVALID_COMMAND.getValue());
		return true;
	}

}
