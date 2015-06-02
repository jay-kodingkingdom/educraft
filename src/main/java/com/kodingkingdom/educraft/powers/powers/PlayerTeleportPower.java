package com.kodingkingdom.educraft.powers.powers;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.kodingkingdom.educraft.powers.Power;

public class PlayerTeleportPower extends Power implements Listener{
	Player teleportPlayer;
	
	public final String getName(){
		return "TeleportTo:"+teleportPlayer.getName();}
	
	HashMap<PowerItem, Location> originalLocationMap = new HashMap<PowerItem, Location>(); 
	public PlayerTeleportPower(Player Player){
		teleportPlayer=Player;}
	protected final void doAction(PowerItem powerItem){
		final Player player = powerItem.getUser().getPlayer();
		if (player!=null){
			originalLocationMap.put(powerItem, player.getLocation());
			powerItem.getUser().getPlayer().teleport(teleportPlayer.getLocation());
			player.sendMessage("You have been teleported!");}}
	protected final void undoAction(PowerItem powerItem){
		final Player player = powerItem.getUser().getPlayer();
		if (player!=null && originalLocationMap.containsKey(powerItem)){
			player.teleport(originalLocationMap.get(powerItem));
			originalLocationMap.remove(powerItem);
			player.sendMessage("You have been unteleported!");}}}
