package com.kodingkingdom.educraft;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

abstract public class User {
	public abstract UUID getID();
	public Player getPlayer(){
		return Bukkit.getPlayer(getID());}

	public void giveItem(ItemStack item){
		int i;for (i=0;i<9;i++){
			if (getPlayer().getInventory().getItem(i)==null)break;}
		if (i<9) getPlayer().getInventory().setItem(i, item);
		else getPlayer().setItemInHand(item);}}
