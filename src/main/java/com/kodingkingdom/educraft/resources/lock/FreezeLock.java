package com.kodingkingdom.educraft.resources.lock;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.Student;
import com.kodingkingdom.educraft.resources.Lock;

public class FreezeLock extends Lock implements Listener{
	public FreezeLock(){
		EduCraftPlugin.getPlugin().getEduCraft().registerEvents(this);}
	protected final void lockAction(LockItem lockItem){
		Player student = Bukkit.getPlayer(lockItem.getStudent().getId());
		if (student!=null) student.sendMessage("You are now freezed!");}
	protected final void unlockAction(LockItem lockItem){
		Player student = Bukkit.getPlayer(lockItem.getStudent().getId());
		if (student!=null) student.sendMessage("You are now unfreezed!");}
	@EventHandler
	public void freezeStudent(PlayerMoveEvent e){
		if (studentLockMap.containsKey(Student.getStudent(e.getPlayer().getUniqueId()))) e.setCancelled(true);}}