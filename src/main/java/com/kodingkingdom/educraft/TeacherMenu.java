package com.kodingkingdom.educraft;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kodingkingdom.educraft.menu.Menu;

public class TeacherMenu {
	Menu teacherMenu;
	ItemStack menuIcon;
	
	public TeacherMenu(){
		menuIcon = makeIcon(Material.ENCHANTED_BOOK)
				.withName("Teacher Menu")
				.asIcon();
		teacherMenu=Menu.createMenu(9, 6, "TeacherMenu", menuIcon);
		
	}

	private Icon makeIcon(Material material){
		Icon icon = new Icon(); 
		icon.iconStack=new ItemStack(material, 1);
		return icon;}
	private Icon makeIcon(Material material, short magicValue){
		Icon icon = new Icon(); 
		icon.iconStack=new ItemStack(material, 1, magicValue);
		return icon;}
	private class Icon{
		ItemStack iconStack;

		private ItemStack asIcon(){
			return iconStack.clone();}
		private Icon withName(String name){
			ItemMeta iconInfo = iconStack.getItemMeta();
			iconInfo.setDisplayName(name);
			iconStack.setItemMeta(iconInfo);
			return this;}
		private Icon withCaption(String caption){
			ItemMeta iconInfo = iconStack.getItemMeta();
			List<String> captionList = Arrays.asList(caption.split("\n")); 
			iconInfo.setLore(captionList);
			iconStack.setItemMeta(iconInfo);
			return this;}
		private Icon(){}}}
