package com.kodingkingdom.educraft.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.kodingkingdom.craftercoordinator.CrafterRegion;
import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.powers.powers.LocationTeleportPower;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.worldcretornica.plotme.PlotManager;
import com.worldcretornica.plotme.PlotMapInfo;
import com.worldcretornica.plotme.PlotMe;
import com.worldcretornica.plotme.SqlManager;

public class PlotWorld implements Comparable<PlotWorld>{
	private static MultiverseCore multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
	private static PlotMe plotMe = (PlotMe) Bukkit.getServer().getPluginManager().getPlugin("PlotMe");
	
	private static HashMap<String, PlotWorld> plotWorlds =new HashMap<String, PlotWorld>();//;
	
	private String name;
	public String getName(){
		return name;}

	private static void addPlotWorld(PlotWorld plotWorld){
		plotWorlds.put(plotWorld.name,plotWorld);}	
	private static void removePlotWorld(PlotWorld plotWorld){
		plotWorlds.remove(plotWorld.name);}	
	public static HashSet<PlotWorld> getPlotWorlds(){
		return new HashSet<PlotWorld> (plotWorlds.values());}	
		
	public static PlotWorld createPlotWorld(String Name){

		if (plotWorlds.containsKey(Name)) throw new RuntimeException();
		
		PlotWorld plotWorld = new PlotWorld();
		
		plotWorld.name=Name;

		if (! multiverseCore.getMVWorldManager().addWorld(Name,Environment.NORMAL,"",WorldType.FLAT,false,"PlotMe",false))
				throw new RuntimeException();

		if(!plotMe.getDataFolder().exists())
			plotMe.getDataFolder().mkdirs();
				
		File configfile = new File(plotMe.getDataFolder().getAbsolutePath(), "config.yml");
		
		FileConfiguration config = new YamlConfiguration();
		
		try {
			config.load(configfile);}
		
		catch (FileNotFoundException e) {}
		catch (IOException e){
			throw new RuntimeException();} 
		catch (InvalidConfigurationException e){
			throw new RuntimeException();}

		ConfigurationSection worlds;
		
		if(!config.contains("worlds"))
			worlds = config.createSection("worlds");
		else
			worlds = config.getConfigurationSection("worlds");

		List<Integer> defaultProtectedBlocks = new ArrayList<Integer>();
		List<String> defaultPreventedBlocks = new ArrayList<String>();
		
		defaultProtectedBlocks.add(Material.CHEST.getId());
		defaultProtectedBlocks.add(Material.FURNACE.getId());
		defaultProtectedBlocks.add(Material.BURNING_FURNACE.getId());
		defaultProtectedBlocks.add(Material.ENDER_PORTAL_FRAME.getId());
		defaultProtectedBlocks.add(Material.DIODE_BLOCK_ON.getId());
		defaultProtectedBlocks.add(Material.DIODE_BLOCK_OFF.getId());
		defaultProtectedBlocks.add(Material.JUKEBOX.getId());
		defaultProtectedBlocks.add(Material.NOTE_BLOCK.getId());
		defaultProtectedBlocks.add(Material.BED.getId());
		defaultProtectedBlocks.add(Material.CAULDRON.getId());
		defaultProtectedBlocks.add(Material.BREWING_STAND.getId());
		defaultProtectedBlocks.add(Material.BEACON.getId());
		defaultProtectedBlocks.add(Material.FLOWER_POT.getId());
		defaultProtectedBlocks.add(Material.ANVIL.getId());
		defaultProtectedBlocks.add(Material.DISPENSER.getId());
		defaultProtectedBlocks.add(Material.DROPPER.getId());
		defaultProtectedBlocks.add(Material.HOPPER.getId());
		
		defaultPreventedBlocks.add("" + Material.INK_SACK.getId() + ":15");
		defaultPreventedBlocks.add("" + Material.FLINT_AND_STEEL.getId());
		defaultPreventedBlocks.add("" + Material.MINECART.getId());
		defaultPreventedBlocks.add("" + Material.POWERED_MINECART.getId());
		defaultPreventedBlocks.add("" + Material.STORAGE_MINECART.getId());
		defaultPreventedBlocks.add("" + Material.HOPPER_MINECART.getId());
		defaultPreventedBlocks.add("" + Material.BOAT.getId());
		
		ConfigurationSection plotworld = worlds.createSection(plotWorld.name);
		
		plotworld.set("PlotAutoLimit", 1000);
		plotworld.set("PathWidth", 7);
		plotworld.set("PlotSize", 32);
		
		plotworld.set("BottomBlockId", "7");
		plotworld.set("WallBlockId", "44");
		plotworld.set("PlotFloorBlockId", "2");
		plotworld.set("PlotFillingBlockId", "3");
		plotworld.set("RoadMainBlockId", "5");
		plotworld.set("RoadStripeBlockId", "5:2");
		
		plotworld.set("RoadHeight", 64);
		plotworld.set("DaysToExpiration", 7);
		plotworld.set("ProtectedBlocks", defaultProtectedBlocks);
		plotworld.set("PreventedItems", defaultPreventedBlocks);
		plotworld.set("ProtectedWallBlockId", "44:4");
		plotworld.set("ForSaleWallBlockId", "44:1");
		plotworld.set("AuctionWallBlockId", "44:1");
		plotworld.set("AutoLinkPlots", true);
		plotworld.set("DisableExplosion", true);
		plotworld.set("DisableIgnition", true);
		
		ConfigurationSection economysection = plotworld.createSection("economy");
		
		economysection.set("UseEconomy", false);
		economysection.set("CanPutOnSale", false);
		economysection.set("CanSellToBank", false);
		economysection.set("RefundClaimPriceOnReset", false);
		economysection.set("RefundClaimPriceOnSetOwner", false);
		economysection.set("ClaimPrice", 0);
		economysection.set("ClearPrice", 0);
		economysection.set("AddPlayerPrice", 0);
		economysection.set("DenyPlayerPrice", 0);
		economysection.set("RemovePlayerPrice", 0);
		economysection.set("UndenyPlayerPrice", 0);
		economysection.set("PlotHomePrice", 0);
		economysection.set("CanCustomizeSellPrice", false);
		economysection.set("SellToPlayerPrice", 0);
		economysection.set("SellToBankPrice", 0);
		economysection.set("BuyFromBankPrice", 0);
		economysection.set("AddCommentPrice", 0);
		economysection.set("BiomeChangePrice", 0);
		economysection.set("ProtectPrice", 0);
		economysection.set("DisposePrice", 0);
		
		plotworld.set("economy", economysection);
		
		worlds.set("plotworld", plotworld);
		config.set("worlds", worlds);
		

		try{
			config.save(configfile);} 
		catch (IOException e){
			throw new RuntimeException();}
		
		plotMe.initialize();
		
		addPlotWorld(plotWorld);
		
		return plotWorld;}
	public static PlotWorld clonePlotWorld(PlotWorld plotWorld, String Name){
		if (plotWorlds.containsKey(Name)) throw new RuntimeException();
		
		PlotWorld newPlot  = new PlotWorld();
		
		newPlot.name=Name;

		if (! multiverseCore.getMVWorldManager().cloneWorld(newPlot.name, Name, "PlotMe"))
				throw new RuntimeException();
		
		addPlotWorld(newPlot);
		
		return newPlot;}
	public static void deletePlotWorld(PlotWorld plotWorld){
		multiverseCore.getMVWorldManager().deleteWorld(plotWorld.name, true, true);
		removePlotWorld(plotWorld);}
	
		
	
	
	private HashMap<Student, HashSet<PlotItem>> studentPlotsMap=new HashMap<Student, HashSet<PlotItem>> ();
	public HashSet<PlotItem> getPlots(){
		return studentPlotsMap.values().stream()
				.flatMap(plots->plots.stream())
				.collect(Collectors.toCollection(HashSet::new));}
	
	public final PlotItem givePlot(Student student){
		return givePlot(new Student[]{student})[0];}
	public final PlotItem[] givePlot(Student... students){
		org.bukkit.World plotWorld = Bukkit.getServer().createWorld(new WorldCreator(name));
		
		PlotItem[] plotItems=new PlotItem[students.length];
		
		for (int j=0; j<students.length; j++){
getPlot:
			for (int i = 0;; i++) {
				for (int x = -i; x <= i; x++) {
					for (int z = -i; z <= i; z++) {
						String id = "" + x + ";" + z;

						boolean cond1=PlotManager.isPlotAvailable(id, plotWorld);
						boolean cond2=PlotManager.getCoordinator().checkPlayerLimit(students[j].getId());
						boolean cond3=PlotManager.getCoordinator().checkPlotLimit(
									new CrafterRegion(PlotManager.getPlotBottomLoc(plotWorld, id), PlotManager.getPlotTopLoc(plotWorld, id)));
						
						EduCraftPlugin.debug("1: "+cond1+", 2: "+cond2+", 3: "+cond3);
						
						if (PlotManager.isPlotAvailable(id, plotWorld) &&
								(PlotManager.getCoordinator().checkPlayerLimit(students[j].getId())||
								PlotManager.getCoordinator().checkPlotLimit(
									new CrafterRegion(PlotManager.getPlotBottomLoc(plotWorld, id), PlotManager.getPlotTopLoc(plotWorld, id))))) {
							String name = students[j].getName();
							UUID uuid = students[j].getId();

							PlotManager.createPlot(plotWorld, id, name, uuid);
							
							PlotItem plotItem = new PlotItem();
							
							plotItem.plotId=id;
							plotItem.plotStudent=students[j];
							
							plotItems[j]=plotItem;
							
							if (studentPlotsMap.get(students[j])==null) studentPlotsMap.put(students[j], new HashSet<PlotItem>());
							studentPlotsMap.get(students[j]).add(plotItem);
							
							break getPlot;}}}}}
		
		return plotItems;}
	public final void takePlot(PlotItem... plotItems){
		org.bukkit.World plotWorld = Bukkit.getServer().createWorld(new WorldCreator(name));

		for (PlotItem plotItem : plotItems){
			com.worldcretornica.plotme.Plot plot = PlotManager.getPlotById(name,plotItem.plotId);
			
			String id = plot.id;

			PlotManager.setBiome(plotWorld, id, plot, Biome.PLAINS);
			PlotManager.clear(plotWorld, plot);

			PlotManager.getPlots(plotItem.getStudent().getPlayer()).remove(id);

			PlotManager.removeOwnerSign(plotWorld, id);
			PlotManager.removeSellSign(plotWorld, id);

			SqlManager.deletePlot(PlotManager.getIdX(id), PlotManager.getIdZ(id), plotWorld.getName().toLowerCase());

			studentPlotsMap.get(plotItem.getStudent()).remove(plotItem);}}	
	
	
	public class PlotItem implements Comparable<PlotItem>{
		String plotId;
		Student plotStudent;
		LocationTeleportPower plotTeleporter=null;
		
		private PlotItem(){}

		public void allow(Student student){
			com.worldcretornica.plotme.Plot plot = PlotManager.getPlotById(PlotWorld.this.name, plotId);
			String allowed = student.getName();
			plot.addAllowed(allowed);
			plot.removeDenied(allowed);}
		
		public void deny(Student student){
			com.worldcretornica.plotme.Plot plot = PlotManager.getPlotById(PlotWorld.this.name, plotId);
			String denied = student.getName();
			plot.addDenied(denied);
			plot.removeAllowed(denied);}		

		public LocationTeleportPower getTeleporter(){
			if (plotTeleporter==null){ 
				org.bukkit.World world = Bukkit.getServer().createWorld(new WorldCreator(PlotWorld.this.name));
				PlotMapInfo pmi = PlotManager.getMap(world);
				com.worldcretornica.plotme.Plot plot = PlotManager.getPlotById(PlotWorld.this.name, plotId);
				plotTeleporter = new LocationTeleportPower(new Location(world, PlotManager.bottomX(plot.id, world) + (PlotManager.topX(plot.id, world) - PlotManager.bottomX(plot.id, world))/2, pmi.RoadHeight + 2, PlotManager.bottomZ(plot.id, world) - 2));}
			return plotTeleporter;}
		public String getId(){
			return plotId;}
		public Student getStudent(){
			return plotStudent;}
		public PlotWorld getPlot(){
			return PlotWorld.this;}

		@Override
		public int compareTo(PlotItem o) {
			return plotId.compareTo(o.plotId);}}

	@Override
	public int compareTo(PlotWorld o) {
		return name.compareTo(o.name);}}
