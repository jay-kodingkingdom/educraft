package com.kodingkingdom.educraft.menu.pages;

import org.bukkit.inventory.ItemStack;

public class SelectItem{
	Runnable action;
	ItemStack icon;
	public SelectItem(Runnable Action,ItemStack Icon){
		action=Action;
		icon=Icon.clone();}}