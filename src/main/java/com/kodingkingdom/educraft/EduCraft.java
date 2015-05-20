package com.kodingkingdom.educraft;

import java.util.HashSet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class EduCraft implements Listener, CommandExecutor{

	EduCraftPlugin plugin;	
	public EduCraft(EduCraftPlugin Plugin){plugin=Plugin;}
	
	public void Live(){
		plugin.getCommand("edu").setExecutor(this);
		plugin.getCommand("educraft").setExecutor(this);
		registerEvents(this);}
	
	public void Die(){
		for (BukkitRunnable runnable:asyncTasks){
			try{runnable.cancel();}catch(IllegalStateException e){}}}
	
	public void registerEvents(Listener listener){
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);}
	
	HashSet<BukkitRunnable> asyncTasks=new HashSet<BukkitRunnable>();
	public void runAsync(BukkitRunnable runnable){
		asyncTasks.add(runnable);
		runnable.runTaskAsynchronously(plugin);}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if (sender instanceof Player && !((Player)sender).isOp()) {
			sender.sendMessage("ERROR: You do not have permission!");
			return true;}
		
		if (args.length==1){
			if (args[0].equalsIgnoreCase("refreshClass")){
				sender.sendMessage("The class has been refreshed");
				return true;}
			if (args[0].equalsIgnoreCase("giveMenu")){ 
				if (sender instanceof Player){
					sender.sendMessage("Teacher menu given");
					return true;}
				else {
					sender.sendMessage("Please specify a player!");
					return true;}}}
		else if (args.length==2){
			if (args[0].equalsIgnoreCase("giveMenu")){
				return true;}}	
		sender.sendMessage("Cannot understand command!");
		return false;}}
