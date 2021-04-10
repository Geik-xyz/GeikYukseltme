package me.geik.arti.basma;

import java.lang.reflect.Field;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private Listeners listeners;
	public static Main instance;
	static int degersizC = 70;
	static int epikC = 50;
	static int efsaneviC = 20;
	public static boolean license = false;
	public static String  pluginName = "GeikYukseltme";
	public static String login = "&4&lUnauthorized Login!";
	static String degersiz = "§a§lDeðersiz ";
	static String epik = "§5§lEpik ";
	static String efsanevi = "§6§lEFSANEVI ";
	
	
	
	public void onEnable() {
		this.listeners = new Listeners(this);
		Bukkit.getPluginManager().registerEvents(this.listeners, this);
		//Listeners.elytraManipulator.start();;
		getCommand("kutsama").setExecutor(new Commands(this));
		instance = this;
		LoadEnch();
		Bukkit.getConsoleSender().sendMessage(color("&6&lGeikYukseltme &aLoaded! Version: 2.0"));
		Bukkit.getConsoleSender().sendMessage(color("&6&lGeikYukseltme &aMade by Geik."));
	}
	@SuppressWarnings({ })
	public void onDisable() {
	}
	
	
	public static String color(String yazirengi){return ChatColor.translateAlternateColorCodes('&', yazirengi);}
	
	private void LoadEnch() {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
             NamespacedKey key = new NamespacedKey(this, getDescription().getName());
               
                ElytraEnchantment glow = new ElytraEnchantment(key);
                TridentEnchantment tridentEnchant = new TridentEnchantment(key);
                Enchantment.registerEnchantment(glow);
                Enchantment.registerEnchantment(tridentEnchant);
            }
            catch (IllegalArgumentException e){
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
            
            
	}

}
