package fr.minecraftjulman.duels;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.minecraftjulman.duels.utils.Tasks;

public class WandSelector extends BukkitRunnable {
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			ItemStack it = player.getItemInHand();
			
			if (it.hasItemMeta() && it.getItemMeta().hasItemFlag(ItemFlag.HIDE_UNBREAKABLE) && it.getType() == Material.GOLDEN_AXE) {
				try {
				Location loc = player.getWorld().rayTraceBlocks(player.getEyeLocation(), player.getEyeLocation().getDirection(), 60)
						.getHitBlock().getLocation();
				if (loc != null) 
					Tasks.generateParticleBlock(loc, Particle.COMPOSTER);
				} catch (NullPointerException e) {
					// 
				}
			}
		}
	}
}
