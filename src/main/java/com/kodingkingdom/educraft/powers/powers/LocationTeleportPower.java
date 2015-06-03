package com.kodingkingdom.educraft.powers.powers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.kodingkingdom.educraft.powers.Power;

public class LocationTeleportPower extends Power implements Listener{
	Location location;
	
	public final String getName(){
		return "TeleportToLocation:"+location.getBlockX()+","+location.getBlockY()+","+location.getBlockZ();}
	
	//HashMap<PowerItem, Location> originalLocationMap = new HashMap<PowerItem, Location>(); 
	public LocationTeleportPower(Location loc){
		location=loc;}
	protected final void doAction(PowerItem powerItem){
		final Player player = powerItem.getUser().getPlayer();
		if (player!=null){
			//originalLocationMap.put(powerItem, player.getLocation());
			player.teleport(location);
			player.sendMessage("You have been teleported!");}
		take(powerItem.getUser());}
	//protected final void undoAction(PowerItem powerItem){
		//final Player player = powerItem.getUser().getPlayer();
		//if (player!=null && originalLocationMap.containsKey(powerItem)){
			//player.teleport(originalLocationMap.get(powerItem));
			//originalLocationMap.remove(powerItem);
			//player.sendMessage("You have been unteleported!");}}
	}
