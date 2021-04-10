package me.geik.arti.basma;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ElytraChecker {
	
	static HashMap<String, Long> cooldown = new HashMap<String, Long>();

	  public static boolean ElytraEquip(Player player)
	  {
	    if (player.getInventory().getChestplate() != null) {
	      if (player.getInventory().getChestplate().getType() == Material.ELYTRA) {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	  
	  
	  public static void elytraEnch(Player player, int cd) {
	    if (player.isSneaking()) {
		  if (player.isGliding()) {
			  if(cooldown.containsKey(player.getName())) {
				  long secondsLeft = ((cooldown.get(player.getName())/1000)+cd) - (System.currentTimeMillis()/1000);
					  if(secondsLeft>0) {
						  player.sendMessage(Main.color("&4&lBS &cBüyüyü kullanmak için &a%s &csn daha beklemen gerek.").replace("%s", String.valueOf(secondsLeft)));
					  } else {
						  cooldown.remove(player.getName());
						  
						  Vector velocity = player.getVelocity();
						  Vector velo_new = new Vector(0D, 1.4D, 0D);
				          if (velocity.length() < 2.0D) {
				        	player.setVelocity(velo_new);
				            player.setVelocity(velocity.normalize().multiply(2.0D));}
				            player.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation(), 10);
				            player.playSound(player.getLocation(), Sound.ENCHANT_THORNS_HIT, 0.05F, 2.0F);
						  
						  Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable(){
			      			  public void run(){
			      				cooldown.put(player.getName(), System.currentTimeMillis());
			      			  }
			      			  
			      		  }, 1);
						  return;
						  
						  
					  }
			  } else {
				  cooldown.put(player.getName(), System.currentTimeMillis());
				  Vector velocity = player.getVelocity();
		          if (velocity.length() < 1.6D) {
		            player.setVelocity(velocity.normalize().multiply(1.6D));}
		            player.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation(), 10);
		            player.playSound(player.getLocation(), Sound.ENCHANT_THORNS_HIT, 0.05F, 2.0F);
		            return;
				  
			  }
			}
	     }
	  }
	  
	  
	  
	}
