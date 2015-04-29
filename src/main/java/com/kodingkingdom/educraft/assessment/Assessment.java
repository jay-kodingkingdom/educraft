package com.kodingkingdom.educraft.assessment;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.Clasz;

public abstract class Assessment{
	public abstract Clasz getClasz();
	public abstract String getName();
	
	public abstract void Administer();	
	public abstract void Mark();
	
	public abstract Assessment getClone();
	public abstract ItemStack getResultItem();}
