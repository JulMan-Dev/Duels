package fr.minecraftjulman.duels;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Arena {
	public Location pos1;
	public Location pos2;
	public Rectengle3D size;
	
	public Arena(Location tpPos1, Location tpPos2, Rectengle3D size) {
		this.pos1 = tpPos1;
		this.pos2 = tpPos2;
		this.size = size;
	}
	
	public static void startDuel(Entry<UUID, UUID> players) {
		try {
			int ArenaIndex = new Random().nextInt(Main.arenasList.size());
			Arena arena = Main.arenasList.get(ArenaIndex);
		
			OfflinePlayer p1 = Bukkit.getOfflinePlayer(players.getKey());
			OfflinePlayer p2 = Bukkit.getOfflinePlayer(players.getValue());
		
			if (p1.isOnline() && p2.isOnline()) {
				Player player1 = p1.getPlayer();
				Player player2 = p2.getPlayer();
			
				player1.teleport(arena.pos1);
				player2.teleport(arena.pos2);
				
				Main.waitingDuels.remove(p1.getUniqueId(), p2.getUniqueId());
				Main.playingDuels.put(players, arena, new Entry<ItemStack[], ItemStack[]>() {
					private ItemStack[] key = Bukkit.getPlayer(players.getKey()).getInventory().getContents();
					private ItemStack[] value = Bukkit.getPlayer(players.getValue()).getInventory().getContents();
					
					@Override
					public ItemStack[] setValue(ItemStack[] value) {
						this.value = value;
						return value;
					}
					
					@Override
					public ItemStack[] getValue() {
						return value;
					}
					
					@Override
					public ItemStack[] getKey() {
						return key;
					}
				});
			} else {
				if (p1.isOnline()) p1.getPlayer().sendMessage("§c" + p2.getName() + " is offline !");
				if (p2.isOnline()) p2.getPlayer().sendMessage("§c" + p1.getName() + " is offline !");
			}
		} catch (Exception e) {
			OfflinePlayer p1 = Bukkit.getOfflinePlayer(players.getKey());
			OfflinePlayer p2 = Bukkit.getOfflinePlayer(players.getValue());
	
			if (p1.isOnline()) p1.getPlayer().sendMessage("§cNo arena avaible !");
			if (p2.isOnline()) p2.getPlayer().sendMessage("§cNo arena avaible !");
		}
	}

	/**
	 * @author MinecraftJulMan
	 * 
	 * @param p : The request player
	 */
	public static void create(Player p) {
		ItemStack it = new ItemStack(Material.GOLDEN_AXE, 1);
		ItemMeta itm = it.getItemMeta();
		itm.setDisplayName("§aArena Selector");
		itm.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itm.setLore(Arrays.asList(
			"§c - §fLeft click for the first pos,",
			"§c - §fRight click for the second pos,",
			"§c - §fDo §a/arena add next§f for continue"
		));
		it.setItemMeta(itm);
		
		p.getInventory().addItem(it);
	} 
}
