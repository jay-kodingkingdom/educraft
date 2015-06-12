package com.kodingkingdom.educraft.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.powers.powers.LocationTeleportPower;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.wimbli.WorldBorder.Config;
import com.wimbli.WorldBorder.CoordXZ;
import com.wimbli.WorldBorder.WorldBorder;
import com.wimbli.WorldBorder.WorldTrimTask;

public class Area implements Comparable<Area>{
	private static MultiverseCore multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
	private static String universeId = "eduCraft_universe";
	
	public static String getUniverseId(){
		return universeId;}

	private static HashSet<Area> areas;
	
	
	private static Region worldsRegion;
	private static int expandFactor = 2;
	
	
	
	
	public class AreaCopy{
		String worldId;
		Student worldStudent;
		LocationTeleportPower worldTeleporter=null;
		
		private AreaCopy(){}
				
		public LocationTeleportPower getTeleporter(){
			if (worldTeleporter==null){ 
				org.bukkit.World MV = Bukkit.getServer().createWorld(new WorldCreator(worldId));
				worldTeleporter = new LocationTeleportPower(MV.getSpawnLocation());}
			return worldTeleporter;}
		public Student getStudent(){
			return worldStudent;}
		public Area getWorld(){
			return Area.this;}}
	
	
	
	
	public class XZ{
		private float X;
		private float Z;
		public XZ(float x, float z){
			X=x;Z=z;}
		public float getX(){
			return X;}
		public float getZ(){
			return Z;}
		public XZ add(XZ XZ2){
			return new XZ(this.X+XZ2.X,this.Z+XZ2.Z);}
		public XZ minus(XZ XZ2){
			return new XZ(this.X-XZ2.X,this.Z-XZ2.Z);}
		public XZ expand(int factor){
			return new XZ(this.X*factor,this.Z*factor);}}
	public class Region{
		public XZ minXZ;
		public XZ maxXZ;
		public Region subRegion1=null;
		public Region subRegion2=null;
		public Region(XZ MinXZ, XZ MaxXZ){
			minXZ=MinXZ;
			maxXZ=MaxXZ;}
		public Region expand(int ExpandFactor){
			Region newRegion = new Region(minXZ,maxXZ.minus(minXZ).expand(ExpandFactor));
			newRegion.insert(maxXZ.minus(minXZ));
			return newRegion;}
		public Region insert(XZ size){
			if (subRegion1==null&&subRegion2==null){
				if (size.X>maxXZ.X-minXZ.X||size.Z>maxXZ.Z-minXZ.Z) return null;
				subRegion1 = new Region(new XZ(minXZ.X,minXZ.Z+size.Z),new XZ(maxXZ.X,maxXZ.Z));
				subRegion2 = new Region(new XZ(minXZ.X+size.X,minXZ.Z),new XZ(maxXZ.X,minXZ.Z+size.Z));
				return new Region(new XZ(minXZ.X,minXZ.Z),new XZ(minXZ.X+size.X,minXZ.Z+size.Z));}
			else {
				Region sub1Insert = subRegion1.insert(size);
				if (sub1Insert != null) return sub1Insert;
				else {
					Region sub2Insert = subRegion2.insert(size);
					return sub2Insert;}}}}
	public class Position{
		private float X;
		private float Z;
		private float Y;
		public Position(float x, float z, float y){
			X=x;Z=z;Y=y;}
		public float getX(){
			return X;}
		public float getZ(){
			return Z;}
		public float getY(){
			return Y;}}
	
	
	public static XZ xZ(float X, float Z){
		return new Area().new XZ(X,Z);}
	public static Region region(XZ MinXZ, XZ MaxXZ){
		return new Area().new Region(MinXZ,MaxXZ);}
	public static Position position(float X, float Z, float Y){
		return new Area().new Position(X,Z,Y);}


	private Area(){}
	
	private String name;
	private Region region;
	private Position spawnPoint;

	private HashMap<Student,HashSet<AreaCopy>> studentWorldsMap;

	
	
	
	public static Area createArea(String Name, Region MVRegion, String MVId, Position SpawnPoint){
		Area area = new Area();
		
		area.name=Name;
		
		Region worldRegion=worldsRegion.insert(MVRegion.maxXZ.minus(MVRegion.minXZ));
		while (worldRegion==null){
			worldsRegion=worldsRegion.expand(expandFactor);
			worldRegion=worldsRegion.insert(MVRegion.maxXZ.minus(MVRegion.minXZ));}
		
		org.bukkit.World MV = Bukkit.getServer().createWorld(new WorldCreator(MVId));
		org.bukkit.World universeMV = Bukkit.getServer().createWorld(new WorldCreator(universeId));
		
		area.copyRegion(MVRegion.minXZ,worldRegion.minXZ,
				MVRegion.maxXZ.minus(MVRegion.minXZ),
				MV,universeMV);
		
		area.region=worldRegion;
		area.spawnPoint=area.new Position(SpawnPoint.X+worldRegion.minXZ.minus(MVRegion.minXZ).X,
				SpawnPoint.Y,SpawnPoint.Z+worldRegion.minXZ.minus(MVRegion.minXZ).Z);
		
		areas.add(area);
		
		return area;}
	public static Area cloneArea(Area area, String Name){
		return createArea(Name, area.region, universeId, area.spawnPoint);}
	public static void deleteArea(Area area){
		org.bukkit.World universeMV = Bukkit.getServer().createWorld(new WorldCreator(universeId));
		
		area.deleteRegion(area.region, universeMV);
		
		area.name=null;
		area.region=null;
		area.spawnPoint=null;
		area.studentWorldsMap=null;
		
		areas.remove(area);}
	
	
	

	public final AreaCopy giveCopy(Student worldStudent){
		try {
			AreaCopy areaCopy=prepareCopy(worldStudent)[0];
			
			studentWorldsMap.get(worldStudent).add(areaCopy);
			return areaCopy;}
		catch (Exception e){
			throw new RuntimeException("Could not create world");}}
	public final AreaCopy[] giveCopy(Student... worldStudents){
		try {
			AreaCopy[] worldItems=prepareCopy(worldStudents);
			
			for (AreaCopy areaCopy : worldItems) studentWorldsMap.get(areaCopy.worldStudent).add(areaCopy);
			return worldItems;}
		catch (Exception e){
			throw new RuntimeException("Could not create areas");}}
	public final void takeCopy(AreaCopy... worldItems){
		for (AreaCopy areaCopy : worldItems){
			com.wimbli.WorldBorder.Config.removeBorder(areaCopy.worldId);
			multiverseCore.getMVWorldManager().deleteWorld(areaCopy.worldId, true, true);
			studentWorldsMap.get(areaCopy.getStudent()).remove(areaCopy);}}	
	
	
	
	
	private final AreaCopy[] prepareCopy(Student... worldStudents){
		AreaCopy[] worldItems = new AreaCopy[worldStudents.length]; 
		
		final org.bukkit.World universeMV = Bukkit.getServer().createWorld(new WorldCreator(universeId));
		final float widthX = (region.maxXZ.minus(region.minXZ)).X;
		final float lengthZ = (region.maxXZ.minus(region.minXZ)).Z;		
		final float distanceX = region.minXZ.X + widthX/2;
		final float distanceZ = region.minXZ.Z + lengthZ/2;

		String masterWorldId=null;
		
		for (int i=0; i<worldStudents.length; i++){
			AreaCopy areaCopy = new AreaCopy();
			worldItems[i] = areaCopy;
			
			String worldId = String.format("eduCraft_world_%1$s_%2$s_%3$s",name,worldStudents[i].getId().toString(),UUID.randomUUID().toString());
			
			areaCopy.worldId = worldId;
			areaCopy.worldStudent = worldStudents[i];
					
			if (i==0){
				if (! multiverseCore.getMVWorldManager().addWorld(worldId,Environment.NORMAL,"",WorldType.FLAT,false,"",false))
						throw new RuntimeException();				

				org.bukkit.World worldMV = Bukkit.getServer().createWorld(new WorldCreator(worldId));
				copyRegion(region.minXZ,
						new XZ(-widthX/2,-lengthZ/2),
						new XZ(widthX,lengthZ),
						universeMV,worldMV);
				worldMV.setSpawnLocation((int)(spawnPoint.X-distanceX),(int)spawnPoint.Y,(int)(spawnPoint.Z-distanceZ));
				
				masterWorldId=worldId;				

				com.wimbli.WorldBorder.Config.setBorderCorners(worldId, -widthX, -lengthZ, widthX, lengthZ, false);
				trimCopy(masterWorldId);}
			else {
				if (! multiverseCore.getMVWorldManager().cloneWorld(masterWorldId, worldId, "")){
						throw new RuntimeException();}
				
				com.wimbli.WorldBorder.Config.setBorderCorners(worldId, -widthX, -lengthZ, widthX, lengthZ, false);}}
		
		return worldItems;}
	private final void copyRegion(XZ fromXZ, XZ toXZ, XZ regionSize, org.bukkit.World fromMV, org.bukkit.World toMV){
		for(float X=fromXZ.X;X<=fromXZ.X+regionSize.X;X++){
			for (float Z=fromXZ.Z;Z<=fromXZ.Z+regionSize.Z;Z++){
				Block fromBlock = fromMV.getBlockAt(new Location(fromMV, X, 0, Z));
				Block toBlock = toMV.getBlockAt(new Location(toMV, X + toXZ.X - fromXZ.X, 0, Z + toXZ.Z - fromXZ.Z));
				
				String fromBiome = fromBlock.getBiome().name();
				toBlock.setBiome(Biome.valueOf(fromBiome));
				for(int Y = 0; Y < toMV.getMaxHeight() ; Y++){
					fromBlock = fromMV.getBlockAt(new Location(fromMV, X, Y, Z));
                    int fromType = fromBlock.getTypeId();
                    byte fromData = fromBlock.getData();
					
                    toBlock = toMV.getBlockAt(new Location(toMV, X + toXZ.X - fromXZ.X, Y, Z + toXZ.Z - fromXZ.Z));
                    toBlock.setTypeIdAndData(fromType, fromData, false);
                    toBlock.setData(fromData);}}}}
	private final void deleteRegion(Region region, org.bukkit.World MV){
		for(float X=region.minXZ.X;X<=region.maxXZ.X;X++){
			for (float Z=region.minXZ.Z;Z<=region.maxXZ.Z;Z++){
				for(int Y = 0; Y < MV.getMaxHeight() ; Y++){
					MV.getBlockAt(new Location(MV, X, Y, Z))
						.setType(Material.AIR, false);}}}}
	private final void trimCopy(String worldId){
		int trimFrequency = 5000;
		int trimPadding = CoordXZ.chunkToBlock(13);
		int ticks = 1, repeats = 1;
		if (trimFrequency > 20)
			repeats = trimFrequency / 20;
		else
			ticks = 20 / trimFrequency;

		WorldTrimTask trimTask = new WorldTrimTask(Bukkit.getServer(), null, worldId, trimPadding, repeats);
		if (trimTask.valid()){
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(WorldBorder.plugin, Config.trimTask, ticks, ticks);
			trimTask.setTaskID(task);}
		else
			throw new RuntimeException();}
	@Override
	public int compareTo(Area o) {
		return name.compareTo(o.name);}}
