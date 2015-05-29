package com.kodingkingdom.educraft.page.icons;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Icon {
	ItemStack iconStack;

	public ItemStack asIcon(){
		return iconStack.clone();}
	public Icon withName(String name){
		ItemMeta iconInfo = iconStack.getItemMeta();
		iconInfo.setDisplayName(name);
		iconStack.setItemMeta(iconInfo);
		return this;}
	public Icon withCaption(String caption){
		ItemMeta iconInfo = iconStack.getItemMeta();
		List<String> captionList = Arrays.asList(caption.split("\n")); 
		iconInfo.setLore(captionList);
		iconStack.setItemMeta(iconInfo);
		return this;}
	
	private Icon(){}

	public static Icon makeIcon(String name){
		throw new UnsupportedOperationException();}
	
	public static Icon makeIcon(Material material){
		Icon icon = new Icon(); 
		icon.iconStack=new ItemStack(material, 1);
		return icon;}
	public static Icon makeIcon(Material material, short magicValue){
		Icon icon = new Icon(); 
		icon.iconStack=new ItemStack(material, 1, magicValue);
		return icon;}}
