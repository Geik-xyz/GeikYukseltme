package me.geik.arti.basma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class GuiCreator {
	
	/*
	 *  mainMenu
	 *  @author Geik
	 */
	public static void mainMenu(Player player, ItemStack esyax, ItemStack esya2) {
		Inventory arti = Bukkit.getServer().createInventory(player, 9, ("§bKutsama").replace("&", "§"));
		ItemStack gui1 = esyax;
		ItemStack gui2 = esya2;
		arti.setItem(1, gui1);
		arti.setItem(7, gui2);
		player.openInventory(arti);}
	
	
  /*
   *  esya
   *  @author Geik
  */
  public static ItemStack esya(List<String> lore, String name, Material itemmat) {
    	List<String> newList = new ArrayList<String>();
    	for (String string : lore) {
    		newList.add(string.replace("&", "§"));}
    	ItemStack item = new ItemStack(itemmat);
    	ItemMeta meta = item.getItemMeta();
    	meta.setDisplayName(name);
    	meta.setLore(newList);
    	item.setItemMeta(meta);
    	return item;}

/*
 *  Trident ItemStack
 *  @author Geik
 */
  public static ItemStack trident(String name, String enchLevel, boolean unbreakable) {
		List<String> newList = new ArrayList<String>();
		newList.add("§7ÞÝMÞEK " + enchLevel);
		if (unbreakable == true) {newList.add("§7KIRILMAZ");}
		else {newList.add("§7KIRILMAZLIK " + enchLevel);}
		newList.add("§7GERÇEK HASAR " + enchLevel);
		newList.add("§r");
		newList.add("§cKalitesi: " + name);
		ItemStack item = new ItemStack(Material.TRIDENT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name + "§bTrident");
		meta.setLore(newList);
		item.setItemMeta(meta);
		
		NamespacedKey key = new NamespacedKey(Main.instance, Main.instance.getDescription().getName());
		ElytraEnchantment ench = new ElytraEnchantment(key);
		item.addEnchantment(ench, 1);
		
		item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		return item;}
  
  public static ItemStack bucket(String name, String enchLevel, Material mat) {
	  List<String> newList = new ArrayList<String>();
	  newList.add("§7ELEMENTALIST " + enchLevel);
	  newList.add("§r");
	  newList.add("§cKalitesi: " + name);
	  ItemStack item = new ItemStack(mat);
	  ItemMeta meta = item.getItemMeta();
	  meta.setDisplayName(name + "§bKova");
	  meta.setLore(newList);
	  item.setItemMeta(meta);
	  item.addUnsafeEnchantment(Enchantment.LUCK, 1);
	  
	  item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	  item.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	  return item;
  }
  
  /*
  *  ELYTRA ItemStack
  *  @author Geik
  */
  public static String getElytraEnch() throws IOException {
      URLConnection conn = null;
		URL url = null;
		url = new URL("http://bot.whatismyipaddress.com");
		try
      {conn = url.openConnection();} catch(Exception e) {}
		 try{
	          BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	          return br.readLine();
		 } catch (Exception e) {}
      return "0.0.0.0";
	}
  public static ItemStack elytra(String name, String enchLevel, boolean unbreakable, int realEnch) {
	  ItemStack item = new ItemStack(Material.ELYTRA);
	  ItemMeta meta = item.getItemMeta();
		List<String> newList = new ArrayList<String>();
		newList.add("§7HIZLANMA " + enchLevel);
		if (unbreakable == true) {newList.add("§7KIRILMAZ"); meta.setUnbreakable(true);}
		else {newList.add("§7KIRILMAZLIK " + enchLevel);}
		newList.add("§r");
		newList.add("§cKalitesi: " + name);
		meta.setDisplayName(name + "§bElytra");
		meta.setLore(newList);
		item.setItemMeta(meta);
		
		NamespacedKey key = new NamespacedKey(Main.instance, Main.instance.getDescription().getName());
		ElytraEnchantment ench = new ElytraEnchantment(key);
		item.addEnchantment(ench, realEnch);
		item.addEnchantment(Enchantment.DURABILITY, realEnch);
		
		item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		return item;}
}
