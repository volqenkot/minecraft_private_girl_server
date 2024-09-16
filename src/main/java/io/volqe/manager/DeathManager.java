package io.volqe.manager;

import java.util.HashMap;


import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathManager implements CommandExecutor, Listener {
   private static HashMap<Player, Location> deathLocation = new HashMap();

   public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
      Player player = (Player)commandSender;
      if (commandSender != Bukkit.getConsoleSender()) {
         if (deathLocation.get(player) != null) {
            player.teleport((Location)deathLocation.get(player));
            player.sendMessage("§aDu wurdest zu deinem letzten Todespunkt teleportiert.");
         } else {
            player.sendMessage("§cDu hast kein Todespunkt, was eigentlich gut ist. :)");
         }
      } else {
         commandSender.sendMessage("Ein Spieler Kommand und nicht für die Konsole.");
      }

      return false;
   }

   @EventHandler
   public void playerDeathHandler(PlayerDeathEvent event) {
      Player player = event.getEntity().getPlayer();
      deathLocation.put(player, event.getEntity().getPlayer().getLastDeathLocation());
      TextComponent message = new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "KLICKE HIER UM ZURÜCK ZU KOMMEN!");
      message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/back"));
      player.sendMessage(message.getText());
   }

   // $FF: synthetic method
}
