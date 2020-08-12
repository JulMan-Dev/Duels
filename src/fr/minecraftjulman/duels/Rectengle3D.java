package fr.minecraftjulman.duels;

import org.bukkit.Location;

/**
 * A 3D rectangle in game !
 * 
 * @author Juliano Mandrillon
 *
 */
public class Rectengle3D {
	public Location pos1;
	public Location pos2;
	
	public Rectengle3D(Location pos1, Location pos2) {
		this.pos1 = pos1;
		this.pos2 = pos2;
	}
}
