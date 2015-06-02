package com.kodingkingdom.educraft.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;

import com.kodingkingdom.craftercoordinator.CrafterRegion;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.powers.powers.LocationTeleportPower;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.worldcretornica.plotme.PlotManager;
import com.worldcretornica.plotme.PlotMapInfo;
import com.worldcretornica.plotme.SqlManager;

public class Plot implements Comparable<Plot>{
	private static MultiverseCore multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
	
	private static HashSet<Plot> plots;
	
	private String name;
	

	
		
	public static Plot createPlot(String Name){
		Plot plot = new Plot();
		
		plot.name=Name;

		if (! multiverseCore.getMVWorldManager().addWorld(Name,Environment.NORMAL,"",WorldType.FLAT,false,"PlotMe",false))
				throw new RuntimeException();
		
		plots.add(plot);
		
		return plot;}
	public static Plot copyPlot(Plot plot, String Name){
		Plot newPlot  = new Plot();
		
		newPlot.name=Name;

		if (! multiverseCore.getMVWorldManager().cloneWorld(newPlot.name, Name, "PlotMe"))
				throw new RuntimeException();
		
		plots.add(newPlot);
		
		return newPlot;}
	public static void deletePlot(Plot plot){
		multiverseCore.getMVWorldManager().deleteWorld(plot.name, true, true);
		plots.remove(plot);}
	
		
	
	
	private HashMap<Student, HashSet<PlotItem>> studentPlotsMap;

	public final PlotItem givePlot(Student student){
		return givePlot(new Student[]{student})[0];}
	public final PlotItem[] givePlot(Student... students){
		org.bukkit.World plotMV = Bukkit.getServer().createWorld(new WorldCreator(name));
		
		PlotItem[] plotItems=new PlotItem[students.length];
		
		for (int j=0; j<students.length; j++){
getPlot:
			for (int i = 0;; i++) {
				for (int x = -i; x <= i; x++) {
					for (int z = -i; z <= i; z++) {
						String id = "" + x + ";" + z;

						if (PlotManager.isPlotAvailable(id, plotMV) &&
								(PlotManager.getCoordinator().checkPlayerLimit(students[j].getId())||
								PlotManager.getCoordinator().checkPlotLimit(
									new CrafterRegion(PlotManager.getPlotBottomLoc(plotMV, id), PlotManager.getPlotTopLoc(plotMV, id))))) {
							String name = students[j].getName();
							UUID uuid = students[j].getId();

							PlotManager.createPlot(plotMV, id, name, uuid);
							
							PlotItem plotItem = new PlotItem();
							
							plotItem.plotId=id;
							plotItem.plotStudent=students[j];
							
							plotItems[j]=plotItem;
							
							studentPlotsMap.get(students[j]).add(plotItem);
							
							break getPlot;}}}}}
		
		return plotItems;}
	public final void takePlot(PlotItem... plotItems){
		org.bukkit.World plotMV = Bukkit.getServer().createWorld(new WorldCreator(name));

		for (PlotItem plotItem : plotItems){
			com.worldcretornica.plotme.Plot plot = PlotManager.getPlotById(name,plotItem.plotId);
			
			String id = plot.id;

			PlotManager.setBiome(plotMV, id, plot, Biome.PLAINS);
			PlotManager.clear(plotMV, plot);

			PlotManager.getPlots(plotItem.getStudent().getName()).remove(id);

			PlotManager.removeOwnerSign(plotMV, id);
			PlotManager.removeSellSign(plotMV, id);

			SqlManager.deletePlot(PlotManager.getIdX(id), PlotManager.getIdZ(id), plotMV.getName().toLowerCase());

			studentPlotsMap.get(plotItem.getStudent()).remove(plotItem);}}	
	
	
	public class PlotItem{
		String plotId;
		Student plotStudent;
		LocationTeleportPower plotTeleporter=null;
		
		private PlotItem(){}

		public void allow(Student student){
			com.worldcretornica.plotme.Plot plot = PlotManager.getPlotById(Plot.this.name, plotId);
			String allowed = student.getName();
			plot.addAllowed(allowed);
			plot.removeDenied(allowed);}
		
		public void deny(Student student){
			com.worldcretornica.plotme.Plot plot = PlotManager.getPlotById(Plot.this.name, plotId);
			String denied = student.getName();
			plot.addDenied(denied);
			plot.removeAllowed(denied);}		

		public LocationTeleportPower getTeleporter(){
			if (plotTeleporter==null){ 
				org.bukkit.World MV = Bukkit.getServer().createWorld(new WorldCreator(Plot.this.name));
				PlotMapInfo pmi = PlotManager.getMap(MV);
				com.worldcretornica.plotme.Plot plot = PlotManager.getPlotById(Plot.this.name, plotId);
				plotTeleporter = new LocationTeleportPower(new Location(MV, PlotManager.bottomX(plot.id, MV) + (PlotManager.topX(plot.id, MV) - PlotManager.bottomX(plot.id, MV))/2, pmi.RoadHeight + 2, PlotManager.bottomZ(plot.id, MV) - 2));}
			return plotTeleporter;}
		public Student getStudent(){
			return plotStudent;}
		public Plot getPlot(){
			return Plot.this;}}

	@Override
	public int compareTo(Plot o) {
		return name.compareTo(o.name);}}
