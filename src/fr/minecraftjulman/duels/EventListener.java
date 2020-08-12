package fr.minecraftjulman.duels;

import java.util.Map.Entry;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.minecraftjulman.duels.utils.EntryMaker;
import fr.minecraftjulman.duels.utils.Other;

public class EventListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		ItemStack it = e.getItem();
		Action action = e.getAction();
		
		if (it == null || !it.hasItemMeta() || !it.getItemMeta().hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) return;
		
		e.setCancelled(true);
		switch (it.getType()) {
		case GOLDEN_AXE:
			switch (action) {
			case LEFT_CLICK_AIR:
				if (Main.ArenaDisigner.containsKey(player.getUniqueId())) {
					try {
						Entry<Location, Location> locs = Main.ArenaDisigner.get(player.getUniqueId());
						Main.ArenaDisigner.replace(player.getUniqueId(), new EntryMaker<Location, Location>(player.getWorld()
								.rayTraceBlocks(player.getEyeLocation(),
										player.getEyeLocation().getDirection(), 60, FluidCollisionMode.NEVER).
								getHitBlock().getLocation(), locs.getKey()).toEntry());
						Location loc = Other.rayTraceLocation(player.getWorld(), player.getEyeLocation(), player.getEyeLocation().getDirection(), 60);
						player.sendMessage("§eSecond location set to :§a " + Double.toString(loc.getX()) + " " + Double.toString(loc.getY()) + " " + Double.toString(loc.getZ()));
					} catch (NullPointerException nullEx) {
						//
					}
				} else {
					try {
						Main.ArenaDisigner.put(player.getUniqueId(), new EntryMaker<Location, Location>(player.getWorld()
								.rayTraceBlocks(player.getEyeLocation(),
								player.getEyeLocation().getDirection(), 60, FluidCollisionMode.NEVER).
								getHitBlock().getLocation(), null).toEntry());
					} catch (Exception ex) {
						//
					}
				}
				break;
			case LEFT_CLICK_BLOCK:
				if (Main.ArenaDisigner.containsKey(player.getUniqueId())) {
					try {
						Entry<Location, Location> locs = Main.ArenaDisigner.get(player.getUniqueId());
						Main.ArenaDisigner.replace(player.getUniqueId(), new EntryMaker<Location, Location>(player.getWorld()
								.rayTraceBlocks(player.getEyeLocation(),
										player.getEyeLocation().getDirection(), 60, FluidCollisionMode.NEVER).
								getHitBlock().getLocation(), locs.getKey()).toEntry());
						Location loc = Other.rayTraceLocation(player.getWorld(), player.getEyeLocation(), player.getEyeLocation().getDirection(), 60);
						player.sendMessage("§eSecond location set to :§a " + Double.toString(loc.getX()) + " " + Double.toString(loc.getY()) + " " + Double.toString(loc.getZ()));
					} catch (NullPointerException nullEx) {
						//
					}
				} else {
					try {
						Main.ArenaDisigner.put(player.getUniqueId(), new EntryMaker<Location, Location>(player.getWorld()
								.rayTraceBlocks(player.getEyeLocation(),
								player.getEyeLocation().getDirection(), 60, FluidCollisionMode.NEVER).
								getHitBlock().getLocation(), null).toEntry());
						Location loc = Other.rayTraceLocation(player.getWorld(), player.getEyeLocation(), player.getEyeLocation().getDirection(), 60);
						player.sendMessage("§eSecond location set to :§a " + Double.toString(loc.getX()) + " " + Double.toString(loc.getY()) + " " + Double.toString(loc.getZ()));
					} catch (Exception ex) {
						//
					}
				}
				break;
			case RIGHT_CLICK_AIR:
				break;
			case RIGHT_CLICK_BLOCK:
				break;
			default:
				break;
			}
			it.setType(Material.AIR);
			break;
		
		default:
			break;
		}
	}
}
