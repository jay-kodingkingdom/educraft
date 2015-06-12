package com.kodingkingdom.educraft;
import java.util.logging.Level;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class EduCraftPlugin extends JavaPlugin implements Listener{
	EduCraft eduCraft=new EduCraft(this);
	@Override
    public void onEnable(){eduCraft.Live();} 
    @Override
    public void onDisable(){eduCraft.Die();}
        
    public EduCraft getEduCraft(){return eduCraft;}
    
    static EduCraftPlugin instance;
    public EduCraftPlugin(){instance=this;}
    public static EduCraftPlugin getPlugin(){return instance;}
    public static void debug(String msg){
    	instance.getLogger().log(Level.FINE
    			, msg);}}