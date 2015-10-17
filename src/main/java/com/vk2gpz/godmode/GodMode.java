package com.vk2gpz.godmode;
import java.util.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
public class GodMode extends JavaPlugin implements CommandExecutor, Listener {
  private static List<Player> GODS = new ArrayList<Player>();
  @Override
  public void onEnable() {
    getCommand("god").setExecutor(this);
    getServer().getPluginManager().registerEvents(this, this);
  }
  @Override
  public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
    if (s instanceof Player && s.hasPermission("godmode.use")) {
      boolean b = (GODS.contains(((Player)s))) ? GODS.remove((Player)s) : GODS.add((Player)s);
      s.sendMessage("§6God mode §c" + (GODS.contains((Player)s) ? "enabled" : "disabled") + "§6.");
    }
    return true;
  }
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
  public void onDamage(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player && GODS.contains(e.getEntity())) {
      e.setCancelled(true);
      ((Player) e.getEntity()).setHealth(((Player) e.getEntity()).getMaxHealth());
    }
  }
  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    GODS.remove(e.getPlayer());
  }
  @EventHandler
  public void onLeave(PlayerQuitEvent e) {
    GODS.remove(e.getPlayer());
  }
}
