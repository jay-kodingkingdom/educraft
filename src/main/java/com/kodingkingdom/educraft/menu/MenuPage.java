package com.kodingkingdom.educraft.menu;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

public abstract class MenuPage {
	public abstract HashMap<Integer,MenuItem> getPageItems();
	public abstract ItemStack getPageItem();
	

	public abstract void flush();
	public abstract void refresh();
	public abstract void nextPage();
	public abstract void prevPage();}
