package com.kodingkingdom.educraft.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.worldcretornica.plotme.utils.NameFetcher;

public class Student {
	
	UUID id;
	String name=null;
	
	static HashMap<UUID, Student> idStudentMap=new HashMap<UUID, Student>(); 
	
	public UUID getId(){
		return id;}
	public String getName(){
		if (name==null){
			getPlayerName();}
		return name;}
	public Player getPlayer(){
		return Bukkit.getPlayer(id);}
	
	private Student(UUID Id){
		id=Id;
		EduCraftPlugin.getPlugin().getEduCraft().scheduleAsyncTask(() -> {getPlayerName();});}
	
	
	public static Student getStudent(UUID id){
		if (idStudentMap.containsKey(id))
			idStudentMap.put(id, new Student(id));
		return idStudentMap.get(id);}
	
	
	private static final long bufferTime = 2*1000L;
	private static ReentrantLock lock = new ReentrantLock(); 
	private void getPlayerName(){			
		lock.lock();
		try{
	        OfflinePlayer player = Bukkit.getOfflinePlayer(id);

	        if (name!=null){
	        	return;}
	        else if (player.getName() != null) {
	            name=player.getName();}
	        else {
	    		try {
	    			Thread.sleep(bufferTime);}
	    		catch (InterruptedException e) {}
	            List<UUID> names = new ArrayList<UUID>();
	            names.add(id);
	            NameFetcher fetcher = new NameFetcher(names);

	            try {
	                //CrafterMakerPlugin.say("Fetching " + playerId.toString() + " Name from Mojang servers...");
	                Map<UUID, String> response = fetcher.call();
	                
	                if (response.size() > 0) {
	                	String playerName = response.values().toArray(new String[0])[0];
	                	//CrafterMakerPlugin.say("Fetched " + playerName + "for" + playerId.toString());
	                	name=playerName;}}
	            catch (IOException e) {
	            	//CrafterMakerPlugin.say("Unable to connect to Mojang server!");
	            	if (e.getMessage()!=null&&e.getMessage().contains("HTTP response code: 429")){
	            		//CrafterMakerPlugin.say("HTTP response code 429");
	                    //CrafterMakerPlugin.say("Retrying...");
	            		getPlayerName();}} 
	            catch (Exception e) {
	            	//CrafterMakerPlugin.say("Exception while running NameFetcher");
	                e.printStackTrace();}}}
		finally{lock.unlock();}}}
