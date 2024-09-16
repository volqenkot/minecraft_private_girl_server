package io.volqe.manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeManager implements CommandExecutor {

   @Override
   public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
      Player player = (Player) commandSender;
      player.teleport(player.getBedSpawnLocation());
      player.sendMessage("§aDu bist nun Zuhause! :)");
      return false;
   }
}
