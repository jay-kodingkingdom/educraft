package com.kodingkingdom.educraft.resources.power;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.event.Listener;

import com.kodingkingdom.educraft.resources.Power;

public class LocationTeleportPower extends Power implements Listener{
	Location location;
	
	HashMap<PowerItem, Location> originalLocationMap = new HashMap<PowerItem, Location>(); 
	public LocationTeleportPower(Location loc){
		location=loc;}
	protected final void doAction(PowerItem powerItem){
		originalLocationMap.put(powerItem, powerItem.getStudent().getPlayer().getLocation());
		powerItem.getStudent().getPlayer().teleport(location);}
	protected final void undoAction(PowerItem powerItem){
		powerItem.getStudent().getPlayer().teleport(originalLocationMap.get(powerItem));
		originalLocationMap.remove(powerItem);}}
