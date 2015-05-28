package com.kodingkingdom.educraft.powers.locks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.powers.Lock;

public class MuteLock extends Lock implements Listener{
	public MuteLock(){
		EduCraftPlugin.getPlugin().getEduCraft().registerEvents(this);}
	protected final void lockAction(LockItem lockItem){
		Player student = Bukkit.getPlayer(lockItem.getStudent().getId());
		if (student!=null) student.sendMessage("You are now muted!");}
	protected final void unlockAction(LockItem lockItem){
		Player student = Bukkit.getPlayer(lockItem.getStudent().getId());
		if (student!=null) student.sendMessage("You are now unmuted!");}
	@EventHandler
	public void muteStudent(AsyncPlayerChatEvent e){
		if (studentLockMap.containsKey(Student.getStudent(e.getPlayer().getUniqueId()))) e.setCancelled(true);}}
