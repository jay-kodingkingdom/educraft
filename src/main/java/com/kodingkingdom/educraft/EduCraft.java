package com.kodingkingdom.educraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

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
		
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if (sender instanceof Player && !((Player)sender).isOp()) {
			return false;}
		
		if (args.length==1){
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
