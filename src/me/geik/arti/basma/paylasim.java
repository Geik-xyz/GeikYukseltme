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
		glassLore.add("&7L�tfen envanterinden");
		glassLore.add("&bElytra &7veya &cM�zra�� &7se�.");
		if (ikinci == true) {
			glassLore.add("&7Buraya sadece &a+0 &bElytra");
			glassLore.add("&7Veya &cM�zrak &7koyabilirsin.");
		} else {}
		String glassName = "�bBo� Slot";
		ItemStack blueGlass = GuiCreator.esya(glassLore, glassName, Material.BLUE_STAINED_GLASS_PANE);
		return blueGlass;}
	
	/*
	 *  Confirmation Key
	 *  @author Geik
	 */
	public static ItemStack confirmationKey(Integer chance) {
		List<String> glassLore = new ArrayList<String>();
		glassLore.add("&r");
		glassLore.add("&2�ans: &a%" + String.valueOf(chance));
		glassLore.add("&r");
		glassLore.add("&cY�kseltme ba�ar�s�z olursa");
		glassLore.add("&cE�yalar silinecek!");
		String glassName = "�4Art�y� Bas �b<TIKLA>";
		ItemStack redWool = GuiCreator.esya(glassLore, glassName, Material.RED_WOOL);
		return redWool;}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
