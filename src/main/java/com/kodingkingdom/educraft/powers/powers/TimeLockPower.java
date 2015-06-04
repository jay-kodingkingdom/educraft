package com.kodingkingdom.educraft.powers.powers;

import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.event.Listener;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.powers.Power;

public class TimeLockPower extends Power implements Listener{
	private static final long lockInterval = 200;
	
	HashMap<String, Long> worldTimeMap = new HashMap<String, Long>(); 
	
	public final String getName(){
		return "TimeLockPower";}
	public TimeLockPower(){
		lockTime();}
		
	void lockTime(){
		for (PowerItem item : userPowerMap.values()){
			World MV = item.getUser().getPlayer()
					.getWorld();
			if (worldTimeMap.containsKey(MV.getName())) {
				MV.setTime(
						worldTimeMap.get(MV.getName()));}
			else {
				worldTimeMap.put(MV.getName(), MV.getFullTime());}}
		EduCraftPlugin.getPlugin().getEduCraft().scheduleAsyncTask(()->{lockTime();},lockInterval);}}
