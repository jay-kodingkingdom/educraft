package com.kodingkingdom.educraft;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.kodingkingdom.educraft.assessment.fillintheblanks.FillBlankMaker;
import com.kodingkingdom.educraft.assessment.questionandanswer.QuestionAnswerMaker;
import com.kodingkingdom.educraft.menu.TeacherMenu;

public class EduCraft implements Listener, CommandExecutor{

	Clasz clasz=new Clasz();
	
	EduCraftPlugin plugin;	
	public EduCraft(EduCraftPlugin Plugin){plugin=Plugin;}
	
	public void Live(){
		plugin.getCommand("edu").setExecutor(this);
		plugin.getCommand("educraft").setExecutor(this);
		registerEvents(this);
		
		setupClasz();
		refreshClasz();
		for (TeacherUser teacher :  clasz.teachers.values()){
			new TeacherMenu(clasz,teacher);}}
	
	public void Die(){
		for (BukkitRunnable runnable:asyncTasks){
			try{runnable.cancel();}catch(IllegalStateException e){}}}
	
	public void registerEvents(Listener listener){
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);}
	
	HashSet<BukkitRunnable> asyncTasks=new HashSet<BukkitRunnable>();
	public void runAsync(BukkitRunnable runnable){
		asyncTasks.add(runnable);
		runnable.runTaskAsynchronously(plugin);}
	
	void setupClasz(){
		clasz.testMakers.add(new FillBlankMaker(clasz));
		clasz.testMakers.add(new QuestionAnswerMaker(clasz));
		//clasz.testMakers.add(new CopyTestMaker());
	}
	
	void refreshClasz(){
		clasz.students.clear();clasz.teachers.clear();
		for (Player player : Bukkit.getOnlinePlayers()){
			if (player.isOp()) clasz.teachers.put(player.getUniqueId(),new TeacherUser(player.getUniqueId()));
			else clasz.students.put(player.getUniqueId(),new StudentUser(player.getUniqueId()));}}
		
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if (sender instanceof Player && !((Player)sender).isOp()) {
			sender.sendMessage("ERROR: You do not have permission!");
			return true;}
		
		if (args.length==1){
			if (args[0].equalsIgnoreCase("refreshClass")){
				refreshClasz();
				sender.sendMessage("The class has been refreshed");
				return true;}
			if (args[0].equalsIgnoreCase("giveMenu")){ 
				if (sender instanceof Player){
					new TeacherMenu(clasz,clasz.teachers.get(((Player)sender).getUniqueId()));
					sender.sendMessage("Teacher menu given");
					return true;}
				else {
					sender.sendMessage("Please specify a player!");
					return true;}}}
		else if (args.length==2){
			if (args[0].equalsIgnoreCase("giveMenu")){
				if (clasz.teachers.containsKey(args[1])) sender.sendMessage("Cannot find teacher!");
				else {
					new TeacherMenu(clasz,clasz.teachers.get((Bukkit.getPlayer(args[1]).getUniqueId())));
					sender.sendMessage("Teacher menu given");}
				return true;}}	
		sender.sendMessage("Cannot understand command!");
		return false;}}
