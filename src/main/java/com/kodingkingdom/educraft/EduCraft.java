package com.kodingkingdom.educraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.kodingkingdom.educraft.group.users.Teacher;
import com.kodingkingdom.educraft.menu.Bible;

public class EduCraft implements Listener, CommandExecutor{

	EduCraftPlugin plugin;	
	public EduCraft(EduCraftPlugin Plugin){plugin=Plugin;}
	
	public void Live(){
		plugin.getCommand("edu").setExecutor(this);
		plugin.getCommand("educraft").setExecutor(this);
		registerEvents(this);}
	
	public void Die(){}
	
	public void registerEvents(Listener listener){
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);}

	public int scheduleAsyncTask(Runnable task){
		return plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, task);}
	public int scheduleTask(Runnable task, long delay){
		return plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, task, delay);}
	public void cancelTask(int taskId){
		plugin.getServer().getScheduler().cancelTask(taskId);}

	@EventHandler
	public void giveBible(PlayerJoinEvent e){
		if (e.getPlayer().isOp()) {
			giveBible(e.getPlayer());}}
	
	private void giveBible(Player player){
		int slotNumber=0;
		Bible newBible = new Bible(Teacher.getTeacher(player.getUniqueId()));
		EduCraftPlugin.debug("new bible is "+newBible);
		for (;slotNumber<player.getInventory().getSize();slotNumber++){
			if (player.getInventory().getItem(slotNumber)==null) break;}
		if (slotNumber<player.getInventory().getSize())
			{EduCraftPlugin.debug("put bible in slot "+slotNumber);
			player.getInventory().setItem(slotNumber,newBible.getIcon());}
		else {
			EduCraftPlugin.debug("put bible in hand");
			player.setItemInHand(newBible.getIcon());}
		player.updateInventory();}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if (sender instanceof Player && !((Player)sender).isOp()) {
			return false;}
		
		if (args.length==1){
			if (args[0].equalsIgnoreCase("giveMenu")){ 
				if (sender instanceof Player){
					giveBible((Player)sender);
					sender.sendMessage("Teacher menu given");
					return true;}
				else {
					sender.sendMessage("Please specify a player!");
					return true;}}}
		else if (args.length==2){
			if (args[0].equalsIgnoreCase("giveMenu")){
				Player player = Bukkit.getPlayer(args[1]);
				if (player != null){
					giveBible(player);
					sender.sendMessage("Teacher menu given");
					return true;}
				else {
					sender.sendMessage("The player specified doesn't exist!");
					return true;}}}	
		
		sender.sendMessage("Cannot understand command!");
		return false;}}
