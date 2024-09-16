package io.volqe.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatHandler implements Listener {
   @EventHandler
   public void playerChatHandler(AsyncPlayerChatEvent event) {
      Player player = event.getPlayer();
      event.setFormat("§8[§cPAAR§8] §7" + player.getName() + "§8: §f" + event.getMessage());
   }
}
