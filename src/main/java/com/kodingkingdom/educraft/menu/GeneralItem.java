package com.kodingkingdom.educraft.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kodingkingdom.educraft.TeacherUser;

class GeneralItem extends MenuItem{
	SplitSelectPage splitSelectPage;
	Runnable methodCall;
	ItemStack item;
	@Override
	public ItemStack getItem() {return item.clone();}
	@Override
	public void onClick(TeacherUser whoTeacher) {try {methodCall.run();}catch(Exception e){}}
	GeneralItem(Runnable MethodCall,Material material, String description){methodCall=MethodCall;item=new ItemStack(material);ItemMeta itemMeta=item.getItemMeta();itemMeta.setDisplayName(description);item.setItemMeta(itemMeta);}
	GeneralItem(Runnable MethodCall,Material material, Byte data, String description){methodCall=MethodCall;item=new ItemStack(material,1,data);ItemMeta itemMeta=item.getItemMeta();itemMeta.setDisplayName(description);item.setItemMeta(itemMeta);}}
