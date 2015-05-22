package com.kodingkingdom.educraft.resources;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;

import com.kodingkingdom.educraft.Student;
import com.onarandombox.MultiverseCore.MultiverseCore;

public class Plot {
	private static MultiverseCore multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
	
	private static HashSet<Plot> plots;
	
	private String name;
	
	
	
	
	
	private HashMap<Student, HashSet<PlotItem>> studentPlotsMap;
	
	public PlotItem[] assignPlot(Student... students){
		
	}
	
	
	
	
	public class PlotItem{
		Student plotStudent;
		
		PlotItem(Student PlotStudent){
			plotStudent=PlotStudent;}
		
		public Student getStudent(){
			return plotStudent;}
		public Plot getPlot(){
			return Plot.this;}}}
