package com.kodingkingdom.educraft.resources.power;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.kodingkingdom.educraft.resources.Power;

public class PlayerTeleportPower extends Power implements Listener{
	Player player;
	
	HashMap<PowerItem, Location> originalLocationMap = new HashMap<PowerItem, Location>(); 
	public PlayerTeleportPower(Player Player){
		player=Player;}
	protected final void doAction(PowerItem powerItem){
		originalLocationMap.put(powerItem, powerItem.getStudent().getPlayer().getLocation());
		powerItem.getStudent().getPlayer().teleport(player.getLocation());}
	protected final void undoAction(PowerItem powerItem){
		powerItem.getStudent().getPlayer().teleport(originalLocationMap.get(powerItem));
		originalLocationMap.remove(powerItem);}}
