package com.kodingkingdom.educraft.powers.locks;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.powers.Lock;

public class ForceBuildLock extends Lock implements Listener{
	HashSet<Material> forceBuildMaterials;
	public final String getName(){
		return "ForceBuildLock";}
	public ForceBuildLock(Collection<Material> ForceBuildMaterials){
		forceBuildMaterials=new HashSet<Material>(ForceBuildMaterials);
		EduCraftPlugin.getPlugin().getEduCraft().registerEvents(this);}
	protected final void lockAction(LockItem lockItem){
		Player student = Bukkit.getPlayer(lockItem.getStudent().getId());
		if (student!=null) student.sendMessage("You can now only build the instructed blocks!");}
	protected final void unlockAction(LockItem lockItem){
		Player student = Bukkit.getPlayer(lockItem.getStudent().getId());
		if (student!=null) student.sendMessage("You can build any blocks now!!");}
	@EventHandler
	public void blockStudentPlace(BlockPlaceEvent e){
		if (!forceBuildMaterials.contains(e.getBlock().getType())&&studentLockMap.containsKey(Student.getStudent(e.getPlayer().getUniqueId()))) e.setCancelled(true);}
	@EventHandler
	public void blockStudentBreak(BlockBreakEvent e){
		if (!forceBuildMaterials.contains(e.getBlock().getType())&&studentLockMap.containsKey(Student.getStudent(e.getPlayer().getUniqueId()))) e.setCancelled(true);}}
