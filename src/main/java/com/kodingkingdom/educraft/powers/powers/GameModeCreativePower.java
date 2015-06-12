package com.kodingkingdom.educraft.powers.powers;

import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.kodingkingdom.educraft.group.User;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.powers.Power;

public class GameModeCreativePower extends Power implements Listener{
		
	public final String getName(){
		return "GameModeCreativePower";}

	public Set<User> getStudents(){
		return Bukkit.getOnlinePlayers().stream()
				.filter(player -> player.getGameMode().equals(GameMode.CREATIVE))
				.map(player -> Student.getStudent(player.getUniqueId()))
				.collect(Collectors.toSet());}
	
	protected final void doAction(PowerItem powerItem){
		final Player player = powerItem.getUser().getPlayer();
		if (player!=null){
			powerItem.getUser().getPlayer().setGameMode(GameMode.CREATIVE);;
			player.sendMessage("Your gamemode has been set to Creative!");}
		take(powerItem.getUser());}}
