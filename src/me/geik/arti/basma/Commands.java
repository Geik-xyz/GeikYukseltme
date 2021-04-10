package me.geik.arti.basma;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Commands implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	public Commands(Main plugin) {
		this.plugin = plugin;
	}
	
	
	
	/*
	 *  COMMANDS
	 *  @author Geik
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("kutsama")) {
		  if (sender instanceof Player) {
			  Player player = (Player) sender;
			  if (args.length == 0) {
				  GuiCreator.mainMenu(player, paylasim.blueIce(false), paylasim.blueIce(true));}
			  else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("yardým") || args[0].equalsIgnoreCase("bilgi") || args[0].equalsIgnoreCase("help")) {
					player.sendMessage(Main.color("&f"));
					player.sendMessage(Main.color("&3Merhaba &4" + player.getName() + "&3 bu eklentinin kullanýmý þu þekildedir:"));
					player.sendMessage(Main.color("&f/kutsama &3- Kutsama menüsünü açar."));
					player.sendMessage(Main.color("&3Ayrýca bastýðýn her eþya &cbaþarýsýz &3olursa yok olur."));
					player.sendMessage(Main.color("&bEnchler &3hakkýnda bilgi için&b https://wiki.rigelmc.com"));
					player.sendMessage(Main.color("&cYakýp &3oyunu býrakmaman dileðiyle! ;)"));
					player.sendMessage(Main.color("&f"));
				}
				else if (args[0].equalsIgnoreCase("admin")) {
					if (player.hasPermission("arti.admin.uuu")) {
						ItemStack pritri = GuiCreator.trident(Main.degersiz, "I", false);
						ItemStack duotri = GuiCreator.trident(Main.epik, "II", false);
						ItemStack tritri = GuiCreator.trident(Main.efsanevi, "III", true);
						ItemStack prielytra = GuiCreator.elytra(Main.degersiz, "I", false, 1);
						ItemStack duoelytra = GuiCreator.elytra(Main.epik, "II", false, 2);
						ItemStack trielytra = GuiCreator.elytra(Main.efsanevi, "III", true, 3);
						ItemStack prikova = GuiCreator.bucket(Main.degersiz, "I", Material.BUCKET);
						ItemStack duokova = GuiCreator.bucket(Main.epik, "II", Material.WATER_BUCKET);
						ItemStack trikova = GuiCreator.bucket(Main.efsanevi, "III", Material.LAVA_BUCKET);
						player.getInventory().addItem(pritri);
						player.getInventory().addItem(duotri);
						player.getInventory().addItem(tritri);
						player.getInventory().addItem(paylasim.blueIce(true));
						player.getInventory().addItem(paylasim.blueIce(false));
						player.getInventory().addItem(paylasim.confirmationKey(999));
						player.getInventory().addItem(prielytra);
						player.getInventory().addItem(duoelytra);
						player.getInventory().addItem(trielytra);
						player.getInventory().addItem(prikova);
						player.getInventory().addItem(duokova);
						player.getInventory().addItem(trikova);
						}}}}}return false;}
	
	

}
