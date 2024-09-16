package io.volqe.manager;

import java.util.BitSet;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.volqe.Private;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SaveManager {
   public void enable() {

      Bukkit.getScheduler().scheduleSyncRepeatingTask(Private.getPlugin(), new Runnable() {
         @Override
         public void run() {
            if (!(Bukkit.getOnlinePlayers().size() == 0)) {
               for (Player all : Bukkit.getOnlinePlayers()) {
                  all.getWorld().save();
                  all.sendMessage("§7§oDie Welten wo Ihr euch befindet wurden soeben erfolgreich gespeichert!");
               }
            }
         }
      },0, 600);
   }
}