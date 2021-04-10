package me.geik.arti.basma;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class TridentChecker {
	
	static HashMap<String, Long> cooldown = new HashMap<String, Long>();
	
	/*
	 *  multipler thrower
	 *  @author Geik
	 */
	public static void multiplier(PlayerInteractEvent e, Player player, double multiply, String metadata, boolean unbreakable, int durabilityLevel, int cd) {
		e.setCancelled(true);
		if(cooldown.containsKey(player.getName())) {
			long secondsLeft = ((cooldown.get(player.getName())/1000)+cd) - (System.currentTimeMillis()/1000);
			if(secondsLeft>0) {player.sendMessage(Main.color("&4&lBS &cKullanmak için &a%s &csn daha beklemen gerek.").replace("%s", String.valueOf(secondsLeft)));}
			else {
				cooldown.remove(player.getName());
				Trident arrow = player.launchProjectile(Trident.class);
				arrow.setShooter(e.getPlayer());
				arrow.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(multiply));
				arrow.setMetadata(metadata, new FixedMetadataValue(Main.instance, true));
				unbrekableMethod(unbreakable, player, durabilityLevel);
				player.getWorld().playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 3.0F, 0.5F);
				Location newLoc = new Location (player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ());
				player.getLocation().getWorld().spawnParticle(Particle.FLAME, newLoc, 5);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable(){public void run(){cooldown.put(player.getName(), System.currentTimeMillis());} }, 1);
				return;}
		} else {
			cooldown.put(player.getName(), System.currentTimeMillis());
			Trident arrow = player.launchProjectile(Trident.class);
			arrow.setShooter(e.getPlayer());
			arrow.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(multiply));
			arrow.setMetadata(metadata, new FixedMetadataValue(Main.instance, true));
			unbrekableMethod(unbreakable, player, durabilityLevel);
			player.getWorld().playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 3.0F, 0.5F);
			Location newLoc = new Location (player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ());
			player.getLocation().getWorld().spawnParticle(Particle.FLAME, newLoc, 5);
	        return;}}
	
	
	/*
	 *  CUSTOM UNBREAKABLE METHOD
	 *  @author Geik
	 */
	@SuppressWarnings("deprecation")
	public static void unbrekableMethod(boolean unbreakable, Player player, int durabilityLevel) {
		if (unbreakable == false) {
			ItemStack s = player.getInventory().getItemInMainHand();
			int durability = s.getDurability();
			if (s.getType().getMaxDurability() != durability) {
				Random rnd = new Random();
				int n = rnd.nextInt(100);
				if (n <= durabilityLevel*25) {
					s.setDurability((short) (durability + 1));
					player.setItemInHand(s);}}
			else {
				player.setItemInHand(null);}}}
	
	
	
	

}
