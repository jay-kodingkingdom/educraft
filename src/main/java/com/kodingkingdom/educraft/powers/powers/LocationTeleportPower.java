package com.kodingkingdom.educraft.powers.powers;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.event.Listener;

import com.kodingkingdom.educraft.powers.Power;

public class LocationTeleportPower extends Power implements Listener{
	Location location;
	
	HashMap<PowerItem, Location> originalLocationMap = new HashMap<PowerItem, Location>(); 
	public LocationTeleportPower(Location loc){
		location=loc;}
	protected final void doAction(PowerItem powerItem){
		originalLocationMap.put(powerItem, powerItem.getUser().getPlayer().getLocation());
		powerItem.getUser().getPlayer().teleport(location);}
	protected final void undoAction(PowerItem powerItem){
		powerItem.getUser().getPlayer().teleport(originalLocationMap.get(powerItem));
		originalLocationMap.remove(powerItem);}}
