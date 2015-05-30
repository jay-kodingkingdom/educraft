package com.kodingkingdom.educraft.page.icons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Icon {;
	ItemStack iconStack;

	private static HashMap<Material, Icon> materialIconMap=new HashMap<Material, Icon> ();
	private static HashMap<Material, HashMap<Short,Icon>> magicValueIconMap=new HashMap<Material, HashMap<Short,Icon>> ();
	
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
	private Icon(Material material, short magicValue){
		if (magicValue>0) {
			if (!magicValueIconMap.containsKey(material)) magicValueIconMap.put(material, new HashMap<Short,Icon>());
			magicValueIconMap.get(material).put(magicValue, this);}
		else if (magicValue<=0) {
			if (!magicValueIconMap.containsKey(material)) magicValueIconMap.put(material, new HashMap<Short,Icon>());
			magicValueIconMap.get(material).put((short) -magicValue, this);
			materialIconMap.put(material, this);}
		else {
			materialIconMap.put(material, this);}}

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
