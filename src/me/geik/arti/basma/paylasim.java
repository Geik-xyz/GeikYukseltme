package me.geik.arti.basma;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class paylasim {
	
	/*
	 *  BlueIce GuiItem
	 *  @author Geik
	 */
	public static ItemStack blueIce(boolean ikinci) {
		List<String> glassLore = new ArrayList<String>();
		glassLore.add("&7Lütfen envanterinden");
		glassLore.add("&bElytra &7veya &cMýzraðý &7seç.");
		if (ikinci == true) {
			glassLore.add("&7Buraya sadece &a+0 &bElytra");
			glassLore.add("&7Veya &cMýzrak &7koyabilirsin.");
		} else {}
		String glassName = "§bBoþ Slot";
		ItemStack blueGlass = GuiCreator.esya(glassLore, glassName, Material.BLUE_STAINED_GLASS_PANE);
		return blueGlass;}
	
	/*
	 *  Confirmation Key
	 *  @author Geik
	 */
	public static ItemStack confirmationKey(Integer chance) {
		List<String> glassLore = new ArrayList<String>();
		glassLore.add("&r");
		glassLore.add("&2Þans: &a%" + String.valueOf(chance));
		glassLore.add("&r");
		glassLore.add("&cYükseltme baþarýsýz olursa");
		glassLore.add("&cEþyalar silinecek!");
		String glassName = "§4Artýyý Bas §b<TIKLA>";
		ItemStack redWool = GuiCreator.esya(glassLore, glassName, Material.RED_WOOL);
		return redWool;}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
