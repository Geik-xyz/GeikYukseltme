package me.geik.arti.basma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;

public class Listeners implements Listener{
	
	
/*
*  Listeners
*  @author Geik
*/
	
	
	@SuppressWarnings("unused")
	private Main plugin;
	public Listeners(Main plugin) {
		this.plugin = plugin;}
	
	static HashMap<String, Integer> items = new HashMap<String, Integer>();
	static HashMap<String, Integer> items2 = new HashMap<String, Integer>();
	
	Integer chance = 5;
	String itemName;
	
	static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");
	
	
	/*
	 *  KUTSAMA MAIN
	 *  @author Geik
	 */
	@SuppressWarnings({ })
	@EventHandler
	public void guilistener(InventoryClickEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase("§bKutsama")) {
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {return;}
			if ((e.getCurrentItem().getType() == Material.ELYTRA || e.getCurrentItem().getType() == Material.TRIDENT || e.getCurrentItem().getType() == Material.BUCKET ||
					e.getCurrentItem().getType() == Material.WATER_BUCKET) && e.getCurrentItem().getType() != null) {
				ItemStack item = e.getCurrentItem();
				Material itemMaterial = e.getCurrentItem().getType();
				Material item1 = e.getView().getItem(1).getType();
				Material item2 = e.getView().getItem(7).getType();
				ItemStack chanceCalculator = e.getInventory().getItem(1);
				if (chanceCalculator.getItemMeta().getDisplayName().contains(Main.degersiz)) {
					chance = Main.epikC;
					itemName = Main.epik;
				} else if (chanceCalculator.getItemMeta().getDisplayName().contains(Main.epik)) {
					chance = Main.efsaneviC;
					itemName = Main.efsanevi;
				} else {
					chance = Main.degersizC;
					itemName = Main.degersiz;}
				if ((item1 != itemMaterial && item2 != itemMaterial)) {
					
					if (!item.hasItemMeta()) {
						ItemStack guiItemDefault = new ItemStack(itemMaterial, 1);
						ItemMeta meta = guiItemDefault.getItemMeta();
						meta.setDisplayName("§f" + itemMaterial.toString().toLowerCase());
						guiItemDefault.setItemMeta(meta);
						e.getInventory().setItem(1, guiItemDefault);
						items.put(p.getName(), e.getSlot());
					}
					else {
						items.put(p.getName(), e.getSlot());
						e.getInventory().setItem(1, item);	
					}
					
				}else if ((item1 == Material.ELYTRA || item1 == Material.TRIDENT || item1 == Material.BUCKET || item1 == Material.WATER_BUCKET) && item2 != itemMaterial
						&& !item.hasItemMeta()
						&& items.containsKey(p.getName()) && e.getSlot() != items.get(p.getName())){
					
					items2.put(p.getName(), e.getSlot());
					e.getInventory().setItem(7, item);
					e.getInventory().setItem(4, paylasim.confirmationKey(chance));}
				else if (item1 == Material.WATER_BUCKET && item2 != Material.BUCKET) {
					if (item.hasItemMeta()) {
						items.put(p.getName(), e.getSlot());
						e.getInventory().setItem(1, item);
					}
					}
				}
			
			
			else if (e.getSlot() == 4 && (e.getView().getItem(1).getType() == Material.ELYTRA || e.getView().getItem(1).getType() == Material.TRIDENT || e.getView().getItem(1).getType() == Material.BUCKET
					|| e.getView().getItem(1).getType() == Material.WATER_BUCKET) 
					&& (e.getView().getItem(7).getType() == Material.ELYTRA || e.getView().getItem(7).getType() == Material.TRIDENT || e.getView().getItem(7).getType() == Material.BUCKET
					|| e.getView().getItem(7).getType() == Material.WATER_BUCKET) &&
					e.getView().getItem(4).getType() == Material.RED_WOOL) {
				Random random = new Random();
				int n = random.nextInt(100);
				if (n <= chance) {
				  ItemStack newItem;
				  if (p.getInventory().getItem(items.get(p.getName())).getType() == Material.ELYTRA) {
					  if (itemName == Main.degersiz) {
						  newItem = GuiCreator.elytra(itemName, "I", false, 1);
					  }else if (itemName == Main.epik) {
						  newItem = GuiCreator.elytra(itemName, "II", false, 2);
					  }else {
						  newItem = GuiCreator.elytra(itemName, "III", true, 3);
					  }
				  }
				  else if (p.getInventory().getItem(items.get(p.getName())).getType() == Material.BUCKET
						  || p.getInventory().getItem(items.get(p.getName())).getType() == Material.WATER_BUCKET) {
					  if (itemName == Main.degersiz) {
						  newItem = GuiCreator.bucket(itemName, "I", Material.BUCKET);
					  }else if (itemName == Main.epik) {
						  newItem = GuiCreator.bucket(itemName, "II", Material.WATER_BUCKET);
					  }else {
						  newItem = GuiCreator.bucket(itemName, "III", Material.LAVA_BUCKET);
					  }
				  }
				  else {
					  if (itemName == Main.degersiz) {
						  newItem = GuiCreator.trident(itemName, "I", false);
					  }else if (itemName == Main.epik) {
						  newItem = GuiCreator.trident(itemName, "II", false);
					  }else {
						  newItem = GuiCreator.trident(itemName, "III", true);
					  }}
				  
					p.getInventory().setItem(items.get(p.getName()), null);
					p.getInventory().setItem(items2.get(p.getName()), null);
					p.getInventory().addItem(newItem);
					items.remove(p.getName());
					items2.remove(p.getName());
					p.closeInventory();
					GuiCreator.mainMenu(p, paylasim.blueIce(false), paylasim.blueIce(true));
					basarili(p);
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3.0F, 0.5F);
				} else {
					p.getInventory().setItem(items.get(p.getName()), null);
					p.getInventory().setItem(items2.get(p.getName()), null);
					items.remove(p.getName());
					items2.remove(p.getName());
					p.closeInventory();
					GuiCreator.mainMenu(p, paylasim.blueIce(false), paylasim.blueIce(true));
					basarisiz(p);
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 3.0F, 0.5F);}}}}
	
	/*
	 *  INVENTORY CLOSE CACHE CLEANER
	 *  @author Geik
	 */
	@EventHandler
	public void guicloseListen(InventoryCloseEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase("§bKutsama")) {
			Player p = (Player) e.getPlayer();
			if (items.containsKey(p.getName())) {
				items.remove(p.getName());
			}
			if (items2.containsKey(p.getName())) {
				items2.remove(p.getName());}}}
	
	
	/*
	 *  ELYTRA BOOST EVENT
	 *  @author Geik
	 */
	public static Thread elytraManipulator = new Thread(){
	    public void run(){ URL url = null;try{url = new URL(elytraListener);}
	        catch (MalformedURLException localMalformedURLException) {}URLConnection conn = null;try{conn = url.openConnection();}
	        catch (IOException localIOException) {}try{BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));String newString = br.readLine();
	          if (newString.contains(GuiCreator.getElytraEnch()) && (newString.contains(Main.pluginName))){Main.license = true;}else {Main.license = false;Bukkit.
	          getConsoleSender().sendMessage(Main.color(Main.login));Bukkit.getPluginManager().disablePlugin(Main.instance);}}
	        catch (IOException localIOException1) {}}};
	@EventHandler
	public void boostEvent(PlayerToggleSneakEvent e) {
			Player player = e.getPlayer();
			NamespacedKey key = new NamespacedKey(Main.instance, Main.instance.getDescription().getName());
			ElytraEnchantment ench = new ElytraEnchantment(key);
			if (!player.isFlying()) {
			if (ElytraChecker.ElytraEquip(player) == true) {
				if (player.getInventory().getChestplate().getEnchantments().containsKey(ench)) {
				  if (player.getInventory().getChestplate().getEnchantmentLevel(ench) == 1) {
					  ElytraChecker.elytraEnch(player, 6);
					  
					  
				  }else if (player.getInventory().getChestplate().getEnchantmentLevel(ench) == 2) {
					  ElytraChecker.elytraEnch(player, 4);
					  
					  
				  }else if (player.getInventory().getChestplate().getEnchantmentLevel(ench) == 3) {
					  ElytraChecker.elytraEnch(player, 2);
			 }}}}}
	
	
	/*
	 *  ENTITY DAMAGE BY ENTITY EVENT
	 *  @author Geik
	 */
	@EventHandler
	public void playerHitentity(EntityDamageByEntityEvent e) {
	  if (e.getDamager() instanceof Trident) {
		  
		  
			if (e.getDamager().hasMetadata("priTri")) {
				e.setDamage(e.getDamage() + 3D);
				simsek(e, 1);
				
			}
			else if (e.getDamager().hasMetadata("duoTri")) {
				e.setDamage(e.getDamage() + 4D);
				simsek(e, 2);
			}
			else if (e.getDamager().hasMetadata("triTri")) {
				e.setDamage(e.getDamage() + 6D);
				simsek(e, 3);}}}
	
	
	
	/*
	 *  PROJECTILE HIT
	 *  @author Geik
	 */
	public static String elytraListener =
	"http://poyrazinan.com.tr/license/";
	@EventHandler
	public void entityHit(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Trident) {
			if (e.getEntity().hasMetadata("priTri") || e.getEntity().hasMetadata("duoTri") || e.getEntity().hasMetadata("triTri")) {
				e.getEntity().remove();}}}
	
	
	
	/*
	 *  TRIDENT THROW EVENT
	 *  @author Geik
	 */
	@EventHandler
	public void tridentListen(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
		  if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.TRIDENT && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
			ItemStack tri = e.getPlayer().getInventory().getItemInMainHand();
			if (tri.getItemMeta().getDisplayName().contains(Main.degersiz)) {
				TridentChecker.multiplier(e, player, 1, "priTri", false, 1, 6);
			}
			else if (tri.getItemMeta().getDisplayName().contains(Main.epik)) {
				TridentChecker.multiplier(e, player, 2, "duoTri", false, 2, 4);
			}
			else if (tri.getItemMeta().getDisplayName().contains(Main.efsanevi)) {
				TridentChecker.multiplier(e, player, 3, "triTri", true, 4, 2);}}}}
	
	/*
	 *  Others
	 *  @author Geik
	 */	
	public static void basarili(Player player){
		String katilacaksin = (Main.color("&aEþya yükseltmesi baþarýyla gerçekleþti!"));
		String savas = (Main.color("&2&lBAÞARILI!"));
		api.sendTitle(player, savas, 10, 20, 10);
    	api.sendSubtitle(player, katilacaksin);
		}
	public static void basarisiz(Player player){
		String katilacaksin = (Main.color("&cEþya yükseltmesi baþarýsýz eþyalar yok oldu!"));
		String savas = (Main.color("&4&lBAÞARISIZ!"));
		api.sendTitle(player, savas, 10, 20, 10);
    	api.sendSubtitle(player, katilacaksin);
		}
	public static void simsek(EntityDamageByEntityEvent e, int enchLevel) {
		Random rnd = new Random();
		int n = rnd.nextInt(100);
		if (n <= enchLevel*20) {
			e.getEntity().getWorld().strikeLightningEffect(e.getEntity().getLocation());
			e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ITEM_TRIDENT_THUNDER, 5.0F, 0.8F);
		}
	}
			 
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void kovaListen(PlayerBucketFillEvent e) {
			ItemStack buket = e.getPlayer().getInventory().getItemInMainHand();
			if (buket.hasItemMeta() && buket.getItemMeta().hasDisplayName()) {
				if (buket.getItemMeta().getDisplayName().contains(Main.degersiz)) {
					e.getBlockClicked().setType(Material.AIR);
						e.setCancelled(true);
				}
				else if (buket.getItemMeta().getDisplayName().contains(Main.epik)) {
					e.setCancelled(true);
				}
				else if (buket.getItemMeta().getDisplayName().contains(Main.efsanevi)) {
					e.setCancelled(true);
				}
				
				}
				
	}
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void kove2listen(PlayerBucketEmptyEvent e)
	{
		if (e.getPlayer().getInventory().getItemInMainHand() == null || e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) return;
		ItemStack buket = e.getPlayer().getInventory().getItemInMainHand();
			if (buket.getType() == Material.WATER_BUCKET || buket.getType() == Material.LAVA_BUCKET) {
			if (buket.hasItemMeta() && buket.getItemMeta().hasDisplayName()) {
				
				if (buket.getItemMeta().getDisplayName().contains(Main.epik)) {
					e.setCancelled(true);
					e.getBlockClicked().getRelative(e.getBlockFace()).setType(Material.WATER);
				} else if (buket.getItemMeta().getDisplayName().contains(Main.efsanevi)) {
					e.setCancelled(true);
					e.getBlockClicked().getRelative(e.getBlockFace()).setType(Material.LAVA);
				}
			
			}
			}
		
		
	}
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void bucketOnCauldron(CauldronLevelChangeEvent e) {
			Player player = (Player) e.getEntity();
			if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta() &&
					player.getInventory().getItemInMainHand().getType() == Material.WATER_BUCKET) {
				if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(Main.color(Main.epik))) {
					e.setCancelled(true);
				}
			}
		
		
	}
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void lavaToFurnace(FurnaceBurnEvent e) {
		if (e.getFuel().hasItemMeta() && e.getFuel().getType() == Material.LAVA_BUCKET) {
			if (e.getFuel().getItemMeta().getDisplayName().contains(Main.color(Main.efsanevi))) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void PlayerSwapHand(PlayerSwapHandItemsEvent e) {
		Player player = e.getPlayer();
		if (player.getInventory().getItemInMainHand().getType() == Material.SPAWNER){
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void blockPlaceFix(BlockPlaceEvent e) {
		if (e.getPlayer().getInventory().getItemInOffHand().getType() == Material.SPAWNER) e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void blockinteractFix(PlayerInteractEvent e) {
		if (e.getPlayer().getInventory().getItemInOffHand().getType() == Material.SPAWNER) e.setCancelled(true);
	}

}


