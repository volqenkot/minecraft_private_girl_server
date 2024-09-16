package io.volqe.manager;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TablistManager implements Listener {

   @EventHandler
   public void joinHandler(PlayerJoinEvent event) {
      Player player = event.getPlayer();
      event.setJoinMessage("§7" + player.getDisplayName() + " §dhat den Server betreten.");
      this.setTeam(player);
   }

   @EventHandler
   public void quitHandler(PlayerQuitEvent event) {
      Player player = event.getPlayer();
      event.setQuitMessage("§7" + player.getDisplayName() + " §dhat den Server verlassen.");
   }

   public void updateList() {
      ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
      ses.scheduleAtFixedRate(new Runnable() {
         public void run() {
            for(Player all : Bukkit.getOnlinePlayers()){
               setList(all);
            }
         }
      }, 0, 3, TimeUnit.SECONDS);

   }

   public void setList(Player user) {
      long usedmemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
      long memory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
      RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
      long uptimeInMillis = runtimeMXBean.getUptime();
      String header =
              "§6Private Survival Server" +
              "\n" +
              "§7Aktuell Online: §e" + Bukkit.getOnlinePlayers().size() + "§7/§e" + Bukkit.getMaxPlayers() +
              "\n" +
              "\n";
      String footer =
              "\n" +
              "\n" +
              "§5Server Information" +
              "\n" +
              "§7Uptime in Minuten: §d" + uptimeInMillis / 60000 + " §8| §7RAM Usage §d" + usedmemory/ (1024 * 1024) + "MB" + " §7/ §d" + memory + "MB";
      user.setPlayerListHeaderFooter(header, footer);
   }

   public void setTeam(Player user) {
      Scoreboard tablist = user.getScoreboard();
      Team member = tablist.getTeam("07member");
      if (member == null) {
         member = tablist.registerNewTeam("07member");
      }

      member.setPrefix("§8[§cPAAR§8] §7");
      member.setColor(ChatColor.GRAY);
      member.addPlayer(user);
   }
}
