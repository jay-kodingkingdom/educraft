package com.kodingkingdom.educraft.powers.powers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.kodingkingdom.educraft.powers.Power;

public class GameModeCreativePower extends Power implements Listener{
		
	public final String getName(){
		return "GameModeCreativePower";}
	 
	protected final void doAction(PowerItem powerItem){
		final Player player = powerItem.getUser().getPlayer();
		if (player!=null){
			powerItem.getUser().getPlayer().setGameMode(GameMode.CREATIVE);;
			player.sendMessage("Your gamemode has been set to Creative!");}
		take(powerItem.getUser());}}
